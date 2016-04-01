package com.doan.adapter;

import java.util.ArrayList;

import com.doan.lichhoctap.R;
import com.doan.model.DayInWeek;
import com.doan.model.ThongBao;
import com.doan.model.ThongBaoTrongNgay;
import com.doan.model.TietHoc;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

public class ThongBaoAdapter extends BaseExpandableListAdapter {

	private Activity activity;
	private ArrayList<Object> childtems;
	private LayoutInflater inflater;
	private ArrayList<ThongBaoTrongNgay> parentItems;
	private ArrayList<ThongBao> child;

	public ThongBaoAdapter(ArrayList<ThongBaoTrongNgay> parents, ArrayList<Object> childern, Activity activity) {
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

		child = (ArrayList<ThongBao>) childtems.get(groupPosition);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.thong_bao_child_item, null);
		}

		TextView tvNoiDung = (TextView) convertView.findViewById(R.id.tvThongBaoContents);
		tvNoiDung.setText(child.get(childPosition).getNoiDungThongBao());

		return convertView;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.thong_bao_parent_item, null);
		}
		ExpandableListView eLV = (ExpandableListView) parent;
	    eLV.expandGroup(groupPosition);
		TextView tvDate = (TextView) convertView.findViewById(R.id.tvDateParentItem);
		tvDate.setText(parentItems.get(groupPosition).getNgayCuThe()+"");
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
