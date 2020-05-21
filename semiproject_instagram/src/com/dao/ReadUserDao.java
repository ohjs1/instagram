package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.db.ConnectionPool;
import com.vo.ReaduserMemberVo;
import com.vo.ReaduserVo;
import com.vo.StoryMemberVo;

public class ReadUserDao {
	public int listCount(int story_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int count_ru=0;

		try {
			con=ConnectionPool.getCon();
			String sql="select count(member_no) count_ru from readuser where story_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, story_no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				count_ru=rs.getInt("count_ru");
			}
			
			return count_ru;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	public ArrayList<ReaduserMemberVo> readuser_list(int story_no){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ReaduserMemberVo> list=new ArrayList<ReaduserMemberVo>();
		try {
			con=ConnectionPool.getCon();
			String sql="select r.*, nickname, profile from\n" + 
					"(select * from readuser where story_no=?)r, member m\n" + 
					"where r.member_no=m.member_no";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, story_no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int readuser_no=rs.getInt("readuser_no");
				int member_no=rs.getInt("member_no");
				String nickname=rs.getString("nickname");
				String profile=rs.getString("profile");
				ReaduserMemberVo vo=new ReaduserMemberVo(readuser_no, member_no, story_no, nickname, profile);
				list.add(vo);
			}
			
			return list;
		}catch(SQLException se) {
			se.printStackTrace();
			return null;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	
	public int insert(ReaduserVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		int n=0;
		try {
			con=ConnectionPool.getCon();
			
			
			String sql="select member_no, story_no from readuser where member_no=? and story_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getMember_no());
			pstmt.setInt(2, vo.getStory_no());
			rs=pstmt.executeQuery();
			if(rs.next()) {
				System.out.println("이미 저장된 정보입니다(readuser)");
				
			}else {
				String sql2="insert into readuser values(readuser_seq.nextval,?,?)";
				pstmt2=con.prepareStatement(sql2);
				pstmt2.setInt(1, vo.getMember_no());
				pstmt2.setInt(2, vo.getStory_no());
				n = pstmt2.executeUpdate();
				
			}
			
			return n;
			
			
			
		}catch(SQLException se) {
			
			return -1;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}

}
