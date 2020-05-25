package com.controller;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DirectMessageDao;

@WebServlet("/dm/member/checked")
public class DirectMessageChatCheckedMemberController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int myMember_no = Integer.parseInt( req.getParameter("myMember_no") );
		HttpSession session = req.getSession();
		
		System.out.println(myMember_no + ">>>>myMember_no");
		
		DirectMessageDao dao = DirectMessageDao.getInstance();
	
		//상대 유저 번호
		int yourMemberNo = dao.getDMUserNumber(myMember_no);
		
		//채팅방 번호 구하기
		int char_no = dao.getChattingRoomNumber(myMember_no, yourMemberNo);
		
		//System.out.println("채팅방번호:"+char_no);
		//채팅방글 개별 구하기
		int n_result = dao.getStatusChat(yourMemberNo, char_no);
		System.out.println("개별 채팅방 글 : " + n_result);
		
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		map.put(myMember_no, n_result);
		
		session.setAttribute("maplist", map);
		
		session.setAttribute("main", "/dm/directMain.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
