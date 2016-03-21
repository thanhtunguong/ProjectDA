package com.doan.model;

public class DiemHocTap {
	String MaLopTinChi, MaLichThi, MaTrangThaiDK, TenMonHoc, ThoiGianDK, MaDiem;
	float DiemCC, DiemKT, DiemThi;
	int SoTinChi;
	
	public DiemHocTap(){}
	
	public DiemHocTap(String MaLopTinChi, String MaLichThi, String MaTrangThaiDK, String TenMonHoc,
			float DiemCC, float DiemKT, float DiemThi, int SoTinChi, String ThoiGianDK){
		this.MaLopTinChi = MaLopTinChi;
		this.MaLichThi = MaLichThi;
		this.MaTrangThaiDK = MaTrangThaiDK;
		this.TenMonHoc = TenMonHoc;
		this.DiemCC = DiemCC;
		this.DiemKT = DiemKT;
		this.DiemThi = DiemThi;
		this.SoTinChi = SoTinChi;
		this.ThoiGianDK = ThoiGianDK;
	}

	
	public String getMaDiem() {
		return MaDiem;
	}

	public void setMaDiem(String maDiem) {
		MaDiem = maDiem;
	}

	public String getThoiGianDK() {
		return ThoiGianDK;
	}

	public void setThoiGianDK(String thoiGianDK) {
		ThoiGianDK = thoiGianDK;
	}

	public String getMaLopTinChi() {
		return MaLopTinChi;
	}

	public void setMaLopTinChi(String maLopTinChi) {
		MaLopTinChi = maLopTinChi;
	}

	public String getMaLichThi() {
		return MaLichThi;
	}

	public void setMaLichThi(String maLichThi) {
		MaLichThi = maLichThi;
	}

	public String getMaTrangThaiDK() {
		return MaTrangThaiDK;
	}

	public void setMaTrangThaiDK(String maTrangThaiDK) {
		MaTrangThaiDK = maTrangThaiDK;
	}

	public String getTenMonHoc() {
		return TenMonHoc;
	}

	public void setTenMonHoc(String tenMonHoc) {
		TenMonHoc = tenMonHoc;
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

	public int getSoTinChi() {
		return SoTinChi;
	}

	public void setSoTinChi(int soTinChi) {
		SoTinChi = soTinChi;
	}

	
	
	/*public DiemHocTap(String maloptinchi, 
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
	}	*/
}
