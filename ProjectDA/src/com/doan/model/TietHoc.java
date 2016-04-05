package com.doan.model;

import java.util.Date;

public class TietHoc {
	int CaHoc;
	String BuoiHoc;
	String SpecificDate;
	String MonHoc;
	String PhongHoc;
	String TrangThai;
	
	public TietHoc(){
		
	}
	
	public TietHoc(int CaHoc, String BuoiHoc, String spec, String monhoc, String phonghoc, String trangthai){
		this.CaHoc = CaHoc;
		this.SpecificDate = spec;
		this.MonHoc = monhoc;
		this.PhongHoc = phonghoc;
		this.TrangThai = trangthai;
		this.BuoiHoc = BuoiHoc;
	}

	public int getCaHoc() {
		return CaHoc;
	}

	public void setCaHoc(int caHoc) {
		CaHoc = caHoc;
	}

	public String getSpecificDate() {
		return SpecificDate;
	}

	public void setSpecificDate(String specificDate) {
		SpecificDate = specificDate;
	}

	public String getBuoiHoc() {
		return BuoiHoc;
	}

	public void setBuoiHoc(String buoiHoc) {
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

	public String getTrangThai() {
		return TrangThai;
	}

	public void setTrangThai(String trangThai) {
		TrangThai = trangThai;
	}
}
