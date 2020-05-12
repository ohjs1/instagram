package com.vo;

import java.util.Date;

public class StoryVo {
	private int story_no;
	private int memver_no;
	private String content;
	private String filepath;
	private Date storydate;
	
	public StoryVo() {}
	
	public StoryVo(int story_no, int memver_no, String content, String filepath, Date storydate) {
		super();
		this.story_no = story_no;
		this.memver_no = memver_no;
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
	public int getMemver_no() {
		return memver_no;
	}
	public void setMemver_no(int memver_no) {
		this.memver_no = memver_no;
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
