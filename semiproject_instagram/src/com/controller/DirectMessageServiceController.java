package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DirectMessageDao;

@WebServlet("/dm/connectClient")
public class DirectMessageServiceController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int myMember_no =Integer.parseInt(req.getParameter("myMember_no"));
		int yourMember_no =Integer.parseInt(req.getParameter("yourMember_no"));
		HttpSession session =req.getSession();
		session.setAttribute("myMember_no", myMember_no);
		session.setAttribute("yourMember_no", yourMember_no);
		
		DirectMessageDao dao =DirectMessageDao.getInstance();
		int chat_no =dao.getChattingRoomNumber(myMember_no, yourMember_no);
		session.setAttribute("chat_no", chat_no);
		
		//유저 닉네임 request에 담기
		String nickname = dao.getUserNickName(yourMember_no);
		req.setAttribute("nickname", nickname);
		req.getRequestDispatcher("/dm/inbox").forward(req, resp);
//		resp.sendRedirect(req.getContextPath() + "/dm/inbox");
	}
}
