package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDao;
import com.vo.MemberVo;
@WebServlet("/member/memberInfo")
public class MemberProfileController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		MemberDao dao=MemberDao.getInstance();
		MemberVo vo=dao.memberInfo(id);
		req.setAttribute("vo", vo);
		req.getRequestDispatcher(req.getContextPath()+"/member/profile_update.jsp").forward(req, resp);
	}
}
