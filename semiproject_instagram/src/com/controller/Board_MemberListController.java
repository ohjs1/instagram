package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
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

@WebServlet("/board/list")
public class Board_MemberListController extends HttpServlet{
	//멤버정보 및 게시글정보 얻어오기
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int board_no=Integer.parseInt(req.getParameter("board_no"));
		BoardDao dao=new BoardDao();
		Board_MemberVo vo=dao.selectMemberBoard(board_no);

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
		DateFormat df=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.sss'Z'"); //시간처리하기
		
		json.put("regdate", df.format(vo.getRegdate()));

		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json);
	}
}
