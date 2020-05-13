package com.direct.server;

import java.sql.Date;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.dao.DirectMessageDao;
import com.vo.ChatContentVo;

@ServerEndpoint("/websocket")
public class DirectMessageServer {
	
	@OnOpen
	public void joinUser(Session session) {
		System.out.println("Ŭ���̾�Ʈ ���ӵ�...");
		System.out.println(session.getId() + " ���ӵ� ������� ���� ���̵�");
	}
	
	@OnMessage
	public String sendMsg(String msg) {
		System.out.println("Ŭ���̾�Ʈ�κ��� ���� �޽��� : " + msg);
		String[] arr =msg.split("#");
		String myuser_no =arr[1];
		String youruser_no =arr[2];
		
		boolean chk =false;
		
		DirectMessageDao dao =DirectMessageDao.getInstance();
		
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		
		//ä�ù� ���翩�� �˻�
		int checkCroom =dao.isChattingRoom(Integer.parseInt(myuser_no), Integer.parseInt(youruser_no));
		if(checkCroom==1) {
			//ä�ù� ����
			int result =dao.createChattingRoom(Integer.parseInt(myuser_no), Integer.parseInt(youruser_no));
			
			//ä�ù� ��ȣ ��ȸ
			int cNum =dao.getChattingRoomNumber(Integer.parseInt(myuser_no), Integer.parseInt(youruser_no));
			
			//ä�ó��� DB�� ����
			//int chatcontent_no, int chat_no, int smember_no, int rmember_no, String content,boolean status, Date senddate
			ChatContentVo vo =new ChatContentVo(
					0,
					cNum,
					Integer.parseInt(myuser_no),
					Integer.parseInt(youruser_no),
					arr[0],
					false,
					null
					);
			
			dao.saveChatDatabase(vo);
		} else {
			//ä�ù� ��ȣ ��ȸ
			int cNum =dao.getChattingRoomNumber(Integer.parseInt(myuser_no), Integer.parseInt(youruser_no));
			
			//ä�ó��� DB�� ����
			//int chatcontent_no, int chat_no, int smember_no, int rmember_no, String content,boolean status, Date senddate
			ChatContentVo vo =new ChatContentVo(
					0,
					cNum,
					Integer.parseInt(myuser_no),
					Integer.parseInt(youruser_no),
					arr[0],
					false,
					null
					);
			
			dao.saveChatDatabase(vo);
		}
		
		return arr[0]; //ä�ó��� �ǵ�����
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
 