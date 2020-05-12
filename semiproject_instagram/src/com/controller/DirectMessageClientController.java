package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dm/client")
public class DirectMessageClientController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int member_no =Integer.parseInt(req.getParameter("member_no"));
		HttpSession session =req.getSession();
		session.setAttribute("your_member_no", member_no);
		resp.sendRedirect(req.getContextPath() + "/dm/dmClient.jsp");
		
	}
}
