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

@WebServlet("/tag/search")
public class TagSearchController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String search=req.getParameter("search");
		System.out.println(search);
		
		TagDao dao=new TagDao();
		ArrayList<String> list=null;
		ArrayList<MemberVo> Memberlist=null;
		
		JSONArray jrr =new JSONArray();
		
		//#해쉬태그 검색일때
		if(search.substring(0, 1).equals("#")) {
			search=search.substring(1, search.length());
			list=dao.tagSearch(search);
			
			for(String l : list) {
				JSONObject json=new JSONObject();
				json.put("keyword", l);
				jrr.put(json);
			}
			
			resp.setContentType("text/plain; charset=utf-8");
			PrintWriter pw =resp.getWriter();
			pw.print(jrr);
		//@검색
		}else if(search.substring(0, 1).equals("@")){
			search=search.substring(1, search.length());
			Memberlist=dao.memberSearch(search);
			
			for(MemberVo l : Memberlist) {
				JSONObject json=new JSONObject();
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
//			list=dao.allSearch(search);
		}
		
		
		
	}
}
