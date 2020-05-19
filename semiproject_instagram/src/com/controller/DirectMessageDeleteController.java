package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DirectMessageDao;

@WebServlet("/dm/delete")
public class DirectMessageDeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DirectMessageDao dao =DirectMessageDao.getInstance();
		int myMember_no = Integer.parseInt(req.getParameter("myMember_no"));
		int yourMember_no =Integer.parseInt(req.getParameter("yourMember_no"));
		
		System.out.println(myMember_no + " : myMember_no");
		System.out.println(yourMember_no + " : yourMember_no");
		
		int r =dao.delDirectMessage(myMember_no, yourMember_no);
		
		System.out.println("삭제된 DM 개수 : " + r);
		
		
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
 