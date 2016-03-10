package com.doan.fragment;
import java.util.ArrayList;

import com.doan.adapter.HocTapDiemAdapter;
import com.doan.lichhoctap.R;
import com.doan.model.DiemHocTap;

import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ListView;
import android.widget.TextView;

public class DiemFragment extends Fragment {
	
	DiemHocTap d1 = new DiemHocTap("AWD1", "Kien truc may tinh", 4, 10, 5, 8);
	DiemHocTap d2 = new DiemHocTap("AWD2", "Ki thuat dien tu so", 4, 8, 8, 5);
	DiemHocTap d3 = new DiemHocTap("AWD3", "Nguyen ly he dieu hanh", 3, 10, 8, 2);
	DiemHocTap d4 = new DiemHocTap("AWD4", "Thuong mai dien tu", 3, 10, 8, 6);
	ArrayList<DiemHocTap> arlDiem = new ArrayList<DiemHocTap>();
	int TC = 0;
    float DTB = 0;
    double tongDiem = 0;
    
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.hoctap_diem_layout,container,false);
        arlDiem.add(d1);
        arlDiem.add(d2);
        arlDiem.add(d3);
        arlDiem.add(d4);
        /*arlDiem.add(d5);
        arlDiem.add(d6);*/
        TextView tvTC, tvDTB, tvSTCpc, tvSTCprogess, tvDTBpc, tvDTBprogess;
        LinearLayout lnSTC, lnDTB;
        for (DiemHocTap diemHocTap : arlDiem) {
        	double sdtb = ((diemHocTap.getDiemCC() + diemHocTap.getDiemKT()*2 + diemHocTap.getDiemThi()*7)/10)*1.0;
        	sdtb = Math.floor(sdtb*10)/10;
			tinhTongKetHocTap(diemHocTap.getSoTinChi(), sdtb, v);
		}
        tvTC = (TextView) v.findViewById(R.id.tvSoTinChiDaHoc);
        tvTC.setText(getString(R.string.string_SoTinChiDaHoc));
        
        tvDTB = (TextView) v.findViewById(R.id.tvDiemTrungBinhTichLuy);
        tvDTB.setText(getString(R.string.string_DiemTrungBinhTichLuy));
        
        Display display = getActivity().getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        
        lnSTC = (LinearLayout) v.findViewById(R.id.lnSTC);
        //lnSTC.setLayoutParams(new LayoutParams(width/2, 12));
        float width2 = (float)(width/1.5);
        width = Math.round(width2);
        LinearLayout.LayoutParams lp1 = new LayoutParams(width, 12);
        lp1.gravity = Gravity.CENTER_VERTICAL;
        lnSTC.setLayoutParams(lp1);        
        tvSTCprogess = (TextView) v.findViewById(R.id.tvTCprogess);
    	float STCpc = TC/120;
    	//int xSTC = lnSTC.getWidth();
    	int xSTC = 220;
    	float x = (TC*width)/120;
    	//tvSTCprogess.setWidth(Math.round(x));
    	//tvSTCprogess.setWidth(270);
    	//LayoutParams lp = (LinearLayout.LayoutParams) tvSTCprogess.getLayoutParams();
    	tvSTCprogess.setLayoutParams(new LayoutParams(Math.round(x), 12));
    	tvSTCpc = (TextView) v.findViewById(R.id.tvTCpc);
    	tvSTCpc.setText(x + " %");
    	
    	lnDTB = (LinearLayout) v.findViewById(R.id.lnDTB);
    	lnDTB.setLayoutParams(new LayoutParams(width, 12));
    	lnDTB.setGravity(Gravity.CENTER_VERTICAL);
        tvDTBprogess = (TextView) v.findViewById(R.id.tvDTBprogess);
        lnDTB.setLayoutParams(lp1);
    	float DTBpc = DTB/10;
    	int xDTB = 220;
    	float y = (width*DTB)/10;
    	tvDTBprogess.setLayoutParams(new LayoutParams(Math.round(y), 12));
    	tvDTBpc = (TextView) v.findViewById(R.id.tvDTBpc);
    	tvDTBpc.setText(DTB + "/10");
    	
    	tvSTCpc = (TextView) v.findViewById(R.id.tvTCpc);
    	tvSTCpc.setText(TC + "/120");
        
        
        
        
        HocTapDiemAdapter adapter = new HocTapDiemAdapter(getActivity(), R.layout.hoctap_diem_diemso_item_test, arlDiem);
        ListView lvDiem = (ListView) v.findViewById(R.id.lvDiem);
        lvDiem.setAdapter(adapter);
        
        return v;
    }
    public void tinhTongKetHocTap(int stc, double sdtb, View v){
    	TC = TC + stc;
    	tongDiem = tongDiem +  sdtb*stc;
    	DTB = (float)(tongDiem/TC);
    	DTB = (float)Math.floor(DTB*10)/10;    	
    }
}