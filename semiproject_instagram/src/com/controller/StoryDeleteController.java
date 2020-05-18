package com.controller;

import java.io.IOException;
import java.io.PrintWriter;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.dao.StoryDao;

@WebServlet("/story/delete")
public class StoryDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int story_no=Integer.parseInt(req.getParameter("story_no"));
		System.out.println("삭제할 스토리번호: " + story_no);
		StoryDao dao=new StoryDao();
		int n=dao.delete(story_no);
		//삭제가 완료되면.. 삭제한 스토리번호+1 한 번호를 가지고 mystories.jsp 로 가서 그 스토리번호부터 재생.. ajax
		//삭제가 완료되면.. 삭제한 스토리번호+1 한 번호를 가지고 /story/afterdelete 로 가서 그 스토리번호를 ..
		//어ㅇ쩌라고ㅠ. 삭제한 다음넘버부터 보여줄 스토리jsp를 하나 더 만들어야하나?
		
		JSONObject json=new JSONObject();
		boolean bl=false;
		if(n>0) {
			bl=true;
			System.out.println("삭제 성공");
		}
		json.put("bl", bl);
		resp.setContentType("text/plain;charset=utf-8");
		PrintWriter pw=resp.getWriter();
		pw.print(json);
		/*
		if(n>0) {
			resp.sendRedirect(req.getContextPath() + "/story/afterdelete?story_no="+(story_no+1));
		}else {
			resp.sendRedirect(req.getContextPath() + "/story/storyfail.jsp");
		}
		*/
	}
}
