package com.doan.model;

public class ItemGhiChu {
	private String title;
	private int day;
	private int month;
	private int year;
	private int hour;
	private int minute;	
	private String content;
	
	public ItemGhiChu(){
		 this.title = "Tiêu đề";		 
		 this.day = 25;
		 this.month = 02;
		 this.year = 2016;
		 this.hour = 16;
		 this.minute = 30;
		 this.content = "Không có nội dung ";
	 };
	 public ItemGhiChu(String title, int hour, int minute , int day, int month, int year,String content){
		 this.title = title;
		 this.day = day;
		 this.month = month;
		 this.year = year;
		 this.hour = hour;
		 this.minute = minute;
		 this.content = content;
	 }
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public int getMonth() {
		return month;
	}
	public void setMonth(int month) {
		this.month = month;
	}
	public int getYear() {
		return year;
	}
	public void setYear(int year) {
		this.year = year;
	}
	public int getHour() {
		return hour;
	}
	public void setHour(int hour) {
		this.hour = hour;
	}
	public int getMinute() {
		return minute;
	}
	public void setMinute(int minute) {
		this.minute = minute;
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
	public String getTime(){
		String str = "";
		if (getHour() < 10) {
			str += "0" + getHour();
		} else {
			str += getHour();
		}
		if (getMinute() < 10) {
			str += ":0" + getMinute();	
		} else {
			str += ":" + getMinute();
		}
		str += " ";
		if (getDay() < 10) {
			str += "0" + getDay();
		} else {
			str += getHour();
		}
		if (getMonth() < 10) {
			str += "/0" + getMonth();	
		} else {
			str += "/" +getMonth();
		}
		str += "/" + getYear();
	
		return str;
		
	}
}
