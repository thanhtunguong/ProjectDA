package com.doan.lichhoctap;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class ThongTinCaNhanActivity extends Activity {

	private TextView tvThongtincanhan,tvTennguoidung,tvNgaysinhnguoidung,
	tvGioitinhnguoidung,tvDiachinguoidung,tvEmailnguoidung,tvLopnguoidung;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thong_tin_ca_nhan);
		
		 tvThongtincanhan = (TextView)findViewById(R.id.tvThongtincanha);
	        tvTennguoidung = (TextView)findViewById(R.id.tvTennguoidung);
	        tvNgaysinhnguoidung = (TextView)findViewById(R.id.tvNgaysinhnguoidung);
	        tvGioitinhnguoidung = (TextView)findViewById(R.id.tvGioitinhnguoidung);
	        tvDiachinguoidung = (TextView)findViewById(R.id.tvDiachinguoidung);
	        tvEmailnguoidung = (TextView)findViewById(R.id.tvEmailnguoidung);
	        tvLopnguoidung = (TextView)findViewById(R.id.tvLopnguoidung);
	        
	        Typeface face = Typeface.createFromAsset(getAssets(),"fonts/Roboto-Thin.ttf");        
	        tvThongtincanhan.setTypeface(face);
	        tvTennguoidung.setTypeface(face);
	        tvNgaysinhnguoidung.setTypeface(face);
	        tvGioitinhnguoidung.setTypeface(face);
	        tvDiachinguoidung.setTypeface(face);
	        tvEmailnguoidung.setTypeface(face);
	        tvLopnguoidung.setTypeface(face);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.thong_tin_ca_nhan, menu);
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
