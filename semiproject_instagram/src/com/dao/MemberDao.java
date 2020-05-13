package com.dao;

import java.sql.Connection;
import java.sql.Date;
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
	//로그인(아이디,로그인) 일치하면 로그인 성공
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
	//비밀번호 변경
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
	//회원정보
	public MemberVo memberInfo(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select * from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int member_no=rs.getInt("member_no");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String nickname=rs.getString("nickname");
				Date regdate=rs.getDate("regdate");
				String profile=rs.getString("profile");
				System.out.println(profile);
				MemberVo vo=new MemberVo(member_no, id, pwd, name, nickname, regdate, profile);
				return vo;
			}
			return null;
		}catch (SQLException se) {
			se.getStackTrace();
			return null;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	//프로필사진 변경
	public int memberProfileUpdate(String id, String profile) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="update member set profile=? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, profile);
			pstmt.setString(2, id);
			return pstmt.executeUpdate();
		}catch (SQLException se) {
			se.getStackTrace();
			return -1;
		}finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}
	//회원정보 변경
	public int memberUpdate(String id, String name, String nickname) {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			con=ConnectionPool.getCon();
			String sql="update member set name=?,nickname=? where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, name);
			pstmt.setString(2, nickname);
			pstmt.setString(3, id);
			return pstmt.executeUpdate();
		}catch (SQLException se) {
			se.getStackTrace();
			return -1;
		}finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}
	//비밀번호 찾기
	public String memberFindpwd(String id, String name) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con=ConnectionPool.getCon();
			String sql="select pwd from member where id=? and name=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, name);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				String pwd =rs.getString("pwd");
				return pwd;
			}
			return null;
		}catch (SQLException se) {
			se.getStackTrace();
			return null;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
}













