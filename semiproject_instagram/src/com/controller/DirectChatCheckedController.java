package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import com.dao.DirectMessageDao;

@WebServlet("/dm/chatChecked")
public class DirectChatCheckedController extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int myMember_no = Integer.parseInt( req.getParameter("myMember_no") );
		HttpSession session = req.getSession();
		
//		System.out.println("myMember_no>>>" + myMember_no);
		
		//ä�ù� ��ȣ ���ϱ�
		DirectMessageDao dao = DirectMessageDao.getInstance();
		
		//�����ִ��� �˻�
		int result = dao.getDMUserStatusList(myMember_no);
		if(result>0) {
			//������ ������
			System.out.println("���ο�� ���� : " + result);
			session.setAttribute("cc_result", result);
			session.setAttribute("main", "/dm/directMain.jsp");
			req.getRequestDispatcher("/layout.jsp").forward(req, resp);
		} else {
			System.out.println("������ �������� ����..");
			session.setAttribute("cc_result", result);
			session.setAttribute("main", "/dm/directMain.jsp");
			req.getRequestDispatcher("/layout.jsp").forward(req, resp);
		}
	}
}
 