package com.vo;

public class LinkVo {
	private int link_no;
	private int board_no;
	private int story_no;
	private int tag_no;
	
	public LinkVo() {}

	public LinkVo(int link_no, int board_no, int story_no, int tag_no) {
		super();
		this.link_no = link_no;
		this.board_no = board_no;
		this.story_no = story_no;
		this.tag_no = tag_no;
	}

	public int getLink_no() {
		return link_no;
	}

	public void setLink_no(int link_no) {
		this.link_no = link_no;
	}

	public int getBoard_no() {
		return board_no;
	}

	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}

	public int getStory_no() {
		return story_no;
	}

	public void setStory_no(int story_no) {
		this.story_no = story_no;
	}

	public int getTag_no() {
		return tag_no;
	}

	public void setTag_no(int tag_no) {
		this.tag_no = tag_no;
	}
	
}
