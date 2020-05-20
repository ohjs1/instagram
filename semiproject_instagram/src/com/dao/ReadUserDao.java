package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import com.db.ConnectionPool;
import com.vo.ReaduserVo;
import com.vo.StoryMemberVo;

public class ReadUserDao {
	
	public ArrayList<ReaduserVo> readuser_list(int story_no){
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		ArrayList<ReaduserVo> list=new ArrayList<ReaduserVo>();
		try {
			con=ConnectionPool.getCon();
			String sql="select readuser_no, member_no from readuser where story_no=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, story_no);
			rs=pstmt.executeQuery();
			while(rs.next()) {
				int readuser_no=rs.getInt("readuser_no");
				int member_no=rs.getInt("member_no");
				ReaduserVo vo=new ReaduserVo(readuser_no, member_no,0);
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
		
		try {
			con=ConnectionPool.getCon();
			String sql="insert into readuser values(readuser_seq.nextval,?,?)";
			pstmt=con.prepareStatement(sql);
			pstmt.setInt(1, vo.getMember_no());
			pstmt.setInt(2, vo.getStory_no());
			int n = pstmt.executeUpdate();
			return n;
		}catch(SQLException se) {
			
			return -1;
		}finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}

}
