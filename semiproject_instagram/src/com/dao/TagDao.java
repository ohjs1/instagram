package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import com.db.ConnectionPool;
import com.vo.ImageVo;
import com.vo.MemberVo;
import com.vo.StoryVo;
import com.vo.TagVo;
import com.vo.Tag_linkVo;
import com.vo.Tag_boardVo;

public class TagDao {
	//해쉬태그(#) 제시어
	public ArrayList<Tag_linkVo> tagSearch(String search) {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		try {
			con =ConnectionPool.getCon();
			String sql ="select count(search) cnt, search from link,tag\r\n" + 
						"where link.tag_no=tag.tag_no\r\n" + 
						"and search like ? \r\n" + 
						"group by search";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "#"+search + "%");
			rs=pstmt.executeQuery();
			
			ArrayList<Tag_linkVo> list=new ArrayList<Tag_linkVo>();
			
			while(rs.next()) {
				String keyword=rs.getString("search");
				int board_cnt=rs.getInt("cnt");
				list.add(new Tag_linkVo(keyword, board_cnt));
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
			pstmt.setString(1, "%" + search + "%");
			pstmt.setString(2, "%" + search + "%");
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
	public Set<Tag_boardVo> allSearch(String search) {
		Connection con=null;
		PreparedStatement pstmt=null;
		PreparedStatement pstmt2=null;
		ResultSet rs=null;
		ResultSet rs2=null;
		
		Set<Tag_boardVo> set=new HashSet<Tag_boardVo>();
		
		try {
			con =ConnectionPool.getCon();
			String sql ="select count(search) cnt, search from link,tag\r\n" + 
						"where link.tag_no=tag.tag_no\r\n" + 
						"and search like ? \r\n" + 
						"group by search";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, "#"+search + "%");
			rs=pstmt.executeQuery();
			
			while(rs.next()) {
				int cnt=rs.getInt("cnt");
				String keyword=rs.getString("search");
				set.add(new Tag_boardVo(0, null, null, null, cnt, keyword));
			}
			String sql2 ="select * from member where nickname like ? or name like ?";
			pstmt2=con.prepareStatement(sql2);
			pstmt2.setString(1, "%" + search + "%");
			pstmt2.setString(2, "%" + search + "%");
			rs2=pstmt2.executeQuery();
			
			while(rs2.next()) {
				int member_no=rs2.getInt("member_no");
				String name=rs2.getString("name");
				String nickname=rs2.getString("nickname");
				String profile=rs2.getString("profile");
				set.add(new Tag_boardVo(member_no, name, nickname, profile, 0, null));
			}
			return set;
		} catch(SQLException se) {
			se.getStackTrace();
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	//검색한 게시물리스트
	public ArrayList<ImageVo> list(String keyword,int snum,int endnum) {
		System.out.println(keyword+"keyword");
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		try {
			con=ConnectionPool.getCon();
			String sql ="select * from(\r\n" + 
					"    select i.board_no,i.image_no,i2.imagepath,rownum rnum from\r\n" + 
					"        (select board_no,min(image_no) image_no from image where board_no in\r\n" + 
					"            (select board_no from link,tag\r\n" + 
					"                where link.tag_no=tag.tag_no \r\n" + 
					"                and link.tag_no=(select tag_no from tag where search=?) \r\n" + 
					"            ) group by board_no\r\n" + 
					"        )i, image i2\r\n" + 
					"    where i.image_no=i2.image_no\r\n" + 
					")where rnum>=? and rnum<=?";
			pstmt=con.prepareStatement(sql);
			pstmt.setString(1, keyword);
			pstmt.setInt(2, snum);//추가됨
			pstmt.setInt(3, endnum);//추가됨
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
	
	public ArrayList<ImageVo> list(String keyword) {
		System.out.println(keyword+"keyword");
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		try {
			con=ConnectionPool.getCon();
			
			  String sql ="select i.board_no,i.image_no,i2.imagepath from\r\n" + "(\r\n" +
			  "    select board_no,min(image_no) image_no from image where board_no in\r\n"
			  + "            (select board_no from link,tag \r\n" +
			  "            where link.tag_no=tag.tag_no\r\n" +
			  "            and link.tag_no=(select tag_no from tag where search=?)\r\n" +
			  "            ) group by board_no\r\n" + ")i, image i2\r\n" +
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
				System.out.println("Dao---"+filepath);
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
