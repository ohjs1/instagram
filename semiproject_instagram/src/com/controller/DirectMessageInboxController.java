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
import com.vo.MemberVo;

@WebServlet("/dm/inbox")
public class DirectMessageInboxController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int member_no = Integer.parseInt( req.getParameter("member_no") );
		
		
		DirectMessageDao dao = DirectMessageDao.getInstance();

		//유저목록 리스트받아오기
		HttpSession session =req.getSession();
		ArrayList<MemberVo> list = dao.getDMUserList(member_no);
		session.setAttribute("dmlist", list);
		
		//dm메뉴에서 클릭시 채팅창 감추기
		session.setAttribute("showboxCheck", "off");
		
		req.setAttribute("main", "/dm/directMain.jsp"); 
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
