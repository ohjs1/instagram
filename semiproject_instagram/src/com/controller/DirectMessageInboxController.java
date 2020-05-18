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

@WebServlet("/dm/inbox")
public class DirectMessageInboxController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String member_no = req.getParameter("member_no");
		DirectMessageDao dao = DirectMessageDao.getInstance();

		//유저목록 리스트받아오기
		HttpSession session =req.getSession();
		ArrayList<ChatUserlistVo> list = dao.getDMUserList(Integer.parseInt( member_no ));
		session.setAttribute("dmlist", list);
		
		req.setAttribute("main", "/dm/directMain.jsp"); 
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
