package com.controller;

import java.io.IOException;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.StoryDao;

@WebServlet("/story/delete")
public class StoryDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int story_no=Integer.parseInt(req.getParameter("story_no"));
		StoryDao dao=new StoryDao();
		int n=dao.delete(story_no);
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath() + "mystories.jsp");
		}else {
			resp.sendRedirect(req.getContextPath() + "/story/upload.jsp");
		}
	}
}
