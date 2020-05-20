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

import com.dao.GoodDao;
import com.vo.Good_MemberVo;

@WebServlet("/good/list")
public class GoodListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int board_no=Integer.parseInt(req.getParameter("board_no"));
		GoodDao dao=new GoodDao();
		ArrayList<Good_MemberVo> list=dao.goodList(board_no);
		JSONArray jarr=new JSONArray();
		for(Good_MemberVo vo:list) {
			JSONObject json=new JSONObject();
			json.put("id",vo.getId());
			json.put("pwd", vo.getPwd() );
			json.put("name", vo.getName());
			json.put("nickname",vo.getNickname());
			json.put("profile", vo.getProfile());
			json.put("good_no", vo.getGood_no());
			json.put("member_no", vo.getMember_no());
			json.put("board_no", vo.getBoard_no());
			jarr.put(json);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);
	}
}
