package com.doan.adapter;

import java.util.ArrayList;

import com.doan.lichhoctap.R;
import com.doan.model.DiemHocTap;
import com.doan.model.DieuLe;
import com.doan.model.MonHoc;
import com.doan.model.MonHocTienQuyet;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DieuLeAdapter extends BaseExpandableListAdapter {

	private Activity activity;
	private ArrayList<Object> childtems;
	private LayoutInflater inflater;
	private ArrayList<String> parentItems;

	public DieuLeAdapter(ArrayList<String> parents, ArrayList<Object> childern, 
			Activity c) {
		this.parentItems = parents;
		this.childtems = childern;
		this.activity = c;
		this.inflater = c.getLayoutInflater();
		/*this.arrColor = arrColor;
		this.arrDiem = arrDiem;
		this.arrStatus = arrStatus;*/
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {

		String child = childtems.get(groupPosition).toString();
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.so_tay_thong_tin_child_item, null);
		}
		TextView tvNoiDung = (TextView) convertView.findViewById(R.id.tvChildDieuLe);
		tvNoiDung.setText(child);
		
		return convertView;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.so_tay_thong_tin_parent_item, null);
		}
		TextView tvParentDieuLe = (TextView) convertView.findViewById(R.id.tvParentDieuLe);
		tvParentDieuLe.setText(parentItems.get(groupPosition).toString());
		
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
