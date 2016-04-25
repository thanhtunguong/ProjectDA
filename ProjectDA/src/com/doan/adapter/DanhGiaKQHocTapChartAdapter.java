package com.doan.adapter;

import java.util.ArrayList;

import com.doan.lichhoctap.R;
import com.doan.model.BoMon;
import com.doan.model.BoMonBieuDo;
import com.doan.model.DiemHocTap;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

public class DanhGiaKQHocTapChartAdapter extends ArrayAdapter<BoMonBieuDo>{
	Activity context = null;
	ArrayList<BoMonBieuDo> arrBieuDo = null;
	int layoutId;

	public DanhGiaKQHocTapChartAdapter (Activity context, ArrayList<BoMonBieuDo> arrBieuDo, int layoutId) {
		super(context, layoutId, arrBieuDo);
		this.context = context;
		this.arrBieuDo = arrBieuDo;
		this.layoutId = layoutId;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = context.getLayoutInflater();
		convertView = inflater.inflate(layoutId, null);
		LinearLayout lnBoMon = (LinearLayout) convertView.findViewById(R.id.lnBoMon);
		FrameLayout flBackground = (FrameLayout) convertView.findViewById(R.id.flBackground);
		ImageView ivBoMonIcon = (ImageView) convertView.findViewById(R.id.ivBoMonIcon);
		TextView tvTenBoMon = (TextView) convertView.findViewById(R.id.tvTenBoMon);
		BoMonBieuDo bm = arrBieuDo.get(position);
		
		float DoRong = bm.getDoRong();
		int width = Math.round(DoRong);
		LinearLayout.LayoutParams lp = new LayoutParams(width, 24);
		lp.gravity = Gravity.CENTER_VERTICAL;
		lp.topMargin= 12;
		lnBoMon.setLayoutParams(lp);
		GradientDrawable d = (GradientDrawable) flBackground.getBackground();
		d.setColor(context.getResources().getColor(bm.getMau()));
		//flBackground.setBackgroundColor(context.getResources().getColor(bm.getMau()));
		//lnBoMon.setBackgroundColor(context.getResources().getColor(bm.getMau()));
		ivBoMonIcon.setBackgroundResource(bm.getIcon());
		ivBoMonIcon.setScaleType(ScaleType.FIT_XY);
		tvTenBoMon.setText(bm.getTenBoMon());
		return convertView;
	}
}
