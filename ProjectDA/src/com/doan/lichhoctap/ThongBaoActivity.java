package com.doan.lichhoctap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.doan.adapter.ThongBaoAdapter;
import com.doan.adapter.ThongBaoListViewAdapter;
import com.doan.app.Global;
import com.doan.database_handle.ExecuteQuery;
import com.doan.model.ThongBao;
import com.doan.model.ThongBaoTrongNgay;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.AbsListView.OnScrollListener;

public class ThongBaoActivity extends ActionBarActivity {

	private ExpandableListView elv_ThongBao;
	private ExecuteQuery exeQ;
	private Context context;
	private Activity activity;
	private ArrayList<ThongBaoTrongNgay> arrThongBaoTrongNgay;
	private ArrayList<ThongBao> arrAllThongBao;
	private ArrayList<Object> childItems;
	private LinearLayout lnCreateTB;
	private ListView lv_ThongBao;
	private SwipeRefreshLayout swipeRefreshLayout;
	private ImageView ivDangXuatGV;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_thong_bao);
		context = this;
		activity = this;
		Toolbar toolbar = (Toolbar) findViewById(R.id.thong_bao_activity_tool_bar);
		ivDangXuatGV = (ImageView) toolbar.findViewById(R.id.ivDangXuatGV);
		lnCreateTB = (LinearLayout) findViewById(R.id.lnCreateTB);
		setSupportActionBar(toolbar);
		String magiangvien = Global.getStringPreference(context, "MaGVDN", "");
		if(magiangvien.matches("")){
			if (getSupportActionBar() != null) {
				getSupportActionBar().setDisplayHomeAsUpEnabled(true);
			}
			ivDangXuatGV.setVisibility(View.GONE);
			lnCreateTB.setVisibility(View.GONE);
		}
		swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_refresh_layout_thongbao_GV);
		swipeRefreshLayout.setRefreshing(true);
		
		exeQ = new ExecuteQuery(context);
		
		//elv_ThongBao = (ExpandableListView) findViewById(R.id.elvThongBao);
		lv_ThongBao = (ListView) findViewById(R.id.lvThongBao);
		
		
		setList();
		/*xulyPhanDinhNgay();*/
		//elv_ThongBao.setGroupIndicator(null);
		//elv_ThongBao.setClickable(true);
		/*ThongBaoAdapter adapter = new ThongBaoAdapter(arrThongBaoTrongNgay, childItems, this);
		ThongBaoListViewAdapter adapter2 = new ThongBaoListViewAdapter(activity, R.layout.list_thong_bao_item, arrAllThongBao);*/
		//elv_ThongBao.setAdapter(adapter);
		/*lv_ThongBao.setAdapter(adapter2);*/
		lv_ThongBao.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				ThongBao tb = arrAllThongBao.get(position);
				Toast.makeText(context, "" + tb.getMaThongBao(), Toast.LENGTH_SHORT).show();
				/*Bundle ThongBaoSingle = new Bundle();
				ThongBaoSingle.putString("mathongbaoBundle", tb.getMaThongBao());*/
				Intent intent = new Intent(ThongBaoActivity.this, ChiTietThongBaoActivity.class);
				intent.putExtra("mathongbaoExtra", tb.getMaThongBao());
				startActivity(intent);
			}
		});
		lv_ThongBao.setOnScrollListener(new OnScrollListener() {

		    @Override
		    public void onScroll(AbsListView view, int firstVisibleItem,
		            int visibleItemCount, int totalItemCount) {
		        boolean enable = false;
		        if(lv_ThongBao != null && lv_ThongBao.getChildCount() > 0){
		            // check if the first item of the list is visible
		            boolean firstItemVisible = lv_ThongBao.getFirstVisiblePosition() == 0;
		            // check if the top of the first item is visible
		            boolean topOfFirstItemVisible = lv_ThongBao.getChildAt(0).getTop() == 0;
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
		//elv_ThongBao.setSelection(arrThongBaoTrongNgay.size() - 1);
		/*elv_ThongBao.setOnChildClickListener(new OnChildClickListener() {

			@Override
			public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition,
					long id) {
				// TODO Auto-generated method stub
				ThongBao tb = ((ArrayList<ThongBao>) childItems.get(groupPosition)).get(childPosition);
				Toast.makeText(context, "" + tb.getMaThongBao(), Toast.LENGTH_SHORT).show();
				Bundle ThongBaoSingle = new Bundle();
				ThongBaoSingle.putString("mathongbaoBundle", tb.getMaThongBao());
				Intent intent = new Intent(ThongBaoActivity.this, ChiTietThongBaoActivity.class);
				intent.putExtra("mathongbaoExtra", tb.getMaThongBao());
				startActivity(intent);
				return false;
			}
		});*/
		lnCreateTB.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(ThongBaoActivity.this, TaoThongBaoGVActivity.class);
				intent.putExtra("checkTaoThongBao", false);
				//startActivity(intent);
				startActivityForResult(intent, 0);
			}
		});
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				swipeRefreshLayout.setRefreshing(true);
				String magiangvien = Global.getStringPreference(context, "MaGVDN", "");
				if(magiangvien.matches("")){				
					getThongBaoSinhVien();
					//getThongBaoGiangVien();
				}else {
					getThongBaoGiangVien();
				}
			}
		});
		ivDangXuatGV.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Global g = new Global();
				g.dialogLogoutConfirm(activity);
			}
		});
	}
	private void setList(){
		arrThongBaoTrongNgay = new ArrayList<ThongBaoTrongNgay>();
		childItems = new ArrayList<Object>();
		arrAllThongBao = new ArrayList<ThongBao>();
		xulyPhanDinhNgay();
		ThongBaoAdapter adapter = new ThongBaoAdapter(arrThongBaoTrongNgay, childItems, this);
		ThongBaoListViewAdapter adapter2 = new ThongBaoListViewAdapter(activity, R.layout.list_thong_bao_item, arrAllThongBao);
		lv_ThongBao.setAdapter(adapter2);
		swipeRefreshLayout.setRefreshing(false);
	}

	private void xulyPhanDinhNgay() {
		exeQ.open();
		ArrayList<ThongBao> arrThongBao = new ArrayList<ThongBao>();
		arrThongBao.addAll(exeQ.getAllThongBaoSqLite());
		for (int i = 0; i < arrThongBao.size(); i++) {
			ThongBao tb = arrThongBao.get(i);
			Date date = epKieuDate(tb.getNgayTaoThongBao());
			int check = 0;
			for (ThongBaoTrongNgay thongBaoTrongNgay : arrThongBaoTrongNgay) {
				if(thongBaoTrongNgay.getNgayCuThe().equals(date)){
					check = 1;
				}
			}
			if(check == 0){
				ThongBaoTrongNgay tbtn = new ThongBaoTrongNgay();
				tbtn.setNgayCuThe(date);
				ArrayList<ThongBao> arrtb = new ArrayList<ThongBao>();
				for (ThongBao thongBao : arrThongBao) {
					if(epKieuDate(thongBao.getNgayTaoThongBao()).equals(date)){
						arrtb.add(thongBao);
						//arrThongBao.remove(arrThongBao.indexOf(thongBao));
					}
				}
				Collections.sort(arrtb);
				tbtn.setArrThongBao(arrtb);
				arrThongBaoTrongNgay.add(tbtn);
			}
		}
		Collections.sort(arrThongBaoTrongNgay);
		for (ThongBaoTrongNgay thongBaoTrongNgay : arrThongBaoTrongNgay) {
			childItems.add(thongBaoTrongNgay.getArrThongBao());
			arrAllThongBao.addAll(thongBaoTrongNgay.getArrThongBao());
		}
		exeQ.close();
	}

	private Date epKieuDate(String ngay) {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = df.parse(ngay);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {
		Boolean checkTaoTB = data.getBooleanExtra("checkTaoThongBao", false);
		if (checkTaoTB == true) {
			swipeRefreshLayout.setRefreshing(true);
			getThongBaoGiangVien();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.thong_bao, menu);
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

	private void getThongBaoGiangVien() {
		String magiangvien = Global.getStringPreference(context, "MaGVDN", "0");
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("magiangvien", magiangvien);
		params.put("access_token", Global.getStringPreference(context, "access_token", ""));
		String url = Global.BASE_URI + Global.URI_THONG_BAO_GV;
		client.post(url, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				Log.e("JsonThongBao", response);
				if (executeWhenGetThongBaoGiangVienSuccess(response)) {
					/*
					 * Toast.makeText(getApplicationContext(), "Thanh cong",
					 * Toast.LENGTH_LONG).show();
					 */
					setList();
				} else {
					/*
					 * Toast.makeText(getApplicationContext(), "That bai",
					 * Toast.LENGTH_LONG) .show();
					 */
					swipeRefreshLayout.setRefreshing(false);
				}
			}

			public void onFailure(int statusCode, Throwable error, String content) {
				Log.e("JsonThongBao", error + " " + content);
				/*
				 * Toast.makeText(getApplicationContext(), error+"",
				 * Toast.LENGTH_LONG) .show();
				 */
				swipeRefreshLayout.setRefreshing(false);
			}
		});
	}

	private boolean executeWhenGetThongBaoGiangVienSuccess(String response) {
		/*
		 * Toast.makeText(getApplicationContext(), response+"",
		 * Toast.LENGTH_LONG) .show();
		 */
		exeQ.open();
		ArrayList<ThongBao> arrThongBao = new ArrayList<ThongBao>();

		try {
			JSONArray arrObj = new JSONArray(response);
			for (int i = 0; i < arrObj.length(); i++) {
				JSONObject thongbaoJson = arrObj.getJSONObject(i);

				String mathongbao = thongbaoJson.optString("PK_ThongBao");
				String tieudethongbao = thongbaoJson.optString("tieudethongbao");
				String noidungthongbao = thongbaoJson.optString("noidungthongbao");
				String ngaytaothongbao = thongbaoJson.optString("ngaytaothongbao");
				String magv = thongbaoJson.optString("MaGV");
				int songuoinhanthongbao = thongbaoJson.optInt("sosv");

				ThongBao tb = new ThongBao(mathongbao, tieudethongbao, noidungthongbao, ngaytaothongbao, magv,
						songuoinhanthongbao);
				arrThongBao.add(tb);
			}
			exeQ.insert_tbl_ThongBao_multi(arrThongBao);
			exeQ.close();
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			exeQ.close();
			return false;
		}
	}

	private void getThongBaoSinhVien() {
		String masinhvien = Global.getStringPreference(context, "MaSVDN", "0");
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("masinhvien", masinhvien);
		params.put("access_token", Global.getStringPreference(context, "access_token", ""));
		String url = Global.BASE_URI + Global.URI_THONG_BAO;
		client.post(url, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				Log.e("JsonThongBao", response);
				if (executeWhenGetThongBaoSinhVienSuccess(response)) {
					/*
					 * Toast.makeText(getApplicationContext(), "Thanh cong",
					 * Toast.LENGTH_LONG).show();
					 */
					setList();
				} else {
					/*
					 * Toast.makeText(getApplicationContext(), "That bai",
					 * Toast.LENGTH_LONG) .show();
					 */
					swipeRefreshLayout.setRefreshing(false);
				}
			}

			public void onFailure(int statusCode, Throwable error, String content) {
				Log.e("JsonThongBao", error + " " + content);
				/*
				 * Toast.makeText(getApplicationContext(), error+"",
				 * Toast.LENGTH_LONG) .show();
				 */
				swipeRefreshLayout.setRefreshing(false);
			}
		});
	}

	private boolean executeWhenGetThongBaoSinhVienSuccess(String response) {
		/*
		 * Toast.makeText(getApplicationContext(), response+"",
		 * Toast.LENGTH_LONG) .show();
		 */
		exeQ.open();
		ArrayList<ThongBao> arrThongBao = new ArrayList<ThongBao>();

		try {
			JSONArray arrObj = new JSONArray(response);
			for (int i = 0; i < arrObj.length(); i++) {
				JSONObject thongbaoJson = arrObj.getJSONObject(i);

				String mathongbao = thongbaoJson.optString("PK_ThongBao");
				String tieudethongbao = thongbaoJson.optString("tieudethongbao");
				String noidungthongbao = thongbaoJson.optString("noidungthongbao");
				String ngaytaothongbao = thongbaoJson.optString("ngaytaothongbao");
				String magv = thongbaoJson.optString("MaGV");
				int songuoinhanthongbao = thongbaoJson.optInt("sosv");

				ThongBao tb = new ThongBao(mathongbao, tieudethongbao, noidungthongbao, ngaytaothongbao, magv,
						songuoinhanthongbao);
				arrThongBao.add(tb);
			}
			exeQ.insert_tbl_ThongBao_multi(arrThongBao);
			exeQ.close();
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			exeQ.close();
			return false;
		}
	}
}
