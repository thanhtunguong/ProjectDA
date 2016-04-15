package com.doan.model;

import java.io.Serializable;

public class SinhVienThongBao implements Serializable {
	private String MaSV, HoTenSinhVien, NgaySinhSinhVien, GioiTinhSinhVien, MaLopHanhChinh;
	int checked;
	
	

	public int getChecked() {
		return checked;
	}

	public void setChecked(int checked) {
		this.checked = checked;
	}

	public String getMaLopHanhChinh() {
		return MaLopHanhChinh;
	}

	public void setMaLopHanhChinh(String maLopHanhChinh) {
		MaLopHanhChinh = maLopHanhChinh;
	}

	public String getMaSV() {
		return MaSV;
	}

	public void setMaSV(String maSV) {
		MaSV = maSV;
	}

	public String getHoTenSinhVien() {
		return HoTenSinhVien;
	}

	public void setHoTenSinhVien(String hoTenSinhVien) {
		HoTenSinhVien = hoTenSinhVien;
	}

	public String getNgaySinhSinhVien() {
		return NgaySinhSinhVien;
	}

	public void setNgaySinhSinhVien(String ngaySinhSinhVien) {
		NgaySinhSinhVien = ngaySinhSinhVien;
	}

	public String getGioiTinhSinhVien() {
		return GioiTinhSinhVien;
	}

	public void setGioiTinhSinhVien(String gioiTinhSinhVien) {
		GioiTinhSinhVien = gioiTinhSinhVien;
	}

}
