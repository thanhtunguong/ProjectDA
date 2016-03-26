package com.doan.lichhoctap;

import java.util.Calendar;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.opengl.Visibility;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ThongTinCaNhanActivity extends ActionBarActivity {

	private TextView tvTennguoidung, tvEmailnguoidung, tvLopnguoidung;
	private EditText edtNgaysinhnguoidung, edtGioitinhnguoidung,
			edtDiachinguoidung, edtSdtnguoidung;
	private Button btnHoantac, btnSua;
	private String ngaysinh,gioitinh,diachi,sdt;
	private String s_ngaysinh,s_gioitinh,s_diachi,s_sdt;
	

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
		
		btnHoantac.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// lay lai gia tri
				edtNgaysinhnguoidung.setText(ngaysinh);
				edtGioitinhnguoidung.setText(gioitinh);
				edtDiachinguoidung.setText(diachi);
				edtSdtnguoidung.setText(sdt);
				
				// Khong cho tuong tac voi edittext
				edtNgaysinhnguoidung.setEnabled(false);
				edtGioitinhnguoidung.setEnabled(false);
				edtDiachinguoidung.setEnabled(false);
				edtSdtnguoidung.setEnabled(false);
				
				// dat lai mau chu va mau nen
				edtNgaysinhnguoidung.setTextColor(Color.WHITE);
				edtNgaysinhnguoidung.setBackgroundColor(Color.red(R.color.ColorPrimary));
				
				edtGioitinhnguoidung.setTextColor(Color.WHITE);
				edtGioitinhnguoidung.setBackgroundColor(Color.red(R.color.ColorPrimary));
				
				edtDiachinguoidung.setTextColor(Color.WHITE);
				edtDiachinguoidung.setBackgroundColor(Color.red(R.color.ColorPrimary));
				
				edtSdtnguoidung.setTextColor(Color.WHITE);
				edtSdtnguoidung.setBackgroundColor(Color.red(R.color.ColorPrimary));
				
				// An di cac nut
				btnHoantac.setVisibility(4);
				btnSua.setVisibility(4);
			}
		});
		
		btnSua.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// chay ham update thong tin nguoi dung
				s_ngaysinh = edtNgaysinhnguoidung.getText().toString();
				s_gioitinh = edtGioitinhnguoidung.getText().toString();
				s_diachi = edtDiachinguoidung.getText().toString();
				s_sdt = edtSdtnguoidung.getText().toString();
				suaThongtin(s_ngaysinh,s_gioitinh,s_diachi,s_sdt);
				
				// Khong cho tuong tac voi edittext
				edtNgaysinhnguoidung.setEnabled(false);
				edtGioitinhnguoidung.setEnabled(false);
				edtDiachinguoidung.setEnabled(false);
				edtSdtnguoidung.setEnabled(false);
				
				// dat lai mau chu va mau nen
				edtNgaysinhnguoidung.setTextColor(Color.WHITE);
				edtNgaysinhnguoidung.setBackgroundColor(Color.red(R.color.ColorPrimary));
				
				edtGioitinhnguoidung.setTextColor(Color.WHITE);
				edtGioitinhnguoidung.setBackgroundColor(Color.red(R.color.ColorPrimary));
				
				edtDiachinguoidung.setTextColor(Color.WHITE);
				edtDiachinguoidung.setBackgroundColor(Color.red(R.color.ColorPrimary));
				
				edtSdtnguoidung.setTextColor(Color.WHITE);
				edtSdtnguoidung.setBackgroundColor(Color.red(R.color.ColorPrimary));
				
				// An di cac nut
				btnHoantac.setVisibility(4);
				btnSua.setVisibility(4);
			}

			private void suaThongtin(String s_ngaysinh, String s_gioitinh,
					String s_diachi, String s_sdt) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(), s_ngaysinh +" + " + s_gioitinh +" + " + s_diachi + " + " + s_sdt,Toast.LENGTH_SHORT).show();
			}
		});
		
		edtNgaysinhnguoidung.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				chonNgaySinhDatetimePicker();
			}
		});
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
		//	Toast.makeText(getApplicationContext(), "Sua thong tin",Toast.LENGTH_SHORT).show();
			btnHoantac.setVisibility(0);
			btnSua.setVisibility(0);
			edtNgaysinhnguoidung.setEnabled(true);
			edtGioitinhnguoidung.setEnabled(true);
			edtDiachinguoidung.setEnabled(true);
			edtSdtnguoidung.setEnabled(true);
			
			ngaysinh = edtNgaysinhnguoidung.getText().toString();
			gioitinh = edtGioitinhnguoidung.getText().toString();
			diachi = edtDiachinguoidung.getText().toString();
			sdt = edtSdtnguoidung.getText().toString();
			//Toast.makeText(getApplicationContext(), ngaysinh +" + " + gioitinh +" + " + diachi + " + " + sdt,Toast.LENGTH_SHORT).show();
			
			edtNgaysinhnguoidung.setTextColor(Color.BLUE);
			edtNgaysinhnguoidung.setBackgroundColor(Color.WHITE);
	
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
		if (id == android.R.id.home){
			onBackPressed();
			
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void chonNgaySinhDatetimePicker(){
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.hoctap_lichhoc_datepicker_dialog);
		dialog.setTitle(R.string.string_LichHoc_DatePicker_Title);
		Calendar c = Calendar.getInstance();
		//c.setTime(ngayhientai);
		int year = c.get(Calendar.YEAR);
		int month = c.get(Calendar.MONTH);
		int day = c.get(Calendar.DATE);
		
		final DatePicker dp = (DatePicker) dialog.findViewById(R.id.dpLichHocDatepicker);
		dp.updateDate(year, month, day);

		Button dialogButtonOK = (Button) dialog.findViewById(R.id.btnLichHocDatePickerOK);
		// if button is clicked, close the custom dialog
		dialogButtonOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Calendar c = Calendar.getInstance();
				c.set(dp.getYear(), dp.getMonth(), dp.getDayOfMonth());
			//	ngayChonDatePicker = c.getTime();
			//	setinItialize(view, l, context);
				edtNgaysinhnguoidung.setText(dp.getDayOfMonth()+"/"+dp.getMonth()+"/"+dp.getYear());
				dialog.dismiss();
			}
		});
		Button dialogButtonCancel = (Button) dialog.findViewById(R.id.btnLichHocDatePickerCancel);
		// if button is clicked, close the custom dialog
		dialogButtonCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();
	}
}
