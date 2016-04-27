package com.doan.lichhoctap;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class DoiMatKhauActivity extends ActionBarActivity {
	private Toolbar toolbar;
	private EditText edtCurrentPwd, edtNewPwd, edtNewPwdReType;
	private Button btnChangePwd, btnUnChangePwd;
	private TextView tvError;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_doi_mat_khau);
		toolbar = (Toolbar) findViewById(R.id.DoiMatKhau_activity_tool_bar);
		setSupportActionBar(toolbar);

		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		edtCurrentPwd = (EditText) findViewById(R.id.edtCurrentPwd);
		edtNewPwd = (EditText) findViewById(R.id.edtNewPwd);
		edtNewPwdReType = (EditText) findViewById(R.id.edtNewPwdReType);
		btnChangePwd = (Button) findViewById(R.id.btnChangePwd);
		btnUnChangePwd = (Button) findViewById(R.id.btnUnChangePwd);
		tvError = (TextView) findViewById(R.id.tvError);
		btnChangePwd.setEnabled(false);
		
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
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
