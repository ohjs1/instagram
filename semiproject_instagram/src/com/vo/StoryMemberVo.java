package com.vo;

import java.util.Date;

public class StoryMemberVo {
	private int story_no;
	private int member_no;
	private String content;
	private String filepath;
	private Date storydate;
	private String id;
	private String pwd;
	private String name;
	private String nickname;
	private Date regdate;
	private String profile;
	
	public StoryMemberVo() {}
	
	public StoryMemberVo(int story_no, int member_no, String content, String filepath, Date storydate,
			String nickname) {
		super();
		this.story_no = story_no;
		this.member_no = member_no;
		this.content = content;
		this.filepath = filepath;
		this.storydate = storydate;
		this.nickname = nickname;
	}

	public StoryMemberVo(int story_no, int member_no, String content, String filepath, Date storydate, String id,
			String pwd, String name, String nickname, Date regdate, String profile) {
		super();
		this.story_no = story_no;
		this.member_no = member_no;
		this.content = content;
		this.filepath = filepath;
		this.storydate = storydate;
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.nickname = nickname;
		this.regdate = regdate;
		this.profile = profile;
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
