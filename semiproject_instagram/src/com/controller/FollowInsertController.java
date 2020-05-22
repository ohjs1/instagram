package com.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

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
		FollowDao dao = new FollowDao();
		if(mymember_no==youmember_no) {
			System.out.println("ºÒ°¡");
			req.setAttribute("msg", "ÆÈ·Î¿ìºÒ°¡");
			req.getRequestDispatcher("/follow/followinfo.jsp").forward(req, resp);
		}else if(dao.followfind(mymember_no,youmember_no)){
			System.out.println("ÀÌ¹ÌÆÈ·Î¿ì");
			req.setAttribute("msg", "ÀÌ¹ÌÆÈ·Î¿ì");
			req.getRequestDispatcher("/follow/followinfo.jsp").forward(req, resp);
		}else {
			int n = dao.insert(mymember_no, youmember_no);
			resp.setContentType("text/plain;charset=utf-8");
			PrintWriter pw =resp.getWriter();
			JSONObject json = new JSONObject();
			if(n>0) {
				System.out.println("ÆÈ·Î¿ì");
				json.put("using", true);
				pw.print(json);
			}else {
				System.out.println("noÆÈ·Î¿ì");
				json.put("using", false);
				pw.print(json);
			}
		}

	}
}
