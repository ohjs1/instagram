package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.dao.DirectMessageDao;

@WebServlet("/dm/chatChecked")
public class DirectChatCheckedController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int myMember_no = Integer.parseInt( req.getParameter("myMember_no") );
		HttpSession session = req.getSession();
		
//		System.out.println("myMember_no>>>" + myMember_no);
		
		//채팅방 번호 구하기
		DirectMessageDao dao = DirectMessageDao.getInstance();
		
		//새글있는지 검사
		int result = dao.getDMUserStatusList(myMember_no);
		if(result>0) {
			//새글이 존재함
			System.out.println("새로운글 갯수 : " + result);
			session.setAttribute("cc_result", result);
			session.setAttribute("main", "/dm/directMain.jsp");
			req.getRequestDispatcher("/layout.jsp").forward(req, resp);
		} else {
			System.out.println("새글이 존재하지 않음..");
			session.setAttribute("cc_result", result);
			session.setAttribute("main", "/dm/directMain.jsp");
			req.getRequestDispatcher("/layout.jsp").forward(req, resp);
		}
	}
}
 