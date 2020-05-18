package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dao.StoryDao;
import com.vo.StoryVo;



@WebServlet("/story/opponentlist")
public class StoryOpponentListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int member_no=Integer.parseInt(req.getParameter("member_no"));

		StoryDao dao=new StoryDao();
		ArrayList<StoryVo> list= dao.mem_list(member_no);

		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/story/opponentstories.jsp").forward(req, resp);
	

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		StoryDao dao=new StoryDao();
		ArrayList<StoryVo> list= dao.storymembers();
		JSONArray jarr=new JSONArray();
		for(StoryVo vo:list) {
			JSONObject json=new JSONObject();
			json.put("member_no", vo.getMember_no());
			System.out.println("dopost opponentlist story¿Ã¸° member_no: "+vo.getMember_no());
			jarr.put(json);
		}
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);
		
		
	}

}
