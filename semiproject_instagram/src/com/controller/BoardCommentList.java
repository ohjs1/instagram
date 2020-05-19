package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dao.BoardDao;
import com.vo.Board_MemberVo;

@WebServlet("/board/commentList")
public class BoardCommentList extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int board_no=Integer.parseInt(req.getParameter("board_no"));
		BoardDao dao=new BoardDao();
		ArrayList<Board_MemberVo> list=dao.selectComment(board_no);
		
		JSONArray jarr=new JSONArray();
		for(Board_MemberVo vo:list) {
			JSONObject json=new JSONObject();
			json.put("id",vo.getId());
			json.put("pwd", vo.getPwd() );
			json.put("name", vo.getName());
			json.put("nickname",vo.getNickname());
			json.put("profile", vo.getProfile());
			json.put("board_no",vo.getBoard_no());
			json.put("member_no",vo.getMember_no());
			json.put("content", vo.getContent());
			json.put("ref",vo.getRef());
			json.put("lev",vo.getLev());
			json.put("step",vo.getStep());
			json.put("regdate", vo.getRegdate());
			jarr.put(json);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);
	}
}
