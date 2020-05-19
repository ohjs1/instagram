package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.FollowDao;
@WebServlet("/follow/insert")
public class FollowInsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int youmember_no = Integer.parseInt(req.getParameter("youmember_no"));
		String smymember_no = String.valueOf(req.getSession().getAttribute("member_no"));
		int mymember_no=0;
		if(smymember_no==null || smymember_no.equals("")) {
			resp.sendRedirect("/member/login");
		}
		mymember_no=Integer.parseInt(smymember_no);
		if(mymember_no==youmember_no) {
			System.out.println("불가");
			req.setAttribute("msg", "팔로우불가");
		}
		FollowDao dao = new FollowDao();
		int n = dao.insert(mymember_no, youmember_no);
		if(n>0) {
			System.out.println("팔로우완료");
			req.setAttribute("msg", "팔로우성공");
		}else {
			System.out.println("오류");
			req.setAttribute("msg", "팔로우실패");
		}
		req.getRequestDispatcher("/follow/followinfo.jsp").forward(req, resp);
	}
}
