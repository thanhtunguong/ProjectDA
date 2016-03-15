package com.doan.lichhoctap;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class BaiVietDetailActivity extends ActionBarActivity {
	
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bai_viet_detail);
		toolbar = (Toolbar) findViewById(R.id.BaiViet_detail_activity_tool_bar);
		setSupportActionBar(toolbar);

		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		
		Intent intent_FrgBV_to_ActvtBVdetail = getIntent();
		String bv_Title = intent_FrgBV_to_ActvtBVdetail.getStringExtra("bvTitle");
		String bv_Contents = intent_FrgBV_to_ActvtBVdetail.getStringExtra("bvContents");
		
		TextView tvTitle, tvContents;
		tvTitle = (TextView) findViewById(R.id.tvBVdetail_Title);
		tvContents = (TextView) findViewById(R.id.tvBVdetail_Contents);
		
		tvTitle.setText(bv_Title);
		tvContents.setText(bv_Contents);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.bai_viet_detail, menu);
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
