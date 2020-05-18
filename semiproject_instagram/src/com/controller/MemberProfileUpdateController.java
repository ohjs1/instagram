package com.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.MemberDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
@WebServlet("/member/profileUpdate")
public class MemberProfileUpdateController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext application=req.getServletContext();
		String upload=application.getRealPath("/upload");
		MultipartRequest mr=new MultipartRequest(
					req,
					upload,
					1024*1024*5,
					"utf-8",
					new DefaultFileRenamePolicy()
				);
		String profile1=mr.getParameter("profile1");
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		String profile=mr.getFilesystemName("profile");
		String name=mr.getParameter("name");
		String nickname=mr.getParameter("nickname");
		
		File f=new File(upload + "\\" + profile1);
		if(!profile1.equals("profile.jpg")) {
			f.delete();
		}
		
		MemberDao dao=MemberDao.getInstance();
		int n=dao.memberProfileUpdate(id,profile);
		if(n>0) {
			req.setAttribute("profile", profile);
			req.setAttribute("name", name);
			req.setAttribute("nickname", nickname);
			req.getRequestDispatcher("/member/profile_update.jsp").forward(req, resp);
		}
	}
}





