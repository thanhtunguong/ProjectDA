package com.doan.adapter;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import com.doan.app.Global;
import com.doan.lichhoctap.R;
import com.doan.model.ChiTietThongBao;
import com.doan.model.ReplyTrongNgay;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class ChiTietThongBaoAdapter extends BaseExpandableListAdapter {

	private Activity activity;
	private ArrayList<Object> childtems;
	private LayoutInflater inflater;
	private ArrayList<ReplyTrongNgay> parentItems;
	private ArrayList<ChiTietThongBao> child;
	private TextView tvNoiDung, tvThoiGian;

	public ChiTietThongBaoAdapter(ArrayList<ReplyTrongNgay> parents, ArrayList<Object> childern, Activity activity) {
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

		child = (ArrayList<ChiTietThongBao>) childtems.get(groupPosition);
		int layoutId = child.get(childPosition).getLayoutId();
		
		if (convertView == null) {
			convertView = inflater.inflate(activity.getResources().getLayout(layoutId), null);
		}
		tvNoiDung = (TextView) convertView.findViewById(R.id.tvChiTietThongBaoReplyNoiDung);
		tvThoiGian = (TextView) convertView.findViewById(R.id.tvChiTietThongBaoReplyTime);
		tvNoiDung.setText(child.get(childPosition).getNoiDungReply());
		String gioPhut = child.get(childPosition).getThoiGianTraLoi().substring(11, 16);
		tvThoiGian.setText(gioPhut);
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
		Date date = parentItems.get(groupPosition).getNgay();
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		
		String thoigian = c.get(Calendar.DAY_OF_WEEK) + ", " 
				+ c.get(Calendar.DAY_OF_MONTH) + "/" + c.get(Calendar.MONTH) + "/" + c.get(Calendar.YEAR);
		//tvDate.setText(parentItems.get(groupPosition).getNgayCuThe()+"");
		tvDate.setText(getNgayThang(c));
		return convertView;
	}
	private String getNgayThang(Calendar c) {
		int date = c.get(Calendar.DATE);
		int day = c.get(Calendar.DAY_OF_WEEK);
		int year = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH);
		
		String month = "";
		switch (m) {
		case Calendar.JANUARY:
			month = activity.getString(R.string.string_January);
			break;
		case Calendar.FEBRUARY:
			month = activity.getString(R.string.string_February);
			break;
		case Calendar.MARCH:
			month = activity.getString(R.string.string_March);
			break;
		case Calendar.APRIL:
			month = activity.getString(R.string.string_April);
			break;
		case Calendar.MAY:
			month = activity.getString(R.string.string_May);
			break;
		case Calendar.JUNE:
			month = activity.getString(R.string.string_June);
			break;
		case Calendar.JULY:
			month = activity.getString(R.string.string_July);
			break;
		case Calendar.AUGUST:
			month = activity.getString(R.string.string_August);
			break;
		case Calendar.SEPTEMBER:
			month = activity.getString(R.string.string_September);
			break;
		case Calendar.OCTOBER:
			month = activity.getString(R.string.string_October);
			break;
		case Calendar.NOVEMBER:
			month = activity.getString(R.string.string_November);
			break;
		case Calendar.DECEMBER:
			month = activity.getString(R.string.string_December);
			break;
		}
		String resultDayName = "";
		switch (day) {
		case Calendar.MONDAY:
			resultDayName = activity.getString(R.string.string_Monday);
			break;
		case Calendar.TUESDAY:
			resultDayName = activity.getString(R.string.string_Tuesday);
			break;
		case Calendar.WEDNESDAY:
			resultDayName = activity.getString(R.string.string_Wednesday);
			break;
		case Calendar.THURSDAY:
			resultDayName = activity.getString(R.string.string_Thursday);
			break;
		case Calendar.FRIDAY:
			resultDayName = activity.getString(R.string.string_Friday);
			break;
		case Calendar.SATURDAY:
			resultDayName = activity.getString(R.string.string_Saturday);
			break;
		case Calendar.SUNDAY:
			resultDayName = activity.getString(R.string.string_Sunday);
			break;
		}

		
		String resultDate = resultDayName + ", " + date + "/" + month + "/" + year;
		return resultDate;
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
