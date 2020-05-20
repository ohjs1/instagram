package com.vo;

public class GoodVo {
	private int good_no;
	private int member_no;
	private int board_no;
	
	public GoodVo() {}

	public GoodVo(int good_no, int member_no, int board_no) {
		super();
		this.good_no = good_no;
		this.member_no = member_no;
		this.board_no = board_no;
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
