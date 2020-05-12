package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MemberDao;
import com.vo.MemberVo;
@WebServlet("/member/insert")
public class MemberInsertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath()+"/member/join.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String pwd=req.getParameter("pwd");
		String nickname=req.getParameter("nickname");
		String name=req.getParameter("name");
		System.out.println(id);
		System.out.println(pwd);
		System.out.println(nickname);
		System.out.println(name);
		MemberVo vo=new MemberVo(0, id, pwd, name, nickname, null, null);
		MemberDao dao=MemberDao.getInstance();
		int n=dao.insert(vo);
		if(n>0) {
			resp.sendRedirect(req.getContextPath()+"/member/login.jsp");
			System.out.println("가입성공");
		}
	}
}
















