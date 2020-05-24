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
import com.vo.MemberVo;
import com.vo.StoryMemberVo;
import com.vo.StoryVo;



@WebServlet("/story/opponentlist")
public class StoryOpponentListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//읽은 사람 저장하는 부분
		int member_no=Integer.parseInt(req.getParameter("member_no"));

		StoryDao dao=new StoryDao();
		ArrayList<StoryMemberVo> list= dao.mem_list(member_no);
		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/story/opponentstories.jsp").forward(req, resp);
	

	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//opponentstories.jsp 상대방 프로필, 닉네임 띄우는거..getprofile
		StoryDao dao=new StoryDao();
		HttpSession session=req.getSession();
		int login_no=(int)session.getAttribute("member_no");
		ArrayList<StoryMemberVo> list= dao.storymembers(login_no);

		JSONArray jarr=new JSONArray();
		for(StoryMemberVo vo:list) {
			JSONObject json=new JSONObject();
			json.put("member_no", vo.getMember_no());
			json.put("profile", vo.getProfile());
			json.put("nickname", vo.getNickname());
			jarr.put(json);
		}
		
	
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(jarr);
		
		
	}

}
