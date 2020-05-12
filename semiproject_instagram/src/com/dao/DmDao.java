package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.db.ConnectionPool;

public class DmDao {
	public DmDao() {}
	
	public int insert(int mymember_no, int yourmember_no, String msg, boolean chk) {
		Connection con =null;
		PreparedStatement pstmtChatroom =null;
		PreparedStatement pstmtChatcontent =null;
		
		try {
			con =ConnectionPool.getCon();
			
			con.setAutoCommit(false);
			
			String sql1 ="insert into chatroom values(chatroom_seq.nextval, ?, ?, sysdate)";
			pstmtChatroom =con.prepareStatement(sql1);
			pstmtChatroom.setInt(1, mymember_no);
			pstmtChatroom.setInt(2, yourmember_no);
			
			String sql2 ="insert into chatcontent values(chatcontent_seq.nextval,"
					+ " chatroom_seq.currval, ?, ?, ?, ?, sysdate)";
			pstmtChatcontent =con.prepareStatement(sql2);
			
			pstmtChatcontent.setInt(1, mymember_no);
			pstmtChatcontent.setInt(2, yourmember_no);
			pstmtChatcontent.setString(3, msg);
			pstmtChatcontent.setBoolean(4, chk);
			
			pstmtChatroom.executeUpdate();
			pstmtChatcontent.executeUpdate();
			
			con.commit();
			return 1;
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return -1;
		} finally {
			ConnectionPool.close(con, pstmtChatroom, null);
		}
	}
}
