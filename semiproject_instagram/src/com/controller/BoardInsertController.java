package com.controller;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.print.attribute.standard.Severity;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dao.BoardDao;
import com.dao.ImageDao;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.vo.BoardVo;

@WebServlet("/board/insert")
public class BoardInsertController extends HttpServlet{
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setAttribute("main", "/board/insert.jsp");
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);
	}
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//게시글부분
        HttpSession session=req.getSession();
        int member_no=(int)session.getAttribute("member_no");
        String sboard_no=req.getParameter("board_no");
        String content="";
		int board_no=0;
		int ref=0;
		int lev=0;
		int step=0;
		if(sboard_no!=null && !sboard_no.equals("")) {
			board_no=Integer.parseInt(sboard_no);
			ref=Integer.parseInt(req.getParameter("ref"));
			lev=Integer.parseInt(req.getParameter("lev"));
			step=Integer.parseInt(req.getParameter("step"));
		}
		
		
		//Apache commons FileUpload구현
		resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        
        String upload=req.getServletContext().getRealPath("/upload");
        System.out.println("파일경로:"+upload+"\n");
        
        File currentDirPath = new File(upload); //업로드할 파일경로 지정
        
        DiskFileItemFactory factory = new DiskFileItemFactory(); //업로드된 파일을 저장할 저장소와 관련된 클래스
        factory.setRepository(currentDirPath); //업로드 된 파일을 저장할 위치를 File 객체로 지정
        factory.setSizeThreshold(1024 * 1024 *5); //업로드 최대크기 지정
        
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        String fileName=null;
        ArrayList<String> fileList= new ArrayList<String>(); //파일이름들을 담을 리스트
        try {
        	List<FileItem> items=fileUpload.parseRequest(req); //전송된 req에서 매개변수를 List로 가져옴
        	for(int i=0; i<items.size(); i++) {
        		FileItem fileItem=(FileItem) items.get(i); //파일 업로드창에서 업로드된 항목들을 하나씩 가져옴
        		//isFormField()메소드는 파일데이터인지, 그외에 폼데이터인지 true,false로 반환 ex) 파일데이터인경우 false반환
        		if(fileItem.isFormField()) {
        			String fieldName=fileItem.getFieldName();
        			if(fieldName.equals("content")) {
        				content=fileItem.getString("utf-8");
        			}
        			System.out.println(fileItem.getFieldName()+"="+fileItem.getString("utf-8"));
        		}else{	//파일이면 파라미터명,파일명,파일크기 및 파일 업로드
        			System.out.println("파라미터명:"+fileItem.getFieldName());
        			System.out.println("파일명:"+fileItem.getName());
        			System.out.println("파일크기:"+fileItem.getSize()+"bytes");
        			
        			if(fileItem.getSize()>0) { //업로드할 파일이 존재하면
        				fileName=fileItem.getName();
        				File uploadFile=new File(currentDirPath,fileName);
        				if(uploadFile.exists()) { //올리려는 파일과 같은 이름이 존재하면 중복파일 처리
        					for(int k=0; true; k++) {
        						//파일명 중복제거를 위해 (k)를 앞에 생성
        						uploadFile=new File(currentDirPath,"("+k+")"+fileName);
        						if(!uploadFile.exists()) {
        							fileName="("+k+")"+fileName;
        							break;
        						}
        					}
        				}
        				fileList.add(fileName); //파일이름을 ArrayList에 추가
        				fileItem.write(uploadFile); //파일 업로드
        			}
        		}
        	}
        	BoardVo boardvo=new BoardVo(board_no,member_no,content,ref,lev,step,null);
        	BoardDao boarddao=new BoardDao();
			boolean n=boarddao.insert(boardvo,fileList,member_no);
			if(n) {
				System.out.println("성공");
			}else {
				System.out.println("실패");
			}
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
		
        req.setAttribute("fileList", fileList);
		req.setAttribute("main", "/board/homefeed");
        
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);

	}
}
