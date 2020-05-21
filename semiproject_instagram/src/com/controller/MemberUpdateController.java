package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDao;
@WebServlet("/member/update")
public class MemberUpdateController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		String name=req.getParameter("name");
		String nickname=req.getParameter("nickname");
		System.out.println("이름"+name+" 닉네임"+nickname);
		
		MemberDao dao=MemberDao.getInstance();
		int n=dao.memberUpdate(id,name,nickname);
		if(n>0) {
			System.out.println("변경 완료");
		}
	
	}
}
