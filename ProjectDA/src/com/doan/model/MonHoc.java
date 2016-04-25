package com.doan.model;

import java.util.ArrayList;

public class MonHoc {
	private String MaMonCTDT, MaMonHoc, TenMon;
	private ArrayList<MonHocTienQuyet> arrMonTienQuyet;
	private int DoKho;
	private String MaBoMon;
	private double DiemTrungBinhSV, DiemTrungBinhToanBo;

	public ArrayList<MonHocTienQuyet> getArrMonTienQuyet() {
		return arrMonTienQuyet;
	}

	public void setArrMonTienQuyet(ArrayList<MonHocTienQuyet> arrMonTienQuyet) {
		this.arrMonTienQuyet = arrMonTienQuyet;
	}

	public String getMaMonCTDT() {
		return MaMonCTDT;
	}

	public void setMaMonCTDT(String maMonCTDT) {
		MaMonCTDT = maMonCTDT;
	}

	public String getMaMonHoc() {
		return MaMonHoc;
	}

	public void setMaMonHoc(String maMonHoc) {
		MaMonHoc = maMonHoc;
	}

	public String getTenMon() {
		return TenMon;
	}

	public void setTenMon(String tenMon) {
		TenMon = tenMon;
	}

	public int getDoKho() {
		return DoKho;
	}

	public void setDoKho(int doKho) {
		DoKho = doKho;
	}

	public String getMaBoMon() {
		return MaBoMon;
	}

	public void setMaBoMon(String maBoMon) {
		MaBoMon = maBoMon;
	}

	public double getDiemTrungBinhSV() {
		return DiemTrungBinhSV;
	}

	public void setDiemTrungBinhSV(double diemTrungBinhSV) {
		DiemTrungBinhSV = diemTrungBinhSV;
	}

	public double getDiemTrungBinhToanBo() {
		return DiemTrungBinhToanBo;
	}

	public void setDiemTrungBinhToanBo(double diemTrungBinhToanBo) {
		DiemTrungBinhToanBo = diemTrungBinhToanBo;
	}
	
}
