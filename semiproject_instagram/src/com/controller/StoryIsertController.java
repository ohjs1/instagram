package com.controller;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.HTTP;
import org.json.JSONObject;

import com.dao.StoryDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vo.StoryVo;

@WebServlet("/story/insert")
public class StoryIsertController extends HttpServlet{
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("main", "/story/story.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ServletContext application=req.getServletContext();
		String upload=application.getRealPath("/upload");
		
		MultipartRequest mr=new MultipartRequest(
				req, //request ��ü
				upload, // ���ε��� ���丮
				1024*1024*5, // �ִ���ε�ũ�� (5MB)
				"utf-8", //���ڵ� ���
				new DefaultFileRenamePolicy() // ������ ���ϸ� ���ε�� ���ϸ� ���� +1 rename ����
				);
		StoryDao dao=new StoryDao();
		
		HttpSession session=req.getSession();
		String id=(String)session.getAttribute("id");
		String content=mr.getParameter("content");
		String filepath=mr.getFilesystemName("file1");
		int member_no=dao.getMemberNo(id);
		StoryVo vo=new StoryVo(0, member_no, content, filepath, null);
		boolean chk= dao.insert(vo);
		
		if(chk) {
			req.setAttribute("member_no", member_no);
			req.setAttribute("filepath", filepath);
			req.getRequestDispatcher("/story/list").forward(req, resp);
			
		}else {
			req.setAttribute("main", "story/storyfail.jsp");
			req.getRequestDispatcher("/layout.jsp").forward(req, resp);
		}
		
		
	}
}
