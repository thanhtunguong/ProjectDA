package com.doan.adapter;

import java.util.ArrayList;

import com.doan.lichhoctap.R;
import com.doan.model.ItemGhiChu;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class GhiChuAdapter extends ArrayAdapter<ItemGhiChu> {

	Context context;
	ArrayList<ItemGhiChu> myArray = null;
	int layoutId;

	public GhiChuAdapter(Context c, int layoutId, ArrayList<ItemGhiChu> arr) {
		super(c, layoutId, arr);
		this.context = c;
		this.layoutId = layoutId;
		this.myArray = arr;
	}

	@SuppressLint("ViewHolder") public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View row = inflater.inflate(R.layout.ghichu_item, parent, false);
		TextView tvTitle = (TextView) row.findViewById(R.id.tvTitle);
		TextView tvContent = (TextView) row.findViewById(R.id.tvContent);

		final ItemGhiChu igc = myArray.get(position);
		tvTitle.setText(igc.getTitle());
		String chitietghichu = igc.getContent();
		if(chitietghichu.length() > 50){
			chitietghichu = chitietghichu.substring(0,40);
			chitietghichu = chitietghichu + ".......";
		}
		tvContent.setText(chitietghichu);

		return row;
	}
}
