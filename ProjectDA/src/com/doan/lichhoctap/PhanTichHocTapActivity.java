package com.doan.lichhoctap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import com.doan.adapter.PhanTichHocTapViewPagerAdapter;
import com.doan.app.Global;
import com.doan.database_handle.ExecuteQuery;
import com.doan.fragment.DuTinhFragment;
import com.doan.model.DiemHocTap;
import com.doan.slydingtab.PhanTichHocTapSlidingTabLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class PhanTichHocTapActivity extends ActionBarActivity {
	private Toolbar toolbar;
	private Context context;
	private ViewPager pager;
	private PhanTichHocTapViewPagerAdapter adapter;
    private PhanTichHocTapSlidingTabLayout tabs;
    private CharSequence Titles[];
    private CharSequence ActivityTitles[];
    int Numboftabs = 3;
    private ExecuteQuery exeQ;
    private ArrayList<DiemHocTap> arlDiemQuaCaoNhat = new ArrayList<DiemHocTap>();
	private ArrayList<DiemHocTap> arlDiemAll = new ArrayList<DiemHocTap>();
	private ArrayList<DiemHocTap> arlDiemQuaLanMot = new ArrayList<DiemHocTap>();
	public int TCtest = 0;
	public float DTBtest = 0;
    private double tongDiemtest = 0;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phan_tich_hoc_tap);
		toolbar = (Toolbar) findViewById(R.id.phan_tich_hoc_tap_activity_tool_bar);
        setSupportActionBar(toolbar);
        
        context = this;
        exeQ = new ExecuteQuery(context);
        loadDuLieu();
        
        
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new PhanTichHocTapViewPagerAdapter(getSupportFragmentManager(),Numboftabs, context);
 
        // Assigning ViewPager View and setting the adapter
		pager = (ViewPager) findViewById(R.id.ptht_pager);
		pager.setAdapter(adapter);
        
 
        // Assiging the Sliding Tab Layout View
        tabs = (PhanTichHocTapSlidingTabLayout) findViewById(R.id.ptht_tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
 
        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new PhanTichHocTapSlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
        sendData();
        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
        
	}
	private void sendData(){
		Global.saveFloatPreference(context, "DiemTrungBinh", DTBtest);
		Global.saveIntegerPreference(context, "SoTinChi", TCtest);
	}
	private void loadDuLieu(){
		exeQ.open();
		arlDiemAll = exeQ.getDiemSV(context); //Điểm trung bình của các môn - trung bình học tập
		getAllDiemQuaMonDiemQuaCaonhat(); //điểm qua cao nhất - trung bình tích lũy
		getAllDiemQuaMonDiemQuaLanMot(); //điểm qua lần 1 - trung bình học kì
		exeQ.close();
        for (DiemHocTap diemHocTap : arlDiemQuaCaoNhat) {
        	double sdtb = ((diemHocTap.getDiemCC() + diemHocTap.getDiemKT()*2 + diemHocTap.getDiemThi()*7)/10)*1.0;
        	sdtb = Math.floor(sdtb*10)/10;
        	//trạng thái = 0 tức là môn đã có điểm
        	if(diemHocTap.getMaTrangThaiDK().matches("0")){
        		tinhTongKetHocTap(diemHocTap.getSoTinChi(), sdtb);
        	}
		}
	}
	private void tinhTongKetHocTap(int stc, double sdtb){
    	TCtest = TCtest + stc;
    	tongDiemtest = tongDiemtest +  sdtb*stc;
    	DTBtest = (float)(tongDiemtest/TCtest);
    	DTBtest = (float)Math.floor(DTBtest*10)/10;    	
    }
	private void getAllDiemQuaMonDiemQuaCaonhat() {
		arlDiemQuaCaoNhat = new ArrayList<DiemHocTap>();
		arlDiemQuaCaoNhat = exeQ.getDiemQuaMonSV(context);
		int x = arlDiemQuaCaoNhat.size();
		for (int i = 0; i < arlDiemQuaCaoNhat.size(); i++) {
			DiemHocTap diem1 = arlDiemQuaCaoNhat.get(i);
			for (int j = 0; j < arlDiemQuaCaoNhat.size(); j++) {
				DiemHocTap diem2 = arlDiemQuaCaoNhat.get(j);
				if (diem1.getMaDiem().matches(diem2.getMaDiem()) == false) {
					if (diem1.getTenMonHoc().matches(diem2.getTenMonHoc())) {
						if (tinhDiemTrungBinhMon(diem1.getDiemCC(), diem1.getDiemKT(),
								diem1.getDiemThi()) > tinhDiemTrungBinhMon(diem2.getDiemCC(), diem2.getDiemKT(),
										diem2.getDiemThi())) {
							arlDiemQuaCaoNhat.remove(j);
						} else {
							if (tinhDiemTrungBinhMon(diem1.getDiemCC(), diem1.getDiemKT(),
									diem1.getDiemThi()) < tinhDiemTrungBinhMon(diem2.getDiemCC(), diem2.getDiemKT(),
											diem2.getDiemThi())) {
								arlDiemQuaCaoNhat.remove(i);
							} else {
								//SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
									arlDiemQuaCaoNhat.remove(j);
								} else {
									if (date1.before(date2)) {
										arlDiemQuaCaoNhat.remove(i);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	private void getAllDiemQuaMonDiemQuaLanMot() {
		arlDiemQuaLanMot= new ArrayList<DiemHocTap>();
		arlDiemQuaLanMot = exeQ.getDiemQuaMonSV(context);
		int x = arlDiemQuaLanMot.size();
		for (int i = 0; i < arlDiemQuaLanMot.size(); i++) {
			DiemHocTap diem1 = arlDiemQuaLanMot.get(i);
			for (int j = 0; j < arlDiemQuaLanMot.size(); j++) {
				DiemHocTap diem2 = arlDiemQuaLanMot.get(j);
				if (diem1.getMaDiem().matches(diem2.getMaDiem()) == false) {
					if (diem1.getTenMonHoc().matches(diem2.getTenMonHoc())) {
						//
						SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
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
							arlDiemQuaLanMot.remove(j);
						} else {
							if (date1.before(date2)) {
								arlDiemQuaLanMot.remove(i);
							}
						}
					}
				}
			}
		}
	}
	private float tinhDiemTrungBinhMon(float d1, float d2, float d7){
		float dtb = (d1 + d2*2 + d7*7)/10;
		return dtb;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.phan_tich_hoc_tap, menu);
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
