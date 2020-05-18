package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.db.ConnectionPool;

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
		
}
