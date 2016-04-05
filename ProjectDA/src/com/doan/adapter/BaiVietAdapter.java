package com.doan.adapter;

import java.util.ArrayList;

import com.doan.lichhoctap.BaiVietDetailActivity;
import com.doan.lichhoctap.R;

import com.doan.model.BaiViet;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class BaiVietAdapter extends ArrayAdapter<BaiViet> {
	Activity context = null;
	ArrayList<BaiViet> myArray = null;
	int layoutId;

	public BaiVietAdapter (Activity context, int layoutId,
			ArrayList<BaiViet> arr) {
		super(context, layoutId, arr);
		this.context = context;
		this.layoutId = layoutId;
		this.myArray = arr;
	}

	public View getView(final int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = context.getLayoutInflater();
		convertView = inflater.inflate(layoutId, null);
		
		TextView tieude, ngaytao, loaibaiviet;
		tieude = (TextView) convertView.findViewById(R.id.tvBaiVietTitle);
		ngaytao = (TextView) convertView.findViewById(R.id.tvBaiVietCreateDate);
		loaibaiviet = (TextView) convertView.findViewById(R.id.tvLoaiBaiViet);
		
		//0-90-80-0 Ä‘á»�, 100-60-0-0 xanh dÆ°Æ¡ng, 60-0-100-0 xanh lÃ¡
		Integer mau;
		GradientDrawable d = (GradientDrawable) loaibaiviet.getBackground();
		switch(myArray.get(position).getLoaiBaiViet()){
		case "LBV01":
			loaibaiviet.setText("DT");
			//mau = Color.argb(0, 90, 80, 0);
			mau = Color.parseColor(context.getString(R.color.ColorPrimary));
			d.setColor(mau);
			break;
		case "LBV02":
			loaibaiviet.setText("HD");
			//mau = Color.argb(100, 60, 0, 0);
			mau = Color.parseColor(context.getString(R.color.indigo));
			d.setColor(mau);
			break;
		case "LBV03":
			loaibaiviet.setText("TN");
			//mau = Color.argb(60, 0, 100, 0);
			mau = Color.parseColor(context.getString(R.color.amber));
			d.setColor(mau);
			break;
		}
		tieude.setText(myArray.get(position).getTieuDeBaiViet());
		ngaytao.setText(myArray.get(position).getNgayTaoBaiViet().toString());
		
		convertView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent_FrgBV_to_ActvtBVdetail = new Intent(context, BaiVietDetailActivity.class);
				intent_FrgBV_to_ActvtBVdetail.putExtra("bvTitle", myArray.get(position).getTieuDeBaiViet());
				intent_FrgBV_to_ActvtBVdetail.putExtra("bvContents", myArray.get(position).getNoiDungBaiViet());
                context.startActivity(intent_FrgBV_to_ActvtBVdetail);
			}
		});
		return convertView;
	}
}
