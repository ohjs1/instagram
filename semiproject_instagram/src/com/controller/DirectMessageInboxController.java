package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.DirectMessageDao;
import com.vo.MemberVo;

@WebServlet("/dm/inbox")
public class DirectMessageInboxController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int user_no = Integer.parseInt( req.getParameter("member_no") );
		
		System.out.println(user_no);
		
		DirectMessageDao dao = DirectMessageDao.getInstance();
		MemberVo vo = dao.getUserList(user_no);
		
		HttpSession session = req.getSession();
		
		session.setAttribute("memberVo", vo);
		
		resp.sendRedirect(req.getContextPath() + "/dm/dmInbox.jsp");
	}
}
  