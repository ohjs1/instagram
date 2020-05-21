package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.db.ConnectionPool;
import com.vo.FollowVo;
import com.vo.MemberVo;

public class FollowDao {
	
	//ÆÈ·Î¿ì
	public int insert(int mymember_no, int youmember_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getCon();
			String sql = "insert into follow values(follow_seq.nextval,?,?,sysdate)";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mymember_no);
			pstmt.setInt(2, youmember_no);
			
			return pstmt.executeUpdate();
			
			}catch(SQLException se){
				se.printStackTrace();
				return -1;
			}finally {
				ConnectionPool.close(con, pstmt, null);
		}
	}
	// ÆÈ·Î¿ö ÆÈ·ÎÀ× Ã£±â
	public ArrayList<MemberVo> followingMem(int mymember_no, int youmember_no, boolean bl){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionPool.getCon();
			ArrayList<MemberVo> listMem=new ArrayList<MemberVo>();
			String sql = "select * from member where member_no in (select ";
			if(bl) {
				sql+= " youmember_no from follow where mymember_no=?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, mymember_no);
			}else{
				sql+= " mymember_no from follow where youmember_no=?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, youmember_no);
			}
			rs=pstmt.executeQuery();
			while(rs.next()) {
				MemberVo vo=new MemberVo(
						rs.getInt("member_no"),
						rs.getString("id"),
						rs.getString("pwd"),
						rs.getString("name"),
						rs.getString("nickname"),
						rs.getDate("regdate"),
						rs.getString("profile"));
				listMem.add(vo);
			}
			return listMem;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	//ÆÈ·Î¿ì ÇØÁ¦
	public int delete(int mymember_no,int youmember_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionPool.getCon();
			String sql = "delete from follow where mymember_no=? and youmember_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mymember_no);
			pstmt.setInt(2, youmember_no);
			int n = pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}
	//ÆÈ·Î¿ì Áßº¹ Ã£±â
	public boolean followfind(int mymember_no,int youmember_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = ConnectionPool.getCon();
			String sql = "select * from follow where mymember_no=? and youmember_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mymember_no);
			pstmt.setInt(2, youmember_no);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				return true;
			}
			return false;
		}catch(SQLException se) {
			se.printStackTrace();
			return false;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	//ÆÈ·Î¿ö Ä«¿îÆ®
/*	public boolean followcount(int mymember_no, int youmember_no, boolean bl) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		bl = true;
		try {
			String sql = "select count(*) from member where member_no in (select ";
			if(bl) {
				sql+= " youmember_no from follow where mymember_no=?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, mymember_no);
			}else{
				sql+= " mymember_no from follow where youmember_no=?)";
				pstmt=con.prepareStatement(sql);
				pstmt.setInt(1, youmember_no);
			}
			rs=pstmt.executeQuery();
		}catch(SQLException se) {
			se.printStackTrace();
		}
	}*/
}
