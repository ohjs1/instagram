package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;

import com.dao.MemberDao;
import com.vo.MemberVo;
@WebServlet("/member/memberInfo")
public class MemberMemberInfoController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		MemberDao dao=MemberDao.getInstance();
		MemberVo vo=dao.memberInfo(id);
		req.setAttribute("vo", vo);
		req.setAttribute("profile", vo.getProfile());
		req.setAttribute("name", vo.getName());
		req.setAttribute("nickname", vo.getNickname());
		req.setAttribute("main", "/member/profile_update.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
	
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		MemberDao dao=MemberDao.getInstance();
		MemberVo vo=dao.memberInfo(id);
		
		JSONObject json=new JSONObject();
		json.put("profile", vo.getProfile());
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json);
		
	}
}
