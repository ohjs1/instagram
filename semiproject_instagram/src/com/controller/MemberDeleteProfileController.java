package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDao;

@WebServlet("/member/delProfile")
public class MemberDeleteProfileController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		String profile=req.getParameter("profile");
		
		MemberDao dao=MemberDao.getInstance();
		int n=dao.memberProfileUpdate(id,profile);
		if(n>0) {
			System.out.println("프로필 삭제 성공");
			session.setAttribute("profile", "profile.jpg");
		}
	}
}
