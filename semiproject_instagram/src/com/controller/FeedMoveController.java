package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MemberDao;
import com.vo.MemberVo;

@WebServlet("/feed/move")
public class FeedMoveController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int youmember_no = Integer.parseInt(req.getParameter("youmember_no"));
		int mymember_no = (int)req.getSession().getAttribute("member_no");
		if(youmember_no==mymember_no) {
			req.getRequestDispatcher("/feed/myfeed.jsp").forward(req, resp);
		}else {
			MemberVo vo = MemberDao.getInstance().memberInfo(youmember_no);
			req.setAttribute("vo", vo);
			
			req.getRequestDispatcher("/feed/youfeed.jsp").forward(req, resp);
		}
		
	}
}
