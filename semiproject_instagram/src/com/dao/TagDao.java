package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.db.ConnectionPool;
import com.vo.ImageVo;
import com.vo.MemberVo;
import com.vo.StoryVo;

public class TagDao {
	//해쉬태그(#) 제시어
	public ArrayList<String> tagSearch(String search) {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		try {
			con =ConnectionPool.getCon();
			String sql ="select search from tag where search like ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "#"+search + "%");
			rs=pstmt.executeQuery();
			
			ArrayList<String> list=new ArrayList<String>();
			
			while(rs.next()) {
				String keyword=rs.getString("search");
				list.add(keyword);
			}
			return list;
		} catch(SQLException se) {
			se.getStackTrace();
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	//nickname(@) 제시어
	public ArrayList<MemberVo> memberSearch(String search) {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		try {
			con =ConnectionPool.getCon();
			String sql ="select * from member where nickname like ? or name like ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, search + "%");
			pstmt.setString(2, search + "%");
			rs=pstmt.executeQuery();
			
			ArrayList<MemberVo> list=new ArrayList<MemberVo>();
			
			while(rs.next()) {
				int member_no=rs.getInt("member_no");
				String id=rs.getString("id");
				String pwd=rs.getString("pwd");
				String name=rs.getString("name");
				String nickname=rs.getString("nickname");
				Date regdate=rs.getDate("regdate");
				String profile=rs.getString("profile");
				list.add(new MemberVo(member_no, id, pwd, name, nickname, regdate, profile));
			}
			return list;
		} catch(SQLException se) {
			se.getStackTrace();
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	//전체검색
	public ArrayList<String> allSearch(String search) {
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		try {
			con =ConnectionPool.getCon();
			String sql ="select keyword.* from (\r\n" + 
					"select nickname keyword from member\r\n" + 
					"union\r\n" + 
					"select search from tag\r\n" + 
					")keyword\r\n" + 
					"where keyword like ?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "%"+search + "%");
			rs=pstmt.executeQuery();
			
			ArrayList<String> list=new ArrayList<String>();
			
			while(rs.next()) {
				String keyword=rs.getString("keyword");
				list.add(keyword);
			}
			return list;
		} catch(SQLException se) {
			se.getStackTrace();
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	//검색한 게시물리스트
	public ArrayList<ImageVo> list(String keyword) {
		System.out.println(keyword+"keyword");
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		try {
			con=ConnectionPool.getCon();
			String sql ="select i.board_no,i.image_no,i2.imagepath from\r\n" + 
					"(\r\n" + 
					"    select board_no,min(image_no) image_no from image where board_no in\r\n" + 
					"            (select board_no from link,tag \r\n" + 
					"            where link.tag_no=tag.tag_no\r\n" + 
					"            and link.tag_no=(select tag_no from tag where search=?)\r\n" + 
					"            ) group by board_no\r\n" + 
					")i, image i2\r\n" + 
					"where i.image_no=i2.image_no";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs=pstmt.executeQuery();
			
			ArrayList<ImageVo> list=new ArrayList<ImageVo>();

			while(rs.next()) {
				int image_no=rs.getInt("image_no");
				int board_no=rs.getInt("board_no");
				String imagepath=rs.getString("imagepath");
				
				list.add(new ImageVo(image_no, board_no, imagepath));
			}
			return list;
		} catch(SQLException se) {
			se.getStackTrace();
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	//검색한 태그 스토리이미지
	public ArrayList<StoryVo> showTagImg(String keyword) {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		try {
			con=ConnectionPool.getCon();
			String sql ="select * from story where story_no in\r\n" + 
					"(\r\n" + 
					"    select story_no from link,tag\r\n" + 
					"    where link.tag_no=tag.tag_no\r\n" + 
					"    and link.tag_no=(select tag_no from tag where search=?)\r\n" + 
					")";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			rs=pstmt.executeQuery();
			
			ArrayList<StoryVo> list=new ArrayList<StoryVo>();

			while(rs.next()) {
				int story_no=rs.getInt("story_no");
				int member_no=rs.getInt("member_no");
				String content=rs.getString("content");
				String filepath=rs.getString("filepath");
				Date storydate=rs.getDate("storydate");
				list.add(new StoryVo(story_no, member_no, content, filepath, storydate));
			}
			return list;
		} catch(SQLException se) {
			se.getStackTrace();
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}

	

	
	
	
	
	
	
	
	
	
		
}
