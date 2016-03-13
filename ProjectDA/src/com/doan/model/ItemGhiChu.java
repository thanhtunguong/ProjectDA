package com.doan.model;

public class ItemGhiChu {
	private String title;
	private String time;
	private String content;
	
	public ItemGhiChu(){
		 this.title = "TiÃªu Ä‘á»�";
		 this.time = "16:30 25/02/2016";
		 this.content = "KhÃ´ng cÃ³ ná»™i dung ";
	 };
	 public ItemGhiChu(String title, String time,String content){
		 this.title = title;
		 this.time = time;
		 this.content = content;
	 }
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
