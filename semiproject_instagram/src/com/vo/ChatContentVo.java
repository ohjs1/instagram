package com.vo;

import java.sql.Date;

public class ChatContentVo {
	private int chatcontent_no;
	private int chat_no;
	private int smember_no;
	private int rmember_no;
	private String content;
	private boolean status;
	private Date senddate;
	
	public ChatContentVo() {
		
	}

	public ChatContentVo(int chatcontent_no, int chat_no, int smember_no, int rmember_no, String content,
			boolean status, Date senddate) {
		super();
		this.chatcontent_no = chatcontent_no;
		this.chat_no = chat_no;
		this.smember_no = smember_no;
		this.rmember_no = rmember_no;
		this.content = content;
		this.status = status;
		this.senddate = senddate;
	}

	public int getChatcontent_no() {
		return chatcontent_no;
	}

	public void setChatcontent_no(int chatcontent_no) {
		this.chatcontent_no = chatcontent_no;
	}

	public int getChat_no() {
		return chat_no;
	}

	public void setChat_no(int chat_no) {
		this.chat_no = chat_no;
	}

	public int getSmember_no() {
		return smember_no;
	}

	public void setSmember_no(int smember_no) {
		this.smember_no = smember_no;
	}

	public int getRmember_no() {
		return rmember_no;
	}

	public void setRmember_no(int rmember_no) {
		this.rmember_no = rmember_no;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Date getSenddate() {
		return senddate;
	}

	public void setSenddate(Date senddate) {
		this.senddate = senddate;
	}
	
	
}
