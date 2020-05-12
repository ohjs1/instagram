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

import com.dao.StoryDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vo.StoryVo;

@WebServlet("/story/insert")
public class StoryIsertController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
		System.out.println("Fk��ȣ:"+member_no);
		System.out.println("���Ǿ��̵�:"+id);
		System.out.println("param content:"+content);
		StoryVo vo=new StoryVo(0, member_no, content, filepath, null);
		int n= dao.insert(vo);
		
		if(n>0) {
			req.getRequestDispatcher("/story/mystory_list.jsp").forward(req, resp);
		}else {
			req.getRequestDispatcher("/story/upload.jsp").forward(req, resp);
		}
		
		
	}
}
