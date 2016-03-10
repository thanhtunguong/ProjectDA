package com.doan.model;

import java.util.Date;

public class DayInWeek {
	String dayName;
	Date dayDate;
	int numberActive;
	int numberTotal;
	int mau;
	
	
	
	public int getMau() {
		return mau;
	}

	public void setMau(int mau) {
		this.mau = mau;
	}

	public DayInWeek(Date daydate, String dayname, int numbertotal, int mau){
		this.dayName = dayname;
		this.dayDate = daydate;
		this.numberTotal = numbertotal;
		this.mau = mau;
	}
	
	public String getDayName() {
		return dayName;
	}
	public void setDayName(String dayName) {
		this.dayName = dayName;
	}
	public Date getDayDate() {
		return dayDate;
	}
	public void setDayDate(Date dayDate) {
		this.dayDate = dayDate;
	}
	public int getNumberActive() {
		return numberActive;
	}
	public void setNumberActive(int numberActive) {
		this.numberActive = numberActive;
	}
	public int getNumberTotal() {
		return numberTotal;
	}
	public void setNumberTotal(int numberTotal) {
		this.numberTotal = numberTotal;
	}	
}
