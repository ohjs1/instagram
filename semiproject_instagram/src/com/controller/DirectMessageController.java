package com.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.DirectMessageDao;
import com.db.ConnectionPool;
import com.vo.ChatContentVo;


@WebServlet("/dm/dmmsg")
public class DirectMessageController extends HttpServlet {
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		
		String content = req.getParameter("content");
		int myMember_no = Integer.parseInt( req.getParameter("myMember_no") );
		int yourMember_no = Integer.parseInt( req.getParameter("yourMember_no") );
		 
//		System.out.println(content);
//		System.out.println(myMember_no);
//		System.out.println(yourMember_no);
		
		DirectMessageDao dao = DirectMessageDao.getInstance();
		//ä�ù� ���翩�� �˻�
		int cnt = dao.isChattingRoom(myMember_no);
		int result = createChatRoom(myMember_no, yourMember_no, cnt);
		
		if(result>0) {
			System.out.println("ä�ù� ���� ����!");
		}
		
		/*
		 * int chatcontent_no, int chat_no, int smember_no, int rmember_no, String content,
			boolean status, Date senddate
		 */
		
		//ä�ù� ��ȣ ��ȸ
		int chat_num = dao.getChattingRoomNumber(myMember_no);
		
		//ä�ó��� DB�� �ֱ�
		ChatContentVo vo = new ChatContentVo(0, chat_num, myMember_no, yourMember_no, content, false, null);
		dao.saveChatDatabase(vo);
	}
	
	
	//ä�ù� �����ϴ� ���θ޼ҵ�
	private int createChatRoom(int myMember_no, int yourMember_no, int cnt) {
		Connection con = null;
		PreparedStatement pstmt_createRoom = null;
		
		
		if(cnt == -1) { //�������������� ä�ù� ����
			try {
				con = ConnectionPool.getCon();
				String sql_cr = "INSERT INTO chatroom(chatroom_seq.NEXTVAL, mymember_no = ?, youmember_no = ?)";
				pstmt_createRoom = con.prepareStatement(sql_cr);
				pstmt_createRoom.setInt(1, myMember_no);
				pstmt_createRoom.setInt(2, yourMember_no);
				
				return pstmt_createRoom.executeUpdate();
				
			} catch(SQLException s) {
				System.out.println(s.getMessage());
				return -1;
			} finally {
				ConnectionPool.close(con, pstmt_createRoom, null);
			}
		} else {
			return -1;
		}
	}
}
