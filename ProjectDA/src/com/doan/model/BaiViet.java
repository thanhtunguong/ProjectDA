package com.doan.model;

import java.util.Date;

public class BaiViet {
	String TieuDeBaiViet, NoiDungBaiViet, MaGiangVien, LoaiBaiViet;
	Date NgayTaoBaiViet, ThoiGianChinhSua;
	
	public BaiViet(){}
	
	public BaiViet(String tieude, String noidung, 
			String giangvien, String loaibai, Date ngaytao, Date thoigianchinhsua){
		this.TieuDeBaiViet = tieude;
		this.NoiDungBaiViet = noidung;
		this.MaGiangVien = giangvien;
		this.LoaiBaiViet = loaibai;
		this.NgayTaoBaiViet = ngaytao;
		this.ThoiGianChinhSua = thoigianchinhsua;
	}
	
	public String getLoaiBaiViet() {
		return LoaiBaiViet;
	}
	public void setLoaiBaiViet(String loaiBaiViet) {
		LoaiBaiViet = loaiBaiViet;
	}
	public String getTieuDeBaiViet() {
		return TieuDeBaiViet;
	}
	public void setTieuDeBaiViet(String tieuDeBaiViet) {
		TieuDeBaiViet = tieuDeBaiViet;
	}
	public String getNoiDungBaiViet() {
		return NoiDungBaiViet;
	}
	public void setNoiDungBaiViet(String noiDungBaiViet) {
		NoiDungBaiViet = noiDungBaiViet;
	}
	public String getMaGiangVien() {
		return MaGiangVien;
	}
	public void setMaGiangVien(String maGiangVien) {
		MaGiangVien = maGiangVien;
	}
	public Date getNgayTaoBaiViet() {
		return NgayTaoBaiViet;
	}
	public void setNgayTaoBaiViet(Date ngayTaoBaiViet) {
		NgayTaoBaiViet = ngayTaoBaiViet;
	}
	public Date getThoiGianChinhSua() {
		return ThoiGianChinhSua;
	}
	public void setThoiGianChinhSua(Date thoiGianChinhSua) {
		ThoiGianChinhSua = thoiGianChinhSua;
	}
	
}
