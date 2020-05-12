package com.vo;

public class ReaduserVo {	
	private int readuser_no;
	private int member_no;
	private int story_no;

	public ReaduserVo() {}

	public ReaduserVo(int readuser_no, int member_no, int story_no) {
		super();
		this.readuser_no = readuser_no;
		this.member_no = member_no;
		this.story_no = story_no;
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
	
	

}
