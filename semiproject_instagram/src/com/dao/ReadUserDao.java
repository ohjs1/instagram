package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.db.ConnectionPool;
import com.vo.ReaduserVo;

public class ReadUserDao {
	public int insert(ReaduserVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ConnectionPool.getCon();
			String sql="insert into readuser values(readuser_seq.nextval,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getMember_no());
			pstmt.setInt(2, vo.getStory_no());
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
