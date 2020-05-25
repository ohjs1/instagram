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
import com.vo.StoryMemberVo;
import com.vo.StoryVo;

@WebServlet("/story/storytaglist")
public class StoryTagListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String keyword=req.getParameter("keyword");
		System.out.println("storytaglist controller : " + keyword );
		TagDao dao=new TagDao();
		ArrayList<StoryMemberVo> list=dao.showTagImg(keyword);
		req.setAttribute("list", list);
		req.setAttribute("keyword", keyword);
		resp.setContentType("text/plain; charset=utf-8");
		req.getRequestDispatcher("/story/tag_stories.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String keyword=req.getParameter("keyword");
		System.out.println("전달받은 키워드 :" + keyword);
		TagDao dao=new TagDao();
		ArrayList<StoryMemberVo> list=dao.showTagImg(keyword);

		JSONArray jrr =new JSONArray();
		
		for(StoryMemberVo vo : list) {
			JSONObject json=new JSONObject();
			json.put("member_no", vo.getMember_no());
			json.put("profile", vo.getProfile());
			json.put("nickname", vo.getNickname());
			System.out.println( vo.getMember_no()+"/"+vo.getNickname()); //잘들어감..
			jrr.put(json);
		}
		
		resp.setContentType("text/plain; charset=utf-8");
		PrintWriter pw =resp.getWriter();
		pw.print(jrr);
		
	}

}
