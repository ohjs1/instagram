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
import com.vo.BoardVo;

@WebServlet("/board/commentInsert")
public class BoardCommentInsert extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int board_no=Integer.parseInt(req.getParameter("board_no"));
		int member_no=Integer.parseInt(req.getParameter("member_no"));
		String comment=req.getParameter("comment");
		int ref=Integer.parseInt(req.getParameter("ref"));
		int lev=Integer.parseInt(req.getParameter("lev"));
		int step=Integer.parseInt(req.getParameter("step"));
		
		BoardVo vo=new BoardVo(board_no,member_no,comment,ref,lev,step,null);
    	BoardDao dao=new BoardDao();
    	int n=dao.insertComment(vo);
		
    	JSONObject json=new JSONObject();
    	json.put("chk", n);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json);	
	}
}
