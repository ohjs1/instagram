package com.vo;

import java.sql.Date;

public class ReaduserMemberVo {
	private int readuser_no;
	private int member_no;
	private int story_no;
	private String nickname;
	private String profile;
	public ReaduserMemberVo() {}
	
	
	
	public ReaduserMemberVo(int readuser_no, int member_no, int story_no, String nickname, String profile) {
		super();
		this.readuser_no = readuser_no;
		this.member_no = member_no;
		this.story_no = story_no;
		this.nickname = nickname;
		this.profile = profile;
	}



	public int getReaduser_no() {
		return readuser_no;
	}
	public void setReaduser_no(int readuser_no) {
		this.readuser_no = readuser_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getStory_no() {
		return story_no;
	}
	public void setStory_no(int story_no) {
		this.story_no = story_no;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}

}
