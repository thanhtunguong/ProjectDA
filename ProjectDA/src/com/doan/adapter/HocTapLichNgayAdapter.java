package com.doan.adapter;

import java.util.ArrayList;

import com.doan.lichhoctap.R;
import com.doan.model.DayInWeek;
import com.doan.model.TietHoc;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class HocTapLichNgayAdapter extends BaseExpandableListAdapter {

	private Activity activity;
	private ArrayList<Object> childtems;
	private LayoutInflater inflater;
	private ArrayList<DayInWeek> parentItems;
	private ArrayList<TietHoc> child;

	public HocTapLichNgayAdapter(ArrayList<DayInWeek> parents, ArrayList<Object> childern) {
		this.parentItems = parents;
		this.childtems = childern;
	}

	public void setInflater(LayoutInflater inflater, Activity activity) {
		this.inflater = inflater;
		this.activity = activity;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {

		child = (ArrayList<TietHoc>) childtems.get(groupPosition);

		TextView tvThoiGian, tvTenMon, tvPhong, tvTrangThai;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.hoctap_lichhoc_tiethoc_item, null);
		}

		tvThoiGian = (TextView) convertView.findViewById(R.id.tvTietHocThoigian);
		if (child.get(childPosition).getBuoiHoc() % 2 != 0) {
			tvThoiGian.setText(R.string.string_hoctap_thoigianSang);
		} else {
			tvThoiGian.setText(R.string.string_hoctap_thoigianChieu);
		}
		tvTenMon = (TextView) convertView.findViewById(R.id.tvTenTietHoc);
		tvTenMon.setText(child.get(childPosition).getMonHoc());
		tvPhong = (TextView) convertView.findViewById(R.id.tvTietHocPhong);
		tvPhong.setText(child.get(childPosition).getPhongHoc());
		//tvTrangThai = (TextView) convertView.findViewById(R.id.ivTietHocTrangThai);
		ImageView ivTrangThai = (ImageView) convertView.findViewById(R.id.ivTietHocTrangThai);
		if(child.get(childPosition).getTrangThai() == 1){
			//tvTrangThai.setText(R.string.string_hoctap_trangthai_active);
			ivTrangThai.setBackgroundResource(R.drawable.circle_active_icon);
		}else if (child.get(childPosition).getTrangThai() == 2) {
			//tvTrangThai.setText(R.string.string_hoctap_trangthai_deactive);
			ivTrangThai.setBackgroundResource(R.drawable.circle_deactive_icon);
		}
		
		convertView.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				/*
				 * Toast.makeText(activity,
				 * child.get(childPosition).getMaSukien(),
				 * Toast.LENGTH_SHORT).show();
				 */
				//sukienDetailDialog(activity, child.get(childPosition));
			}
		});

		return convertView;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.hoctap_lichhoc_day_group_layout, null);
		}
		TextView tvDayDate = (TextView) convertView.findViewById(R.id.tvDayDate);
		tvDayDate.setText(""  + parentItems.get(groupPosition).getDayDate().getDate());
		GradientDrawable d = (GradientDrawable) tvDayDate.getBackground();
		//d.setColor(Color.BLUE);
		d.setColor(parentItems.get(groupPosition).getMau());

		TextView tvDayName = (TextView) convertView.findViewById(R.id.dayName);
		tvDayName.setText(parentItems.get(groupPosition).getDayName() + " ");

		TextView tvNumber = (TextView) convertView.findViewById(R.id.numberOfContents);
		tvNumber.setText("(" + parentItems.get(groupPosition).getNumberTotal() +")");

		return convertView;
	}

	@Override
	public Object getChild(int groupPosition, int childPosition) {
		return null;
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		return 0;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return ((ArrayList<String>) childtems.get(groupPosition)).size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return null;
	}

	@Override
	public int getGroupCount() {
		return parentItems.size();
	}

	@Override
	public void onGroupCollapsed(int groupPosition) {
		super.onGroupCollapsed(groupPosition);
	}

	@Override
	public void onGroupExpanded(int groupPosition) {
		super.onGroupExpanded(groupPosition);
	}

	@Override
	public long getGroupId(int groupPosition) {
		return 0;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return false;
	}

}
