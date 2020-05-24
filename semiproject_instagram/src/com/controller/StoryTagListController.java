package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.TagDao;
import com.vo.StoryVo;

@WebServlet("/story/storytaglist")
public class StoryTagListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String keyword=req.getParameter("keyword");
		TagDao dao=new TagDao();
		ArrayList<StoryVo> list=dao.showTagImg(keyword);
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/story/tag_stories.jsp").forward(req, resp);
	}

}
