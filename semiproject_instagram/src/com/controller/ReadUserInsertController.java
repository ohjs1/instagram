package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.ReadUserDao;
import com.vo.ReaduserVo;

@WebServlet("/readuser/insert")
public class ReadUserInsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int member_no=Integer.parseInt(req.getParameter("member_no"));
		int story_no=Integer.parseInt(req.getParameter("story_no"));
		ReadUserDao dao=new ReadUserDao();
		ReaduserVo vo=new ReaduserVo(0, member_no, story_no);
		int n=dao.insert(vo);
		JSONObject json=new JSONObject();
		boolean bl=false;
		if(n>0) {
			bl=true;
			System.out.println("readuser table insert success!");
		}
		json.put("bl", bl);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json);
	}
}
