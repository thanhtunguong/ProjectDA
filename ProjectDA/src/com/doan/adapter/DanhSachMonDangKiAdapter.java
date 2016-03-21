package com.doan.adapter;

import java.util.ArrayList;

import com.doan.lichhoctap.R;
import com.doan.model.DayInWeek;
import com.doan.model.MonHoc;
import com.doan.model.MonHocTienQuyet;
import com.doan.model.TietHoc;

import android.app.Activity;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DanhSachMonDangKiAdapter extends BaseExpandableListAdapter {

	private Activity activity;
	private ArrayList<Object> childtems;
	private LayoutInflater inflater;
	private ArrayList<MonHoc> parentItems;
	private ArrayList<MonHocTienQuyet> child;

	public DanhSachMonDangKiAdapter(ArrayList<MonHoc> parents, ArrayList<Object> childern, Activity c) {
		this.parentItems = parents;
		this.childtems = childern;
		this.activity = c;
		this.inflater = c.getLayoutInflater();
	}

	/*public void setInflater(LayoutInflater inflater, Activity activity) {
		this.inflater = inflater;
		this.activity = activity;
	}*/

	@Override
	public View getChildView(int groupPosition, final int childPosition, boolean isLastChild, View convertView,
			ViewGroup parent) {

		child = (ArrayList<MonHocTienQuyet>) childtems.get(groupPosition);
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.danh_sach_mon_dang_ki_child_item, null);
		}
		TextView tvChildMonHoc;
		tvChildMonHoc = (TextView) convertView.findViewById(R.id.tvChildTenMon);
		tvChildMonHoc.setText(child.get(childPosition).getTenMon_MHTQ());

		return convertView;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.danh_sach_mon_dang_ki_mon_hoc_parent_item, null);
		}
		TextView tvParentMonHoc = (TextView) convertView.findViewById(R.id.tvParentTenMonHocCTDT);
		tvParentMonHoc.setText(""  + parentItems.get(groupPosition).getTenMon());
		/*GradientDrawable d = (GradientDrawable) tvDayDate.getBackground();
		//d.setColor(Color.BLUE);
		d.setColor(parentItems.get(groupPosition).getMau());

		TextView tvDayName = (TextView) convertView.findViewById(R.id.dayName);
		tvDayName.setText(parentItems.get(groupPosition).getDayName() + " ");

		TextView tvNumber = (TextView) convertView.findViewById(R.id.numberOfContents);
		tvNumber.setText("(" + parentItems.get(groupPosition).getNumberTotal() +")");*/

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
