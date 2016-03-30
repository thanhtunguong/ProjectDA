package com.doan.model;

public class MonHocAndStatus implements Comparable<MonHocAndStatus> {
	private String TenMon;
	private int Status;
	
	public MonHocAndStatus(String tenmon, int status){
		this.TenMon = tenmon;
		this.Status = status;
	}
	
	public String getTenMon() {
		return TenMon;
	}
	public void setTenMon(String tenMon) {
		TenMon = tenMon;
	}
	public int getStatus() {
		return Status;
	}
	public void setStatus(int status) {
		Status = status;
	}

	@Override
	public int compareTo(MonHocAndStatus another) {
		// TODO Auto-generated method stub
		return Integer.compare(this.Status, another.Status);
	}
	
}
