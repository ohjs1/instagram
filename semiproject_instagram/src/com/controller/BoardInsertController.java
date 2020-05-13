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
		//Apache commons FileUpload구현
		resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        PrintWriter out = resp.getWriter();
        
        String upload=req.getServletContext().getRealPath("/upload");
        System.out.println("파일경로:"+upload+"\n");
        
        File attachesDir = new File("C:\\attaches");
        
        DiskFileItemFactory fileItemFactory = new DiskFileItemFactory(); //업로드된 파일을 저장할 저장소와 관련된 클래스
        fileItemFactory.setRepository(attachesDir); //업로드 된 파일을 저장할 위치를 File 객체로 지정
        fileItemFactory.setSizeThreshold(1024 * 1024 * 5); //업로드 최대크기 지정
        ServletFileUpload fileUpload = new ServletFileUpload(fileItemFactory);

		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		//MultipartRequest부분
//		req.setCharacterEncoding("utf-8");
//		HttpSession session=req.getSession();
//		//int member_no=(int)session.getAttribute("member_no"); //세션회원번호
//		String upload=req.getServletContext().getRealPath("/upload");
//		String snum=req.getParameter("num");
//		System.out.println("파일경로:"+upload+"\n");
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
//		//form태그에 enctype="multipart/form-data"가 있어야 에러안남
//		MultipartRequest mr=new MultipartRequest(
//				req, //request 객체(getParameter 불가)
//				upload, //업로드할 디렉토리
//				1025*1024*50, //최대업로드 크기(50MB)
//				"utf-8", //인코딩방식
//				new DefaultFileRenamePolicy() //중복되는 파일명이 업로드되면
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
