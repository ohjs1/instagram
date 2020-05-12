package com.vo;

public class ImageVo {
	private int image_no;
	private int board_no;
	private String imagepath;
	public ImageVo() {}
	public ImageVo(int image_no, int board_no, String imagepath) {
		this.image_no = image_no;
		this.board_no = board_no;
		this.imagepath = imagepath;
	}
	public int getImage_no() {
		return image_no;
	}
	public void setImage_no(int image_no) {
		this.image_no = image_no;
	}
	public int getBoard_no() {
		return board_no;
	}
	public void setBoard_no(int board_no) {
		this.board_no = board_no;
	}
	public String getImagepath() {
		return imagepath;
	}
	public void setImagepath(String imagepath) {
		this.imagepath = imagepath;
	}
}
