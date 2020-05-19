package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDao;
@WebServlet("/member/pwdupdate")
public class MemberPassWordUpdateController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("main", "/member/pw_update.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		String newPwd=req.getParameter("newPwd1");
		MemberDao dao=MemberDao.getInstance();
		int n=dao.pwdupdate(id,newPwd);
		if(n>0) {
			System.out.println("비밀번호 변경 완료");
			resp.sendRedirect(req.getContextPath()+"/home");
		}
	}
}













