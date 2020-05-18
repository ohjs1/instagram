package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.db.ConnectionPool;
import com.vo.ImageVo;

public class TagDao {

	public ArrayList<String> tagSearch(String search) {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		try {
			con =ConnectionPool.getCon();
			String sql ="select search from tag where search like ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, search + "%");
			rs=pstmt.executeQuery();
			
			ArrayList<String> list=new ArrayList<String>();
			
			while(rs.next()) {
				String keyword=rs.getString("search");
				list.add(keyword);
			}
			return list;
		} catch(SQLException se) {
			se.getStackTrace();
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}

	public ArrayList<ImageVo> list(String keyword) {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		try {
			con=ConnectionPool.getCon();
			String sql ="select i.board_no,i.image_no,i2.imagepath from\r\n" + 
					"(\r\n" + 
					"    select board_no,min(image_no) image_no from image where board_no in\r\n" + 
					"            (select board_no from link,tag \r\n" + 
					"            where link.tag_no=tag.tag_no\r\n" + 
					"            and link.tag_no=(select tag_no from tag where search=?)\r\n" + 
					"            ) group by board_no\r\n" + 
					")i, image i2\r\n" + 
					"where i.image_no=i2.image_no";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "#"+keyword);
			rs=pstmt.executeQuery();
			
			ArrayList<ImageVo> list=new ArrayList<ImageVo>();

			while(rs.next()) {
				int image_no=rs.getInt("image_no");
				int board_no=rs.getInt("board_no");
				String imagepath=rs.getString("imagepath");
				
				list.add(new ImageVo(image_no, board_no, imagepath));
			}
			return list;
		} catch(SQLException se) {
			se.getStackTrace();
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	
	}
		
}