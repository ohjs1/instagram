package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.MemberDao;
import com.vo.MemberVo;
@WebServlet("/follow/move")
public class FollowMoveController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String syoumember_no=req.getParameter("youmember_no");
		int youmember_no=0;
		if(syoumember_no!=null) {
			youmember_no=Integer.parseInt(syoumember_no);
			System.out.println(youmember_no);
		}
		MemberDao dao=MemberDao.getInstance();
		MemberVo vo=dao.memberInfo(youmember_no);
		req.setAttribute("member_no", vo.getMember_no());
		req.setAttribute("id", vo.getId());
		//req.setAttribute("youmember_no", youmember_no);
		
		req.getRequestDispatcher("../feed/myfeed?youmember_no="+youmember_no).forward(req, resp);
	}
}
