package com.doan.lichhoctap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Random;
import com.doan.database_handle.ExecuteQuery;
import com.doan.model.DiemHocTap;
import com.doan.model.MonHoc;
import com.doan.model.MonHocAndStatus;
import com.doan.model.MonHocTienQuyet;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class DanhSachMonCoTheDangKiActivity extends ActionBarActivity {

	private ExecuteQuery exeQ;
	private ArrayList<MonHoc> arMonHoc;
	private ArrayList<MonHoc> arrMonHocKi1, arrMonHocKi2, arrMonHocKi3, arrMonHocKi4, 
								arrMonHocKi5, arrMonHocKi6, arrMonHocKi7, arrMonHocKi8;
	private ArrayList<DiemHocTap> arrDiem;
	private Activity c;
	private ExpandableListView elv_MonHoc;
	private DanhSachMonDangKiAdapter2 adapter;
	private ArrayList<MonHoc> parentItems;
	private ArrayList<Object> childItems;
	private ArrayList<Integer> arrStatus = new ArrayList<Integer>();
	private ArrayList<Integer> arrStatusTemp = new ArrayList<Integer>();
	private ArrayList<Integer> arrColor = new ArrayList<Integer>();
	private int checkEditOption;
	private Button btnEditDSMH, btnSortDsdk;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_danh_sach_mon_co_the_dang_ki_test);
		Toolbar toolbar = (Toolbar) findViewById(R.id.danh_sach_mon_hoc_activity_tool_bar);
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		c = this;
		elv_MonHoc = (ExpandableListView) findViewById(R.id.elvAllMonHoc);
		
		btnSortDsdk = (Button) findViewById(R.id.btnSort);
		btnSortDsdk.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialogSortDSDK(c);
			}
		});
		
		btnEditDSMH = (Button) findViewById(R.id.btnEditDSMH);
		btnEditDSMH.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				doiBieuTuongEdit();
			}
		});
		initiatDSMH();
	}
	private void initiatDSMH(){
		arrStatus = new ArrayList<Integer>();
		arrStatusTemp = new ArrayList<Integer>();
		exeQ = new ExecuteQuery(this);
		arMonHoc = new ArrayList<MonHoc>();
		arMonHoc = exeQ.getAllMonHoc();
		arrDiem = getAllDiemQuaMon();
		phanChiaMonHocKi();
		setGroupParents();
		arrStatus = xacDinhStatus2();
		arrStatusTemp.addAll(arrStatus);
		setList(elv_MonHoc, adapter, parentItems, childItems, arrStatus);
	}
	
	private void setList(ExpandableListView lv, DanhSachMonDangKiAdapter2 adapter, 
			ArrayList<MonHoc> arMH, ArrayList<Object> arrObj, ArrayList<Integer> arrStatus){
		lv.setClickable(true);
		lv.setGroupIndicator(null);
		intTaoMau();
		adapter = new DanhSachMonDangKiAdapter2(arMH, arrObj, c, arrColor, arrStatusTemp, R.layout.danh_sach_mon_dang_ki_mon_hoc_parent_item);
		lv.setAdapter(adapter);
	}

	private ArrayList<Integer> xacDinhStatus2() {
		for (int i = 0; i < arMonHoc.size(); i++) {
			MonHoc mh = arMonHoc.get(i);
			int check = 0;
			for (DiemHocTap diemht : arrDiem) {
				if(diemht.getTenMonHoc().matches(mh.getTenMon())){
					check = 1;
					break;
				}
			}
			if(check == 1){
				arrStatus.set(i, 1); //mon da hoc
			}else {
				int x = mh.getArrMonTienQuyet().size();
				int biendem = 0;
				if (x == 0) {
					arrStatus.set(i, 0); //mon k can tien quyet
				}else {
					for(int j = 0; j < x; j++){
						MonHocTienQuyet mhtq = mh.getArrMonTienQuyet().get(j);
						for (DiemHocTap diem : arrDiem) {
							if(diem.getTenMonHoc().matches(mhtq.getTenMon_MHTQ())){
								biendem = biendem + 1;
							}
						}
					}
					if(biendem == x){
						arrStatus.set(i, 2); //mon du dieu kien tien quyet
					}else {
						if(biendem > 0){
							arrStatus.set(i, 3); //mon thieu dieu kien tien quyet
						}else {
							if(biendem == 0){
								arrStatus.set(i, 4); //mon chua duoc hoc
							}
						}
					}
				}
			}
		}
		return arrStatus;
	}
	private ArrayList<Integer> xacDinhStatusKichBan() {
		for (int i = 0; i < arMonHoc.size(); i++) {
			MonHoc mh = arMonHoc.get(i);
			int check = 0;	
			if(arrStatus.get(i) == 1){
				check = 1;
			}			
			if(check == 1){
				arrStatus.set(i, 1); //mon da hoc
			}else {
				int x = mh.getArrMonTienQuyet().size();
				int biendem = 0;
				if (x == 0) {
					arrStatus.set(i, 0); //mon k can tien quyet
				}else {
					for(int j = 0; j < x; j++){
						MonHocTienQuyet mhtq = mh.getArrMonTienQuyet().get(j);
						for (MonHoc monhoc : arMonHoc) {
							if(mhtq.getTenMon_MHTQ().matches(monhoc.getTenMon())){
								int index = arMonHoc.indexOf(monhoc);
								if(arrStatus.get(index) == 1){
									biendem = biendem + 1;
								}
							}
						}
					}
					if(biendem == x){
						arrStatus.set(i, 2); //mon du dieu kien tien quyet
					}else {
						if(biendem > 0){
							arrStatus.set(i, 3); //mon thieu dieu kien tien quyet
						}else {
							if(biendem == 0){
								arrStatus.set(i, 4); //mon chua duoc hoc
							}
						}
					}
				}
			}
		}
		return arrStatus;
	}
	
	private void phanChiaMonHocKi(){
		arrMonHocKi1 = new ArrayList<MonHoc>();
		arrMonHocKi2 = new ArrayList<MonHoc>();
		arrMonHocKi3 = new ArrayList<MonHoc>();
		arrMonHocKi4 = new ArrayList<MonHoc>();
		arrMonHocKi5 = new ArrayList<MonHoc>();
		arrMonHocKi6 = new ArrayList<MonHoc>();
		arrMonHocKi7 = new ArrayList<MonHoc>();
		arrMonHocKi8 = new ArrayList<MonHoc>();
		
		arrStatus = new ArrayList<Integer>();
		for (MonHoc mh : arMonHoc) {
			String maki = mh.getMaMonCTDT().charAt(3) + "";
			arrStatus.add(3);
			switch(maki){
			case "1":
				arrMonHocKi1.add(mh);
				break;
			case "2":
				arrMonHocKi2.add(mh);
				break;
			case "3":
				arrMonHocKi3.add(mh);
				break;
			case "4":
				arrMonHocKi4.add(mh);
				break;
			case "5":
				arrMonHocKi5.add(mh);
				break;
			case "6":
				arrMonHocKi6.add(mh);
				break;
			case "7":
				arrMonHocKi7.add(mh);
				break;
			case "8":
				arrMonHocKi8.add(mh);
				break;
			}
		}
		arMonHoc = new ArrayList<MonHoc>();
		arMonHoc.addAll(arrMonHocKi1);
		arMonHoc.addAll(arrMonHocKi2);
		arMonHoc.addAll(arrMonHocKi3);
		arMonHoc.addAll(arrMonHocKi4);
		arMonHoc.addAll(arrMonHocKi5);
		arMonHoc.addAll(arrMonHocKi6);
		arMonHoc.addAll(arrMonHocKi7);
		arMonHoc.addAll(arrMonHocKi8);
	}
	private ArrayList<DiemHocTap> getAllDiemQuaMon() {
		arrDiem = new ArrayList<DiemHocTap>();
		arrDiem = exeQ.getDiemQuaMonSV(c);
		for (int i = 0; i < arrDiem.size(); i++) {
			DiemHocTap diem1 = arrDiem.get(i);
			for (int j = 0; j < arrDiem.size(); j++) {
				DiemHocTap diem2 = arrDiem.get(j);
				if (diem1.getMaDiem().matches(diem2.getMaDiem()) == false) {
					if (diem1.getTenMonHoc().matches(diem2.getTenMonHoc())) {
						if (tinhDiemTrungBinhMon(diem1.getDiemCC(), diem1.getDiemKT(),
								diem1.getDiemThi()) > tinhDiemTrungBinhMon(diem2.getDiemCC(), diem2.getDiemKT(),
										diem2.getDiemThi())) {
							arrDiem.remove(j);
						} else {
							if (tinhDiemTrungBinhMon(diem1.getDiemCC(), diem1.getDiemKT(),
									diem1.getDiemThi()) < tinhDiemTrungBinhMon(diem2.getDiemCC(), diem2.getDiemKT(),
											diem2.getDiemThi())) {
								arrDiem.remove(i);
							} else {
								SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
								Date date1 = null;
								Date date2 = null;
								try {
									date1 = df.parse(diem1.getThoiGianDK());
									date2 = df.parse(diem2.getThoiGianDK());
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (date1.after(date2)) {
									arrDiem.remove(j);
								} else {
									if (date1.before(date2)) {
										arrDiem.remove(i);
									}
								}
							}
						}
					}
				}
			}
		}
		return arrDiem;
	}
	private float tinhDiemTrungBinhMon(float d1, float d2, float d7){
		float dtb = (d1 + d2*2 + d7*7)/10;
		return dtb;
	}
	private void setGroupParents(){
		parentItems = new ArrayList<MonHoc>();
		childItems = new ArrayList<Object>();
		for (MonHoc mh : arMonHoc) {
			parentItems.add(mh);
			childItems.add(mh.getArrMonTienQuyet());
		}
	}
	
	private void intTaoMau() {
		Random rnd = new Random();
		ArrayList<Integer> arlColor = new ArrayList<Integer>();
		//Integer mau = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
		for(int j = 0; j < 8; j++){
			Integer mau = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
			if (arlColor.size() == 0) {
				arlColor.add(mau);
				arrColor.add(mau);
			} else {
				for (int i = 0; i < arlColor.size(); i++) {
					int mau2 = arlColor.get(i);
					if (mau2 == mau) {
						intTaoMau();
					} else {
						arlColor.add(mau);
						arrColor.add(mau);
						break;
					}
				}
			}
		}
	}
	private void doiBieuTuongEdit(){
		if(checkEditOption == 0){
			checkEditOption = 1;
			btnEditDSMH.setBackgroundResource(R.drawable.ic_done_white_36dp);
			adapter = new DanhSachMonDangKiAdapter2(parentItems, childItems, c, arrColor, arrStatusTemp,
					R.layout.danh_sach_dang_ki_mon_hoc_parent_item_switch);
			int index = elv_MonHoc.getFirstVisiblePosition();
			View viewLV = elv_MonHoc.getChildAt(0);
			int top = (viewLV == null) ? 0 : (viewLV.getTop() - elv_MonHoc.getPaddingTop());
			elv_MonHoc.setAdapter(adapter);
			elv_MonHoc.setSelectionFromTop(index, top);
		}else {
			checkEditOption = 0;
			btnEditDSMH.setBackgroundResource(R.drawable.ic_create_white_36dp);
			/*adapter = new DanhSachMonDangKiAdapter2(parentItems, childItems, c, intTaoMau(),
					getAllDiemQuaMon(), arrStatus,
					R.layout.danh_sach_mon_dang_ki_mon_hoc_parent_item);*/
			int index = elv_MonHoc.getFirstVisiblePosition();
			View viewLV = elv_MonHoc.getChildAt(0);
			int top = (viewLV == null) ? 0 : (viewLV.getTop() - elv_MonHoc.getPaddingTop());
			initiatDSMH();
			elv_MonHoc.setSelectionFromTop(index, top);
		}
	}
	private void sortDS(int check){
		ArrayList<MonHocAndStatus> MHaStatusChuaQua = new ArrayList<MonHocAndStatus>();
		ArrayList<MonHocAndStatus> MHaStatusDaQua = new ArrayList<MonHocAndStatus>();
		ArrayList<MonHocAndStatus> MHaStatus = new ArrayList<MonHocAndStatus>();
		for(int i = 0; i < arrStatus.size(); i++){
			MonHocAndStatus mh = new MonHocAndStatus(arMonHoc.get(i).getTenMon(), arrStatus.get(i));
			if(arrStatus.get(i) == 1){
				MHaStatusDaQua.add(mh);
			}else {
				MHaStatusChuaQua.add(mh);
			}
		}
		MHaStatus.addAll(MHaStatusDaQua);
		if(check == 0){
			Collections.sort(MHaStatusChuaQua);
		}
		MHaStatus.addAll(MHaStatusChuaQua);
		ArrayList<MonHoc> arrMHMoi = new ArrayList<MonHoc>();
		ArrayList<Integer> arrStatusMoi = new ArrayList<Integer>();
		ArrayList<Integer> arrStatusGocMoi = new ArrayList<Integer>();
		for(int i = 0; i < MHaStatus.size(); i++){
			for (MonHoc mh : arMonHoc) {
				if(MHaStatus.get(i).getTenMon().matches(mh.getTenMon())){
					int vitri = arMonHoc.indexOf(mh);
					arrMHMoi.add(mh);
					arrStatusMoi.add(arrStatus.get(vitri));
					arrStatusGocMoi.add(arrStatusTemp.get(vitri));
					break;
				}
			}
		}
		arMonHoc.clear();
		arMonHoc.addAll(arrMHMoi);
		setGroupParents();
		arrStatus.clear();
		arrStatus.addAll(arrStatusMoi);
		arrStatusTemp.clear();
		arrStatusTemp.addAll(arrStatusGocMoi);
		
		adapter = new DanhSachMonDangKiAdapter2(parentItems, childItems, c,	arrColor, arrStatusTemp,
				R.layout.danh_sach_dang_ki_mon_hoc_parent_item_switch);
		// save index and top position
		int index = elv_MonHoc.getFirstVisiblePosition();
		View viewLV = elv_MonHoc.getChildAt(0);
		int top = (viewLV == null) ? 0 : (viewLV.getTop() - elv_MonHoc.getPaddingTop());
		elv_MonHoc.setAdapter(adapter);
		// elv_MonHoc.setSelection(vitri);
		// restore index and position
		elv_MonHoc.setSelectionFromTop(index, top);
	}
	private void dialogSortDSDK(final Activity context){
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.dialog_sort_danh_sach_mon_dang_ki);
		//dialog.setTitle(R.string.string_LichHoc_DatePicker_Title);
		RadioGroup rg = (RadioGroup) dialog.findViewById(R.id.rgGroupRbtnSortDSDK);
		/*int checkId = rg.getCheckedRadioButtonId();
		switch(checkId){
		case R.id.rBtnNamHoc:
			break;
		case R.id.rBtnMonHoc:
			break;
		}*/

		Button dialogButtonOK = (Button) dialog.findViewById(R.id.btnOKDialogSortDSDK);
		// if button is clicked, close the custom dialog
		dialogButtonOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				sortDS(0);
				dialog.dismiss();
			}
		});
		Button dialogButtonCancel = (Button) dialog.findViewById(R.id.btnCancelDialogSortDSDK);
		// if button is clicked, close the custom dialog
		dialogButtonCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.danh_sach_mon_co_the_dang_ki, menu);
		
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
		/*case R.id.action_settings_edit_DSMH:
			doiBieuTuongEdit();
			break;*/
		}
		return super.onOptionsItemSelected(item);
	}
	
	//-------------------------------------------------------------------------------------------------------------------------------------//
	////////////////////////////////////////////////////////////////////////////////ADAPTER//////////////////////////////////////////////////
	
	class DanhSachMonDangKiAdapter2 extends BaseExpandableListAdapter {

		private Activity activity;
		private ArrayList<Object> childtems;
		private LayoutInflater inflater;
		private ArrayList<MonHoc> parentItems;
		private ArrayList<MonHocTienQuyet> child;
		private ArrayList<Integer> arrColorKi;
		private ArrayList<Integer> arrstttemp;
		private int layoutId;

		public DanhSachMonDangKiAdapter2(ArrayList<MonHoc> parents, ArrayList<Object> childern, 
				Activity c, ArrayList<Integer> arrColor, ArrayList<Integer> arrstatustemp, int layoutID) {
			this.parentItems = parents;
			this.childtems = childern;
			this.activity = c;
			this.inflater = c.getLayoutInflater();
			this.arrColorKi = arrColor;
			//this.arrDiem = arrDiem;
			//this.arrstt = arrStatus;
			this.arrstttemp = arrstatustemp;
			this.layoutId = layoutID;
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
			for (int i = 0; i < parentItems.size(); i++) {
				MonHoc monhoc = parentItems.get(i);
				if(child.get(childPosition).getTenMon_MHTQ().matches(monhoc.getTenMon())){
					status = arrStatus.get(i);
				}
			}
			ImageView ivChildStatus = (ImageView) convertView.findViewById(R.id.ivChildStatus);
			setIcon(childPosition, status, ivChildStatus);
			
			return convertView;
		}

		@Override
		public View getGroupView(final int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
			//Danh sach chinh sua
			if (layoutId == R.layout.danh_sach_dang_ki_mon_hoc_parent_item_switch) {
				if (convertView == null) {
					convertView = inflater.inflate(layoutId, null);
				}
				TextView tvParentMonHoc = (TextView) convertView.findViewById(R.id.tvParentTenMonHocCTDTSwitch);
				tvParentMonHoc.setText("" + parentItems.get(groupPosition).getTenMon());
				TextView tvKiMon = (TextView) convertView.findViewById(R.id.tvKiMonSwitch);
				String maki = parentItems.get(groupPosition).getMaMonCTDT().charAt(3) + "";
				String kitudau = parentItems.get(groupPosition).getTenMon().charAt(0) + "";
				tvKiMon.setText(kitudau);
				setMaMau(tvKiMon, maki);
				ImageView ivStatus = (ImageView) convertView.findViewById(R.id.ivStatusSwitch);
				final Switch switchBtn = (Switch) convertView.findViewById(R.id.switchBtnMonHocSwitch);
				final MonHoc mh = parentItems.get(groupPosition);
				int x = arrStatus.get(groupPosition);
				setIcon(groupPosition, x, ivStatus);
				final Context context = activity;
				switchBtn.setTag(groupPosition);
				setSwitch(arrStatus.get(groupPosition), switchBtn, arrstttemp.get(groupPosition), groupPosition);
				switchBtn.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						xulySwitch(switchBtn, context, groupPosition, mh);
					}
				});

			} 
			//Danh sach don thuan
			else {
				if (convertView == null) {
					convertView = inflater.inflate(layoutId, null);
				}
				TextView tvParentMonHoc = (TextView) convertView.findViewById(R.id.tvParentTenMonHocCTDT);
				tvParentMonHoc.setText("" + parentItems.get(groupPosition).getTenMon());
				TextView tvKiMon = (TextView) convertView.findViewById(R.id.tvKiMon);
				String maki = parentItems.get(groupPosition).getMaMonCTDT().charAt(3) + "";
				String kitudau = parentItems.get(groupPosition).getTenMon().charAt(0) + "";
				tvKiMon.setText(kitudau);
				setMaMau(tvKiMon, maki);
				ImageView ivStatus = (ImageView) convertView.findViewById(R.id.ivStatus);
				int x = arrStatus.get(groupPosition);
				setIcon(groupPosition, x, ivStatus);
				setIcon(groupPosition, x, ivStatus);
				TextView tvNumberMTQ = (TextView) convertView.findViewById(R.id.tvMonTienQuyetNumber);
				int n = parentItems.get(groupPosition).getArrMonTienQuyet().size();
					tvNumberMTQ.setText("(" + n + ")");
			}

			return convertView;
		}
		private void xulySwitch(Switch switchBtn, Context context, int groupPosition, MonHoc mh){
			if (switchBtn.isChecked() == false) {
				// switchBtn.setChecked(false);

				Toast.makeText(context, mh.getTenMon() + "Unchecked", Toast.LENGTH_SHORT).show();
				arrStatus.set(groupPosition, arrstttemp.get(groupPosition));
				arrStatus = xacDinhStatusKichBan();
				adapter = new DanhSachMonDangKiAdapter2(parentItems, childItems, c,	arrColorKi, arrstttemp,
						R.layout.danh_sach_dang_ki_mon_hoc_parent_item_switch);
				// save index and top position
				int index = elv_MonHoc.getFirstVisiblePosition();
				View viewLV = elv_MonHoc.getChildAt(0);
				int top = (viewLV == null) ? 0 : (viewLV.getTop() - elv_MonHoc.getPaddingTop());
				elv_MonHoc.setAdapter(adapter);
				// elv_MonHoc.setSelection(vitri);
				// restore index and position
				elv_MonHoc.setSelectionFromTop(index, top);
			} else {
				// switchBtn.setChecked(true);
				Toast.makeText(context, mh.getTenMon() + "checked", Toast.LENGTH_SHORT).show();
				arrStatus.set(groupPosition, 1);
				arrStatus = xacDinhStatusKichBan();
				adapter = new DanhSachMonDangKiAdapter2(parentItems, childItems, c, arrColorKi, arrstttemp,
						R.layout.danh_sach_dang_ki_mon_hoc_parent_item_switch);
				// save index and top position
				int index = elv_MonHoc.getFirstVisiblePosition();
				View viewLV = elv_MonHoc.getChildAt(0);
				int top = (viewLV == null) ? 0 : (viewLV.getTop() - elv_MonHoc.getPaddingTop());

				// ...

				//
				elv_MonHoc.setAdapter(adapter);
				// elv_MonHoc.setSelection(vitri);
				// restore index and position
				elv_MonHoc.setSelectionFromTop(index, top);
			}
		}
		private void setMaMau(TextView tvKiMon, String maki){
			GradientDrawable d = (GradientDrawable) tvKiMon.getBackground();
			int so = Integer.parseInt(maki);
			so = so - 1;
			d.setColor(arrColorKi.get(so));
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
		private void setSwitch(int status, Switch switchBtn, int statusGoc, int stt){
			switch (status) {
			case 0:
				switchBtn.setVisibility(View.VISIBLE);
				switchBtn.setEnabled(true);
				switchBtn.setChecked(false);
				break;
			case 1:
				if(statusGoc == 1){
					/*switchBtn.setEnabled(false);
					switchBtn.setChecked(true);*/
					switchBtn.setVisibility(View.GONE);
				}else {
					switchBtn.setVisibility(View.VISIBLE);
					switchBtn.setEnabled(true);
					switchBtn.setChecked(true);
				}
				break;
			case 2:
				switchBtn.setVisibility(View.VISIBLE);
				switchBtn.setEnabled(true);
				switchBtn.setChecked(false);
				break;
			case 3:
				switchBtn.setVisibility(View.VISIBLE);
				switchBtn.setEnabled(false);
				switchBtn.setChecked(false);
				break;
			case 4:
				switchBtn.setEnabled(false);
				switchBtn.setChecked(false);
				switchBtn.setVisibility(View.GONE);
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

}
