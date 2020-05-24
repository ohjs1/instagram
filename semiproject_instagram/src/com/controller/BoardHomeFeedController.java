package com.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.BoardDao;
import com.vo.Board_MemberVo;

@WebServlet("/board/homefeed")
public class BoardHomeFeedController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session=req.getSession();
		int member_no=(int)session.getAttribute("member_no");
		BoardDao dao=new BoardDao();
		ArrayList<Board_MemberVo> list=dao.selectHomeFeedMemberBoard(member_no);
		long regdate[]=new long[list.size()];
		for(int i=0; i<list.size(); i++) {
			Board_MemberVo vo=list.get(i);
			regdate[i]=vo.getRegdate().getTime();
		}
		req.setAttribute("regdate", regdate);
		req.setAttribute("boardList", list);
		req.setAttribute("main", "/homefeed.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
}
