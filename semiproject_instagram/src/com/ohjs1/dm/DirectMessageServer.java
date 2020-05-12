package com.ohjs1.dm;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

@ServerEndpoint("/websocket")
public class DirectMessageServer {
	@OnOpen
	public void joinUser() {
		System.out.println("클라이언트 접속됨...");
	}
	
	@OnMessage
	public String sendMsg(String msg) {
		System.out.println("클라이언트로부터 받은 메시지 : " + msg);
		return msg;
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
 