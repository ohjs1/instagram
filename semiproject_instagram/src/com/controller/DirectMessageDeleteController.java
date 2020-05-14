package com.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DirectMessageDao;

@WebServlet("/dm/delete")
public class DirectMessageDeleteController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		DirectMessageDao dao =DirectMessageDao.getInstance();
		int chat_no =Integer.parseInt(req.getParameter("chatroomNumber"));
		int r =dao.delDirectMessageRoomNumber(chat_no);
		
		if(r>0) {
			System.out.println("채팅방 삭제가 잘되었습니다.");
		} else {
			System.out.println("채팅방 삭제 실패..");
		}
	}
}
