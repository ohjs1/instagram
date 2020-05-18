package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.ReadUserDao;
import com.vo.ReaduserVo;

@WebServlet("/readuser/insert")
public class ReadUserInsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int member_no=Integer.parseInt(req.getParameter("readuser"));
		int story_no=Integer.parseInt(req.getParameter("readstory"));
		
		ReadUserDao dao=new ReadUserDao();
		ReaduserVo vo=new ReaduserVo(0, member_no, story_no);
		int n=dao.insert(vo);
		
		if(n>0) {
			System.out.println("스토리읽은 사람 목록 저장 완료");
		}else {
			System.out.println("readuser insert fail");
		}
	}
}
