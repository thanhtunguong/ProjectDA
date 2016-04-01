package com.doan.model;

import java.util.Date;

public class ThongBao {
	private String MaThongBao, TieuDeThongBao, NoiDungThongBao, NgayTaoThongBao,MaGVGui;
	
	public ThongBao(){};
	public ThongBao(String mathongbao, String tieudethongbao, 
			String noidungthongbao, String ngaytaothongbao, String magv){
		this.MaThongBao = mathongbao;
		this.TieuDeThongBao = tieudethongbao;
		this.NoiDungThongBao = noidungthongbao;
		this.NgayTaoThongBao = ngaytaothongbao;
		this.MaGVGui = magv;
	}
	public String getMaThongBao() {
		return MaThongBao;
	}
	public void setMaThongBao(String maThongBao) {
		MaThongBao = maThongBao;
	}
	public String getTieuDeThongBao() {
		return TieuDeThongBao;
	}
	public void setTieuDeThongBao(String tieuDeThongBao) {
		TieuDeThongBao = tieuDeThongBao;
	}
	public String getNoiDungThongBao() {
		return NoiDungThongBao;
	}
	public void setNoiDungThongBao(String noiDungThongBao) {
		NoiDungThongBao = noiDungThongBao;
	}
	public String getMaGVGui() {
		return MaGVGui;
	}
	public void setMaGVGui(String maGVGui) {
		MaGVGui = maGVGui;
	}
	public String getNgayTaoThongBao() {
		return NgayTaoThongBao;
	}
	public void setNgayTaoThongBao(String ngayTaoThongBao) {
		NgayTaoThongBao = ngayTaoThongBao;
	}
	
}
