package com.vo;

import java.sql.Date;

public class ChatUserlistVo {
	private int member_no;
	private String id;
	private String pwd;
	private String name;
	private String nickname;
	private Date regdate;
	private String profile;
	
	public ChatUserlistVo() {
		
	}

	public ChatUserlistVo(int member_no, String id, String pwd, String name, String nickname, Date regdate,
			String profile) {
		super();
		this.member_no = member_no;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.nickname = nickname;
		this.regdate = regdate;
		this.profile = profile;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public Date getRegdate() {
		return regdate;
	}

	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}
	
}
