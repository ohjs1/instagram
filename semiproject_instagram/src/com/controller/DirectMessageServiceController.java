package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DirectMessageDao;
import com.vo.ChatUserlistVo;

@WebServlet("/dm/connectClient")
public class DirectMessageServiceController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int myMember_no =Integer.parseInt(req.getParameter("myMember_no"));
		int yourMember_no =Integer.parseInt(req.getParameter("yourMember_no"));
		
		System.out.println("myMember_no>>" + myMember_no);
		System.out.println("yourMember_no>>" + yourMember_no);
		
		HttpSession session =req.getSession();
		session.setAttribute("myMember_no", myMember_no);
		session.setAttribute("yourMember_no", yourMember_no);
		
		DirectMessageDao dao =DirectMessageDao.getInstance();
		int chat_no =dao.getChattingRoomNumber(myMember_no, yourMember_no);
		session.setAttribute("chat_no", chat_no);

		req.setAttribute("main", "/dm/directMain.jsp"); 
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
//		resp.sendRedirect(req.getContextPath() + "/dm/inbox");
	}
}
