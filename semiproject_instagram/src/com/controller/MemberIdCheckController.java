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
import com.vo.MemberVo;

@WebServlet("/member/idcheck")
public class MemberIdCheckController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id = req.getParameter("id");
		MemberDao dao = MemberDao.getInstance(); // boolean check=dao.memberInfo(id);
		boolean b=dao.check(id);
		JSONObject json = new JSONObject();
		if (!b) {
			json.put("idcheck", "사용가능");
		} else {
			json.put("idcheck", "X");
		}
		resp.setContentType("text/plian; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
	}
}
