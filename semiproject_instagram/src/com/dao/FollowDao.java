package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.db.ConnectionPool;

public class FollowDao {
	public int insert(int following_no, int mymember_no, int youmember_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getCon();
			String sql = "insert into follow values(?,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, following_no);
			pstmt.setInt(2, mymember_no);
			pstmt.setInt(3, youmember_no);
			
			return pstmt.executeUpdate();
			
			}catch(SQLException se){
				se.printStackTrace();
				return -1;
			}finally {
				ConnectionPool.close(con, pstmt, null);
		}
	}
}
