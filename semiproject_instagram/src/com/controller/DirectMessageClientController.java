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
import com.vo.ChatContentVo;

@WebServlet("/dm/client")
public class DirectMessageClientController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int myMember_no =Integer.parseInt(req.getParameter("myMember_no"));
		int yourMember_no =Integer.parseInt(req.getParameter("yourMember_no"));
		HttpSession session =req.getSession();
		session.setAttribute("yourMember_no", yourMember_no);
		session.setAttribute("myMember_no", myMember_no);
		
		System.out.println("/dm/client");
		
		DirectMessageDao dao =DirectMessageDao.getInstance();
		
		//채팅방 번호 불러오기
		System.out.println("myMember_no : " + myMember_no);
		System.out.println("yourMember_no : " + yourMember_no);
		int chat_no =dao.getChattingRoomNumber(myMember_no);
		System.out.println(chat_no + " 채팅 번호~~~~~~~~~~~~~~");
		
		//DB에서 유저 채팅기록 불러오기
		ArrayList<ChatContentVo> list =dao.getChatShow(chat_no);
		session.setAttribute("list", list);
		session.setAttribute("chat_no", chat_no);
		
		resp.sendRedirect(req.getContextPath() + "/dm/dmClient.jsp");
	}
}
