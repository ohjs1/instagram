package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.db.ConnectionPool;
import com.vo.MemberVo;
import com.vo.StoryMemberVo;
import com.vo.StoryVo;

public class StoryDao {
	
	//스토리 올린 회원들 멤버번호 중복없이 가져오기(storydate 내림차순)
	public ArrayList<StoryMemberVo> storymembers() {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<StoryMemberVo> list=new ArrayList<StoryMemberVo>();		
		try {
			con=ConnectionPool.getCon();
			String sql="select m.member_no, maxdate, profile, nickname\r\n" + 
					"from (select member_no,max(storydate) maxdate \r\n" + 
					"from story \r\n" + 
					"group by member_no \r\n" + 
					"order by maxdate desc)s, member m\r\n" + 
					"where s.member_no=m.member_no";
			pstmt=con.prepareStatement(sql);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				StoryMemberVo vo=new StoryMemberVo();
				int member_no=rs.getInt("member_no");
				String profile=rs.getString("profile");
				String nickname=rs.getString("nickname");
				vo.setMember_no(member_no);
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
	public int insert(StoryVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ConnectionPool.getCon();
			String sql="insert into story values(story_seq.nextval,?,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getMember_no());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getFilepath());
			int n = pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}
}
