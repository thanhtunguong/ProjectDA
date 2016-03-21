package com.doan.lichhoctap;

import java.util.ArrayList;

import com.doan.adapter.HocTapDiemAdapter;
import com.doan.app.Global;
import com.doan.database_handle.ExecuteQuery;
import com.doan.model.DiemHocTap;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Display;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class DiemHocTapActivity extends ActionBarActivity {
	
	private Toolbar toolbar;
	/*DiemHocTap d1 = new DiemHocTap("AWD1", "Kien truc may tinh", 4, 10, 5, 8);
	DiemHocTap d2 = new DiemHocTap("AWD2", "Ki thuat dien tu so", 4, 8, 8, 5);
	DiemHocTap d3 = new DiemHocTap("AWD3", "Nguyen ly he dieu hanh", 3, 10, 8, 2);
	DiemHocTap d4 = new DiemHocTap("AWD4", "Thuong mai dien tu", 3, 10, 8, 6);*/
	private ArrayList<DiemHocTap> arlDiem = new ArrayList<DiemHocTap>();
	private int TC = 0;
	private float DTB = 0;
    private double tongDiem = 0;
    private Context c;
    private ExecuteQuery exeQ;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diem_hoc_tap);
		toolbar = (Toolbar) findViewById(R.id.BaiViet_detail_activity_tool_bar);
		setSupportActionBar(toolbar);

		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		c = this;
        //setTitleActivity(R.string.title_activity_for_selector_NF);
        /*arlDiem.add(d1);
        arlDiem.add(d2);
        arlDiem.add(d3);
        arlDiem.add(d4);*/
        /*arlDiem.add(d5);
        arlDiem.add(d6);*/
		String masv = Global.getStringPreference(c, "MaSVDN", "0");
		exeQ = new ExecuteQuery(c);
		arlDiem = exeQ.getDiemSV(c);
		
        TextView tvTC, tvDTB, tvSTCpc, tvSTCprogess, tvDTBpc, tvDTBprogess;
        LinearLayout lnSTC, lnDTB;
        for (DiemHocTap diemHocTap : arlDiem) {
        	double sdtb = ((diemHocTap.getDiemCC() + diemHocTap.getDiemKT()*2 + diemHocTap.getDiemThi()*7)/10)*1.0;
        	sdtb = Math.floor(sdtb*10)/10;
        	//trạng thái = 0 tức là môn đã có điểm
        	if(diemHocTap.getMaTrangThaiDK().matches("0")){
        		tinhTongKetHocTap(diemHocTap.getSoTinChi(), sdtb);
        	}
		}
        tvTC = (TextView) findViewById(R.id.tvSoTinChiDaHoc);
        tvTC.setText(getString(R.string.string_SoTinChiDaHoc));
        
        tvDTB = (TextView) findViewById(R.id.tvDiemTrungBinhTichLuy);
        tvDTB.setText(getString(R.string.string_DiemTrungBinhTichLuy));
        
        Display display = this.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;
        
        lnSTC = (LinearLayout) findViewById(R.id.lnSTC);
        //lnSTC.setLayoutParams(new LayoutParams(width/2, 12));
        float width2 = (float)(width/1.5);
        width = Math.round(width2);
        LinearLayout.LayoutParams lp1 = new LayoutParams(width, 12);
        lp1.gravity = Gravity.CENTER_VERTICAL;
        lnSTC.setLayoutParams(lp1);        
        tvSTCprogess = (TextView) findViewById(R.id.tvTCprogess);
    	float STCpc = TC/120;
    	int xSTC = 220;
    	float x = (TC*width)/120;
    	tvSTCprogess.setLayoutParams(new LayoutParams(Math.round(x), 12));
    	tvSTCpc = (TextView) findViewById(R.id.tvTCpc);
    	tvSTCpc.setText(x + " %");
    	
    	lnDTB = (LinearLayout) findViewById(R.id.lnDTB);
    	lnDTB.setLayoutParams(new LayoutParams(width, 12));
    	lnDTB.setGravity(Gravity.CENTER_VERTICAL);
        tvDTBprogess = (TextView) findViewById(R.id.tvDTBprogess);
        lnDTB.setLayoutParams(lp1);
    	float DTBpc = DTB/10;
    	int xDTB = 220;
    	float y = (width*DTB)/10;
    	tvDTBprogess.setLayoutParams(new LayoutParams(Math.round(y), 12));
    	tvDTBpc = (TextView) findViewById(R.id.tvDTBpc);
    	tvDTBpc.setText(DTB + "/10");
    	
    	tvSTCpc = (TextView) findViewById(R.id.tvTCpc);
    	tvSTCpc.setText(TC + "/120");
        
        
        
        
        HocTapDiemAdapter adapter = new HocTapDiemAdapter(this , R.layout.hoctap_diem_diemso_item_test, arlDiem);
        ListView lvDiem = (ListView) findViewById(R.id.lvDiem);
        lvDiem.setAdapter(adapter);
	}

	private void tinhTongKetHocTap(int stc, double sdtb){
    	TC = TC + stc;
    	tongDiem = tongDiem +  sdtb*stc;
    	DTB = (float)(tongDiem/TC);
    	DTB = (float)Math.floor(DTB*10)/10;    	
    }
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.diem_hoc_tap, menu);
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
