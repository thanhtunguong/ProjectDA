package com.doan.model;

import java.util.ArrayList;

public class BoMon {
	private String MaBoMon, TenBoMon;
	private ArrayList<MonHoc> arrMonThuocBoMon;
	private ArrayList<DiemHocTap> arrMonTinhDiem;
	
	public String getMaBoMon() {
		return MaBoMon;
	}
	public void setMaBoMon(String maBoMon) {
		MaBoMon = maBoMon;
	}
	public String getTenBoMon() {
		return TenBoMon;
	}
	public void setTenBoMon(String tenBoMon) {
		TenBoMon = tenBoMon;
	}
	public ArrayList<MonHoc> getArrMonThuocBoMon() {
		return arrMonThuocBoMon;
	}
	public void setArrMonThuocBoMon(ArrayList<MonHoc> arrMonThuocBoMon) {
		this.arrMonThuocBoMon = arrMonThuocBoMon;
	}
	public double getDiemKiNangBoMon(){
		double diemKiNangBoMon = 0;
		for (MonHoc monHoc : arrMonThuocBoMon) {
			double diemKiNang = (monHoc.getDiemTrungBinhSV()/10)*monHoc.getDoKho();
			diemKiNangBoMon = diemKiNangBoMon + diemKiNang;
		}
		return diemKiNangBoMon;
	}
	public int getDiemKiNangMax(){
		int max = 0;
		for (MonHoc monHoc : arrMonThuocBoMon) {
			max = max + monHoc.getDoKho();
		}
		return max;
	}
}
