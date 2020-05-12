package com.vo;

import java.sql.Date;

public class BoardVo {
	private int board_no;
	private int member_no;
	private String content;
	private int ref;
	private int lev;
	private int step;
	private Date regdate;
	
	public BoardVo() {}
	public BoardVo(int board_no, int member_no, String content, int ref, int lev, int step, Date regdate) {
		this.board_no = board_no;
		this.member_no = member_no;
		this.content = content;
		this.ref = ref;
		this.lev = lev;
		this.step = step;
		this.regdate = regdate;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
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
	public int getRef() {
		return ref;
	}
	public void setRef(int ref) {
		this.ref = ref;
	}
	public int getLev() {
		return lev;
	}
	public void setLev(int lev) {
		this.lev = lev;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	
}
