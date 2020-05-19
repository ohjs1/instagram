package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/dm/connectClient")
public class DirectMessageServiceController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int myMember_no =Integer.parseInt(req.getParameter("myMember_no"));
		int yourMember_no =Integer.parseInt(req.getParameter("yourMember_no"));
		String nickname = req.getParameter("nickname");
		String showboxCheck = req.getParameter("showboxCheck");
		System.out.println("showboxCheck>>" + showboxCheck);
		
		HttpSession session =req.getSession();
		session.setAttribute("myMember_no", myMember_no);
		session.setAttribute("yourMember_no", yourMember_no);
		session.setAttribute("showboxCheck", "on");
		
		//닉네임정보
		session.setAttribute("nickname", nickname);

		req.setAttribute("main", "/dm/directMain.jsp"); 
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
