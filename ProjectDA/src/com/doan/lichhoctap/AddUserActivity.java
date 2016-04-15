package com.doan.lichhoctap;

import java.util.ArrayList;
import java.util.List;

import com.doan.adapter.AddSVTBAdapter;
import com.doan.database_handle.ExecuteQuery;
import com.doan.model.LopHanhChinh;
import com.doan.model.SinhVienThongBao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.EventLog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class AddUserActivity extends ActionBarActivity {
	private ArrayList<String> arrMaLop;
	private ArrayList<Object> childern;
	private ArrayList<SinhVienThongBao> arrSinhVienNhanThongBao;
	private ArrayList<LopHanhChinh> arrayLopHanhChinh;
	private Activity activity;
	private ExecuteQuery exeQ;
	private AddSVTBAdapter2 adapter;
	private ExpandableListView elv_SinhVien;
	private Toolbar toolbar;
	private TextView tvNumberSVSelected;
	private ImageView btnAddSVTBSelectedAll, btnAddSVTBDeselectedAll;
	private Boolean checkChonTatCa;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_user);
		toolbar = (Toolbar) findViewById(R.id.add_user_activity_tool_bar);
		setSupportActionBar(toolbar);

		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		checkChonTatCa = false;
		tvNumberSVSelected = (TextView) findViewById(R.id.tvNumberSVSelected);
		ImageView tvXong = (ImageView) toolbar.findViewById(R.id.btnDoneAddSV);
		btnAddSVTBSelectedAll = (ImageView) findViewById(R.id.btnAddSVTBSelectedAll);
		btnAddSVTBDeselectedAll = (ImageView) findViewById(R.id.btnAddSVTBDeselectedAll);
		tvXong.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent();
				intent.putExtra("danhsachSVDone", arrayLopHanhChinh);
				setResult(RESULT_OK, intent);
				finish();
			}
		});

		activity = this;
		exeQ = new ExecuteQuery(activity);
		exeQ.open();
		arrayLopHanhChinh = new ArrayList<LopHanhChinh>();
		arrMaLop = new ArrayList<String>();
		//arrSinhVienNhanThongBao = new ArrayList<SinhVienThongBao>();
		childern = new ArrayList<Object>();
		elv_SinhVien = (ExpandableListView) findViewById(R.id.elvAllSinhVienThongBao);
		Intent callerIntent = getIntent();
		Bundle bundle = callerIntent.getExtras();
		//mathongbao = bundle.getString("mathongbaoExtra");
		//arrMaLop = exeQ.getAllLopHanhChinhSqLite();
		arrMaLop = bundle.getStringArrayList("danhsachMaLop");
		
		/*for (String malop : arrMaLop) {
			LopHanhChinh lhc = new LopHanhChinh();
			lhc.setMaLopHanhChinh(malop);
			lhc.setArrSinhVien(exeQ.getAllSinhVienThongBaoTheoMaLopSqLite(malop));
			lhc.setCheck(0);
			childern.add(lhc.getArrSinhVien());
			arrayLopHanhChinh.add(lhc);
		}*/
		arrayLopHanhChinh = (ArrayList<LopHanhChinh>) bundle.getSerializable("danhsachBanDau");
		for (LopHanhChinh lop : arrayLopHanhChinh) {
			childern.add(lop.getArrSinhVien());
		}
		adapter = new AddSVTBAdapter2(arrMaLop, childern, activity);
		elv_SinhVien.setAdapter(adapter);
		elv_SinhVien.setGroupIndicator(null);
		elv_SinhVien.setClickable(true);
		for(int i = 0; i < childern.size(); i++){
		}
		/*elv_SinhVien.setOnChildClickListener(new OnChildClickListener() {
			
			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
				// TODO Auto-generated method stub
				CheckBox cb = (CheckBox) v.findViewById(R.id.cbSendThongBaoSelected);
				Boolean b = cb.isChecked();
				SinhVienThongBao sv = new SinhVienThongBao();
				sv = arrayLopHanhChinh.get(groupPosition).getArrSinhVien().get(childPosition);
				if(b == false){
					cb.setChecked(true);
					//arrSinhVienNhanThongBao.add(sv);
					ArrayList<SinhVienThongBao> artest = new ArrayList<SinhVienThongBao>();
					//artest.addAll(arrSinhVienNhanThongBao);
				}else {
					cb.setChecked(false);
					//arrSinhVienNhanThongBao.remove(arrSinhVienNhanThongBao.indexOf(sv));
					ArrayList<SinhVienThongBao> artest = new ArrayList<SinhVienThongBao>();
					//artest.addAll(arrSinhVienNhanThongBao);
				}
				Boolean b = cb.isChecked();
				SinhVienThongBao sv = new SinhVienThongBao();
				sv = arrayLopHanhChinh.get(groupPosition).getArrSinhVien().get(childPosition);
				if(b == true){
					//cbox.setChecked(true);
					arrayLopHanhChinh.get(groupPosition).getArrSinhVien().get(childPosition).setChecked(1);
					childern = new ArrayList<Object>();
					for (LopHanhChinh lop : arrayLopHanhChinh) {
						childern.add(lop.getArrSinhVien());
					}
					//arrSinhVienNhanThongBao.add(sv);
					ArrayList<SinhVienThongBao> artest = new ArrayList<SinhVienThongBao>();
					//artest.addAll(arrSinhVienNhanThongBao);
					
					adapter = new AddSVTBAdapter2(arrMaLop, childern, activity);
					int index = elv_SinhVien.getFirstVisiblePosition();
					View viewLV = elv_SinhVien.getChildAt(0);
					int top = (viewLV == null) ? 0 : (viewLV.getTop() - elv_SinhVien.getPaddingTop());
					elv_SinhVien.setAdapter(adapter);
					elv_SinhVien.setSelectionFromTop(index, top);
					int t = 0;
				}else {
					//cbox.setChecked(false);
					arrayLopHanhChinh.get(groupPosition).getArrSinhVien().get(childPosition).setChecked(0);
					childern = new ArrayList<Object>();
					for (LopHanhChinh lop : arrayLopHanhChinh) {
						childern.add(lop.getArrSinhVien());
					}
					//arrSinhVienNhanThongBao.remove(arrSinhVienNhanThongBao.indexOf(sv));
					ArrayList<SinhVienThongBao> artest = new ArrayList<SinhVienThongBao>();
					//artest.addAll(arrSinhVienNhanThongBao);
					
					adapter = new AddSVTBAdapter2(arrMaLop, childern, activity);
					int index = elv_SinhVien.getFirstVisiblePosition();
					View viewLV = elv_SinhVien.getChildAt(0);
					int top = (viewLV == null) ? 0 : (viewLV.getTop() - elv_SinhVien.getPaddingTop());
					elv_SinhVien.setAdapter(adapter);
					elv_SinhVien.setSelectionFromTop(index, top);
					int t = 0;
				}
				return false;
			}
		});*/
		demSinhVien();
		exeQ.close();
		btnAddSVTBSelectedAll.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for (LopHanhChinh lhc : arrayLopHanhChinh) {
					for (SinhVienThongBao sv : lhc.getArrSinhVien()) {
						sv.setChecked(1);
					}
				}
				childern = new ArrayList<Object>();
				for (LopHanhChinh lop : arrayLopHanhChinh) {
					childern.add(lop.getArrSinhVien());
				}
				Parcelable state = elv_SinhVien.onSaveInstanceState();
				adapter.notifyDataSetChanged();
				elv_SinhVien.onRestoreInstanceState(state);
				checkChonTatCa = true;
				demSinhVien();
			}
		});
		btnAddSVTBDeselectedAll.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				for (LopHanhChinh lhc : arrayLopHanhChinh) {
					for (SinhVienThongBao sv : lhc.getArrSinhVien()) {
						sv.setChecked(0);
					}
				}
				childern = new ArrayList<Object>();
				for (LopHanhChinh lop : arrayLopHanhChinh) {
					childern.add(lop.getArrSinhVien());
				}
				Parcelable state = elv_SinhVien.onSaveInstanceState();
				adapter.notifyDataSetChanged();
				elv_SinhVien.onRestoreInstanceState(state);
				checkChonTatCa = false;
				demSinhVien();
			}
		});
	}

	private void demSinhVien(){
		int dem = 0;
		ArrayList<String> arDemLop = new ArrayList<String>();
		for (LopHanhChinh lhc : arrayLopHanhChinh) {
			for (SinhVienThongBao sv : lhc.getArrSinhVien()) {
				if(sv.getChecked() == 1){
					dem = dem + 1;
					if(arDemLop.size() > 0){
						Boolean check = false;
						for (String string : arDemLop) {
							if(string.matches(sv.getMaLopHanhChinh())){
								check = true;
								break;
							}
						}
						if(check == false){
							arDemLop.add(sv.getMaLopHanhChinh());
						}
					}else {
						arDemLop.add(sv.getMaLopHanhChinh());
					}
				}
			}
		}
		if(dem > 0){
			tvNumberSVSelected.setText(dem + " " + getString(R.string.string_sinhvien_duoc_chon_tu) 
										+ " " + arDemLop.size() + " " + getString(R.string.string_lop));
		}else {
			tvNumberSVSelected.setText(getString(R.string.string_chua_sv_nao_dc_chon));
		}
	}

	@Override
	public void onBackPressed() {
	    // your code.
		Intent intent = new Intent();
		intent.putExtra("danhsachSVDone", arrayLopHanhChinh);
		setResult(RESULT_OK, intent);
		finish();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_user, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
	
	//class adapter
	class AddSVTBAdapter2 extends BaseExpandableListAdapter {

		private Activity activity;
		private ArrayList<Object> childtems;
		private LayoutInflater inflater;
		private ArrayList<String> parentItems;
		private ArrayList<SinhVienThongBao> child;

		public AddSVTBAdapter2(ArrayList<String> parents, ArrayList<Object> childern, Activity activity) {
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
		public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView,
				ViewGroup parent) {
			
			child = (ArrayList<SinhVienThongBao>) childtems.get(groupPosition);
			SinhVienThongBao sv = child.get(childPosition);

			TextView tvMaSinhVien, tvSTT, tvNgaySinh, tvTen, tvGioiTinh;

			if (convertView == null) {
				convertView = inflater.inflate(R.layout.danh_sach_sinh_vien_thong_bao_child_item, null);
			}
			tvMaSinhVien = (TextView) convertView.findViewById(R.id.tvSVTBchildMaSV);
			tvSTT = (TextView) convertView.findViewById(R.id.tvSVTBchildSTT);
			tvTen = (TextView) convertView.findViewById(R.id.tvSVTBchildTenSV);
			tvNgaySinh = (TextView) convertView.findViewById(R.id.tvSVTBchildNgaySinh);
			tvGioiTinh = (TextView) convertView.findViewById(R.id.tvSVTBchildGioiTinh);
			
			tvMaSinhVien.setText(child.get(childPosition).getMaSV());
			int stt = childPosition + 1;
			tvSTT.setText(stt + "");
			tvTen.setText(sv.getHoTenSinhVien());
			tvNgaySinh.setText(sv.getNgaySinhSinhVien());
			tvGioiTinh.setText(sv.getGioiTinhSinhVien());
			
			final CheckBox cbox = (CheckBox) convertView.findViewById(R.id.cbSendThongBaoSelected);
			setCheckChildCheckBox(cbox, childPosition);
			cbox.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Boolean b = cbox.isChecked();
					SinhVienThongBao sv = new SinhVienThongBao();
					sv = arrayLopHanhChinh.get(groupPosition).getArrSinhVien().get(childPosition);
					if(b == true){
						//cbox.setChecked(true);
						arrayLopHanhChinh.get(groupPosition).getArrSinhVien().get(childPosition).setChecked(1);
						demSinhVien();
						childern = new ArrayList<Object>();
						for (LopHanhChinh lop : arrayLopHanhChinh) {
							childern.add(lop.getArrSinhVien());
						}
						Parcelable state = elv_SinhVien.onSaveInstanceState();
						notifyDataSetChanged();
						elv_SinhVien.onRestoreInstanceState(state);
					}else {
						//cbox.setChecked(false);
						arrayLopHanhChinh.get(groupPosition).getArrSinhVien().get(childPosition).setChecked(0);
						demSinhVien();
						childern = new ArrayList<Object>();
						for (LopHanhChinh lop : arrayLopHanhChinh) {
							childern.add(lop.getArrSinhVien());
						}
						Parcelable state = elv_SinhVien.onSaveInstanceState();
						notifyDataSetChanged();
						elv_SinhVien.onRestoreInstanceState(state);
					}
				}
			});
			return convertView;
		}
		private void setCheckChildCheckBox(CheckBox cbox, int childPosition){
			if(child.get(childPosition).getChecked() == 0){
				cbox.setChecked(false);
			}else {
				cbox.setChecked(true);
			}
		}
		private void setCheckParentButton(TextView tvBtn, int groupPosition){
			if(arrayLopHanhChinh.get(groupPosition).getCheck() == 0){
				tvBtn.setText(activity.getString(R.string.btn_select_svtb_all_lop));
			}else {
				tvBtn.setText(activity.getString(R.string.btn_deselect_svtb_all));
			}
		}

		@Override
		public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, final ViewGroup parent) {
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.danh_sach_sinh_vien_thong_bao_parent, null);
			}
			TextView tvSTT = (TextView) convertView.findViewById(R.id.tvSTTLop);
			int STT = groupPosition + 1;
			tvSTT.setText(STT + "");
			TextView tvMaLop = (TextView) convertView.findViewById(R.id.tvSVTBMaLop);
			tvMaLop.setText(parentItems.get(groupPosition).toString());
			final TextView tvBtn = (TextView) convertView.findViewById(R.id.btnAddSVSelectAllLop);
			setCheckParentButton(tvBtn, groupPosition);
			tvBtn.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					String str = tvBtn.getText().toString();
					if(str.matches(activity.getString(R.string.btn_select_svtb_all_lop))){
						arrayLopHanhChinh.get(groupPosition).setCheck(1);
						for (SinhVienThongBao sv : arrayLopHanhChinh.get(groupPosition).getArrSinhVien()) {
							sv.setChecked(1);
						}
						tvBtn.setText(activity.getString(R.string.btn_deselect_svtb_all));
						childern = new ArrayList<Object>();
						for (LopHanhChinh lop : arrayLopHanhChinh) {
							childern.add(lop.getArrSinhVien());
						}
						Parcelable state = elv_SinhVien.onSaveInstanceState();
						notifyDataSetChanged();
						elv_SinhVien.onRestoreInstanceState(state);
					}else {
						arrayLopHanhChinh.get(groupPosition).setCheck(0);
						for (SinhVienThongBao sv : arrayLopHanhChinh.get(groupPosition).getArrSinhVien()) {
							sv.setChecked(0);
						}
						tvBtn.setText(activity.getString(R.string.btn_select_svtb_all_lop));
						childern = new ArrayList<Object>();
						for (LopHanhChinh lop : arrayLopHanhChinh) {
							childern.add(lop.getArrSinhVien());
						}
						Parcelable state = elv_SinhVien.onSaveInstanceState();
						notifyDataSetChanged();
						elv_SinhVien.onRestoreInstanceState(state);
					}
				}
			});
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
		class ViewHolder {
            LinearLayout linearLayout;
            TextView tvSV;
            CheckBox cb;
        }
	}

}
