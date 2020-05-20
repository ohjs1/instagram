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
		//읽은 사람 count 
		int story_no=Integer.parseInt(req.getParameter("story_no"));
		ReadUserDao dao=new ReadUserDao();
		ArrayList<ReaduserMemberVo> list=dao.readuser_list(story_no);
		int count_ru=dao.listCount(story_no);
		System.out.println("readuser/list = sotry_no:"+story_no +"읽은 사람 : " + count_ru+"명");
		JSONArray jarr=new JSONArray();
		for(ReaduserMemberVo vo:list) {
			JSONObject json=new JSONObject();
			json.put("readuser_no", vo.getReaduser_no());
			json.put("member_no",vo.getMember_no());
			json.put("count_ru", count_ru);
			jarr.put(json);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//mystories.jsp에서 모달 실행시켰을때 실행되는 dopost
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
			jarr.put(json);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);
	}
}
