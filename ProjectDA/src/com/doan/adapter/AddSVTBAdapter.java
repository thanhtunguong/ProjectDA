package com.doan.adapter;

import java.util.ArrayList;

import com.doan.lichhoctap.R;
import com.doan.model.DayInWeek;
import com.doan.model.SinhVienThongBao;
import com.doan.model.TietHoc;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class AddSVTBAdapter extends BaseExpandableListAdapter {

	private Activity activity;
	private ArrayList<Object> childtems;
	private LayoutInflater inflater;
	private ArrayList<String> parentItems;
	private ArrayList<SinhVienThongBao> child;

	public AddSVTBAdapter(ArrayList<String> parents, ArrayList<Object> childern, Activity activity) {
		this.parentItems = parents;
		this.childtems = childern;
		this.activity = activity;
		this.inflater = activity.getLayoutInflater();
	}

	/*public void setInflater(LayoutInflater inflater, Activity activity) {
		this.inflater = inflater;
		this.activity = activity;
	}*/

	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {

		child = (ArrayList<SinhVienThongBao>) childtems.get(groupPosition);

		TextView tvMaSinhVien;

		if (convertView == null) {
			convertView = inflater.inflate(R.layout.danh_sach_sinh_vien_thong_bao_child_item, null);
		}
		tvMaSinhVien = (TextView) convertView.findViewById(R.id.tvSVTBchildMaSV);
		tvMaSinhVien.setText(child.get(childPosition).getMaSV());
		return convertView;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.danh_sach_sinh_vien_thong_bao_parent, null);
		}
		TextView tvMaLop = (TextView) convertView.findViewById(R.id.tvSVTBMaLop);
		tvMaLop.setText(parentItems.get(groupPosition).toString());

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
