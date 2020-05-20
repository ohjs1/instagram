package com.vo;

public class Tag_linkVo {
	private String search;
	private int board_cnt;//게시물수
	
	public Tag_linkVo() {}

	public Tag_linkVo(String search, int board_cnt) {
		this.search = search;
		this.board_cnt = board_cnt;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	public int getBoard_cnt() {
		return board_cnt;
	}

	public void setBoard_cnt(int board_cnt) {
		this.board_cnt = board_cnt;
	}
	
	
}
