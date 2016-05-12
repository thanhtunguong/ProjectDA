package com.doan.adapter;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import com.doan.lichhoctap.R;
import com.doan.model.BoMonBieuDo;
import com.doan.model.MonHoc;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;

public class DialogChiTietBoMonAdapter extends ArrayAdapter<MonHoc>{
	Activity context = null;
	ArrayList<MonHoc> arrMonHoc = null;
	int layoutId;

	public DialogChiTietBoMonAdapter (Activity context, ArrayList<MonHoc> arrMonHoc, int layoutId) {
		super(context, layoutId, arrMonHoc);
		this.context = context;
		this.arrMonHoc = arrMonHoc;
		this.layoutId = layoutId;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = context.getLayoutInflater();
		convertView = inflater.inflate(layoutId, null);
		MonHoc mh = arrMonHoc.get(position);
		TextView tvDialogBoMonTenMon = (TextView) convertView.findViewById(R.id.tvDialogBoMonTenMon);
		TextView tvKQMau = (TextView) convertView.findViewById(R.id.tvKQMau);
		TextView tvSinhVienTongKet = (TextView) convertView.findViewById(R.id.tvSinhVienTongKet);
		TextView tvToanBoTongKet = (TextView) convertView.findViewById(R.id.tvSVNhieuDiemTongKet);
		tvDialogBoMonTenMon.setText(mh.getTenMon());
		double diemtk = mh.getDiemTrungBinhSV();
		DecimalFormat df = new DecimalFormat("#.00");
		String dtk = df.format(diemtk);
		if(diemtk != 0){
			if(diemtk <5){
				tvKQMau.setBackgroundColor(context.getColor(R.color.ColorPrimaryDark));
				//tvKQMau.setText("Trung bình");
				tvKQMau.setText(dtk+"");
			}else {
				if(diemtk >= 5 && diemtk <7){
					tvKQMau.setBackgroundColor(context.getColor(R.color.ColorPrimary));
					//tvKQMau.setText("Trung bình");
					tvKQMau.setText(dtk+"");
				} else {
					if(diemtk >= 7 && diemtk < 9){
						tvKQMau.setBackgroundColor(context.getColor(R.color.bomon_Toan));
						//tvKQMau.setText("Khá");
						tvKQMau.setText(dtk+"");
					}else {
						if (diemtk >= 9) {
							tvKQMau.setBackgroundColor(context.getColor(R.color.hard_green));
							//tvKQMau.setText("Giỏi");
							tvKQMau.setText(dtk+"");
						}
					}
				}
			}
		}else {
			tvKQMau.setBackgroundColor(context.getColor(R.color.greyDeactive));
			tvKQMau.setText(context.getString(R.string.string_chuahoc));
		}
		//tvSinhVienTongKet.setText(dtk+"");
		double diemtbtb = mh.getDiemTrungBinhToanBo();
		tvToanBoTongKet.setText(""+diemtbtb);
		if(diemtbtb != 0){
			if(diemtbtb < 5){
				tvToanBoTongKet.setTextColor(context.getColor(R.color.ColorPrimaryDark));
			}else {
				if(diemtbtb >= 5 && diemtbtb <7){
					tvToanBoTongKet.setTextColor(context.getColor(R.color.ColorPrimary));
				}else {
					if(diemtbtb >=7 && diemtbtb <9){
						tvToanBoTongKet.setTextColor(context.getColor(R.color.bomon_Toan));
					}else {
						if(diemtbtb >= 9){
							tvToanBoTongKet.setTextColor(context.getColor(R.color.hard_green));
						}
					}
				}
			}
		}else {
			tvToanBoTongKet.setTextColor(context.getColor(R.color.gray));
		}
		return convertView;
	}
}
