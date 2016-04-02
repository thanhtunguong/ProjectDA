package com.doan.model;

public class ItemGhiChu {
	private String maghichu;
	private String title;
	private String thoigiannhac;
	private String thoigianchinhsua;
	private String content;
	
	public ItemGhiChu(){
		 this.maghichu ="maghichu01";
		 this.title = "Tieu de";		 
		 this.thoigiannhac = "2016-03-03 16:30:00";
		 this.thoigianchinhsua = "2016-03-03 16:30:00";
		 this.content = "khong co noi dung";
	 };
	 public ItemGhiChu(String maghichu, String title, String thoigiannhac, String thoigianchinhsua,String content){
		 this.maghichu = maghichu;
		 this.title = title;
		 this.thoigiannhac = thoigiannhac;
		 this.thoigianchinhsua = thoigianchinhsua;
		 this.content = content;
	 }
	
	public String getMaghichu() {
		return maghichu;
	}
	public void setMaghichu(String maghichu) {
		this.maghichu = maghichu;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getThoigiannhac() {
		return thoigiannhac;
	}
	public void setThoigiannhac(String thoigiannhac) {
		this.thoigiannhac = thoigiannhac;
	}
	public String getThoigianchinhsua() {
		return thoigianchinhsua;
	}
	public void setThoigianchinhsua(String thoigianchinhsua) {
		this.thoigianchinhsua = thoigianchinhsua;
	}

}
