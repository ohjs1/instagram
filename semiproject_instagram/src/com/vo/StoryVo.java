package com.vo;

import java.util.Date;

public class StoryVo {
	private int story_no;
	private int member_no;
	private String content;
	private String filepath;
	private Date storydate;
	
	public StoryVo() {}
	
	public StoryVo(int story_no, int member_no, String content, String filepath, Date storydate) {
		super();
		this.story_no = story_no;
		this.member_no = member_no;
		this.content = content;
		this.filepath = filepath;
		this.storydate = storydate;
	}

	public int getStory_no() {
		return story_no;
	}
	public void setStory_no(int story_no) {
		this.story_no = story_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFilepath() {
		return filepath;
	}
	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}
	public Date getStorydate() {
		return storydate;
	}
	public void setStorydate(Date storydate) {
		this.storydate = storydate;
	}
	
	
	

}
