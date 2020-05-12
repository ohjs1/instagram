package com.vo;

public class TagVo {
	private int tag_no;
	private int search;
	
	public TagVo() {}

	public TagVo(int tag_no, int search) {
		super();
		this.tag_no = tag_no;
		this.search = search;
	}

	public int getTag_no() {
		return tag_no;
	}

	public void setTag_no(int tag_no) {
		this.tag_no = tag_no;
	}

	public int getSearch() {
		return search;
	}

	public void setSearch(int search) {
		this.search = search;
	}
	
	
	
}
