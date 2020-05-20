package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.ConnectionPool;

public class GoodDao {
	public int GoodInsertDelete(int member_no,int board_no) {
		Connection con=null;
		PreparedStatement pstmt1=null;
		PreparedStatement pstmt2=null;
		PreparedStatement pstmt3=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql1="select * from good where member_no=? and board_no=?";
			pstmt1=con.prepareStatement(sql1);
			pstmt1.setInt(1, member_no);
			pstmt1.setInt(2, board_no);
			rs=pstmt1.executeQuery();
			if(rs.next()) {
				
				String sql2="delete from good where member_no=? and board_no=?";
				pstmt2=con.prepareStatement(sql2);
				pstmt2.setInt(1, member_no);
				pstmt2.setInt(2, board_no);
				pstmt2.executeUpdate();
				return 0; //�����Ǿ��ٴ� ������ 0(����) ����
			}else {
				System.out.println(member_no+"���"+board_no+"�۹�ȣzzz");
				String sql3="insert into good values(good_seq.nextval,?,?)";
				pstmt3=con.prepareStatement(sql3);
				pstmt3.setInt(1, member_no);
				pstmt3.setInt(2, board_no);
				pstmt3.executeUpdate();
				return 1; //�߰��Ǿ��ٴ� ������ 1(��) ����
			}
		}catch(SQLException se) {
			System.out.println(se.getMessage());
			return -1; //�����޽����� -1����
		}finally {
			try {
				if(rs!=null) rs.close();
				if(pstmt3!=null) pstmt3.close();
				if(pstmt2!=null) pstmt2.close();
				if(pstmt1!=null) pstmt1.close();
				if(con!=null) con.close();
			}catch(SQLException se) {
				se.printStackTrace();
			}
		}
	}
}