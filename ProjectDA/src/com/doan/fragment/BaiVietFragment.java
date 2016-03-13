package com.doan.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.doan.lichhoctap.R;
import com.doan.model.BaiViet;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class BaiVietFragment extends Fragment{
	
	ArrayList<BaiViet> allBaiViet = new ArrayList<BaiViet>();
	ArrayList<BaiViet> allBaiVietTinHoatDong = new ArrayList<BaiViet>();
	ArrayList<BaiViet> allBaiVietTinDaoTao = new ArrayList<BaiViet>();
	ArrayList<BaiViet> allBaiVietTinTotNghiep = new ArrayList<BaiViet>();
	
	@Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.hoctap_diem_layout,container,false);
        
        BaiViet bv1 = new BaiViet("Tieu de 1", "Noi dung 1", "gv01", "l01", setNgay(2015, 2, 13), setNgay(2015, 2, 13));
        BaiViet bv2 = new BaiViet("Tieu de 2", "Noi dung 2", "gv01", "l02", setNgay(2015, 2, 13), setNgay(2015, 2, 13));
        BaiViet bv3 = new BaiViet("Tieu de 3", "Noi dung 3", "gv01", "l03", setNgay(2015, 2, 13), setNgay(2015, 2, 13));
        
        return v;
	}
	private Date setNgay(int nam, int thang, int ngay){
		Date date;
		Calendar calendar = Calendar.getInstance();
		calendar.set(nam, thang, ngay); //tháng bắt đầu từ 0
		date = calendar.getTime();
		return date;
	}
}
