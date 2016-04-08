package com.doan.model;

import java.util.Date;

public class ChiTietThongBao implements Comparable<ChiTietThongBao> {
	private String MaChiTietThongBao, MaNguoiReply, TenNguoiReply, NoiDungReply, MaThongBao, ThoiGianTraLoi;
	//private Date ThoiGianTraLoi;
	
	public String getMaChiTietThongBao() {
		return MaChiTietThongBao;
	}
	public void setMaChiTietThongBao(String maChiTietThongBao) {
		MaChiTietThongBao = maChiTietThongBao;
	}
	public String getMaNguoiReply() {
		return MaNguoiReply;
	}
	public void setMaNguoiReply(String maNguoiReply) {
		MaNguoiReply = maNguoiReply;
	}
	public String getTenNguoiReply() {
		return TenNguoiReply;
	}
	public void setTenNguoiReply(String tenNguoiReply) {
		TenNguoiReply = tenNguoiReply;
	}
	public String getNoiDungReply() {
		return NoiDungReply;
	}
	public void setNoiDungReply(String noiDungReply) {
		NoiDungReply = noiDungReply;
	}
	public String getMaThongBao() {
		return MaThongBao;
	}
	public void setMaThongBao(String maThongBao) {
		MaThongBao = maThongBao;
	}
	public String getThoiGianTraLoi() {
		return ThoiGianTraLoi;
	}
	public void setThoiGianTraLoi(String thoiGianTraLoi) {
		ThoiGianTraLoi = thoiGianTraLoi;
	}
	@Override
	public int compareTo(ChiTietThongBao another) {
		// TODO Auto-generated method stub
		return this.ThoiGianTraLoi.compareTo(another.ThoiGianTraLoi);
	}
	
}
