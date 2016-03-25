package com.doan.lichhoctap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import com.doan.adapter.DanhSachMonDangKiAdapter;
import com.doan.database_handle.ExecuteQuery;
import com.doan.model.DayInWeek;
import com.doan.model.DiemHocTap;
import com.doan.model.MonHoc;
import com.doan.model.MonHocTienQuyet;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.Telephony.Sms.Conversations;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

public class DanhSachMonCoTheDangKiActivity extends ActionBarActivity {

	private ExecuteQuery exeQ;
	private ArrayList<MonHoc> arMonHoc;
	private ArrayList<MonHoc> arrMonHocKi1, arrMonHocKi2, arrMonHocKi3, arrMonHocKi4, 
								arrMonHocKi5, arrMonHocKi6, arrMonHocKi7, arrMonHocKi8;
	private ArrayList<DiemHocTap> arrDiem;
	private Activity c;
	private ArrayList<MonHoc> parentItems1, parentItems2, parentItems3, parentItems4, parentItems5, parentItems6, parentItems7, parentItems8;
	private ArrayList<Object> childItems1, childItems2, childItems3, childItems4, childItems5, childItems6, childItems7, childItems8;
	private ExpandableListView elv_Ki_1, elv_Ki_2, elv_Ki_3, elv_Ki_4, elv_Ki_5, elv_Ki_6, elv_Ki_7;
	private ExpandableListView elv_MonHoc;
	private DanhSachMonDangKiAdapter adapter;
	private ArrayList<MonHoc> parentItems;
	private ArrayList<Object> childItems;
	private ArrayList<Integer> arrStatus;
	private DanhSachMonDangKiAdapter adapter1, adapter2, adapter3, adapter4, adapter5, adapter6, adapter7, adapter8;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_danh_sach_mon_co_the_dang_ki_test);
		Toolbar toolbar = (Toolbar) findViewById(R.id.BaiViet_activity_tool_bar);
		setSupportActionBar(toolbar);

		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		c = this;
		
		elv_MonHoc = (ExpandableListView) findViewById(R.id.elvAllMonHoc);
		
		exeQ = new ExecuteQuery(this);
		arMonHoc = new ArrayList<MonHoc>();
		arMonHoc = exeQ.getAllMonHoc();
		arrDiem = getAllDiemQuaMon();
		phanChiaMonHocKi();
		setGroupParents();
		arrStatus = xacDinhStatus2();
		setList(elv_MonHoc, adapter, parentItems, childItems, arrStatus);
	}
	
	private void setList(ExpandableListView lv, DanhSachMonDangKiAdapter adapter, 
			ArrayList<MonHoc> arMH, ArrayList<Object> arrObj, ArrayList<Integer> arrStatus){
		lv.setClickable(true);
		lv.setGroupIndicator(null);
		adapter = new DanhSachMonDangKiAdapter(arMH, arrObj, c, intTaoMau(), getAllDiemQuaMon(), arrStatus);
		lv.setAdapter(adapter);
	}

	private ArrayList<Integer> xacDinhStatus2() {
		int t = 0;
		for (int i = 0; i < arMonHoc.size(); i++) {
			if(i == 12){
				t = 1;
			}
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
		int x = arrDiem.size();
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
								String a = diem1.getThoiGianDK();
								String b = diem2.getThoiGianDK();
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
	
	private ArrayList<Integer> intTaoMau() {
		Random rnd = new Random();
		ArrayList<Integer> arlColor = new ArrayList<Integer>();
		//Integer mau = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
		for(int j = 0; j < 8; j++){
			Integer mau = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
			if (arlColor.size() == 0) {
				arlColor.add(mau);
			} else {
				for (int i = 0; i < arlColor.size(); i++) {
					int mau2 = arlColor.get(i);
					if (mau2 == mau) {
						intTaoMau();
					} else {
						arlColor.add(mau);
						break;
					}
				}
			}
		}
		return arlColor;
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.danh_sach_mon_co_the_dang_ki, menu);
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
}
