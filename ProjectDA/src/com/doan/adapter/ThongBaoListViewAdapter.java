package com.doan.adapter;

import java.util.ArrayList;

import com.doan.lichhoctap.R;
import com.doan.model.DiemHocTap;
import com.doan.model.ThongBao;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ThongBaoListViewAdapter extends ArrayAdapter<ThongBao> {
	Activity context = null;
	ArrayList<ThongBao> myArray = null;
	int layoutId;

	public ThongBaoListViewAdapter (Activity context, int layoutId,
			ArrayList<ThongBao> arr) {
		super(context, layoutId, arr);
		this.context = context;
		this.layoutId = layoutId;
		this.myArray = arr;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = context.getLayoutInflater();
		convertView = inflater.inflate(layoutId, null);
		TextView tvNumberNguoiNhanTB, tvTieuDeTB, tvNoiDungTB, tvDateTB, tvTimeTB;
		tvNumberNguoiNhanTB = (TextView) convertView.findViewById(R.id.tvNumberNguoiNhanTB);
		tvTieuDeTB = (TextView) convertView.findViewById(R.id.tvTieuDeTB);
		tvNoiDungTB = (TextView) convertView.findViewById(R.id.tvNoiDungTB);
		tvDateTB = (TextView) convertView.findViewById(R.id.tvDateTB);
		tvTimeTB = (TextView) convertView.findViewById(R.id.tvTimeTB);
		
		ThongBao tb = myArray.get(position);
		String ngaythang = tb.getNgayTaoThongBao().substring(0, 10);
		String giophut = tb.getNgayTaoThongBao().substring(11, 16);
		tvDateTB.setText(ngaythang);
		tvTimeTB.setText(giophut);
		tvNumberNguoiNhanTB.setText(tb.getSonguoinhan()+"");
		tvTieuDeTB.setText(tb.getTieuDeThongBao());
		tvNoiDungTB.setText(tb.getNoiDungThongBao());
		return convertView;
	}
}
