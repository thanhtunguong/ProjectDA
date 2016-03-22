package com.doan.adapter;

import java.util.ArrayList;

import com.doan.lichhoctap.R;
import com.doan.model.DayInWeek;
import com.doan.model.DiemHocTap;
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
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

public class DanhSachMonDangKiAdapter extends BaseExpandableListAdapter {

	private Activity activity;
	private ArrayList<Object> childtems;
	private LayoutInflater inflater;
	private ArrayList<MonHoc> parentItems;
	private ArrayList<MonHocTienQuyet> child;
	private ArrayList<Integer> arrColor;
	private ArrayList<Integer> arrStatus;
	private ArrayList<DiemHocTap> arrDiem;

	public DanhSachMonDangKiAdapter(ArrayList<MonHoc> parents, ArrayList<Object> childern, 
			Activity c, ArrayList<Integer> arrColor, ArrayList<DiemHocTap> arrDiem, ArrayList<Integer> arrStatus) {
		this.parentItems = parents;
		this.childtems = childern;
		this.activity = c;
		this.inflater = c.getLayoutInflater();
		this.arrColor = arrColor;
		this.arrDiem = arrDiem;
		this.arrStatus = arrStatus;
	}

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
		int status = 4;
		for (DiemHocTap diem : arrDiem) {
			if(child.get(childPosition).getTenMon_MHTQ().matches(diem.getTenMonHoc())){
				status = 1;
			}
		}
		ImageView ivChildStatus = (ImageView) convertView.findViewById(R.id.ivChildStatus);
		setIcon(childPosition, status, ivChildStatus);
		
		return convertView;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.danh_sach_mon_dang_ki_mon_hoc_parent_item, null);
		}
		TextView tvParentMonHoc = (TextView) convertView.findViewById(R.id.tvParentTenMonHocCTDT);
		tvParentMonHoc.setText(""  + parentItems.get(groupPosition).getTenMon());
		TextView tvKiMon = (TextView) convertView.findViewById(R.id.tvKiMon);
		String maki = parentItems.get(groupPosition).getMaMonCTDT().charAt(3) + "";
		String kitudau = parentItems.get(groupPosition).getTenMon().charAt(0) + "";
		tvKiMon.setText(kitudau);
		GradientDrawable d = (GradientDrawable) tvKiMon.getBackground();
		switch(maki){
		case "1":
			d.setColor(arrColor.get(0));
			break;
		case "2":
			d.setColor(arrColor.get(2));
			break;
		case "3":
			d.setColor(arrColor.get(3));
			break;
		case "4":
			d.setColor(arrColor.get(4));
			break;
		case "5":
			d.setColor(arrColor.get(5));
			break;
		case "6":
			d.setColor(arrColor.get(6));
			break;
		case "7":
			d.setColor(arrColor.get(7));
			break;
		case "8":
			d.setColor(arrColor.get(8));
			break;
		}
		ImageView ivStatus = (ImageView) convertView.findViewById(R.id.ivStatus);
		String tenmon = parentItems.get(groupPosition).getTenMon();
		int t = groupPosition;
		if(t == 12){
			t = 0;
		}
		MonHoc mh = parentItems.get(groupPosition);
		int x = arrStatus.get(groupPosition);
		setIcon(groupPosition, x, ivStatus);
		/*switch (arrStatus.get(groupPosition)) {
		case 0:
			ivStatus.setBackgroundResource(R.drawable.ic_mon_hoc_available);
			break;
		case 1:
			ivStatus.setBackgroundResource(R.drawable.ic_mon_hoc_done);
			break;
		case 2:
			ivStatus.setBackgroundResource(R.drawable.ic_mon_hoc_available);
			break;
		case 3:
			ivStatus.setBackgroundResource(R.drawable.ic_mon_hoc_pending);
			break;
		case 4:
			ivStatus.setBackgroundResource(R.drawable.circle_deactive_icon);
			break;
		}*/
		
		TextView tvNumberMTQ = (TextView) convertView.findViewById(R.id.tvMonTienQuyetNumber);
		int n = parentItems.get(groupPosition).getArrMonTienQuyet().size();
			tvNumberMTQ.setText("(" + n + ")");
		return convertView;
	}
	private void setIcon(int position, int status, ImageView ivStatus){
		switch (status) {
		case 0:
			ivStatus.setBackgroundResource(R.drawable.ic_mon_hoc_available);
			break;
		case 1:
			ivStatus.setBackgroundResource(R.drawable.ic_mon_hoc_done);
			break;
		case 2:
			ivStatus.setBackgroundResource(R.drawable.ic_mon_hoc_available);
			break;
		case 3:
			ivStatus.setBackgroundResource(R.drawable.ic_mon_hoc_pending);
			break;
		case 4:
			ivStatus.setBackgroundResource(R.drawable.circle_deactive_icon);
			break;
		}
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
