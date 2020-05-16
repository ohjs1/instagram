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
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("태그리스트 컨트롤러");
		req.setAttribute("main", "/tag/list.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
