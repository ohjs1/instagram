package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.db.ConnectionPool;
import com.vo.StoryVo;

public class StoryDao {
	
	public int delete(int story_no) {
		Connection con=null;
		PreparedStatement pstmt=null;
		StoryVo vo=new StoryVo();
		try {
			con=ConnectionPool.getCon();
			String sql="delete from story where=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getStory_no());
			int n = pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}
	public ArrayList<StoryVo> mem_list(int member_no){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<StoryVo> list=new ArrayList<StoryVo>();
		try {
			con=ConnectionPool.getCon();
			String sql="select * from story where member_no=? order by story_no asc";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int story_no=rs.getInt("story_no");
				String content=rs.getString("content");
				String filepath=rs.getString("filepath");
				Date storydate=rs.getDate("storydate");
				StoryVo vo=new StoryVo(story_no, member_no, content, filepath, storydate);
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
	public int getMemberNo(String id) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		int member_no=0;
		try {
			con=ConnectionPool.getCon();
			String sql="select member_no from member where id=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				member_no=rs.getInt("member_no");
			}
			
			return member_no;
		}catch(SQLException se) {
			se.printStackTrace();
			return -1;
		}finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	public int insert(StoryVo vo) {
		Connection con=null;
		PreparedStatement pstmt=null;
		
		try {
			con=ConnectionPool.getCon();
			String sql="insert into story values(story_seq.nextval,?,?,?,sysdate)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getMember_no());
			pstmt.setString(2, vo.getContent());
			pstmt.setString(3, vo.getFilepath());
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
