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

@WebServlet("/good/listhomefeed")
public class GoodListHomeFeedController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String sboard_no=req.getParameter("halist");
		String[] nolist=sboard_no.split(",");
		JSONArray arr=new JSONArray();
		GoodDao dao=new GoodDao();
		for(String board_no:nolist) {
			ArrayList<Good_MemberVo> list=dao.goodList(Integer.parseInt(board_no));
			JSONArray jarr=new JSONArray();
			if(list!=null) {
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
				arr.put(jarr);
			}
		}
		System.out.println(arr);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(arr);
		System.out.println(arr);
	}
}
