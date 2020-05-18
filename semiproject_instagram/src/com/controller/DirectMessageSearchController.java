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

import com.dao.DirectMessageDao;
import com.vo.MemberVo;

@WebServlet("/dm/usersearch")
public class DirectMessageSearchController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String keyword =req.getParameter("keyword");
		int myMember_no =Integer.parseInt(req.getParameter("myMember_no"));
		
//		System.out.println("�Ѿ�� myMember_no >>" + myMember_no);
		
		DirectMessageDao dao =DirectMessageDao.getInstance();
		ArrayList<MemberVo> list =dao.getUserList(keyword, myMember_no);
		
		JSONArray jrr =new JSONArray();
		
		for(MemberVo vo : list) {
			JSONObject json =new JSONObject();
			json.put("yourMember_no", vo.getMember_no());
			json.put("id", vo.getId());
			json.put("name", vo.getName());
			json.put("nickname", vo.getNickname());
			json.put("profile", vo.getProfile());
			json.put("myMember_no", myMember_no); //���ڽ��� ��ȣ
			jrr.put(json);
		}
		
		
		resp.setContentType("text/plain; charset=utf-8"); //plain �ܼ� ���ڿ��̶�� ǥ��
		
		PrintWriter pw =resp.getWriter();
		pw.print(jrr); //json���ڿ��� ����
	}
}
