package com.doan.model;

import java.util.Date;

import com.doan.app.Global;

public class ThongBao implements Comparable<ThongBao> {
	private String MaThongBao, TieuDeThongBao, NoiDungThongBao, NgayTaoThongBao,MaGVGui;
	int songuoinhan;
	
	public ThongBao(){};
	public ThongBao(String mathongbao, String tieudethongbao, 
			String noidungthongbao, String ngaytaothongbao, String magv, int songuoinhan){
		this.MaThongBao = mathongbao;
		this.TieuDeThongBao = tieudethongbao;
		this.NoiDungThongBao = noidungthongbao;
		this.NgayTaoThongBao = ngaytaothongbao;
		this.MaGVGui = magv;
		this.songuoinhan = songuoinhan;
	}
	
	public int getSonguoinhan() {
		return songuoinhan;
	}
	public void setSonguoinhan(int songuoinhan) {
		this.songuoinhan = songuoinhan;
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
	@Override
	public int compareTo(ThongBao another) {
		// TODO Auto-generated method stub
		Date anotherDate = Global.epKieuDateAndTime(another.NgayTaoThongBao);
		Date thisDate = Global.epKieuDateAndTime(this.NgayTaoThongBao);
		return anotherDate.compareTo(thisDate);
	}
	
}
