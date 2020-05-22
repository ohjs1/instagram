package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/dm/chatChecked")
public class DirectChatCheckedController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int myMember_no = Integer.parseInt( req.getParameter("myMember_no") );
		int youMember_no = Integer.parseInt( req.getParameter("youMember_no") );
		
		System.out.println(myMember_no);
		System.out.println(youMember_no);
	}
}
