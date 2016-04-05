package com.doan.lichhoctap;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.doan.app.Global;
import com.doan.database_handle.ExecuteQuery;
import com.doan.model.SinhVien;
import com.doan.model.ThongBao;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

	private EditText edEmail, edPwd;
	private Button btnLogin;
	private ExecuteQuery exeQ;
	private ArrayList<SinhVien> arrAllSV;
	private SinhVien sv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		exeQ = new ExecuteQuery(this);
		exeQ.createDatabase();
		exeQ.open();
		arrAllSV = exeQ.getAllSinhVien();
		
		edEmail = (EditText) findViewById(R.id.edtLoginEmail);
		edPwd = (EditText) findViewById(R.id.edtLoginPwd);
		edEmail.setText("viethungtrn94@gmail.com");
		edPwd.setText("1");
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				/*if(checkAcc() == true){
					Toast.makeText(getBaseContext(), "Ok", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(LoginActivity.this, SplashScreen.class);
					startActivity(intent);
				}else {
					Toast.makeText(getBaseContext(), "Failed", Toast.LENGTH_SHORT).show();
				}*/
				loginToServer(edEmail.getText().toString(), edPwd.getText().toString());
			}
		});
		checkEditText();
		edEmail.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				checkEditText();
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
		edPwd.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				checkEditText();
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
	private boolean checkAcc(){
		String dbEmail, dbPwd, inputEmail, inputPwd;
		inputEmail = edEmail.getText().toString();
		inputPwd = edPwd.getText().toString();
		int i=0;
		if(exeQ.getSvUser(inputEmail, inputPwd, sv) == true){
			return true;
		}else {
			return false;
		}
		
	}
	private void checkEditText(){
		String eemail, epwd;
		eemail = edEmail.getText().toString();
		epwd = edPwd.getText().toString();
		if(eemail.matches("") || epwd.matches("")){
			btnLogin.setEnabled(false);
			btnLogin.setTextColor(R.color.soft_grey);
			btnLogin.setTextColor(getBaseContext().getResources().getColor(R.color.soft_grey));
		}
		else {
			btnLogin.setEnabled(true);
			btnLogin.setTextColor(getBaseContext().getResources().getColor(R.color.white));
		}
	}
	private void loginToServer(String email, String matkhau){
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("email", email);
		String pwd = Global.maHoaMd5(matkhau);
		params.put("matkhau", pwd);
		String url = Global.BASE_URI + Global.URI_DANGNHAP;
		client.post(url, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				Log.e("JsonLogin", response);
				if (executeWhenLoginSuccess(response)) {
					Intent intent = new Intent(LoginActivity.this, SplashScreen.class);
					startActivity(intent);
				} else {
					/*Toast.makeText(getApplicationContext(),
							"Login that bai", Toast.LENGTH_LONG)
							.show();*/
					
					//test
					Intent intent = new Intent(LoginActivity.this, SplashScreen.class);
					startActivity(intent);
				}
			}

			public void onFailure(int statusCode, Throwable error,
					String content) {
				Log.e("JsonLogin", error+" "+content);
				Toast.makeText(getApplicationContext(),
						error+"", Toast.LENGTH_LONG)
						.show();
				//test
				Intent intent = new Intent(LoginActivity.this, SplashScreen.class);
				startActivity(intent);
			}
		});
	}
	private boolean executeWhenLoginSuccess(String response) {
		Toast.makeText(getApplicationContext(),
				response+"", Toast.LENGTH_LONG)
				.show();
		String status = "";
		String masinhvien = "";
		try {
			/*JSONArray arrObj = new JSONArray(response);
			for (int i = 0; i < arrObj.length(); i++) {
				JSONObject thongbaoJson = arrObj.getJSONObject(i);
				status = thongbaoJson.optString("status");
			}*/
			JSONObject loginJson = new JSONObject(response);
			status = loginJson.optString("status");
			if(status.matches(getString(R.string.string_login_sinhvien_success))){
				masinhvien = loginJson.optString("pk_sv");
				Global.saveStringPreference(getBaseContext(), "MaSVDN", masinhvien);
				return true;
			}else {
				return false;
			}
			//return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
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
