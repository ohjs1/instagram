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
		int following_no = Integer.parseInt(req.getParameter("following_no"));
		FollowDao dao = new FollowDao();
		try {
			int n = dao.delete(following_no);
			resp.sendRedirect(req.getContentType()+"/follow/select");
		}catch(Exception e) {
			System.out.println(e.getMessage());
			req.setAttribute("msg", "fail");
			req.getRequestDispatcher("/follow/followinfo.jsp").forward(req, resp);
		}
	}
}
