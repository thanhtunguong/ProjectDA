package com.doan.model;

import java.util.ArrayList;
import java.util.Date;

public class ThongBaoTrongNgay implements Comparable<ThongBaoTrongNgay>{
	private Date NgayCuThe;
	private ArrayList<ThongBao> arrThongBao;
	
	public ThongBaoTrongNgay(){}
	public ThongBaoTrongNgay(Date ngaycuthe, ArrayList<ThongBao> arrthongbao){
		this.NgayCuThe = ngaycuthe;
		this.arrThongBao = arrthongbao;
	}
	public Date getNgayCuThe() {
		return NgayCuThe;
	}
	public void setNgayCuThe(Date ngayCuThe) {
		NgayCuThe = ngayCuThe;
	}
	public ArrayList<ThongBao> getArrThongBao() {
		return arrThongBao;
	}
	public void setArrThongBao(ArrayList<ThongBao> arrThongBao) {
		this.arrThongBao = arrThongBao;
	}
	@Override
	public int compareTo(ThongBaoTrongNgay another) {
		// TODO Auto-generated method stub
		//return this.NgayCuThe.compareTo(another.NgayCuThe);
		return another.NgayCuThe.compareTo(this.NgayCuThe);
	}
}
