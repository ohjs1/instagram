package com.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.dao.BoardDao;
import com.dao.ImageDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vo.BoardVo;

@WebServlet("/board/insert")
public class BoardInsertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		resp.sendRedirect(req.getContextPath()+"/board/insert.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//Apache commons FileUpload����
		resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        
        String upload=req.getServletContext().getRealPath("/upload");
        System.out.println("���ϰ��:"+upload+"\n");
        
        File attachesDir = new File("C:\\attaches");
        
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory(); //���ε�� ������ ������ ����ҿ� ���õ� Ŭ����
        fileItemFactory.setRepository(attachesDir); //���ε� �� ������ ������ ��ġ�� File ��ü�� ����
        fileItemFactory.setSizeThreshold(1024 * 1024 * 5); //���ε� �ִ�ũ�� ����
        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//MultipartRequest�κ�
//		req.setCharacterEncoding("utf-8");
//		HttpSession session=req.getSession();
//		//int member_no=(int)session.getAttribute("member_no"); //����ȸ����ȣ
//		String upload=req.getServletContext().getRealPath("/upload");
//		String snum=req.getParameter("num");
//		System.out.println("���ϰ��:"+upload+"\n");
//		String content=req.getParameter("content");
//		int board_num=0;
//		int ref=0;
//		int lev=0;
//		int step=0;
//		if(snum!=null && !snum.equals("")) {
//			board_num=Integer.parseInt(snum);
//			ref=Integer.parseInt(req.getParameter("ref"));
//			lev=Integer.parseInt(req.getParameter("lev"));
//			step=Integer.parseInt(req.getParameter("step"));
//		}
//		BoardVo boardVo=new BoardVo(board_num,1,content,ref,lev,step,null);
//		BoardDao boardDao=new BoardDao();
//		//form�±׿� enctype="multipart/form-data"�� �־�� �����ȳ�
//		MultipartRequest mr=new MultipartRequest(
//				req, //request ��ü(getParameter �Ұ�)
//				upload, //���ε��� ���丮
//				1025*1024*50, //�ִ���ε� ũ��(50MB)
//				"utf-8", //���ڵ����
//				new DefaultFileRenamePolicy() //�ߺ��Ǵ� ���ϸ��� ���ε�Ǹ�
//				);
//		Enumeration<String> files=mr.getFileNames();
//		//ImageDao imageDao=new ImageDao();
//		//int n=boardDao.insert(boardVo);
//		while(files.hasMoreElements()) {
//			String name=files.nextElement();
//			String savefileName=mr.getFilesystemName(name);
//			System.out.println(savefileName);
//		}
	}
}
