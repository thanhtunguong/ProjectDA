package com.doan.lichhoctap;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.doan.app.Global;
import com.doan.model.SinhVienThongBao;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;

public class DoiMatKhauActivity extends ActionBarActivity {
	private Toolbar toolbar;
	private EditText edtCurrentPwd, edtNewPwd, edtNewPwdReType;
	private Button btnChangePwd, btnUnChangePwd;
	private TextView tvError;
	private CheckBox cbPwdVisible;
	private Context c;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doi_mat_khau);
		toolbar = (Toolbar) findViewById(R.id.DoiMatKhau_activity_tool_bar);
		setSupportActionBar(toolbar);

		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		c = this;
		edtCurrentPwd = (EditText) findViewById(R.id.edtCurrentPwd);
		edtNewPwd = (EditText) findViewById(R.id.edtNewPwd);
		edtNewPwdReType = (EditText) findViewById(R.id.edtNewPwdReType);
		btnChangePwd = (Button) findViewById(R.id.btnChangePwd);
		btnUnChangePwd = (Button) findViewById(R.id.btnUnChangePwd);
		tvError = (TextView) findViewById(R.id.tvError);
		cbPwdVisible = (CheckBox) findViewById(R.id.cbPwdVisible);
		btnChangePwd.setEnabled(false);
		
		edtCurrentPwd.setSelectAllOnFocus(true);
		edtNewPwd.setSelectAllOnFocus(true);
		edtNewPwdReType.setSelectAllOnFocus(true);
		edtCurrentPwd.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				checkValidate();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		edtNewPwd.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				checkValidate();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		edtNewPwdReType.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				checkValidate();
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
		cbPwdVisible.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				// TODO Auto-generated method stub
				if(cbPwdVisible.isChecked()){
					edtCurrentPwd.setTransformationMethod(null);
					edtNewPwd.setTransformationMethod(null);
					edtNewPwdReType.setTransformationMethod(null);
				}else {
					if(cbPwdVisible.isChecked() == false){
						edtCurrentPwd.setTransformationMethod(new PasswordTransformationMethod());
						edtNewPwd.setTransformationMethod(new PasswordTransformationMethod());
						edtNewPwdReType.setTransformationMethod(new PasswordTransformationMethod());
					}
				}
			}
		});
		btnChangePwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				btnUnChangePwd.setEnabled(false);
				doiMatKhau();
			}
		});
		btnUnChangePwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				onBackPressed();
			}
		});
	}
	private void checkValidate(){
		tvError.setText("");
		if(edtCurrentPwd.getText().toString().matches("") == false
			&& edtNewPwd.getText().toString().matches("") == false
			&& edtNewPwdReType.getText().toString().matches("") == false
			&& edtCurrentPwd.getText().toString().matches(edtNewPwd.getText().toString()) == false
			&& edtNewPwd.getText().toString().matches(edtNewPwdReType.getText().toString())){
			tvError.setText("");
			btnChangePwd.setEnabled(true);
		}else {
			btnChangePwd.setEnabled(false);
		}
		if(edtCurrentPwd.getText().toString().matches(edtNewPwd.getText().toString())){
			tvError.setText(getString(R.string.string_matkhaumoitrung));
		}
		if(edtNewPwd.getText().toString().matches(edtNewPwdReType.getText().toString()) == false
				&& edtNewPwdReType.getText().toString().matches("") == false){
			tvError.setText(getString(R.string.string_xacnhanmatkhaukhongdung));
		}
	}

	private void doiMatKhau() {
		String magiangvien = Global.getStringPreference(c, "MaGVDN", "0");
		String masinhvien = Global.getStringPreference(c, "MaSVDN", "0");
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		String uri = "";
		if (masinhvien.matches("")) {
			params.put("magiangvien", magiangvien);
			uri = Global.URI_DOI_MAT_KHAU_GV;
		} else {
			params.put("masinhvien", masinhvien);
			uri = Global.URI_DOI_MAT_KHAU_SV;
		}
		params.put("matkhaucu", Global.maHoaMd5(edtCurrentPwd.getText().toString()));
		params.put("matkhaumoi", Global.maHoaMd5(edtNewPwd.getText().toString()));
		params.put("access_token", Global.getStringPreference(c, "access_token", ""));
		String url = Global.BASE_URI + uri;
		client.post(url, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				Log.e("DanhSachSinhVienTheoMaLop", response);
				btnUnChangePwd.setEnabled(true);
				if (executeWhenDoiMatKhauSuccess(response)) {
					edtCurrentPwd.setText("");
					edtNewPwd.setText("");
					edtNewPwdReType.setText("");
					tvError.setText(getString(R.string.string_tv_doi_mat_khau_thanh_cong));
					tvError.setTextColor(getResources().getColor(R.color.indigo));
				} else {
					tvError.setTextColor(getResources().getColor(R.color.ColorPrimary));
					tvError.setText(getString(R.string.string_tv_doi_mat_khau_that_bai));
				}
			}

			public void onFailure(int statusCode, Throwable error, String content) {
				Log.e("DanhSachSinhVienTheoMaLop", error + " " + content);
				btnUnChangePwd.setEnabled(true);
				/*
				 * Toast.makeText(getApplicationContext(), error+"",
				 * Toast.LENGTH_LONG) .show();
				 */
			}
		});
	}
	private boolean executeWhenDoiMatKhauSuccess(String response) {
		String status = "";
		try {
			JSONArray arrObj = new JSONArray(response);
			for (int i = 0; i < arrObj.length(); i++) {
				JSONObject svtbJson = arrObj.getJSONObject(i);
				status = svtbJson.optString("status");
			}
			if(status.matches(getString(R.string.string_doi_mat_khau_thanh_cong))){
				return true;
			}else {
				return false;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.doi_mat_khau, menu);
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
