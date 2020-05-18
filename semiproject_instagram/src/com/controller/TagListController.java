package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.TagDao;
import com.vo.ImageVo;
@WebServlet("/tag/list")
public class TagListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String keyword=req.getParameter("keyword");
		TagDao dao=new TagDao();
		ArrayList<ImageVo> vo=dao.list(keyword);
		
		req.setAttribute("main", "/tag/list.jsp");
		req.setAttribute("keyword", keyword);
		req.setAttribute("vo", vo);
		
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}












