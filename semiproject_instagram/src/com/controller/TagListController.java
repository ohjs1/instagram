package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/tag/list")
public class TagListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String search=req.getParameter("search");
		System.out.println(search + "태그리스트");
		req.setAttribute("main", "/tag/list.jsp");
		req.setAttribute("search", search);
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}












