package com.vo;

public class Good_MemberVo {
	private String id;
	private String pwd;
	private String name;
	private String nickname;
	private String profile;
	private int good_no;
	private int member_no;
	private int board_no;
	public Good_MemberVo() {}
	public Good_MemberVo(String id, String pwd, String name, String nickname, String profile, int good_no,
			int member_no, int board_no) {
		super();
		this.id = id;
		this.pwd = pwd;
		this.name = name;
		this.nickname = nickname;
		this.profile = profile;
		this.good_no = good_no;
		this.member_no = member_no;
		this.board_no = board_no;
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
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	public int getGood_no() {
		return good_no;
	}
	public void setGood_no(int good_no) {
		this.good_no = good_no;
	}
	public int getMember_no() {
		return member_no;
	}
	public void setMember_no(int member_no) {
		this.member_no = member_no;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	
	
}
