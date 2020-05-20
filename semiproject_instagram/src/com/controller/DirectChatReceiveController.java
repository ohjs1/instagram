package com.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONObject;

import com.dao.DirectMessageDao;
import com.db.ConnectionPool;
import com.vo.ChatContentVo;

@WebServlet("/dm/chatReceive")
public class DirectChatReceiveController extends HttpServlet {
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int myMember_no = Integer.parseInt( req.getParameter("myMember_no") );
		int yourMember_no = Integer.parseInt( req.getParameter("yourMember_no") );
		
		DirectMessageDao dao = DirectMessageDao.getInstance();
		
		//채팅창 번호 조회
		int chat_no = dao.getChattingRoomNumber(myMember_no, yourMember_no);
		if(chat_no != -1) { //채팅방번호 조회됨
			//유저 채팅 내용 읽기
			ArrayList<ChatContentVo> list = dao.getDmmsgAll(myMember_no, yourMember_no, chat_no);
			
			//프로필 사진 얻어오기
			String myProfile = getProfiles(myMember_no);
			String yourProfile = getProfiles(yourMember_no);
			
			if(list != null) {
				//채팅내용 JSON배열에 담기
				JSONArray jarr =new JSONArray();
				for(ChatContentVo vo : list) {
					JSONObject json =new JSONObject();
					json.put("chatcontent_no", vo.getChatcontent_no());
					json.put("chat_no", vo.getChat_no());
					json.put("smember_no", vo.getSmember_no());
					json.put("rmember_no", vo.getRmember_no());
					json.put("content", vo.getContent());
					json.put("status", vo.isStatus());
					json.put("senddate", vo.getSenddate());
					json.put("myProfile", myProfile);
					json.put("yourProfile", yourProfile);
					
					jarr.put(json);
				}
				
				resp.setContentType("text/plain; charset=utf-8");
				PrintWriter pw =resp.getWriter();
				pw.print(jarr);
			}
		}
	}
	
	private String getProfiles(int member_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionPool.getCon();
			String sql = "select profile from member where member_no = ?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getString("profile");
			}
			return null;
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
}
