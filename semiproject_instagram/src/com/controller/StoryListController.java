package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.StoryDao;
import com.vo.StoryVo;


@WebServlet("/story/list")
public class StoryListController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int member_no=Integer.parseInt(req.getParameter("member_no"));
		StoryDao dao=new StoryDao();
		ArrayList<StoryVo> list= dao.mem_list(member_no);
	
	}

}
