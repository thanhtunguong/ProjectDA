package com.doan.model;

import java.io.Serializable;
import java.util.ArrayList;

public class LopHanhChinh implements Serializable{
	private String MaLopHanhChinh;
	private ArrayList<SinhVienThongBao> arrSinhVien;
	private int check;
	
	
	public int getCheck() {
		return check;
	}
	public void setCheck(int check) {
		this.check = check;
	}
	public String getMaLopHanhChinh() {
		return MaLopHanhChinh;
	}
	public void setMaLopHanhChinh(String maLopHanhChinh) {
		MaLopHanhChinh = maLopHanhChinh;
	}
	public ArrayList<SinhVienThongBao> getArrSinhVien() {
		return arrSinhVien;
	}
	public void setArrSinhVien(ArrayList<SinhVienThongBao> arrSinhVien) {
		this.arrSinhVien = arrSinhVien;
	}
	
}
