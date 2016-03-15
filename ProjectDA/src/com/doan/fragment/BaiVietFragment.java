package com.doan.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.doan.adapter.BaiVietAdapter;
import com.doan.lichhoctap.BaiVietDetailActivity;
import com.doan.lichhoctap.R;
import com.doan.model.BaiViet;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

public class BaiVietFragment extends Fragment{
	
	ArrayList<BaiViet> allBaiViet = new ArrayList<BaiViet>();
	ArrayList<BaiViet> allBaiVietTinHoatDong = new ArrayList<BaiViet>();
	ArrayList<BaiViet> allBaiVietTinDaoTao = new ArrayList<BaiViet>();
	ArrayList<BaiViet> allBaiVietTinTotNghiep = new ArrayList<BaiViet>();
	ListView lvBV;
	BaiVietAdapter bvAdapter;
	
	@Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v =inflater.inflate(R.layout.bai_viet_fragment,container,false);
        
        BaiViet bv1 = new BaiViet("Tieu de 1", "Noi dung 1", "gv01", "l01", setNgay(2015, 2, 13), setNgay(2015, 2, 13));
        BaiViet bv2 = new BaiViet("Tieu de 2", "Noi dung 2", "gv01", "l02", setNgay(2015, 2, 13), setNgay(2015, 2, 13));
        BaiViet bv3 = new BaiViet("Tieu de 3", getString(R.string.text_test), "gv01", "l03", setNgay(2015, 2, 13), setNgay(2015, 2, 13));
        allBaiViet.add(bv1);
        allBaiViet.add(bv2);
        allBaiViet.add(bv3);
        
        final ListView lvBV = (ListView) v.findViewById(R.id.lvBaiViet);
        bvAdapter = new BaiVietAdapter(getActivity(), R.layout.bai_viet_item, allBaiViet);
        lvBV.setAdapter(bvAdapter);
        
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
