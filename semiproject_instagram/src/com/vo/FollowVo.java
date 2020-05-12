package com.vo;

import java.sql.Date;

public class FollowVo {
	private int follow_no;
	private int mymember_no;
	private int youmember_no;
	private Date followingdate;
	
	public FollowVo() {};
	
	public FollowVo(int follow_no, int mymember_no, int youmember_no, Date followingdate) {
		super();
		this.follow_no = follow_no;
		this.mymember_no = mymember_no;
		this.youmember_no = youmember_no;
		this.followingdate = followingdate;
	}

	public int getFollow_no() {
		return follow_no;
	}

	public void setFollow_no(int follow_no) {
		this.follow_no = follow_no;
	}

	public int getMymember_no() {
		return mymember_no;
	}

	public void setMymember_no(int mymember_no) {
		this.mymember_no = mymember_no;
	}

	public int getYoumember_no() {
		return youmember_no;
	}

	public void setYoumember_no(int youmember_no) {
		this.youmember_no = youmember_no;
	}

	public Date getFollowingdate() {
		return followingdate;
	}

	public void setFollowingdate(Date followingdate) {
		this.followingdate = followingdate;
	}
	
	
}
