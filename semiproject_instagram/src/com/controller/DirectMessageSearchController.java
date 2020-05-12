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

import com.dao.DmDao;
import com.vo.MemberVo;

@WebServlet("/dm/usersearch")
public class DirectMessageSearchController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String keyword =req.getParameter("keyword");
//		System.out.println(keyword);
		DmDao dao =new DmDao();
		ArrayList<MemberVo> list =dao.getUserList(keyword);
		
		JSONArray jrr =new JSONArray();
		
		for(MemberVo vo : list) {
			JSONObject json =new JSONObject();
			json.put("member_no", vo.getMember_no());
			json.put("id", vo.getId());
			json.put("name", vo.getName());
			json.put("nickname", vo.getNickname());
			json.put("profile", vo.getProfile());
			jrr.put(json);
		}
		 
		
		resp.setContentType("text/plain; charset=utf-8"); //plain 단순 문자열이라는 표시
		
		PrintWriter pw =resp.getWriter();
		pw.print(jrr); //json문자열로 응답
	}
}
