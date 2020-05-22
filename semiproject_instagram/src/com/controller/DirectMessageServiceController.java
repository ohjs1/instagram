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
		String nickname = req.getParameter("nickname");
		//String showboxCheck = req.getParameter("showboxCheck");
//		System.out.println("showboxCheck>>" + showboxCheck);
		
		HttpSession session =req.getSession();
		session.setAttribute("myMember_no", myMember_no);
		session.setAttribute("yourMember_no", yourMember_no);
		session.setAttribute("showboxCheck", "on");
		
		//닉네임정보
		session.setAttribute("nickname", nickname);
		
		//채팅창번호
		DirectMessageDao dao = DirectMessageDao.getInstance();
		int chat_no = dao.getChattingRoomNumber(myMember_no, yourMember_no);
		session.setAttribute("chat_no", chat_no);
		//System.out.println("채팅창 번호: " + chat_no); 
		System.out.println(myMember_no + "회원이, " + yourMember_no + "회원방에 입장하였습니다." + "채팅방 번호는 " + chat_no);
		
		//채팅방 읽음으로 표시
		int n = dao.setStatusChatRoom(yourMember_no, chat_no);
		if(n>0) {
			System.out.println("채팅방 상태값 수정 완료" + n);
		}
		
		req.setAttribute("main", "/dm/directMain.jsp"); 
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
