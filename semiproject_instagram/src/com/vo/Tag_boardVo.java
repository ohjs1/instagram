package com.vo;

public class Tag_boardVo {
	private int member_no;
	private String name;
	private String nickname;
	private String profile;
	private int cnt; //게시물수
	private String search;
	
	public Tag_boardVo() {}
	
	public Tag_boardVo(int member_no, String name, String nickname, String profile, int cnt, String search) {
		this.member_no = member_no;
		this.name = name;
		this.nickname = nickname;
		this.profile = profile;
		this.cnt = cnt;
		this.search = search;
	}

	public int getMember_no() {
		return member_no;
	}

	public void setMember_no(int member_no) {
		this.member_no = member_no;
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

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public int getCnt() {
		return cnt;
	}

	public void setCnt(int cnt) {
		this.cnt = cnt;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
	
	
	
}
