package com.doan.adapter;

import java.util.ArrayList;

import com.doan.fragment.DiemFragment;
import com.doan.lichhoctap.R;
import com.doan.model.DiemHocTap;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HocTapDiemAdapter extends ArrayAdapter<DiemHocTap> {
	Activity context = null;
	ArrayList<DiemHocTap> myArray = null;
	int layoutId;

	public HocTapDiemAdapter (Activity context, int layoutId,
			ArrayList<DiemHocTap> arr) {
		super(context, layoutId, arr);
		this.context = context;
		this.layoutId = layoutId;
		this.myArray = arr;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = context.getLayoutInflater();
		convertView = inflater.inflate(layoutId, null);
		
		TextView tvStt, tvMaLopTC, tvTenMon, tvSotinchi, tvDiemCC, tvDiemKT, tvDiemThi, tvDiemTongKet;
		LinearLayout ln;
		ln = (LinearLayout) convertView.findViewById(R.id.lnDiemItem);
		
		tvStt = (TextView) convertView.findViewById(R.id.tvSttDiem);
		int stt = position + 1;
		if(stt%2 != 0){
			ln.setBackgroundColor(R.color.DiemItemBackground);
		}
		tvStt.setText(stt+"");
		//tvMaLopTC = (TextView) convertView.findViewById(R.id.tvMaLopTC);
		//tvMaLopTC.setText(myArray.get(position).getMaLopTinChi());
		tvTenMon = (TextView) convertView.findViewById(R.id.tvTenMon);
		tvTenMon.setText(myArray.get(position).getTenMonHoc());
		//tvSotinchi = (TextView) convertView.findViewById(R.id.tvSoTinChi);
		//tvSotinchi.setText(myArray.get(position).getSoTinChi() + "");
		
		float d1, d2, d7, stc;
		stc = myArray.get(position).getSoTinChi();
		d1 = myArray.get(position).getDiemCC();
		d2 = myArray.get(position).getDiemKT();
		d7 = myArray.get(position).getDiemThi();
		
		tvDiemCC = (TextView) convertView.findViewById(R.id.tvDiemCC);
		tvDiemCC.setText(d1 + "");
		tvDiemKT = (TextView) convertView.findViewById(R.id.tvDiemKT);
		tvDiemKT.setText(d2 + "");
		tvDiemThi = (TextView) convertView.findViewById(R.id.tvDiemThi);
		tvDiemThi.setText(d7 + "");
		double dtb = ((d1 + d2*2 + d7*7)/10)*1.0;
		//dtb = Math.round(dtb*10)/10;
		dtb = Math.floor(dtb*10)/10;
		//tvDiemTongKet = (TextView) convertView.findViewById(R.id.tvDiemTongKet);
		//tvDiemTongKet.setText(dtb+"");
		return convertView;
	}
}
