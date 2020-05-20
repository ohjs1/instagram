package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.GoodDao;

@WebServlet("/good/insertdelete")
public class GoodInsertDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int member_no=Integer.parseInt(req.getParameter("member_no"));
		int board_no=Integer.parseInt(req.getParameter("board_no"));
		System.out.println(member_no+"价价价");
		GoodDao dao=new GoodDao();
		int n=dao.GoodInsertDelete(member_no,board_no);
		System.out.println(n+"府畔蔼");
		JSONObject json=new JSONObject();
		json.put("result", n);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json);
	}
}
