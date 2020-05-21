package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dao.TagDao;
import com.vo.MemberVo;
import com.vo.Tag_boardVo;
import com.vo.Tag_linkVo;

@WebServlet("/tag/search")
public class TagSearchController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String search=req.getParameter("search");
		
		TagDao dao=new TagDao();
		ArrayList<Tag_linkVo> tag_linklist=null;
		ArrayList<MemberVo> memberlist=null;
		ArrayList<Tag_boardVo> allList=null;
		
		JSONArray jrr =new JSONArray();
		
		//#해쉬태그 검색일때
		if(search.substring(0, 1).equals("#")) {
			search=search.substring(1, search.length());
			tag_linklist=dao.tagSearch(search);
			
			for(Tag_linkVo l : tag_linklist) {
				JSONObject json=new JSONObject();
				json.put("result", 1);
				json.put("keyword", l.getSearch());
				json.put("board_cnt", l.getBoard_cnt());
				jrr.put(json);
			}
			
			resp.setContentType("text/plain; charset=utf-8");
			PrintWriter pw =resp.getWriter();
			pw.print(jrr);
		//@검색
		}else if(search.substring(0, 1).equals("@")){
			search=search.substring(1, search.length());
			memberlist=dao.memberSearch(search);
			
			for(MemberVo l : memberlist) {
				JSONObject json=new JSONObject();
				json.put("result", 2);
				json.put("member_no", l.getMember_no());
				json.put("name", l.getName());
				json.put("keyword", l.getNickname());
				json.put("profile", l.getProfile());
				jrr.put(json);
			}
			
			resp.setContentType("text/plain; charset=utf-8");
			PrintWriter pw =resp.getWriter();
			pw.print(jrr);
		//전체검색
		}else {
			allList=dao.allSearch(search);
			
			for(Tag_boardVo l : allList) {
				JSONObject json=new JSONObject();
				json.put("result", 3);
				json.put("keyword", l.getSearch());
				json.put("cnt", l.getCnt());
				json.put("member_no", l.getMember_no());
				json.put("name", l.getName());
				json.put("nickname", l.getNickname());
				json.put("profile", l.getProfile());
				jrr.put(json);
				
//				System.out.println(l.getSearch());
//				System.out.println(l.getCnt());
//				System.out.println(l.getMember_no());
//				System.out.println(l.getName());
//				System.out.println(l.getNickname());
//				System.out.println(l.getProfile());
//				System.out.println("------------------");
				
			}
				resp.setContentType("text/plain; charset=utf-8");
				PrintWriter pw =resp.getWriter();
				pw.print(jrr);
			
			
		}
	}
}
