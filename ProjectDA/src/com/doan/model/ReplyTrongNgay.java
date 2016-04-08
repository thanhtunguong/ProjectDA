package com.doan.model;

import java.util.ArrayList;
import java.util.Date;

public class ReplyTrongNgay implements Comparable<ReplyTrongNgay>{
	private Date Ngay;
	private ArrayList<ChiTietThongBao> arrChiTiet;
	public Date getNgay() {
		return Ngay;
	}
	public void setNgay(Date ngay) {
		Ngay = ngay;
	}
	public ArrayList<ChiTietThongBao> getArrChiTiet() {
		return arrChiTiet;
	}
	public void setArrChiTiet(ArrayList<ChiTietThongBao> arrChiTiet) {
		this.arrChiTiet = arrChiTiet;
	}
	@Override
	public int compareTo(ReplyTrongNgay another) {
		// TODO Auto-generated method stub
		return this.Ngay.compareTo(another.Ngay);
	}
	
}
