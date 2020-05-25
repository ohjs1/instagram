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
		System.out.println("���̵�:"+id);
		System.out.println("�̸�:"+name);
		
		MemberDao dao=MemberDao.getInstance();
		String pwd=dao.memberFindpwd(id,name);
		
		resp.setContentType("text/plian; charset=utf-8");
		JSONObject json=new JSONObject();
		if(pwd!=null) {
			json.put("msg", pwd);
		}else {
			json.put("msg", "���̵� �Ǵ� �̸��� ���� �ʾƿ�!");
		}
		PrintWriter pw=resp.getWriter();
		pw.print(json);
		
	}
}
