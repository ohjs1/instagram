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
import com.vo.ReaduserVo;

@WebServlet("/readuser/list")
public class ReadUserListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int story_no=Integer.parseInt(req.getParameter("story_no"));
		ReadUserDao dao=new ReadUserDao();
		ArrayList<ReaduserVo> list=dao.readuser_list(story_no);
		System.out.println("readuser/list µµÂø ..json");
		JSONArray jarr=new JSONArray();
		for(ReaduserVo vo:list) {
			JSONObject json=new JSONObject();
			json.put("readuser_no", vo.getReaduser_no());
			json.put("member_no",vo.getMember_no());
			jarr.put(json);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);
	}

}
