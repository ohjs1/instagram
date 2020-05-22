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

import com.dao.ReadUserDao;
import com.vo.ReaduserMemberVo;
import com.vo.ReaduserVo;

@WebServlet("/readuser/list")
public class ReadUserListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//���� ��� count - mystories.jsp�� jarr����
		int story_no=Integer.parseInt(req.getParameter("story_no"));
		ReadUserDao dao=new ReadUserDao();
		ArrayList<ReaduserMemberVo> list=dao.readuser_list(story_no);
		System.out.println(story_no+":story_no");
		int i=0;
		JSONArray jarr=new JSONArray();
		for(ReaduserMemberVo vo:list) {
			JSONObject json=new JSONObject();
			json.put("readuser_no", vo.getReaduser_no());
			json.put("member_no",vo.getMember_no());
			jarr.put(json);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);

	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//mystories.jsp���� ��� ����������� ����Ǵ� dopost
		int story_no=Integer.parseInt(req.getParameter("story_no"));
		ReadUserDao dao=new ReadUserDao();
		ArrayList<ReaduserMemberVo> list=dao.readuser_list(story_no);
		JSONArray jarr=new JSONArray();
		for(ReaduserMemberVo vo:list) {
			JSONObject json=new JSONObject();
			json.put("readuser_no", vo.getReaduser_no());
			json.put("member_no",vo.getMember_no());
			json.put("nickname",vo.getNickname());
			json.put("profile",vo.getProfile());
			json.put("name", vo.getName());
			jarr.put(json);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);
	}
}
