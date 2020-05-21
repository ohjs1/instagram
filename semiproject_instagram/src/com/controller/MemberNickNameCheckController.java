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

@WebServlet("/member/nicknameCheck")
public class MemberNickNameCheckController extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String nickname = req.getParameter("nickname");
		MemberDao dao = MemberDao.getInstance();
		boolean b=dao.nickNamecheck(nickname);
		JSONObject json = new JSONObject();
		if (!b) {
			json.put("nicknameCheck", "o");
		} else {
			json.put("nicknameCheck", "x");
		}
		resp.setContentType("text/plian; charset=utf-8");
		PrintWriter pw = resp.getWriter();
		pw.print(json);
	}
}
