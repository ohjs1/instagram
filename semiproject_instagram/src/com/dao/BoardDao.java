package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.db.ConnectionPool;
import com.vo.BoardVo;
import com.vo.Board_MemberVo;
import com.vo.ImageVo;

public class BoardDao {
	//선택한 게시물의 member테이블에 회원아이디,닉네임,프로필사진과 board테이블 내용 전부 가져오기(join)
	public ArrayList<Board_MemberVo> selectMemberBoard(int board_no){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<Board_MemberVo> list=new ArrayList<Board_MemberVo>();
		try {
			con=ConnectionPool.getCon();
			String sql="select m.id,m.pwd,m.name,m.nickname,m.profile,b.* " + 
					"from board b, member m " + 
					"where m.member_no=b.member_no and b.board_no=? " + 
					"order by b.regdate desc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
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
					Date regdate=rs.getDate("regdate");
					Board_MemberVo vo=new Board_MemberVo(id,pwd,name,nickname,profile,board_no,member_no,content,ref,lev,step,regdate);
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
	//로그인된 회원의 게시글 얻어오기
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
	//이미지vo 얻어오기
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
}
