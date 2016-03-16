package com.doan.lichhoctap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends ActionBarActivity {

	EditText edEmail, edPwd;
	Button btnLogin;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		edEmail = (EditText) findViewById(R.id.edtLoginEmail);
		edPwd = (EditText) findViewById(R.id.edtLoginPwd);
		edEmail.setText("abc@gmail.com");
		edPwd.setText("123");
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String email = getString(R.string.acc1_email);
				String pwd = getString(R.string.acc1_pwd);
				String email_input = edEmail.getText().toString();
				String pwd_input = edPwd.getText().toString();
				if(email_input.matches(email) && pwd_input.matches(pwd)){
					Toast.makeText(getBaseContext(), "Ok", Toast.LENGTH_SHORT).show();
					Intent intent = new Intent(LoginActivity.this, SplashScreen.class);
					startActivity(intent);
				}else {
					Toast.makeText(getBaseContext(), "Failed", Toast.LENGTH_SHORT).show();
				}
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
