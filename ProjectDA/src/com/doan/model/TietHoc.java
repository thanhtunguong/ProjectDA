package com.doan.model;

import java.util.Date;

public class TietHoc {
	int BuoiHoc;
	Date SpecificDate;
	String MonHoc;
	String PhongHoc;
	int TrangThai;
	
	public TietHoc(){
		
	}
	
	public TietHoc(int buoihoc, Date spec, String monhoc, String phonghoc, int trangthai){
		this.BuoiHoc = buoihoc;
		this.SpecificDate = spec;
		this.MonHoc = monhoc;
		this.PhongHoc = phonghoc;
		this.TrangThai = trangthai;
	}
	
	public Date getSpecificDate() {
		return SpecificDate;
	}

	public void setSpecificDate(Date specificDate) {
		SpecificDate = specificDate;
	}

	public int getBuoiHoc() {
		return BuoiHoc;
	}

	public void setBuoiHoc(int buoiHoc) {
		BuoiHoc = buoiHoc;
	}

	public String getMonHoc() {
		return MonHoc;
	}

	public void setMonHoc(String monHoc) {
		MonHoc = monHoc;
	}

	public String getPhongHoc() {
		return PhongHoc;
	}

	public void setPhongHoc(String phongHoc) {
		PhongHoc = phongHoc;
	}

	public int getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(int trangThai) {
		TrangThai = trangThai;
	}
}
