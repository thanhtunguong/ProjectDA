package com.doan.lichhoctap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.doan.adapter.ThongBaoAdapter;
import com.doan.database_handle.ExecuteQuery;
import com.doan.model.ThongBao;
import com.doan.model.ThongBaoTrongNgay;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ExpandableListView;

public class ThongBaoActivity extends ActionBarActivity {
	
	private ExpandableListView elv_ThongBao;
	private ExecuteQuery exeQ;
	private Context context;
	private ArrayList<ThongBaoTrongNgay> arrThongBaoTrongNgay;
	private ArrayList<Object> childItems;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thong_bao);
		Toolbar toolbar = (Toolbar) findViewById(R.id.thong_bao_activity_tool_bar);
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		context = this;
		exeQ = new ExecuteQuery(context);
		arrThongBaoTrongNgay = new ArrayList<ThongBaoTrongNgay>();
		childItems = new ArrayList<Object>();
		xulyPhanDinhNgay();
		elv_ThongBao = (ExpandableListView) findViewById(R.id.elvThongBao);
		elv_ThongBao.setGroupIndicator(null);
		ThongBaoAdapter adapter = new ThongBaoAdapter(arrThongBaoTrongNgay, childItems, this);
		elv_ThongBao.setAdapter(adapter);
	}

	private void xulyPhanDinhNgay() {
		ArrayList<ThongBao> arrThongBao = new ArrayList<ThongBao>();
		arrThongBao.addAll(exeQ.getAllThongBaoSqLite());
		for (int i = 0; i < arrThongBao.size(); i++) {
			ThongBao tb = arrThongBao.get(i);
			Date date = epKieuDate(tb.getNgayTaoThongBao());
			int check = 0;
			for (ThongBaoTrongNgay thongBaoTrongNgay : arrThongBaoTrongNgay) {
				if(thongBaoTrongNgay.getNgayCuThe().equals(date)){
					check = 1;
				}
			}
			if(check == 0){
				ThongBaoTrongNgay tbtn = new ThongBaoTrongNgay();
				tbtn.setNgayCuThe(date);
				ArrayList<ThongBao> arrtb = new ArrayList<ThongBao>();
				for (ThongBao thongBao : arrThongBao) {
					if(epKieuDate(thongBao.getNgayTaoThongBao()).equals(date)){
						arrtb.add(thongBao);
						//arrThongBao.remove(arrThongBao.indexOf(thongBao));
					}
				}
				tbtn.setArrThongBao(arrtb);
				arrThongBaoTrongNgay.add(tbtn);
			}
		}
		Collections.sort(arrThongBaoTrongNgay);
		for (ThongBaoTrongNgay thongBaoTrongNgay : arrThongBaoTrongNgay) {
			childItems.add(thongBaoTrongNgay.getArrThongBao());
		}
	}
	
	private Date epKieuDate(String ngay){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(ngay);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.thong_bao, menu);
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
