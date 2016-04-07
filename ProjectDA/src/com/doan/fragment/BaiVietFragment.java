package com.doan.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.doan.adapter.BaiVietAdapter;
import com.doan.app.Global;
import com.doan.database_handle.ExecuteQuery;
import com.doan.lichhoctap.BaiVietDetailActivity;
import com.doan.lichhoctap.HocTapActivity;
import com.doan.lichhoctap.R;
import com.doan.lichhoctap.SplashScreen;
import com.doan.lichhoctap.ThongBaoActivity;
import com.doan.lichhoctap.ThongTinCaNhanActivity;
import com.doan.model.BaiViet;
import com.doan.model.TietHoc;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnScrollChangeListener;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;

public class BaiVietFragment extends Fragment{
	
	private ArrayList<BaiViet> allBaiViet = new ArrayList<BaiViet>();
	private ArrayList<BaiViet> allBaiVietTinHoatDong = new ArrayList<BaiViet>();
	private ArrayList<BaiViet> allBaiVietTinDaoTao = new ArrayList<BaiViet>();
	private ArrayList<BaiViet> allBaiVietTinTotNghiep = new ArrayList<BaiViet>();
	private BaiVietAdapter bvAdapter;
	private ExecuteQuery exeQ;
	private Context context;
	private SwipeRefreshLayout swipeRefreshLayout;
	private ListView lvBV;
	
	@Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v =inflater.inflate(R.layout.bai_viet_fragment,container,false);
		context = getActivity();
		exeQ = new ExecuteQuery(context);
        lvBV = (ListView) v.findViewById(R.id.lvBaiViet);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout_bang_tin);
        lvBV.setOnScrollListener(new OnScrollListener() {
			
			@Override
			public void onScrollStateChanged(AbsListView view, int scrollState) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
				// TODO Auto-generated method stub
				boolean enable = false;
		        if(lvBV != null && lvBV.getChildCount() > 0){
		            // check if the first item of the list is visible
		            boolean firstItemVisible = lvBV.getFirstVisiblePosition() == 0;
		            // check if the top of the first item is visible
		            boolean topOfFirstItemVisible = lvBV.getChildAt(0).getTop() == 0;
		            // enabling or disabling the refresh layout
		            enable = firstItemVisible && topOfFirstItemVisible;
		        }
		        swipeRefreshLayout.setEnabled(enable);
			}
		});
        
        allBaiViet = exeQ.getAllBaiVietSqLite();
        bvAdapter = new BaiVietAdapter(getActivity(), R.layout.bai_viet_item, allBaiViet);
        lvBV.setAdapter(bvAdapter);
        
        TextView tvThongBao = (TextView) v.findViewById(R.id.tvIntentThongBao);
        tvThongBao.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getContext(),ThongBaoActivity.class);
				startActivity(i);
			}
		});
        swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				getBaiViet();
			}
		});
        
        return v;
	}
	private Date setNgay(int nam, int thang, int ngay){
		Date date;
		Calendar calendar = Calendar.getInstance();
		calendar.set(nam, thang, ngay); //tháng bắt đầu từ 0
		date = calendar.getTime();
		return date;
	}
	private void getBaiViet() {

		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();

		String url = Global.BASE_URI + Global.URI_DANHSACHBAIVIET;
		client.post(url, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				// Log.e("JsonGhiChu", response);
				if (executeWhenGetBaiVietSuccess(response)) {
					swipeRefreshLayout.setRefreshing(false);
					allBaiViet = exeQ.getAllBaiVietSqLite();
			        bvAdapter = new BaiVietAdapter(getActivity(), R.layout.bai_viet_item, allBaiViet);
			        lvBV.setAdapter(bvAdapter);
				} else {

				}
			}

			public void onFailure(int statusCode, Throwable error, String content) {
				Log.e("JsonBaiViet", error + " " + content);

			}
		});
	}

	private boolean executeWhenGetBaiVietSuccess(String response) {

		// String masinhvien = Global.getStringPreference(c, "MaSVDN", "0");
		// ArrayList<ThongBao> arrThongBao = new ArrayList<ThongBao>();

		ArrayList<BaiViet> arrBaiViet = new ArrayList<BaiViet>();
		try {
			JSONArray arrObj = new JSONArray(response);
			for (int i = 0; i < arrObj.length(); i++) {
				JSONObject baivietJson = arrObj.getJSONObject(i);

				String mabaiviet = baivietJson.optString("pk_baiviet");
				String tieudebaiviet = baivietJson.optString("tieudebaiviet");
				String noidungbaiviet = baivietJson.optString("noidungbaiviet");
				String ngaytaobaiviet = baivietJson.optString("ngaytaobaiviet");
				String thoigiansua = baivietJson.optString("thoigianchinhsua");
				String loaibaiviet = baivietJson.optString("maloaibaiviet");
				String giangvien = baivietJson.optString("hoten");

				// Toast.makeText(getApplicationContext(),
				// maghichu, Toast.LENGTH_LONG)
				// .show();
				BaiViet bv = new BaiViet(mabaiviet, tieudebaiviet, noidungbaiviet, giangvien, loaibaiviet,
						ngaytaobaiviet, thoigiansua);
				arrBaiViet.add(bv);
			}

			exeQ.insert_tbl_BaiViet_multi(arrBaiViet);
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}
}
