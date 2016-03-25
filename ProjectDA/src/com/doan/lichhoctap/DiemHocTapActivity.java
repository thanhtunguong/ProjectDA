package com.doan.lichhoctap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

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
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.LinearLayout.LayoutParams;

public class DiemHocTapActivity extends ActionBarActivity {
	
	private Toolbar toolbar;
	/*DiemHocTap d1 = new DiemHocTap("AWD1", "Kien truc may tinh", 4, 10, 5, 8);
	DiemHocTap d2 = new DiemHocTap("AWD2", "Ki thuat dien tu so", 4, 8, 8, 5);
	DiemHocTap d3 = new DiemHocTap("AWD3", "Nguyen ly he dieu hanh", 3, 10, 8, 2);
	DiemHocTap d4 = new DiemHocTap("AWD4", "Thuong mai dien tu", 3, 10, 8, 6);*/
	private ArrayList<DiemHocTap> arlDiem = new ArrayList<DiemHocTap>();
	private ArrayList<DiemHocTap> arlDiemAll = new ArrayList<DiemHocTap>();
	private int TC = 0;
	private int TC2 = 0;
	private float DTB = 0;
	private float DTB2 = 0;
    private double tongDiem = 0;
    private double tongDiem2 = 0;
    private Context c;
    private ExecuteQuery exeQ;
    private LinearLayout.LayoutParams lp1;
    private int biencheck;
    private LinearLayout lnDTB;
    private TextView tvDTBprogess, tvDTBpc;
    private int width;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_diem_hoc_tap);
		toolbar = (Toolbar) findViewById(R.id.BaiViet_activity_tool_bar);
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
		arlDiemAll = exeQ.getDiemSV(c);
		arlDiem = getAllDiemQuaMon(); 
		
        TextView tvTC, tvDTB, tvSTCpc, tvSTCprogess;
        LinearLayout lnSTC;
        tvTC = (TextView) findViewById(R.id.tvSoTinChiDaHoc);
        tvTC.setText(getString(R.string.string_SoTinChiDaHoc));
        
        tvDTB = (TextView) findViewById(R.id.tvDiemTrungBinhTichLuy);
        tvDTB.setText(getString(R.string.string_DiemTrungBinhTichLuy));
        
        lnSTC = (LinearLayout) findViewById(R.id.lnSTC);
        tvSTCprogess = (TextView) findViewById(R.id.tvTCprogess);
        lnDTB = (LinearLayout) findViewById(R.id.lnDTB);
        
        tvSTCpc = (TextView) findViewById(R.id.tvTCpc);
        tvDTBprogess = (TextView) findViewById(R.id.tvDTBprogess);
        tvDTBpc = (TextView) findViewById(R.id.tvDTBpc);
        tvSTCpc = (TextView) findViewById(R.id.tvTCpc);
        ListView lvDiem = (ListView) findViewById(R.id.lvDiem);
        
        for (DiemHocTap diemHocTap : arlDiem) {
        	double sdtb = ((diemHocTap.getDiemCC() + diemHocTap.getDiemKT()*2 + diemHocTap.getDiemThi()*7)/10)*1.0;
        	sdtb = Math.floor(sdtb*10)/10;
        	//trạng thái = 0 tức là môn đã có điểm
        	if(diemHocTap.getMaTrangThaiDK().matches("0")){
        		tinhTongKetHocTap(diemHocTap.getSoTinChi(), sdtb);
        	}
		}
        
        Display display = this.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        width = size.x;
        int height = size.y;
        
        
        //lnSTC.setLayoutParams(new LayoutParams(width/2, 12));
        float width2 = (float)(width/1.5);
        width = Math.round(width2);
        lp1 = new LayoutParams(width, 12);
        lp1.gravity = Gravity.CENTER_VERTICAL;
        lnSTC.setLayoutParams(lp1);        
        
    	float STCpc = TC/120;
    	int xSTC = 220;
    	float x = (TC*width)/120;
    	tvSTCprogess.setLayoutParams(new LayoutParams(Math.round(x), 12));
    	
    	tvSTCpc.setText(x + " %");
    	
    	
    	/*lnDTB.setLayoutParams(new LayoutParams(width, 12));
    	lnDTB.setGravity(Gravity.CENTER_VERTICAL);
       
        lnDTB.setLayoutParams(lp1);
    	float DTBpc = DTB/10;
    	int xDTB = 220;
    	float y = (width*DTB)/10;
    	tvDTBprogess.setLayoutParams(new LayoutParams(Math.round(y), 12));
    	
    	tvDTBpc.setText(DTB + "/10");*/
    	getResultHocTap(arlDiem, lnDTB, tvDTBprogess, tvDTBpc);
    	biencheck = 0;
    	ImageButton imgBtn = (ImageButton) findViewById(R.id.btnDiemOption);
    	imgBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(biencheck == 0){
					getResultHocTap(arlDiemAll, lnDTB, tvDTBprogess, tvDTBpc);
					biencheck = 1;
					Toast.makeText(c, biencheck + "", Toast.LENGTH_SHORT).show();
				}else {
					getResultHocTap(arlDiem, lnDTB, tvDTBprogess, tvDTBpc);
					biencheck = 0;
					Toast.makeText(c, biencheck + "", Toast.LENGTH_SHORT).show();
					biencheck = 0;
				}
			}
		});
    	
    	tvSTCpc.setText(TC + "/120");
        
        HocTapDiemAdapter adapter = new HocTapDiemAdapter(this , R.layout.hoctap_diem_diemso_item_test, arlDiemAll);
        lvDiem.setAdapter(adapter);
	}
	private void getResultHocTap(ArrayList<DiemHocTap> arlDiem, LinearLayout lnDTB, TextView tvDTBprogess, TextView tvDTBpc){
		for (DiemHocTap diemHocTap : arlDiem) {
        	double sdtb = ((diemHocTap.getDiemCC() + diemHocTap.getDiemKT()*2 + diemHocTap.getDiemThi()*7)/10)*1.0;
        	sdtb = Math.floor(sdtb*10)/10;
        	//trạng thái = 0 tức là môn đã có điểm
        	if(diemHocTap.getMaTrangThaiDK().matches("0")){
        		tinhTongKetHocTap2(diemHocTap.getSoTinChi(), sdtb);
        	}
		}
		/*Display display = this.getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;*/
        
        lnDTB.setLayoutParams(new LayoutParams(width, 12));
    	lnDTB.setGravity(Gravity.CENTER_VERTICAL);
       
        lnDTB.setLayoutParams(lp1);
    	float DTBpc = DTB2/10;
    	int xDTB = 220;
    	float y = (width*DTB2)/10;
    	tvDTBprogess.setLayoutParams(new LayoutParams(Math.round(y), 12));
    	
    	tvDTBpc.setText(DTB2 + "/10");
	}

	private void tinhTongKetHocTap(int stc, double sdtb){
    	TC = TC + stc;
    	tongDiem = tongDiem +  sdtb*stc;
    	DTB = (float)(tongDiem/TC);
    	DTB = (float)Math.floor(DTB*10)/10;    	
    }
	private void tinhTongKetHocTap2(int stc, double sdtb){
    	TC2 = TC2 + stc;
    	tongDiem2 = tongDiem2 +  sdtb*stc;
    	DTB2 = (float)(tongDiem2/TC2);
    	DTB2 = (float)Math.floor(DTB2*10)/10;    	
    }
	private ArrayList<DiemHocTap> getAllDiemQuaMon() {
		arlDiem = new ArrayList<DiemHocTap>();
		arlDiem = exeQ.getDiemQuaMonSV(c);
		int x = arlDiem.size();
		for (int i = 0; i < arlDiem.size(); i++) {
			DiemHocTap diem1 = arlDiem.get(i);
			for (int j = 0; j < arlDiem.size(); j++) {
				DiemHocTap diem2 = arlDiem.get(j);
				if (diem1.getMaDiem().matches(diem2.getMaDiem()) == false) {
					if (diem1.getTenMonHoc().matches(diem2.getTenMonHoc())) {
						if (tinhDiemTrungBinhMon(diem1.getDiemCC(), diem1.getDiemKT(),
								diem1.getDiemThi()) > tinhDiemTrungBinhMon(diem2.getDiemCC(), diem2.getDiemKT(),
										diem2.getDiemThi())) {
							arlDiem.remove(j);
						} else {
							if (tinhDiemTrungBinhMon(diem1.getDiemCC(), diem1.getDiemKT(),
									diem1.getDiemThi()) < tinhDiemTrungBinhMon(diem2.getDiemCC(), diem2.getDiemKT(),
											diem2.getDiemThi())) {
								arlDiem.remove(i);
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
									arlDiem.remove(j);
								} else {
									if (date1.before(date2)) {
										arlDiem.remove(i);
									}
								}
							}
						}
					}
				}
			}
		}
		return arlDiem;
	}
	private float tinhDiemTrungBinhMon(float d1, float d2, float d7){
		float dtb = (d1 + d2*2 + d7*7)/10;
		return dtb;
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
