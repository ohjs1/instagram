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
@WebServlet("/member/profileUpdate")
public class MemberProfileUpdateController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		String profile=req.getParameter("profile");
		MemberDao dao=MemberDao.getInstance();
		int n=dao.memberProfileUpdate(id,profile);
		if(n>0) {
			resp.setContentType("text/plian; charset=utf-8");
			JSONObject json=new JSONObject();
			json.put("profile", profile);
			PrintWriter pw=resp.getWriter();
			pw.print(json);
		}
		
		
	}
}





