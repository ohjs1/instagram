package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dao.DirectMessageDao;
import com.vo.ChatContentVo;

@WebServlet("/dm/dmmsg")
public class DirectMessageController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		int myuser_no =Integer.parseInt(req.getParameter("myuser_no"));
		int your_member_no =Integer.parseInt(req.getParameter("your_member_no"));
		String content =req.getParameter("content");
		
//		System.out.println(content);
//		System.out.println(myuser_no);
//		System.out.println(your_member_no);
		
		DirectMessageDao dao =DirectMessageDao.getInstance();
		//채팅방 존재여부 검사
		int checkCroom =dao.isChattingRoom(myuser_no);
		if(checkCroom==1) {
			//채팅방 생성
			int result =dao.createChattingRoom(myuser_no, your_member_no);
			if(result>0) {
				System.out.println("채팅방 새로생성 됨!");
			} else {
				System.out.println("채팅방 생성 실패..");
				return;
			}
		} 
		
		//채팅방 번호 조회
		int cNum =dao.getChattingRoomNumber(myuser_no);
		System.out.println("채팅방 번호 : " + cNum);
		//채팅내용 DB에 저장
		ChatContentVo vo =new ChatContentVo(
				0,
				cNum,
				myuser_no,
				your_member_no,
				content,
				false,
				null);
		
		dao.saveChatDatabase(vo);
		
		//채팅내용가져와 JSON배열에 담기
		int chat_no =dao.getChattingRoomNumber(myuser_no); //채팅방 번호
		ArrayList<ChatContentVo> list =dao.getChatShow(chat_no);
		
		JSONArray jarr =new JSONArray();
		for(ChatContentVo chatvo : list) {
			JSONObject json =new JSONObject();
			
			json.put("chatcontent_no", chatvo.getChatcontent_no());
			json.put("chat_no", chatvo.getChat_no());
			json.put("smember_no", chatvo.getSmember_no());
			json.put("rmember_no", chatvo.getRmember_no());
			json.put("content", chatvo.getContent());
			json.put("status", chatvo.isStatus());
			json.put("senddate", chatvo.getSenddate());
			
			jarr.put(json);
		}
		
		resp.setContentType("text/plain; charset=utf-8");
		PrintWriter pw =resp.getWriter();
		pw.print(jarr);
	}
}
