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
		//�Խñۺκ�
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
		
		
		//Apache commons FileUpload����
		resp.setContentType("text/html; charset=UTF-8");
        req.setCharacterEncoding("utf-8");
        
        String upload=req.getServletContext().getRealPath("/upload");
        System.out.println("���ϰ��:"+upload+"\n");
        
        File currentDirPath = new File(upload); //���ε��� ���ϰ�� ����
        
        DiskFileItemFactory factory = new DiskFileItemFactory(); //���ε�� ������ ������ ����ҿ� ���õ� Ŭ����
        factory.setRepository(currentDirPath); //���ε� �� ������ ������ ��ġ�� File ��ü�� ����
        factory.setSizeThreshold(1024 * 1024 *5); //���ε� �ִ�ũ�� ����
        
        ServletFileUpload fileUpload = new ServletFileUpload(factory);
        String fileName=null;
        ArrayList<String> fileList= new ArrayList<String>(); //�����̸����� ���� ����Ʈ
        try {
        	List<FileItem> items=fileUpload.parseRequest(req); //���۵� req���� �Ű������� List�� ������
        	for(int i=0; i<items.size(); i++) {
        		FileItem fileItem=(FileItem) items.get(i); //���� ���ε�â���� ���ε�� �׸���� �ϳ��� ������
        		//isFormField()�޼ҵ�� ���ϵ���������, �׿ܿ� ������������ true,false�� ��ȯ ex) ���ϵ������ΰ�� false��ȯ
        		if(fileItem.isFormField()) {
        			String fieldName=fileItem.getFieldName();
        			if(fieldName.equals("content")) {
        				content=fileItem.getString("utf-8");
        			}
        			System.out.println(fileItem.getFieldName()+"="+fileItem.getString("utf-8"));
        		}else{	//�����̸� �Ķ���͸�,���ϸ�,����ũ�� �� ���� ���ε�
        			System.out.println("�Ķ���͸�:"+fileItem.getFieldName());
        			System.out.println("���ϸ�:"+fileItem.getName());
        			System.out.println("����ũ��:"+fileItem.getSize()+"bytes");
        			
        			if(fileItem.getSize()>0) { //���ε��� ������ �����ϸ�
        				fileName=fileItem.getName();
        				File uploadFile=new File(currentDirPath,fileName);
        				if(uploadFile.exists()) { //�ø����� ���ϰ� ���� �̸��� �����ϸ� �ߺ����� ó��
        					for(int k=0; true; k++) {
        						//���ϸ� �ߺ����Ÿ� ���� (k)�� �տ� ����
        						uploadFile=new File(currentDirPath,"("+k+")"+fileName);
        						if(!uploadFile.exists()) {
        							fileName="("+k+")"+fileName;
        							break;
        						}
        					}
        				}
        				fileList.add(fileName); //�����̸��� ArrayList�� �߰�
        				fileItem.write(uploadFile); //���� ���ε�
        			}
        		}
        	}
        	BoardVo boardvo=new BoardVo(board_no,member_no,content,ref,lev,step,null);
        	BoardDao boarddao=new BoardDao();
			boolean n=boarddao.insert(boardvo,fileList,member_no);
			if(n) {
				System.out.println("����");
			}else {
				System.out.println("����");
			}
        }catch(Exception e) {
        	System.out.println(e.getMessage());
        }
		
        req.setAttribute("fileList", fileList);
		req.setAttribute("main", "/board/homefeed");
        
		req.getRequestDispatcher("/layout.jsp").forward(req, resp);

	}
}
