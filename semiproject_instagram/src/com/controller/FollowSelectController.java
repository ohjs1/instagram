package com.controller;

import java.io.IOException;

import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.FollowDao;
import com.vo.MemberVo;
@WebServlet("/follow/select")
public class FollowSelectController extends HttpServlet{
	 @Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		 	String syoumember_no=req.getParameter("youmember_no");
			String smymember_no=req.getParameter("mymember_no");
			String profile=req.getParameter("profile");
			int youmember_no=0;
			int mymember_no=0;
			boolean bl=false;
			if(syoumember_no==null) {
				mymember_no=Integer.parseInt(smymember_no);
				bl=true;
			}else {
				youmember_no=Integer.parseInt(syoumember_no);
			}
			boolean bool = false;
			int n=(int)req.getSession().getAttribute("member_no");
			if(n==youmember_no || n==mymember_no) {
				bool=true;
			}
			FollowDao dao=new FollowDao();
			ArrayList<MemberVo> list=dao.followingMem(mymember_no, youmember_no, bl);
			req.setAttribute("bl", bl);
			req.setAttribute("list", list);
			req.setAttribute("main", "/follow/followList.jsp");
			req.setAttribute("mymember_no", smymember_no);
			req.setAttribute("profile", profile);
			req.setAttribute("check",bool);
			req.getRequestDispatcher("/layout.jsp").forward(req,resp);
	}
}