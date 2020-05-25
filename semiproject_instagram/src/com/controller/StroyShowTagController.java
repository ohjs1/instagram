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
@WebServlet("/tag/showStory")
public class StroyShowTagController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String keyword=req.getParameter("keyword");
		TagDao dao=new TagDao();
		ArrayList<StoryMemberVo> list=dao.showTagImg(keyword);
		JSONArray jrr =new JSONArray();
		
		for(StoryMemberVo l : list) {
			JSONObject json=new JSONObject();
			json.put("filepath", l.getFilepath());
			System.out.println(l.getFilepath()+"------");
			jrr.put(json);
		}
		
		resp.setContentType("text/plain; charset=utf-8");
		PrintWriter pw =resp.getWriter();
		pw.print(jrr);
		
	}
}
