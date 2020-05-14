package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.db.ConnectionPool;
import com.vo.BoardVo;
import com.vo.ImageVo;

public class BoardDao {
	//이미지vo 얻어오기
	public ArrayList<ImageVo> selectImg(int member_no){
		System.out.println(member_no+"이것");
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
	
	//게시글 추가
	public boolean insert(BoardVo vo, ArrayList<String> fileList, int member_no) {
		Connection con=null;
		PreparedStatement pstmt1=null; //게시글추가용
		PreparedStatement pstmt=null;
		boolean transactionChk=true;
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
			con.commit();
			return transactionChk;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
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
		}
	}
}
