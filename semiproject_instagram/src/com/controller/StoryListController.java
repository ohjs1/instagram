package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.StoryDao;
import com.vo.StoryVo;


@WebServlet("/story/list")
public class StoryListController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		int member_no=(int)session.getAttribute("member_no");
		StoryDao dao=new StoryDao();
		ArrayList<StoryVo> list= dao.mem_list(member_no);

		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/story/mystories.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int member_no=(int) req.getAttribute("member_no");
		String filepath=(String)req.getAttribute("filepath");
		System.out.println("방금업로드한 파일명:"+filepath);
		StoryDao dao=new StoryDao();
		ArrayList<StoryVo> list= dao.mem_list(member_no);

		req.setAttribute("list", list);
		
		req.getRequestDispatcher("/story/mystories.jsp").forward(req, resp);
	}

}
