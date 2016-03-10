package com.doan.model;

public class DiemHocTap {
	String MaLopTinChi, TenMon;
	int SoTinChi;
	float DiemCC, DiemKT, DiemThi;
	
	public DiemHocTap(){}
	
	public DiemHocTap(String maloptinchi, 
			String tenmon, int sotinchi, float diemcc, float diemkt, float diemthi){
		this.MaLopTinChi = maloptinchi;
		this.TenMon = tenmon;
		this.SoTinChi = sotinchi;
		this.DiemCC = diemcc;
		this.DiemKT = diemkt;
		this.DiemThi = diemthi;
	}
	
	public int getSoTinChi() {
		return SoTinChi;
	}

	public void setSoTinChi(int soTinChi) {
		SoTinChi = soTinChi;
	}

	public String getMaLopTinChi() {
		return MaLopTinChi;
	}
	public void setMaLopTinChi(String maLopTinChi) {
		MaLopTinChi = maLopTinChi;
	}
	public String getTenMon() {
		return TenMon;
	}
	public void setTenMon(String tenMon) {
		TenMon = tenMon;
	}
	public float getDiemCC() {
		return DiemCC;
	}
	public void setDiemCC(float diemCC) {
		DiemCC = diemCC;
	}
	public float getDiemKT() {
		return DiemKT;
	}
	public void setDiemKT(float diemKT) {
		DiemKT = diemKT;
	}
	public float getDiemThi() {
		return DiemThi;
	}
	public void setDiemThi(float diemThi) {
		DiemThi = diemThi;
	}	
}
