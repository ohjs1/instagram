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
		System.out.println("Ŭ���̾�Ʈ ���ӵ�...");
	}
	
	@OnMessage
	public String sendMsg(String msg) {
		System.out.println("Ŭ���̾�Ʈ�κ��� ���� �޽��� : " + msg);
		return msg;
	}
	
	@OnClose
	public void disConnected() {
		System.out.println("Ŭ���̾�Ʈ�� ���� ����..");
	}
	
	@OnError
	public void ErrorMsg(Throwable t) {
		System.out.println(t.getMessage());
		//�����߻��� ���
	}
}
 