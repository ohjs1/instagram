package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.BoardDao;

@WebServlet("/board/delete")
public class BoardDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int board_no=Integer.parseInt(req.getParameter("board_no"));
		System.out.println(board_no);
		BoardDao dao=new BoardDao();
		int n=dao.boardDelete(board_no);
		JSONObject json=new JSONObject();
		json.put("result", n);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json);
	}
}
