package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.db.ConnectionPool;
import com.vo.ImageVo;

public class ImageDao {
	//대표이미지ArrayList 얻어오기
	public ArrayList<ImageVo> selectMainImg(int member_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ImageVo> list=new ArrayList<ImageVo>();
		try {
			con=ConnectionPool.getCon();
			String sql="select i3.*,b.member_no from" + 
					"(" + 
					"    select i.board_no,i.image_no,i2.imagepath from " + 
					"    (" + 
					"        select board_no,min(image_no) image_no " + 
					"        from image " + 
					"        group by board_no " + 
					"        order by board_no " + 
					"    )i, image i2 " + 
					"    where i.image_no=i2.image_no " + 
					"    order by board_no desc " + 
					")i3, board b " + 
					"where i3.board_no=b.board_no and member_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					int board_no=rs.getInt("board_no");
					int image_no=rs.getInt("image_no");
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
	//게시글별 이미지 얻어오기
	public ArrayList<ImageVo> selectBoardImg(int board_no){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ImageVo> list=new ArrayList<ImageVo>();
		try {
			con=ConnectionPool.getCon();
			String sql="select * from image where board_no=? order by image_no";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, board_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					int image_no=rs.getInt("image_no");
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
}
