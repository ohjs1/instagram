package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.StoryDao;
import com.vo.StoryVo;

@WebServlet("/story/storydate")
public class StoryDateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int story_no=Integer.parseInt(req.getParameter("story_no"));
		StoryDao dao=new StoryDao();
		StoryVo vo=dao.story_date(story_no);
		System.out.println(story_no+"번 스토리 올린 날짜 : "+vo.getStorydate().getTime());
		JSONObject json=new JSONObject();
		json.put("storydate", vo.getStorydate().getTime());
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json);
		
	}

}
