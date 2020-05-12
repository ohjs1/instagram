package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.db.ConnectionPool;
import com.vo.MemberVo;

public class MemberDao {
	private static MemberDao instance=new MemberDao();
	private MemberDao() {}
	public static MemberDao getInstance() {
		return instance;
	}
	//회원가입
	public int insert(MemberVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="insert into member values(member_seq.nextval,?,?,?,?,sysdate,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPwd());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getNickname());
			pstmt.setString(5, vo.getProfile());
			return pstmt.executeUpdate();
		}catch (SQLException se) {
			se.getStackTrace();
			return -1;
		}finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}
	//로그인
	public int isMember(String id, String pwd) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select member_no from member where id=? and pwd=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pwd);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int member_no =rs.getInt("member_no");
				return member_no;
			}
			return -1;
		}catch (SQLException se) {
			se.getStackTrace();
			return -1;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	public int pwdupdate(String id, String newPwd) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="update member set pwd=? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, newPwd);
			pstmt.setString(2, id);
			return pstmt.executeUpdate();
		}catch (SQLException se) {
			se.getStackTrace();
			return -1;
		}finally {
			ConnectionPool.close(con, pstmt, null);
		}
	
	}
}













