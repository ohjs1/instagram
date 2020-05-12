package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.db.ConnectionPool;
import com.vo.MemberVo;

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
	
	public ArrayList<MemberVo> getUserList(String keyword){
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con =ConnectionPool.getCon();
			String sql ="select * from member where id like ? or name like ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			rs =pstmt.executeQuery();
			
			ArrayList<MemberVo> list =new ArrayList<MemberVo>();
			
			while(rs.next()) {
				//int member_no, String id, String pwd, String name, String nickname, Date regdate, String profile
				int member_no =rs.getInt("member_no");
				String id =rs.getString("id");
				String name =rs.getString("name");
				String nickname =rs.getString("nickname");
				String profile =rs.getString("profile");
				
				list.add(new MemberVo(member_no, id, null, name, nickname, null, profile));
			}
			return list;
			
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
}
