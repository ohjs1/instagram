package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.FollowDao;
@WebServlet("/follow/delete")
public class FollowDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int youmember_no = Integer.parseInt(req.getParameter("youmember_no"));
		int mymember_no = (int)req.getSession().getAttribute("member_no");
		FollowDao dao = new FollowDao();
		try {
			int n = dao.delete(mymember_no, youmember_no);
			if(n>0) {
				resp.sendRedirect("/follow/select?mymember_no="+mymember_no);
			}
		}catch(Exception e) {
			System.out.println("팔로우딜리트컨트롤러 예외발생");
			req.setAttribute("msg", "fail");
			req.getRequestDispatcher("/follow/followinfo.jsp").forward(req, resp);
		}
	}
}
