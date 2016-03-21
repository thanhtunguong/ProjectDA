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

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.provider.Telephony.Sms.Conversations;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

public class DanhSachMonCoTheDangKiActivity extends Activity {

	private ExecuteQuery exeQ;
	private ArrayList<MonHoc> arMonHoc;
	private ArrayList<MonHoc> arrMonHocKi1, arrMonHocKi2, arrMonHocKi3, arrMonHocKi4, 
								arrMonHocKi5, arrMonHocKi6, arrMonHocKi7, arrMonHocKi8;
	private ArrayList<DiemHocTap> arrDiem;
	private Activity c;
	private ArrayList<MonHoc> parentItems1, parentItems2, parentItems3, parentItems4, parentItems5, parentItems6, parentItems7, parentItems8;
	private ArrayList<Object> childItems1, childItems2, childItems3, childItems4, childItems5, childItems6, childItems7, childItems8;
	private ExpandableListView elv_Ki_1, elv_Ki_2, elv_Ki_3, elv_Ki_4, elv_Ki_5, elv_Ki_6, elv_Ki_7;
	private DanhSachMonDangKiAdapter adapter1, adapter2, adapter3, adapter4, adapter5, adapter6, adapter7, adapter8;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_danh_sach_mon_co_the_dang_ki);
		
		c = this;
		
		
		elv_Ki_1 = (ExpandableListView) findViewById(R.id.elvKi1);
		elv_Ki_2 = (ExpandableListView) findViewById(R.id.elvKi2);
		elv_Ki_3 = (ExpandableListView) findViewById(R.id.elvKi3);
		elv_Ki_4 = (ExpandableListView) findViewById(R.id.elvKi4);
		elv_Ki_5 = (ExpandableListView) findViewById(R.id.elvKi5);
		elv_Ki_6 = (ExpandableListView) findViewById(R.id.elvKi6);
		elv_Ki_7 = (ExpandableListView) findViewById(R.id.elvKi7);
		
		exeQ = new ExecuteQuery(this);
		arMonHoc = new ArrayList<MonHoc>();
		arMonHoc = exeQ.getAllMonHoc();
		
		phanChiaMonHocKi();
		//setChildData();
		setGroupParents();
		//setAdapter();
		/*elv_Ki_1.setGroupIndicator(null);
		elv_Ki_1.setClickable(true);
		adapter1 = new DanhSachMonDangKiAdapter(parentItems1, childItems1, c);
		adapter2 = new DanhSachMonDangKiAdapter(parentItems2, childItems2, c);
		adapter3 = new DanhSachMonDangKiAdapter(parentItems3, childItems3, c);
		adapter4 = new DanhSachMonDangKiAdapter(parentItems4, childItems4, c);
		adapter5 = new DanhSachMonDangKiAdapter(parentItems5, childItems5, c);
		adapter6 = new DanhSachMonDangKiAdapter(parentItems6, childItems6, c);
		adapter7 = new DanhSachMonDangKiAdapter(parentItems7, childItems7, c);
		adapter8 = new DanhSachMonDangKiAdapter(parentItems8, childItems8, c);
		elv_Ki_1.setAdapter(adapter1);
		elv_Ki_2.setAdapter(adapter2);
		elv_Ki_3.setAdapter(adapter3);
		elv_Ki_4.setAdapter(adapter4);
		elv_Ki_5.setAdapter(adapter5);
		elv_Ki_6.setAdapter(adapter6);
		elv_Ki_7.setAdapter(adapter7);*/
		setList(elv_Ki_1, adapter1, parentItems1, childItems1);
		setList(elv_Ki_2, adapter2, arrMonHocKi2, childItems2);
		setList(elv_Ki_3, adapter3, arrMonHocKi3, childItems3);
		setList(elv_Ki_4, adapter4, arrMonHocKi4, childItems4);
		setList(elv_Ki_5, adapter5, arrMonHocKi5, childItems5);
		setList(elv_Ki_6, adapter6, arrMonHocKi6, childItems6);
		setList(elv_Ki_7, adapter7, parentItems7, childItems7);
		
		getAllDiemQuaMon();
	}
	private void setList(ExpandableListView lv, DanhSachMonDangKiAdapter adapter, ArrayList<MonHoc> arMH, ArrayList<Object> arrObj){
		lv.setClickable(true);
		lv.setGroupIndicator(null);
		adapter = new DanhSachMonDangKiAdapter(arMH, arrObj, c);
		lv.setAdapter(adapter);
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
		for (MonHoc mh : arMonHoc) {
			String maki = mh.getMaMonCTDT().charAt(3) + "";
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
	}

	private void getAllDiemQuaMon() {
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
		/*for (DiemHocTap diem : arrDiem) {
			for(DiemHocTap diem2 : arrDiem){
				if(diem.getTenMonHoc().matches(diem2.getTenMonHoc())){
					if(tinhDiemTrungBinhMon(diem.getDiemCC(), diem.getDiemKT(), diem.getDiemThi())
							> tinhDiemTrungBinhMon(diem2.getDiemCC(), diem2.getDiemKT(), diem2.getDiemThi())){
						arrDiem.
					}
				}
			}
		}*/
	}
	private float tinhDiemTrungBinhMon(float d1, float d2, float d7){
		float dtb = (d1 + d2*2 + d7*7)/10;
		return dtb;
	}
	/*private void setChildData() {
		childItems1 = new ArrayList<Object>();
		childItems2 = new ArrayList<Object>();
		childItems3 = new ArrayList<Object>();
		childItems4 = new ArrayList<Object>();
		childItems5 = new ArrayList<Object>();
		childItems6 = new ArrayList<Object>();
		childItems7 = new ArrayList<Object>();
		childItems8 = new ArrayList<Object>();
		
		childItems1.add(arrMonHocKi1);
		childItems2.add(arrMonHocKi2);
		childItems3.add(arrMonHocKi3);
		childItems4.add(arrMonHocKi4);
		childItems5.add(arrMonHocKi5);
		childItems6.add(arrMonHocKi6);
		childItems7.add(arrMonHocKi7);
		childItems8.add(arrMonHocKi8);
	}*/
	private void setGroupParents() {
		parentItems1 = new ArrayList<MonHoc>();
		parentItems2 = new ArrayList<MonHoc>();
		parentItems3 = new ArrayList<MonHoc>();
		parentItems4 = new ArrayList<MonHoc>();
		parentItems5 = new ArrayList<MonHoc>();
		parentItems6 = new ArrayList<MonHoc>();
		parentItems7 = new ArrayList<MonHoc>();
		parentItems8 = new ArrayList<MonHoc>();
		
		childItems1 = new ArrayList<Object>();
		childItems2 = new ArrayList<Object>();
		childItems3 = new ArrayList<Object>();
		childItems4 = new ArrayList<Object>();
		childItems5 = new ArrayList<Object>();
		childItems6 = new ArrayList<Object>();
		childItems7 = new ArrayList<Object>();
		childItems8 = new ArrayList<Object>();
		
		
		for (MonHoc mh : arrMonHocKi1) {
			parentItems1.add(mh);
			childItems1.add(mh.getArrMonTienQuyet());
		}
		for (MonHoc mh : arrMonHocKi2) {
			parentItems2.add(mh);
			childItems2.add(mh.getArrMonTienQuyet());
		}
		for (MonHoc mh : arrMonHocKi3) {
			parentItems3.add(mh);
			childItems3.add(mh.getArrMonTienQuyet());
		}
		for (MonHoc mh : arrMonHocKi4) {
			parentItems4.add(mh);
			childItems4.add(mh.getArrMonTienQuyet());
		}
		for (MonHoc mh : arrMonHocKi5) {
			parentItems5.add(mh);
			childItems5.add(mh.getArrMonTienQuyet());
		}
		for (MonHoc mh : arrMonHocKi6) {
			parentItems6.add(mh);
			childItems6.add(mh.getArrMonTienQuyet());
		}
		for (MonHoc mh : arrMonHocKi7) {
			parentItems7.add(mh);
			childItems7.add(mh.getArrMonTienQuyet());
		}
		for (MonHoc mh : arrMonHocKi8) {
			parentItems8.add(mh);
			childItems8.add(mh.getArrMonTienQuyet());
		}
	}
	private void setAdapter(){
		adapter1 = new DanhSachMonDangKiAdapter(parentItems1, childItems1, c);
		adapter2 = new DanhSachMonDangKiAdapter(parentItems2, childItems2, c);
		adapter3 = new DanhSachMonDangKiAdapter(parentItems3, childItems3, c);
		adapter4 = new DanhSachMonDangKiAdapter(parentItems4, childItems4, c);
		adapter5 = new DanhSachMonDangKiAdapter(parentItems5, childItems5, c);
		adapter6 = new DanhSachMonDangKiAdapter(parentItems6, childItems6, c);
		adapter7 = new DanhSachMonDangKiAdapter(parentItems7, childItems7, c);
		adapter8 = new DanhSachMonDangKiAdapter(parentItems8, childItems8, c);
		elv_Ki_1.setAdapter(adapter1);
		elv_Ki_2.setAdapter(adapter2);
		elv_Ki_3.setAdapter(adapter3);
		elv_Ki_4.setAdapter(adapter4);
		elv_Ki_5.setAdapter(adapter5);
		elv_Ki_6.setAdapter(adapter6);
		elv_Ki_7.setAdapter(adapter7);
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
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
