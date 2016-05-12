package com.doan.lichhoctap;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.doan.app.Global;
import com.doan.database_handle.ExecuteQuery;
import com.doan.model.ChiTietThongBao;
import com.doan.model.LopHanhChinh;
import com.doan.model.SinhVienThongBao;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TaoThongBaoGVActivity extends ActionBarActivity {
	private LinearLayout lnAddUser;
	private ArrayList<LopHanhChinh> arrayLopHanhChinh;
	private ArrayList<SinhVienThongBao> arrSVTB;
	private ArrayList<String> arrMaLop;
	private ExecuteQuery exeQ;
	private Activity activity;
	private TextView tvNumberUserAddThongBao;
	private ImageButton btnSendThongBao;
	private Toolbar toolbar;
	private ImageView ivNumberUser;
	private EditText edND, edtThongBaoTieuDe;
	private Boolean checkTaoThongBao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tao_thong_bao_gv);
		toolbar = (Toolbar) findViewById(R.id.thong_bao_gv_activity_tool_bar);
		setSupportActionBar(toolbar);

		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		lnAddUser = (LinearLayout) findViewById(R.id.lnAddUser);
		tvNumberUserAddThongBao = (TextView) findViewById(R.id.tvNumberUserAddThongBao);
		btnSendThongBao = (ImageButton) findViewById(R.id.btnSendThongBao);
		ivNumberUser = (ImageView) findViewById(R.id.ivNumberUserAddThongBao);
		edND = (EditText) findViewById(R.id.edtThongBaoConTents);
		edtThongBaoTieuDe = (EditText) findViewById(R.id.edtThongBaoTieuDe);
		
		Intent callerIntent = getIntent();
		Bundle bundle = callerIntent.getExtras();
		checkTaoThongBao = bundle.getBoolean("checkTaoThongBao");
		
		btnSendThongBao.setEnabled(false);
		activity = this;
		exeQ = new ExecuteQuery(activity);
		exeQ.open();
		arrayLopHanhChinh = new ArrayList<LopHanhChinh>();
		arrSVTB = new ArrayList<SinhVienThongBao>();
		arrMaLop = new ArrayList<String>();
		arrMaLop = exeQ.getAllLopHanhChinhSqLite();
		for (String malop : arrMaLop) {
			LopHanhChinh lhc = new LopHanhChinh();
			lhc.setMaLopHanhChinh(malop);
			lhc.setArrSinhVien(exeQ.getAllSinhVienThongBaoTheoMaLopSqLite(malop));
			lhc.setCheck(0);
			arrayLopHanhChinh.add(lhc);
		}
		exeQ.close();
		
		edND.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				checkDone();
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
		edtThongBaoTieuDe.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				checkDone();
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
		
		lnAddUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(TaoThongBaoGVActivity.this, AddUserActivity.class);
				ArrayList<LopHanhChinh> at = new ArrayList<LopHanhChinh>();
				at.addAll(arrayLopHanhChinh);
				ArrayList<String> as = new ArrayList<String>();
				as.addAll(arrMaLop);
				intent.putExtra("danhsachBanDau", arrayLopHanhChinh);
				intent.putExtra("danhsachMaLop", arrMaLop);
				startActivityForResult(intent, 0);
			}
		});
		
		btnSendThongBao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendThongBao();
			}
		});
	}
	@Override
	public void onActivityResult(int requestCode,int resultCode, Intent data)
	{
	    //edtText.setText(data.getStringExtra("NAMA_PERASAT"));
	    arrayLopHanhChinh = new ArrayList<LopHanhChinh>();
	    arrayLopHanhChinh = (ArrayList<LopHanhChinh>) data.getSerializableExtra("danhsachSVDone");
	    arrSVTB = new ArrayList<SinhVienThongBao>();
	    for (LopHanhChinh lop : arrayLopHanhChinh) {
			for (SinhVienThongBao sv : lop.getArrSinhVien()) {
				if(sv.getChecked() == 1){
					arrSVTB.add(sv);
				}
			}
		}
	    if(arrSVTB.size() > 0){
	    	tvNumberUserAddThongBao.setText(" - "+ arrSVTB.size() + " sinh viên");
	    	tvNumberUserAddThongBao.setTextColor(getResources().getColor(R.color.ColorPrimary));
	    	ivNumberUser.setBackgroundResource(R.drawable.ic_add_user_red);
	    	ivNumberUser.setScaleType(ScaleType.FIT_XY);
	    	checkDone();
	    	/*btnSendThongBao.setEnabled(true);
	    	btnSendThongBao.setBackgroundResource(R.drawable.ic_send_color_primary_48dp);
	    	btnSendThongBao.setScaleType(ScaleType.FIT_XY);*/
	    }else {
			tvNumberUserAddThongBao.setText(" - "+ getText(R.string.string_moi_chon_sv));
			tvNumberUserAddThongBao.setTextColor(getResources().getColor(R.color.greyDeactive));
			ivNumberUser.setBackgroundResource(R.drawable.ic_add_user);
	    	ivNumberUser.setScaleType(ScaleType.FIT_XY);
	    	checkDone();
			/*btnSendThongBao.setEnabled(false);
			btnSendThongBao.setBackgroundResource(R.drawable.ic_send_color_primary_48dp_grey);
	    	btnSendThongBao.setScaleType(ScaleType.FIT_XY);*/
		}
	}
	private void checkDone(){
		String str = edND.getText().toString();
		if(edND.getText().toString().matches("") == false 
				&& edtThongBaoTieuDe.getText().toString().matches("") == false && arrSVTB.size() > 0){
			/*tvNumberUserAddThongBao.setText(" - "+ arrSVTB.size() + " sinh viên");
	    	tvNumberUserAddThongBao.setTextColor(getResources().getColor(R.color.ColorPrimary));
	    	ivNumberUser.setBackgroundResource(R.drawable.ic_add_user_red);
	    	ivNumberUser.setScaleType(ScaleType.FIT_XY);*/
	    	btnSendThongBao.setEnabled(true);
	    	btnSendThongBao.setBackgroundResource(R.drawable.ic_send_color_primary_48dp);
	    	btnSendThongBao.setScaleType(ScaleType.FIT_XY);
		}else {
			btnSendThongBao.setEnabled(false);
			btnSendThongBao.setBackgroundResource(R.drawable.ic_send_color_primary_48dp_grey);
	    	btnSendThongBao.setScaleType(ScaleType.FIT_XY);
		}
	}
	
	@Override
	public void onBackPressed() {
	    // your code.
		Intent intent = new Intent();
		intent.putExtra("checkTaoThongBao", checkTaoThongBao);
		setResult(RESULT_OK, intent);
		finish();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.thong_bao_gv, menu);
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
	private void sendThongBao() {
		String magiangvien = Global.getStringPreference(activity, "MaGVDN", "");
		String tieude = edtThongBaoTieuDe.getText().toString();
		String noidung = edND.getText().toString();
		String danhsachsv = "";
		for (LopHanhChinh lop : arrayLopHanhChinh) {
			for (SinhVienThongBao sv : lop.getArrSinhVien()) {
				if(sv.getChecked() == 1){
					danhsachsv = danhsachsv + sv.getMaSV() + "/";
				}
			}
		}
		String access_token = Global.getStringPreference(activity, "access_token", "");
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("magiangvien", magiangvien);
		params.put("tieude", tieude);
		params.put("noidung", noidung);
		params.put("danhsachsv", danhsachsv);
		params.put("access_token", access_token);

		client.post(Global.BASE_URI + Global.URI_GUI_THONG_BAO, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				// Log.e("loginToServer", response);
				if (executeWhensendThongBaoSuccess(response)) {
					Toast.makeText(activity, "ok", Toast.LENGTH_SHORT).show();
					checkTaoThongBao = true;
					onBackPressed();
				} else {
					Toast.makeText(activity, "fail 1", Toast.LENGTH_SHORT).show();
				}
			}
			public void onFailure(int statusCode, Throwable error, String content) {
				Log.e("loginToServer", error + " " + content);
				Toast.makeText(activity, "fail 2", Toast.LENGTH_SHORT).show();
			}
		});
	}

	private boolean executeWhensendThongBaoSuccess(String response) {
		ArrayList<ChiTietThongBao> arrChiTietThongBao = new ArrayList<ChiTietThongBao>();
		String status = "";
		try {
			JSONArray arrObj = new JSONArray(response);
			for (int i = 0; i < arrObj.length(); i++) {
				JSONObject sendTBJSON = arrObj.getJSONObject(i);
				status = sendTBJSON.optString("status");
				
			}
			if(status.matches(activity.getString(R.string.string_success))){
				return true;
			}else {
				return false;
			}
		} catch (JSONException e) {
			e.printStackTrace();
			Log.e("loi", e + "");
			return false;
		}
	}
}
