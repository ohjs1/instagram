package com.ohjs.dm;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

import com.dao.DmDao;

@ServerEndpoint("/websocket")
public class DirectMessageServer {
	
	@OnOpen
	public void joinUser() {
		System.out.println("클라이언트 접속됨...");
	}
	
	@OnMessage
	public String sendMsg(String msg) {
		System.out.println("클라이언트로부터 받은 메시지 : " + msg);
		String[] arr =msg.split("#");
		String myuser_no =arr[1];
		String youruser_no =arr[2];
		
		boolean chk =false;
		
		DmDao dao =new DmDao();
		
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		
		int n =dao.insert(Integer.parseInt(myuser_no), Integer.parseInt(myuser_no), arr[0], chk);
		
		if(n>0) {
			System.out.println("chatroom insert 성공!");
			return arr[0];
		} else {
			System.out.println("chatroom insert 실패!");
		}
		return null;
	}
	
	@OnClose
	public void disConnected() {
		System.out.println("클라이언트와 접속 끊김..");
	}
	
	@OnError
	public void ErrorMsg(Throwable t) {
		System.out.println(t.getMessage());
		//에러발생시 출력
	}
}
 