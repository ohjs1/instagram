package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDao;
import com.vo.MemberVo;
@WebServlet("/member/login")
public class LoginController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getServletContext().setAttribute("cp", req.getContextPath());
//		req.getRequestDispatcher("/member/login.jsp").forward(req, resp);
		
		resp.sendRedirect(req.getContextPath()+"/member/login.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		String id=req.getParameter("id");
		String pwd=req.getParameter("pwd");
		MemberDao dao=MemberDao.getInstance();
		int n=dao.isMember(id,pwd);
		MemberVo vo= dao.memberInfo(id);
		if(n>0) {
			HttpSession session=req.getSession();
			session.setAttribute("id", id);
			session.setAttribute("pwd", pwd);
			session.setAttribute("member_no", n);
			session.setAttribute("nickname", vo.getNickname());
			session.setAttribute("profile", vo.getProfile());
			
//			req.setAttribute("main", "/homefeed.jsp"); 
			
//			req.getRequestDispatcher("/layout.jsp").forward(req, resp);
			
			//로그인 시간 생성
			SimpleDateFormat logintime = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
			Calendar time = Calendar.getInstance();
//			System.out.println( logintime.format( time.getTime() ) );
			String logintimef = logintime.format( time.getTime() );
			session.setAttribute("logintimef", logintimef);
			
			resp.sendRedirect(req.getContextPath()+"/board/homefeed"); // homefeed컨트롤러로 이동(송영현 수정)
		}else {
			req.setAttribute("errMsg", "아이디 또는 비밀번호가 맞지 않아요!");
			req.getRequestDispatcher("/member/login.jsp").forward(req, resp);
		}
	}
}
