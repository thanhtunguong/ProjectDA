package com.doan.model;

import java.util.ArrayList;

public class MonHoc {
	private String MaMonCTDT, MaMonHoc, TenMon;
	private ArrayList<MonHocTienQuyet> arrMonTienQuyet;
	

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
	
}
