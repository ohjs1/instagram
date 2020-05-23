package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.text.html.HTMLDocument.HTMLReader.PreAction;

import com.db.ConnectionPool;
import com.vo.BoardVo;
import com.vo.Board_MemberVo;
import com.vo.ImageVo;

public class BoardDao {
	//homefeed에서 내 게시물 및 내가 팔로우한 회원들의 member테이블에 회원아이디,닉네임,프로필사진과 board테이블 내용 전부 가져오기(join)
	public ArrayList<Board_MemberVo> selectHomeFeedMemberBoard(int mymember_no){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select m.id,m.pwd,m.name,m.nickname,m.profile,bb.board_no,bb.member_no,bb.content,bb.ref,bb.lev,bb.step,to_char(bb.regdate,'YYYYMMDDHH24MISS') regdate " + 
					"from board bb, member m " + 
					"where m.member_no=bb.member_no and bb.lev=? and (bb.regdate,bb.board_no) in (select distinct b.regdate, b.board_no " + 
					"                                                                from follow f,board b " + 
					"                                                                where f.youmember_no=b.member_no and f.mymember_no=? or b.member_no=? and b.lev=? " + 
					") \r\n" + 
					"order by bb.regdate desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, 0);
			pstmt.setInt(2, mymember_no);
			pstmt.setInt(3, mymember_no);
			pstmt.setInt(4, 0);
			rs=pstmt.executeQuery();
			 ArrayList<Board_MemberVo> list=new ArrayList<Board_MemberVo>();
			if(rs.next()) {
				do {
					String id=rs.getString("id");
					String pwd=rs.getString("pwd");
					String name=rs.getString("name");
					String nickname=rs.getString("nickname");
					String profile=rs.getString("profile");
					int board_no=rs.getInt("board_no");
					int member_no=rs.getInt("member_no");
					String content=rs.getString("content");
					content = content.replace("\r\n", "<br>");
					int ref=rs.getInt("ref");
					int lev=rs.getInt("lev");
					int step=rs.getInt("step");
					String sregdate=rs.getString("regdate");
					String y=sregdate.substring(0, 4);
					String m=sregdate.substring(4,6);
					String d=sregdate.substring(6, 8);
					String h=sregdate.substring(8,10);
					String mm=sregdate.substring(10,12);
					String s=sregdate.substring(12,14);
					String date=y+"-"+m+"-"+d+" "+h+":"+mm+":"+s;
					SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					java.util.Date ddd=sd.parse(date);
					
					Date regdate=new Date(ddd.getTime());
					Board_MemberVo vo=new Board_MemberVo(id,pwd,name,nickname,profile,board_no,member_no,content,ref,lev,step,regdate);
					list.add(vo);
				}while(rs.next());
			}else {
				return null;
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	//myfeed에서 선택한 게시물의 member테이블에 회원아이디,닉네임,프로필사진과 board테이블 내용 전부 가져오기(join)
	public Board_MemberVo selectMemberBoard(int board_no){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select m.id,m.pwd,m.name,m.nickname,m.profile,b.board_no,b.member_no,b.content,b.ref,b.lev,b.step,to_char(b.regdate,'YYYYMMDDHH24MISS') regdate " + 
					"from board b, member m " + 
					"where m.member_no=b.member_no and b.board_no=? " + 
					"order by b.regdate desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String nickname=rs.getString("nickname");
				String profile=rs.getString("profile");
				int member_no=rs.getInt("member_no");
				String content=rs.getString("content");
				int ref=rs.getInt("ref");
				int lev=rs.getInt("lev");
				int step=rs.getInt("step");
				String sregdate=rs.getString("regdate");
				String y=sregdate.substring(0, 4);
				String m=sregdate.substring(4,6);
				String d=sregdate.substring(6, 8);
				String h=sregdate.substring(8,10);
				String mm=sregdate.substring(10,12);
				String s=sregdate.substring(12,14);
				String date=y+"-"+m+"-"+d+" "+h+":"+mm+":"+s;
				SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date ddd=sd.parse(date);
				
				Date regdate=new Date(ddd.getTime());
				Board_MemberVo vo=new Board_MemberVo(id,pwd,name,nickname,profile,board_no,member_no,content,ref,lev,step,regdate);
				return vo;
			}else {
				return null;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	//로그인된 회원의 게시글 업어오기
	public ArrayList<BoardVo> selectBoard(int member_no){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<BoardVo> list=new ArrayList<BoardVo>();
		try {
			con=ConnectionPool.getCon();
			String sql="select * from board where member_no=? order by regdate desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					int board_no=rs.getInt("board_no");
					String content=rs.getString("content");
					int ref=rs.getInt("ref");
					int lev=rs.getInt("lev");
					int step=rs.getInt("step");
					Date regdate=rs.getDate("regdate");
					BoardVo vo=new BoardVo(board_no,member_no,content,ref,lev,step,regdate);
					list.add(vo);
				}while(rs.next());
				return list;
			}else {
				return null;
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	//이미지vo 업어오기
	public ArrayList<ImageVo> selectImg(int member_no){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ImageVo> list=new ArrayList<ImageVo>();
		try {
			con=ConnectionPool.getCon();
			String sql="select i.* " + 
					"from (" + 
					"    select * " + 
					"    from board " + 
					"    where member_no=?" + 
					")b,image i " + 
					"where b.board_no=i.board_no";
			
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					int image_no=rs.getInt("image_no");
					int board_no=rs.getInt("board_no");
					String imagepath=rs.getString("imagepath");
					list.add(new ImageVo(image_no,board_no,imagepath));
				}while(rs.next()); 
			}else {
				return null;
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	//해당 게시물의 댓글 리스트
	public ArrayList<Board_MemberVo> selectComment(int board_no1){
		String sql="select m.id,m.pwd,m.name,m.nickname,m.profile,b.board_no,b.member_no,b.content,b.ref,b.lev,b.step,to_char(b.regdate,'YYYYMMDDHH24MISS') regdate " + 
				"					from board b, member m " + 
				"					where m.member_no=b.member_no and b.board_no!=? and ref=? " + 
				"					order by b.regdate asc,step asc";
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,board_no1);
			pstmt.setInt(2, board_no1);
			rs=pstmt.executeQuery();
			ArrayList<Board_MemberVo> list=new ArrayList<Board_MemberVo>();
			while(rs.next()) {
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String nickname=rs.getString("nickname");
				String profile=rs.getString("profile");
				int board_no=rs.getInt("board_no");
				int member_no=rs.getInt("member_no");
				String content=rs.getString("content");
				int ref=rs.getInt("ref");
				int lev=rs.getInt("lev");
				int step=rs.getInt("step");
				String sregdate=rs.getString("regdate");
				String y=sregdate.substring(0, 4);
				String m=sregdate.substring(4,6);
				String d=sregdate.substring(6, 8);
				String h=sregdate.substring(8,10);
				String mm=sregdate.substring(10,12);
				String s=sregdate.substring(12,14);
				String date=y+"-"+m+"-"+d+" "+h+":"+mm+":"+s;
				SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				java.util.Date ddd=sd.parse(date);
				
				Date regdate=new Date(ddd.getTime());
				
				Board_MemberVo vo=new Board_MemberVo(id,pwd,name,nickname,profile,board_no,member_no,content,ref,lev,step,regdate);
				list.add(vo);
			}
			return list;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return null;
		}catch(Exception e) {
			System.out.println(e.getMessage());
			return null;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	//댓글 추가
	public int insertComment(BoardVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			int board_no=vo.getBoard_no();
			int member_no=vo.getMember_no();
			String content=vo.getContent();
			int ref=board_no;
			int lev=vo.getLev();
			int step=vo.getStep();
			lev +=1;
			step +=1;
			
			String sql="insert into board values(board_seq.nextval,?,?,?,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1,member_no);
			pstmt.setString(2,content);
			pstmt.setInt(3, ref);
			pstmt.setInt(4, lev);
			pstmt.setInt(5, step);
			pstmt.executeUpdate();
			return 1;
			
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			ConnectionPool.close(pstmt);
			ConnectionPool.close(con);
		}
	}
	
	//게시글 추가
	public boolean insert(BoardVo vo, ArrayList<String> fileList, int member_no) {
		Connection con=null;
		PreparedStatement pstmt1=null; //게시글추가용
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
			String sql1="insert into board values(board_seq.nextval,?,?,?,?,?,sysdate)";
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setInt(1, member_no);
			pstmt1.setString(2, vo.getContent());
			pstmt1.setInt(3, vo.getRef());
			pstmt1.setInt(4, vo.getLev());
			pstmt1.setInt(5, vo.getStep());
			int n=pstmt1.executeUpdate();
			if(n<=0) {
				transactionChk=false;
			}
			for(int i=0;i<fileList.size(); i++) {
				String sql="insert into image values(image_seq.nextval,board_seq.currval,?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setString(1, fileList.get(i));
				int m=pstmt.executeUpdate();
				if(m<=0) {
					transactionChk=false;
				}
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
			pstmtLink=con.prepareStatement("select board_seq.currval from dual");
			rs=pstmtLink.executeQuery();
			int board_no=0;
			if(rs.next()) {
				board_no=rs.getInt(1);
			}
			//태그번호 꺼내기
			for(String tag:list) {
				String sql4="select tag_no from tag where search=?";
				pstmtTag=con.prepareStatement(sql4);
				pstmtTag.setString(1, tag);
				rs=pstmtTag.executeQuery();
				if(rs.next()) {
					int tag_no=rs.getInt(1);
					String sql5="insert into link values(link_seq.nextval,?,null,?)";
					pstmtLink2=con.prepareStatement(sql5);
					pstmtLink2.setInt(1, board_no);
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
			for(int i=0; i<fileList.size(); i++) {
				try {
					if(pstmt!=null) pstmt.close();
					if(pstmt1!=null) pstmt1.close();
					if(con!=null) con.close();
				}catch (SQLException se) {
					se.printStackTrace();
				}
			}
			ConnectionPool.close(pstmtLink2);
			ConnectionPool.close(pstmtLink);
			ConnectionPool.close(pstmtTag2);
			ConnectionPool.close(con, pstmtTag, rs);
		}
	}
	
	//게시글 또는 댓글 삭제(댓글도 삭제해야되므로 트랜잭션처리,이미지테이블,링크테이블은 CASCADE로 설정되어 자동삭제)
	public int boardDelete(int board_no) {
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		try {
			con=ConnectionPool.getCon();
			con.setAutoCommit(false);
			String sql1="delete from board where board_no=?";
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setInt(1, board_no);
			pstmt1.executeUpdate();
			
			String sql2="delete from board where ref=?";
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setInt(1, board_no);
			pstmt2.executeUpdate();
			con.commit();
			return 1;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			ConnectionPool.close(con, pstmt1, null);
		}
	}
}





















