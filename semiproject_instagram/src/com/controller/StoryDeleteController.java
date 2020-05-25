package com.controller;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.StoryDao;

@WebServlet("/story/delete")
public class StoryDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int story_no=Integer.parseInt(req.getParameter("story_no"));
		System.out.println("삭제할 스토리번호: " + story_no);
		StoryDao dao=new StoryDao();
		int n=dao.delete(story_no);
		
		if(n>0) {
			System.out.println(story_no+"번 스토리 삭제 성공");
			//req.setAttribute("main", "/homefeed.jsp");
			req.getRequestDispatcher("../board/homefeed").forward(req, resp);
		}else {
			resp.sendRedirect(req.getContextPath() + "/story/storyfail.jsp");
		}
		
		/*
		JSONObject json=new JSONObject();
		boolean bl=false;
		if(n>0) {
			bl=true;
			System.out.println("삭제 성공");
		}
		json.put("bl", bl);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json);
		*/
		
		/*
		if(n>0) {
			resp.sendRedirect(req.getContextPath() + "/story/afterdelete?story_no="+(story_no+1));
		}else {
			resp.sendRedirect(req.getContextPath() + "/story/storyfail.jsp");
		}
		*/
	}
}
