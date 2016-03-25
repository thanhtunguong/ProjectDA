package com.doan.lichhoctap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ThongTinCaNhanActivity extends ActionBarActivity {

	private TextView tvTennguoidung, tvEmailnguoidung, tvLopnguoidung;
	private EditText edtNgaysinhnguoidung, edtGioitinhnguoidung,
			edtDiachinguoidung, edtSdtnguoidung;
	private Button btnHoantac, btnSua;

	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thong_tin_ca_nhan);

		toolbar = (Toolbar) findViewById(R.id.thongtincanhan_activity_tool_bar);
		setSupportActionBar(toolbar);

		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}

		tvTennguoidung = (TextView) findViewById(R.id.tvTennguoidung);
		tvEmailnguoidung = (TextView) findViewById(R.id.tvEmailnguoidung);
		tvLopnguoidung = (TextView) findViewById(R.id.tvLopnguoidung);
		edtNgaysinhnguoidung = (EditText) findViewById(R.id.edtNgaysinhnguoidung);
		edtGioitinhnguoidung = (EditText) findViewById(R.id.edtGioitinhnguoidung);
		edtDiachinguoidung = (EditText) findViewById(R.id.edtDiachinguoidung);
		edtSdtnguoidung = (EditText) findViewById(R.id.edtSdtnguoidung);

		Typeface face = Typeface.createFromAsset(getAssets(),
				"fonts/Roboto-Thin.ttf");

		tvTennguoidung.setTypeface(face);
		tvEmailnguoidung.setTypeface(face);
		tvLopnguoidung.setTypeface(face);
		edtNgaysinhnguoidung.setTypeface(face);
		edtGioitinhnguoidung.setTypeface(face);
		edtDiachinguoidung.setTypeface(face);
		edtSdtnguoidung.setTypeface(face);
		// tvNgaysinhnguoidung.setEnabled(false);
		// tvGioitinhnguoidung.setEnabled(false);
		// tvDiachinguoidung.setEnabled(false);
		// tvSdtnguoidung.setEnabled(false);
		
		btnHoantac = (Button) findViewById(R.id.btnHoantac);
		btnSua = (Button) findViewById(R.id.btnSua);
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
		if (id == R.id.edit) {
			Toast.makeText(getApplicationContext(), "Sua thong tin",Toast.LENGTH_SHORT).show();
			btnHoantac.setVisibility(0);
			btnSua.setVisibility(0);
			edtGioitinhnguoidung.setEnabled(true);
			edtDiachinguoidung.setEnabled(true);
			edtSdtnguoidung.setEnabled(true);
			
			edtGioitinhnguoidung.setTextColor(Color.BLUE);
			edtGioitinhnguoidung.setBackgroundColor(Color.WHITE);
			edtGioitinhnguoidung.isFocusable();
			
			edtDiachinguoidung.setTextColor(Color.BLUE);
			edtDiachinguoidung.setBackgroundColor(Color.WHITE);
			
			
			edtSdtnguoidung.setTextColor(Color.BLUE);
			edtSdtnguoidung.setBackgroundColor(Color.WHITE);
		
			
			edtNgaysinhnguoidung.setTextColor(Color.BLUE);
			edtNgaysinhnguoidung.setBackgroundColor(Color.WHITE);
			
			
			
			
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
