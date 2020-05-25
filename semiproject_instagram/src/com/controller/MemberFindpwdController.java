package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.MemberDao;
@WebServlet("/member/findpwd")
public class MemberFindpwdController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String name=req.getParameter("name");
		System.out.println("아이디:"+id);
		System.out.println("이름:"+name);
		
		MemberDao dao=MemberDao.getInstance();
		String pwd=dao.memberFindpwd(id,name);
		
		resp.setContentType("text/plian; charset=utf-8");
		JSONObject json=new JSONObject();
		if(pwd!=null) {
			json.put("msg", pwd);
		}else {
			json.put("msg", "아이디 또는 이름이 맞지 않아요!");
		}
		PrintWriter pw=resp.getWriter();
		pw.print(json);
		
	}
}
