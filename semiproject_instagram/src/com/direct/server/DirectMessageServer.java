package com.direct.server;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.server.ServerEndpoint;

import com.dao.DirectMessageDao;
import com.vo.ChatContentVo;

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
		
		DirectMessageDao dao =DirectMessageDao.getInstance();
		
		System.out.println(arr[0]);
		System.out.println(arr[1]);
		System.out.println(arr[2]);
		
		//채팅방 존재여부 검사
		int checkCroom =dao.isChattingRoom(Integer.parseInt(myuser_no));
		if(checkCroom==1) {
			//채팅방 생성
			int result =dao.createChattingRoom(Integer.parseInt(myuser_no), Integer.parseInt(youruser_no));
			if(result>0) {
				System.out.println("채팅방 새로생성 됨!");
			} else {
				System.out.println("채팅방 생성 실패..");
			}
			
			//채팅방 번호 조회
			int cNum =dao.getChattingRoomNumber(Integer.parseInt(myuser_no));
			System.out.println("채팅방 번호 : " + cNum);
			//채팅내용 DB에 저장
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
			return arr[0]; //채팅내용 되돌려줌
		} else {
			//채팅방 번호 조회
			int cNum =dao.getChattingRoomNumber(Integer.parseInt(myuser_no));
			System.out.println("채팅방 번호 : " + cNum);
			//채팅내용 DB에 저장
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
			return arr[0]; //채팅내용 되돌려줌
		}
		
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
 