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
	
	
	//채팅방 생성하기
	public int createChattingRoom(int mymember_no, int yourmember_no) {
		Connection con =null;
		PreparedStatement pstmt =null;
		
		try {
			con =ConnectionPool.getCon();
			String sql ="insert into chatroom values(chatroom_seq.nextval, ?, ?, sysdate)";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, mymember_no);
			pstmt.setInt(2, yourmember_no);
			
			System.out.println("채팅방 생성하기!");
			System.out.println("mymember_no:" + mymember_no);
			System.out.println("yourmember_no:" + yourmember_no);
			
			return pstmt.executeUpdate();
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return -1;
		} finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}
	
	//채팅방 번호 조회
	public int getChattingRoomNumber(int mymember_no, int youmember_no) {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con =ConnectionPool.getCon();
			String sql ="select chatroom_no from chatroom where (mymember_no=? and youmember_no=?) or (mymember_no=? and youmember_no=?)";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, mymember_no);
			pstmt.setInt(2, youmember_no);
			pstmt.setInt(3, youmember_no);
			pstmt.setInt(4, mymember_no);
			
			rs =pstmt.executeQuery();
			
			if(rs.next()) {
//				System.out.println("채팅방번호" + rs.getInt("chatroom_no"));
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
	
	//채팅방이 존재하는지 확인하기
	public int isChattingRoom(int mymember_no, int youmember_no) {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con =ConnectionPool.getCon();
			String sql ="select count(*) cnt from chatroom where (mymember_no=? and youmember_no=?) or (mymember_no=? and youmember_no=?)";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, mymember_no);
			pstmt.setInt(2, youmember_no);
			pstmt.setInt(3, youmember_no);
			pstmt.setInt(4, mymember_no);
			rs =pstmt.executeQuery();
			
			if(rs.next()) {
				int cnt = rs.getInt("cnt");
				
				if(cnt > 0) {
					return cnt;
				}
			}
			return -1;
			
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return -1;
		} finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}
	
	//DM유저목록 가져오기
	public ArrayList<MemberVo> getDMUserList(int mymember_no){
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		
		try {
			con = ConnectionPool.getCon();
			String sql = "select * from chatroom where mymember_no=? or youmember_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mymember_no);
			pstmt.setInt(2, mymember_no);
			rs = pstmt.executeQuery();
			/*
			 * int member_no, String id, String pwd, String name, String nickname, Date regdate,
			String profile
			 */
			ArrayList<MemberVo> list = new ArrayList<MemberVo>();
			while(rs.next()) {
				//상대 번호 구하기
				int m_num = rs.getInt("mymember_no");
				int y_num = rs.getInt("youmember_no");
				
				
				int member_no = 0;

				if(m_num == mymember_no) {
					member_no = y_num;
				} else {
					member_no = m_num;
				}
				
				MemberVo vo = getYourMember(member_no);
				
				list.add(vo);

			}
			return list;
			
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	//DM유저목록 채팅상태값 
		public int getDMUserStatusList(int mymember_no){
			Connection con =null;
			PreparedStatement pstmt =null;
			ResultSet rs =null;
			
			
			try {
				con = ConnectionPool.getCon();
				String sql = "select * from chatroom where mymember_no=? or youmember_no=?";
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, mymember_no);
				pstmt.setInt(2, mymember_no);
				rs = pstmt.executeQuery();
				/*
				 * int member_no, String id, String pwd, String name, String nickname, Date regdate,
				String profile
				 */
				
				int status = 0;
				while(rs.next()) {
					//상대 번호 구하기
					int m_num = rs.getInt("mymember_no");
					int y_num = rs.getInt("youmember_no");
					
					
					int member_no = 0;

					if(m_num == mymember_no) {
						member_no = m_num;
					} else {
						member_no = y_num;
					}
//					System.out.println("채팅상태값 유저 번호 : " + member_no);
//					System.out.println("채팅상태값 채팅번호 : " + getChattingRoomNumber(m_num, y_num));
					status += getStatusChat(member_no, getChattingRoomNumber(m_num, y_num));

				}
				return status;
				
			} catch(SQLException s) {
				System.out.println(s.getMessage());
				return -1;
			} finally {
				ConnectionPool.close(con, pstmt, rs);
			}
		}
	
	//채팅방 읽음으로 표시
	public int setStatusChatRoom(int rmember_no, int chat_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try {
			con = ConnectionPool.getCon();
			String sql = "update chatcontent set status=0 where rmember_no=? and chat_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rmember_no);
			pstmt.setInt(2, chat_no);
			
			
			return pstmt.executeUpdate();
			
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return -1;
		} finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}
	
	//상대 번호 구하기
	public MemberVo getYourMember(int member_no) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = ConnectionPool.getCon();
			String sql = "select * from member where member_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, member_no);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				MemberVo vo = new MemberVo(
						rs.getInt("member_no"),
						rs.getString("id"),
						rs.getString("pwd"),
						rs.getString("name"),
						rs.getString("nickname"),
						rs.getDate("regdate"),
						rs.getString("profile"));
				return vo;
			}
			return null;
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	
	//채팅방 내용을 DB에 저장
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
			
			int n =pstmt.executeUpdate();
			
			if(n>0) {
				System.out.println("채팅내용 DB 저장 성공!");
			} else {
				System.out.println("DB저장 실패");
				return -1;
			}
			return -1;
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return -1;
		} finally {
			ConnectionPool.close(con, pstmt, null);
		}
	}
	
	//채팅방 상태값 읽어와 새글 있는지 검사
	public int getStatusChat(int rmember_no, int chat_no) {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con = ConnectionPool.getCon();
			String sql = "select sum(status) sums from chatcontent where rmember_no=? and chat_no=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, rmember_no);
			pstmt.setInt(2, chat_no);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				return rs.getInt("sums");
			}
			return -1;
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return -1;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	
	//검색된 유저를 구하는 메소드
	public ArrayList<MemberVo> getUserList(String keyword, int myMember_no){
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
//				System.out.println("myMember_no : " + myMember_no);
				
				if(myMember_no != member_no) {
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
	
	//팔로우, 팔로잉 유저 검색
	public MemberVo getUserList(int myMember_no){
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con =ConnectionPool.getCon();
			String sql ="select * from member where member_no = ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, myMember_no);
			rs =pstmt.executeQuery();
			
			
			if(rs.next()) {
				//int member_no, String id, String pwd, String name, String nickname, Date regdate, String profile
				int member_no =rs.getInt("member_no");
				String id =rs.getString("id");
				String name =rs.getString("name");
				String nickname =rs.getString("nickname");
				String profile =rs.getString("profile");
//				System.out.println("myMember_no : " + myMember_no);
				
				MemberVo vo =new MemberVo(member_no, id, null, name, nickname, null, profile);
				return vo;
			} else {
				return null;
			}
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	//유저 닉네임 가져옴
	public String getUserNickName(int yourMember_no){
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con =ConnectionPool.getCon();
			String sql ="SELECT nickname FROM member WHERE member_no = ?";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, yourMember_no);
			rs =pstmt.executeQuery();
			
			
			if(rs.next()) {
				String nickname =rs.getString("nickname");
				return nickname;
			} else {
				return null;
			}
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	
	//유저의 채팅내용을 불러와 보여주는 메소드
	public ArrayList<ChatContentVo> getChatShow(int chat_no){
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con =ConnectionPool.getCon();
			String sql ="select * from chatcontent where chat_no=? order by chatcontent_no asc";
			pstmt =con.prepareStatement(sql);
			pstmt.setInt(1, chat_no);
			rs =pstmt.executeQuery();
			
			ArrayList<ChatContentVo> list =new ArrayList<ChatContentVo>();
			
			//int chatcontent_no, int chat_no, int smember_no, int rmember_no, String content,
			//boolean status, Date senddate
			while(rs.next()) {
				ChatContentVo vo =new ChatContentVo(
						rs.getInt("chatcontent_no"),
						rs.getInt("chat_no"),
						rs.getInt("smember_no"),
						rs.getInt("rmember_no"),
						rs.getString("content"),
						rs.getBoolean("status"),
						rs.getDate("senddate")
						);
				
				list.add(vo);
			}
			return list;
			
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	//유저간 DM 메시지 불러오기
	public ArrayList<ChatContentVo> getDmmsgAll(int mymemberId, int youmemberId, int chat_no){
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con = ConnectionPool.getCon();
			String sql = "SELECT chct.* FROM( \r\n" + 
					"        SELECT * FROM chatcontent WHERE (smember_no=? and rmember_no=?) or (smember_no=? and rmember_no=?) ORDER BY chatcontent_no ASC \r\n" + 
					"     )chct\r\n" + 
					"    WHERE chat_no=?";

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, mymemberId);
			pstmt.setInt(2, youmemberId);
			pstmt.setInt(3, youmemberId);
			pstmt.setInt(4, mymemberId);
			pstmt.setInt(5, chat_no);
			/*
			 * int chatcontent_no, int chat_no, int smember_no, int rmember_no, String content,
			boolean status, Date senddate
			 */
			rs = pstmt.executeQuery();
			ArrayList<ChatContentVo> list = new ArrayList<ChatContentVo>();
			while( rs.next() ) {
				ChatContentVo vo = new ChatContentVo(
						rs.getInt("chatcontent_no"),
						rs.getInt("chat_no"),
						rs.getInt("smember_no"),
						rs.getInt("rmember_no"),
						rs.getString("content"),
						rs.getBoolean("status"),
						rs.getDate("senddate"));
				
				list.add(vo);
			}
			return list;
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return null;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	//채팅방 유저수 검사
	public int searchRoomUserCnt(int chat_no, int rmember_no) {
		Connection con =null;
		PreparedStatement pstmt =null;
		ResultSet rs =null;
		
		try {
			con =ConnectionPool.getCon();
			String sql ="select count(*) cnt from chatcontent where chat_no= ? and rmember_no=?";
			pstmt =con.prepareStatement(sql);
			
			pstmt.setInt(1, chat_no);
			pstmt.setInt(2, rmember_no);
			rs =pstmt.executeQuery();
			
			int cnt =0;
			if(rs.next()) {
				cnt =rs.getInt("cnt");
			}
			
			return cnt;
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return -1;
		} finally {
			ConnectionPool.close(con, pstmt, rs);
		}
	}
	
	//DM삭제 하는 기능
	public int delDirectMessage(int myMember_no, int yourMember_no) { //내번호 상대번호로 DM삭제
		Connection con =null;
		PreparedStatement pstmt1 =null;
		PreparedStatement pstmt2 =null;
		PreparedStatement pstmt3 =null;
		ResultSet rs = null;
		
		try {
			con =ConnectionPool.getCon();
			
			con.setAutoCommit(false);//트랜젝션 시작
			
			//채팅방 번호 알아내기
			String sql1 ="select chatroom_no from chatroom where "
					+ "(mymember_no=? and youmember_no=?) or (mymember_no=? and youmember_no=?)";
			pstmt1 =con.prepareStatement(sql1);
			pstmt1.setInt(1, myMember_no);
			pstmt1.setInt(2, yourMember_no);
			pstmt1.setInt(3, yourMember_no);
			pstmt1.setInt(4, myMember_no);
			rs = pstmt1.executeQuery();
			
			int chat_no = 0;
			int n = 0;
			if(rs.next()) {
				chat_no = rs.getInt("chatroom_no");
				
				String sql2 = "delete from chatcontent where chat_no=?";
				pstmt2 = con.prepareStatement(sql2);
				pstmt2.setInt(1, chat_no);
				
				pstmt2.executeUpdate();
				
				String sql3 = "delete from chatroom where chatroom_no=?";
				pstmt3 = con.prepareStatement(sql3);
				pstmt3.setInt(1, chat_no);
				n = pstmt3.executeUpdate();
				
				con.commit(); //커밋완료
				System.out.println("DM삭제 완료함");
				return n;
			} else {
				con.rollback(); //커밋실패 롤백
				System.out.println("DM삭제 실패함");
				return -1;
			}
			
		} catch(SQLException s) {
			System.out.println(s.getMessage());
			return -1;
			
		} finally {
			ConnectionPool.close(pstmt1);
			ConnectionPool.close(pstmt2);
			ConnectionPool.close(pstmt3);
			ConnectionPool.close(rs);
			ConnectionPool.close(con);
		}
	}
}
