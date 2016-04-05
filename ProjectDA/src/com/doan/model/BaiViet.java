package com.doan.model;

import java.util.Date;

public class BaiViet {
	String MaBaiViet,TieuDeBaiViet, NoiDungBaiViet, MaGiangVien, LoaiBaiViet;
	String NgayTaoBaiViet, ThoiGianChinhSua;
	
	public BaiViet(){}
	
	public BaiViet(String mabaiviet,String tieude, String noidung, 
			String giangvien, String loaibai, String ngaytao, String thoigianchinhsua){
		this.MaBaiViet = mabaiviet;
		this.TieuDeBaiViet = tieude;
		this.NoiDungBaiViet = noidung;
		this.MaGiangVien = giangvien;
		this.LoaiBaiViet = loaibai;
		this.NgayTaoBaiViet = ngaytao;
		this.ThoiGianChinhSua = thoigianchinhsua;
	}
	
	public String getMaBaiViet() {
		return MaBaiViet;
	}

	public void setMaBaiViet(String maBaiViet) {
		MaBaiViet = maBaiViet;
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
	public String getNgayTaoBaiViet() {
		return NgayTaoBaiViet;
	}
	public void setNgayTaoBaiViet(String ngayTaoBaiViet) {
		NgayTaoBaiViet = ngayTaoBaiViet;
	}
	public String getThoiGianChinhSua() {
		return ThoiGianChinhSua;
	}
	public void setThoiGianChinhSua(String thoiGianChinhSua) {
		ThoiGianChinhSua = thoiGianChinhSua;
	}
	
}
