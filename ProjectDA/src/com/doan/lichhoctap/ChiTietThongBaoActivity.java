package com.doan.lichhoctap;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.doan.adapter.ChiTietThongBaoAdapter;
import com.doan.app.Global;
import com.doan.database_handle.ExecuteQuery;
import com.doan.model.ChiTietThongBao;
import com.doan.model.ReplyTrongNgay;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView.ScaleType;
import android.widget.EditText;

public class ChiTietThongBaoActivity extends ActionBarActivity {
	
	private ExecuteQuery exeQ;
	private String mathongbao;
	private SwipeRefreshLayout swipeRefreshLayout;
	private ArrayList<ChiTietThongBao> arrChiTietThongBao;
	private ArrayList<ReplyTrongNgay> arrReplyTrongNgay;
	private ArrayList<Object> childItems;
	private ChiTietThongBaoAdapter adapter;
	private ExpandableListView elv_Reply;
	private Toolbar toolbar;
	private Context c;
	private EditText edtReplyBox;
	private ImageButton btnSendReplyThongBao;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.a_test_2);
		toolbar = (Toolbar) findViewById(R.id.chi_tiet_thong_bao_activity_tool_bar);
		setSupportActionBar(toolbar);

		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		c = this;
		exeQ = new ExecuteQuery(this);
		
		exeQ.open();
		edtReplyBox = (EditText) findViewById(R.id.edtReplyBox);
		btnSendReplyThongBao = (ImageButton) findViewById(R.id.btnSendReplyThongBao);
		btnSendReplyThongBao.setEnabled(false);
		swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_chi_tiet_thong_bao);
		swipeRefreshLayout.setRefreshing(true);
		Intent callerIntent = getIntent();
		Bundle bundle = callerIntent.getExtras();
		mathongbao = bundle.getString("mathongbaoExtra");
		elv_Reply = (ExpandableListView) findViewById(R.id.elvReplyThongBao);
		elv_Reply.setClickable(true);
		elv_Reply.setOnScrollListener(new OnScrollListener() {

		    @Override
		    public void onScroll(AbsListView view, int firstVisibleItem,
		            int visibleItemCount, int totalItemCount) {
		        boolean enable = false;
		        if(elv_Reply != null && elv_Reply.getChildCount() > 0){
		            // check if the first item of the list is visible
		            boolean firstItemVisible = elv_Reply.getFirstVisiblePosition() == 0;
		            // check if the top of the first item is visible
		            boolean topOfFirstItemVisible = elv_Reply.getChildAt(0).getTop() == 0;
		            // enabling or disabling the refresh layout
		            enable = firstItemVisible && topOfFirstItemVisible;
		        }
		        swipeRefreshLayout.setEnabled(enable);
		    }

			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
		});
		elv_Reply.setGroupIndicator(null);
		getReplyTheoThongBao(mathongbao);
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				exeQ.open();
				getReplyTheoThongBao(mathongbao);
			}
		});
		exeQ.close();
		edtReplyBox.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				validateReply();
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
		btnSendReplyThongBao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				sendReply();
			}
		});
	}
	private void setList(){
		arrChiTietThongBao = new ArrayList<ChiTietThongBao>();
		arrReplyTrongNgay = new ArrayList<ReplyTrongNgay>();
		//arrChiTietThongBao = exeQ.getAllReplyTheoThongBaoSqLite(mathongbao);
		childItems = new ArrayList<Object>();
		xulyPhanDinhNgay();
		adapter = new ChiTietThongBaoAdapter(arrReplyTrongNgay, childItems, this);
		elv_Reply.setAdapter(adapter);
	}
	private void xulyPhanDinhNgay() {
		//ArrayList<ChiTietThongBao> arrChiTietThongBao = new ArrayList<ChiTietThongBao>();
		arrChiTietThongBao.addAll(exeQ.getAllReplyTheoThongBaoSqLite(mathongbao));
		for (int i = 0; i < arrChiTietThongBao.size(); i++) {
			ChiTietThongBao tb = arrChiTietThongBao.get(i);
			String thuNgayThang = tb.getThoiGianTraLoi().substring(0, 10);
			//Date date = Global.epKieuDateAndTime(tb.getThoiGianTraLoi());
			Date date = Global.epKieuDate(thuNgayThang);
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			int check = 0;
			for (ReplyTrongNgay replyTrongNgay : arrReplyTrongNgay) {
				if(replyTrongNgay.getNgay().equals(date)){
					check = 1;
				}
			}
			if(check == 0){
				ReplyTrongNgay tbtn = new ReplyTrongNgay();
				tbtn.setNgay(date);
				ArrayList<ChiTietThongBao> arrtb = new ArrayList<ChiTietThongBao>();
				for (ChiTietThongBao thongBao : arrChiTietThongBao) {
					if(Global.epKieuDate(thongBao.getThoiGianTraLoi().substring(0, 10)).equals(date)){
						arrtb.add(thongBao);
						//arrThongBao.remove(arrThongBao.indexOf(thongBao));
					}
				}
				Collections.sort(arrtb);
				tbtn.setArrChiTiet(arrtb);
				arrReplyTrongNgay.add(tbtn);
			}
		}
		Collections.sort(arrReplyTrongNgay);
		for (ReplyTrongNgay replyThongBaoTrongNgay : arrReplyTrongNgay) {
			childItems.add(replyThongBaoTrongNgay.getArrChiTiet());
		}
	}
	private void validateReply(){
		if(edtReplyBox.getText().toString().matches("") == false){
			btnSendReplyThongBao.setEnabled(true);
			btnSendReplyThongBao.setBackgroundResource(R.drawable.ic_send_color_primary_48dp);
			btnSendReplyThongBao.setScaleType(ScaleType.FIT_XY);
		}else {
			btnSendReplyThongBao.setEnabled(false);
			btnSendReplyThongBao.setBackgroundResource(R.drawable.ic_send_color_primary_48dp_grey);
			btnSendReplyThongBao.setScaleType(ScaleType.FIT_XY);
		}
	}
	private void sendReply(){
		String URI_REPLY, MaNguoiDung, NoiDung, AccToken, MaThongBao;
		int check;
		NoiDung = edtReplyBox.getText().toString();
		MaThongBao = mathongbao;
		AccToken = Global.getStringPreference(c, "access_token", "");
		if(Global.getStringPreference(c, "MaSVDN", "0").matches("0")){
			URI_REPLY = Global.URI_GUI_REPLY_GV;
			MaNguoiDung = Global.getStringPreference(c, "MaGVDN", "0");
			check = 0;
		}else {
			URI_REPLY = Global.URI_GUI_REPLY_SV;
			MaNguoiDung = Global.getStringPreference(c, "MaSVDN", "0");
			check = 1;
		}
		sendReplyAsync(MaNguoiDung, NoiDung, MaThongBao, AccToken, URI_REPLY, check);
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.chi_tiet_thong_bao, menu);
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
	
	private void getReplyTheoThongBao(String mathongbao) {

		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("mathongbao", mathongbao);
		params.put("access_token", Global.getStringPreference(c, "access_token", ""));
		client.post(Global.BASE_URI + Global.URI_REPLY_THEO_MA_THONGBAO, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				// Log.e("loginToServer", response);
				if (executeWhenGetReplyTheoThongBaoSuccess(response)) {
					setList();
					exeQ.close();
					swipeRefreshLayout.setRefreshing(false);
				} else {
					
				}
			}
			public void onFailure(int statusCode, Throwable error, String content) {
				Log.e("loginToServer", error + " " + content);
			}
		});
	}

	private boolean executeWhenGetReplyTheoThongBaoSuccess(String response) {
		ArrayList<ChiTietThongBao> arrChiTietThongBao = new ArrayList<ChiTietThongBao>();
		String MaCTThongBao, MaNguoiGui, TenNguoiGui, NoiDung, ThoiGian, MaThongBao;
		try {
			JSONArray arrObj = new JSONArray(response);
			for (int i = 0; i < arrObj.length(); i++) {
				JSONObject CTThongBaoJson = arrObj.getJSONObject(i);
				ChiTietThongBao cttb = new ChiTietThongBao();
				
				cttb.setMaChiTietThongBao(CTThongBaoJson.optString("cttthongbao"));
				cttb.setMaNguoiReply(CTThongBaoJson.optString("PK_User"));
				cttb.setTenNguoiReply(CTThongBaoJson.optString("HoTen"));
				cttb.setNoiDungReply(CTThongBaoJson.optString("NoiDungTraLoi"));
				//Date thoigian = Global.epKieuDateAndTime(CTThongBaoJson.optString("ThoiGianTraLoi"));
				cttb.setThoiGianTraLoi(CTThongBaoJson.optString("ThoiGianTraLoi"));
				cttb.setMaThongBao(CTThongBaoJson.optString("mathongbao"));
				String manguoidung = CTThongBaoJson.optString("PK_User").substring(0, 2);
				int layoutId;
				if(manguoidung.matches("GV")){
					if(CTThongBaoJson.optString("PK_User").matches(Global.getStringPreference(c, "MaGVDN", "0"))){
						layoutId = R.layout.chi_tiet_thong_bao_child_item_others_gv_for_gv;
					}else {
						layoutId = R.layout.chi_tiet_thong_bao_child_item_others_gv;
					}
				}else {
					if(CTThongBaoJson.optString("PK_User").matches(Global.getStringPreference(c, "MaSVDN", "0"))){
						layoutId = R.layout.chi_tiet_thong_bao_child_item_me;
					}else {
						layoutId = R.layout.chi_tiet_thong_bao_child_item_others;
					}
				}
				cttb.setLayoutId(layoutId);
				
				arrChiTietThongBao.add(cttb);
				/*MaCTThongBao = CTThongBaoJson.optString("cttthongbao");
				MaNguoiGui = CTThongBaoJson.optString("PK_User");
				TenNguoiGui = CTThongBaoJson.optString("HoTen");
				NoiDung = CTThongBaoJson.optString("NoiDungTraLoi");
				ThoiGian = CTThongBaoJson.optString("ThoiGianTraLoi");
				MaThongBao = CTThongBaoJson.optString("mathongbao");
				
				exeQ.insert_tbl_CTThongBao_single(MaCTThongBao, MaNguoiGui, TenNguoiGui, NoiDung, ThoiGian, MaThongBao);*/
			}
			exeQ.insert_tbl_CTThongBao_multi(arrChiTietThongBao);
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			Log.e("loi", e + "");
			return false;
		}
	}
	private void sendReplyAsync(String MaNguoiDung, String NoiDung, final String mathongbao, String AccToken, String URI_REPLY, int check) {
		String paraMaNguoiDungName;
		if(check == 0){
			paraMaNguoiDungName = "magiangvien";
		}else {
			paraMaNguoiDungName = "masinhvien";
		}
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put(paraMaNguoiDungName, MaNguoiDung);
		params.put("noidung", NoiDung);
		params.put("mathongbao", mathongbao);
		params.put("access_token", AccToken);
		client.post(Global.BASE_URI + URI_REPLY, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				// Log.e("loginToServer", response);
				if (executeWhenSendReplyTheoThongBaoSuccess(response)) {
					getReplyTheoThongBao(mathongbao);
					swipeRefreshLayout.setRefreshing(false);
					edtReplyBox.setText("");
					elv_Reply.smoothScrollToPosition(elv_Reply.getChildCount() - 1);
				} else {
					
				}
			}
			public void onFailure(int statusCode, Throwable error, String content) {
				Log.e("loginToServer", error + " " + content);
			}
		});
	}
	private boolean executeWhenSendReplyTheoThongBaoSuccess(String response) {
		ArrayList<ChiTietThongBao> arrChiTietThongBao = new ArrayList<ChiTietThongBao>();
		String Status = "";
		try {
			JSONArray arrObj = new JSONArray(response);
			for (int i = 0; i < arrObj.length(); i++) {
				JSONObject Json = arrObj.getJSONObject(i);
				Status = Json.optString("status");
			}
			if(Status.matches(getString(R.string.string_success))){
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
