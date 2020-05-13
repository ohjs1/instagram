package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.db.ConnectionPool;
import com.vo.ChatContentVo;
import com.vo.MemberVo;

public class DirectMessageDao {
	private DirectMessageDao() {}
	private static DirectMessageDao dmdao =null;
	public static DirectMessageDao getInstance() {
		dmdao =new DirectMessageDao();
		return dmdao;
	}
	
	
	//ä�ù� �����ϱ�
	public int createChattingRoom(int mymember_no, int yourmember_no) {
		Connection con =null;
		PreparedStatement pstmt =null;
		
		try {
			con =ConnectionPool.getCon();
			String sql ="insert into chatroom values(chatroom_seq.nextval, ?, ?, sysdate)";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, mymember_no);
			pstmt.setInt(2, yourmember_no);
			return pstmt.executeUpdate();
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return -1;
		} finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}
	
	//ä�ù� ��ȣ ��ȸ
	public int getChattingRoomNumber(int mymember_no, int yourmember_no) {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con =ConnectionPool.getCon();
			String sql ="select chatroom_no from chatroom where mymember_no=? and youmember_no=?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, mymember_no);
			pstmt.setInt(2, yourmember_no);
			
			rs =pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("chatroom_no");
			} else {
				return -1;
			}
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return -1;
		} finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}
	
	//ä�ù��� �����ϴ��� Ȯ���ϱ�
	public int isChattingRoom(int mymember_no, int yourmember_no) {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con =ConnectionPool.getCon();
			String sql ="select chatroom_no from chatroom where mymember_no=? and youmember_no=?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, mymember_no);
			pstmt.setInt(2, yourmember_no);
			rs =pstmt.executeQuery();
			
			if(rs.next()) {
				System.out.println("�̹� �����ϴ� ä�÷� �Դϴ�.");
				return -1;
			} else {
				return 1;
			}
			
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return -1;
		} finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}
	
	//ä�ù� ������ DB�� ����
	public int saveChatDatabase(ChatContentVo vo) {
		Connection con =null;
		PreparedStatement pstmt =null;
		
		try {
			con =ConnectionPool.getCon();
//int chatcontent_no, int chat_no, int smember_no, int rmember_no, String content,boolean status, Date senddate
			String sql ="insert into chatcontent values(chatcontent_seq.nextval, ?, ?, ?, ?, ?, sysdate)";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, vo.getChat_no());
			pstmt.setInt(2, vo.getSmember_no());
			pstmt.setInt(3, vo.getRmember_no());
			pstmt.setString(4, vo.getContent());
			pstmt.setBoolean(5, vo.isStatus());
			
			System.out.println("ä�ó��� DB ���� ����!");
			return pstmt.executeUpdate();
			
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return -1;
		} finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}
	
	//�˻��� ������ ���ϴ� �޼ҵ�
	public ArrayList<MemberVo> getUserList(String keyword, int mymember_no){
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con =ConnectionPool.getCon();
			String sql ="select * from member where id like ? or name like ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setString(1, "%" + keyword + "%");
			pstmt.setString(2, "%" + keyword + "%");
			rs =pstmt.executeQuery();
			
			ArrayList<MemberVo> list =new ArrayList<MemberVo>();
			
			while(rs.next()) {
				//int member_no, String id, String pwd, String name, String nickname, Date regdate, String profile
				int member_no =rs.getInt("member_no");
				String id =rs.getString("id");
				String name =rs.getString("name");
				String nickname =rs.getString("nickname");
				String profile =rs.getString("profile");
				
				if(mymember_no != member_no) {
					list.add(new MemberVo(member_no, id, null, name, nickname, null, profile));
				}
				
			}
			return list;
			
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	//������ ä�ó����� �ҷ��� �����ִ� �޼ҵ�
//	public ArrayList<MemberVo> getChatShow(int chat_no){
//		Connection con =null;
//		PreparedStatement pstmt =null;
//		ResultSet rs =null;
//		
//		try {
//			con =ConnectionPool.getCon();
//			String sql ="select * from chatcontent where chat_no =?";
//			pstmt =con.prepareStatement(sql);
//			pstmt.setInt(1, chat_no);
//			rs =pstmt.executeQuery();
//			
//			ArrayList<MemberVo> list =new ArrayList<MemberVo>();
//			
//			while(rs.next()) {
//				
//			}
//			
//		} catch(SQLException s) {
//			System.out.println(s.getMessage());
//		} finally {
//			ConnectionPool.close(con, pstmt, rs);
//		}
//	}
}
