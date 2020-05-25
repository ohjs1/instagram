package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.db.ConnectionPool;
import com.vo.MemberVo;
import com.vo.StoryMemberVo;
import com.vo.StoryVo;

public class StoryDao {
	
	public StoryVo story_date(int story_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select to_char(storydate,'YYYYMMDDHH24MISS') storydate from story where story_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, story_no);
			rs=pstmt.executeQuery();
			StoryVo vo=new StoryVo();
			if(rs.next()) {
				String storydate1=rs.getString("storydate");
				String y=storydate1.substring(0, 4);
				String m=storydate1.substring(4,6);
				String d=storydate1.substring(6, 8);
				String h=storydate1.substring(8,10);
				String mm=storydate1.substring(10,12);
				String s=storydate1.substring(12,14);
				String date=y+"-"+m+"-"+d+" "+h+":"+mm+":"+s;
				SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date ddd = sd.parse(date);		
				Date storydate=new Date(ddd.getTime());
				vo.setStorydate(storydate);
				
			}
			return vo;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		}
		finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	//스토리 올린 회원들 멤버번호 중복없이 가져오기(storydate 내림차순)-> 로그인한 멤버가 팔로우한 멤버 중 스토리 올린 멤버만 가져오기ㅠ
	public ArrayList<StoryMemberVo> storymembers(int login_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<StoryMemberVo> list=new ArrayList<StoryMemberVo>();		
		try {
			con=ConnectionPool.getCon();
			String sql="select s.* from\r\n" + 
					"(select * from\r\n" + 
					"        (\r\n" + 
					"            select m.member_no, maxdate, profile, nickname from\r\n" + 
					"            (\r\n" + 
					"                select member_no,max(storydate) maxdate\r\n" + 
					"                from story \r\n" + 
					"                group by member_no \r\n" + 
					"                order by maxdate desc\r\n" + 
					"            )s , member m \r\n" + 
					"            where s.member_no=m.member_no\r\n" + 
					"        )a\r\n" + 
					"        where member_no not in(?)\r\n" + 
					"        ) s, follow f\r\n" + 
					"where f.mymember_no=? and f.youmember_no=s.member_no";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, login_no);
			pstmt.setInt(2, login_no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				StoryMemberVo vo=new StoryMemberVo();
				int member_no=rs.getInt("member_no");
				Date maxdate=rs.getDate("maxdate");
				String profile=rs.getString("profile");
				String nickname=rs.getString("nickname");
				vo.setMember_no(member_no);
				vo.setStorydate(maxdate);
				vo.setProfile(profile);
				vo.setNickname(nickname);

				
				list.add(vo);
			}
			
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	// 해당 스토리번호 삭제
	public int delete(int story_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		;
		try {
			con=ConnectionPool.getCon();
			String sql="delete from story where story_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, story_no);
			int n = pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}

	//해당 멤버의 모든 스토리 정보 가져오기
	public ArrayList<StoryMemberVo> mem_list(int member_no){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<StoryMemberVo> list=new ArrayList<StoryMemberVo>();
		try {
			con=ConnectionPool.getCon();
			String sql="select s.*, m.nickname from story s, member m\r\n" + 
					"where s.member_no=? and m.member_no=s.member_no\r\n" + 
					"order by story_no asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int story_no=rs.getInt("story_no");
				String content=rs.getString("content");
				String filepath=rs.getString("filepath");
				Date storydate=rs.getDate("storydate");
				String nickname=rs.getString("nickname");
				StoryMemberVo vo=new StoryMemberVo(story_no, member_no, content, filepath, storydate, nickname);
				list.add(vo);
			}
			
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	//세션아이디의 멤버번호 가져오기..
	public int getMemberNo(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int member_no=0;
		try {
			con=ConnectionPool.getCon();
			String sql="select member_no from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				member_no=rs.getInt("member_no");
			}
			
			return member_no;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}

	//스토리 업로드
	public boolean insert(StoryVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		boolean transactionChk=true;
		PreparedStatement pstmtTag=null; //태그추가용
		PreparedStatement pstmtTag2=null; //태그중복검사
		PreparedStatement pstmtLink=null; //링크추가용
		PreparedStatement pstmtLink2=null; //링크추가용
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			con.setAutoCommit(false);
			String sql="insert into story values(story_seq.nextval,?,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getMember_no());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getFilepath());
			int n = pstmt.executeUpdate();
			if(n<=0) {
				transactionChk=false;
			}
			//태그추가↓
			String content=vo.getContent();
			String[] str=content.split("\\s");
			ArrayList<String> list=new ArrayList<String>();
			for(String s:str) {
				if(s.contains("#")) {
					list.add(s);
				}
			}
			
			for(String tag:list) {
				//태그중복검사
				pstmtTag2=con.prepareStatement("select search from tag where search=?");
				pstmtTag2.setString(1, tag);
				rs=pstmtTag2.executeQuery();
				if(rs.next()) {
					String search=rs.getString("search");
				}else {
					//중복된 태그가 없으면 추가
					String sql3="insert into tag values(tag_seq.nextval,?)";
					pstmtTag=con.prepareStatement(sql3);
					pstmtTag.setString(1, tag);
					pstmtTag.executeUpdate();
				}
			}
			//링크추가↓
			//글번호 꺼내기
			pstmtLink=con.prepareStatement("select story_seq.currval from dual");
			rs=pstmtLink.executeQuery();
			int story_no=0;
			if(rs.next()) {
				story_no=rs.getInt(1);
			}
			//태그번호 꺼내기
			for(String tag:list) {
				String sql4="select tag_no from tag where search=?";
				pstmtTag=con.prepareStatement(sql4);
				pstmtTag.setString(1, tag);
				rs=pstmtTag.executeQuery();
				if(rs.next()) {
					int tag_no=rs.getInt(1);
					String sql5="insert into link values(link_seq.nextval,null,?,?)";
					pstmtLink2=con.prepareStatement(sql5);
					pstmtLink2.setInt(1, story_no);
					pstmtLink2.setInt(2, tag_no);
					pstmtLink2.executeUpdate();
				}
			}
			
			
			con.commit();
			return transactionChk;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			try {
				con.rollback();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}finally {
			
				try {
					if(pstmt!=null) pstmt.close();
					if(con!=null) con.close();
				}catch (SQLException se) {
					se.printStackTrace();
				}
			
			ConnectionPool.close(pstmtLink2);
			ConnectionPool.close(pstmtLink);
			ConnectionPool.close(pstmtTag2);
			ConnectionPool.close(con, pstmtTag, rs);
		}
	}
			
	
}
