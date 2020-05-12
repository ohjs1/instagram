package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.db.ConnectionPool;
import com.vo.BoardVo;

public class BoardDao {
	//게시글 추가
	public int insert(BoardVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			con.setAutoCommit(false);
			return 0;
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1;
		}finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}
}
