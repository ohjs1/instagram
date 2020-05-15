package com.controller;

import java.io.IOException;

import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.StoryDao;

@WebServlet("/story/delete")
public class StoryDeleteController extends HttpServlet{
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int story_no=Integer.parseInt(req.getParameter("story_no"));
		System.out.println("������ ���丮��ȣ: " + story_no);
		StoryDao dao=new StoryDao();
		int n=dao.delete(story_no);
		//������ �Ϸ�Ǹ�.. ������ ���丮��ȣ+1 �� ��ȣ�� ������ mystories.jsp �� ���� �� ���丮��ȣ���� ���.. ajax
		//������ �Ϸ�Ǹ�.. ������ ���丮��ȣ+1 �� ��ȣ�� ������ /story/afterdelete �� ���� �� ���丮��ȣ�� ..
		//�¼����. ������ �����ѹ����� ������ ���丮jsp�� �ϳ� �� �������ϳ�?
		
		if(n>0) {
			resp.sendRedirect(req.getContextPath() + "/story/afterdelete?story_no="+story_no+1);
		}else {
			resp.sendRedirect(req.getContextPath() + "/story/storyfail.jsp");
		}
	}
}
