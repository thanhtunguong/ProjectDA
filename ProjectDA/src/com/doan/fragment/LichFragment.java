package com.doan.fragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import com.doan.adapter.HocTapLichNgayAdapter;
import com.doan.app.Global;
import com.doan.database_handle.ExecuteQuery;
import com.doan.lichhoctap.R;
import com.doan.model.DayInWeek;
import com.doan.model.TietHoc;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.TextView;

public class LichFragment extends Fragment {
	
	private ArrayList<DayInWeek> parentItems;
	private ArrayList<Object> childItems;
	private ArrayList<TietHoc> arlAll;
	private ArrayList<TietHoc> arlTh2, arlTh3, arlTh4, arlTh5, arlTh6, arlTh7, arlTh8;
	private ArrayList<Integer> arlColor = new ArrayList<Integer>();
	private String resultDate = "";
	private String firstdayOfWeek, lastdayOfWeek;
	private String resultWeek = "";
	private String resultDayName = "";
	private Date ngayTinh, ngayChonDatePicker, ngayDauTuanTru1, ngayCuoiTuanCong1;
	private TextView tvToday, tvDayName, tvWeek;
	private ExpandableListView expandableList;
	private HocTapLichNgayAdapter adapter;
	private View view;
	private LayoutInflater l;
	private int lan;
	private Activity c;
	private ExecuteQuery exeQ;
	private SwipeRefreshLayout swipeRefreshLayout;
	
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.hoctap_lichhoc_layout_test, container, false);
		c = getActivity();
		view = v;
		l = inflater;
		lan = 0;
		exeQ = new ExecuteQuery(c);
		tvToday = (TextView) v.findViewById(R.id.tvResetCalendar);
		tvWeek = (TextView) v.findViewById(R.id.tvWeek);
		swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout_lich);
		swipeRefreshLayout.setColorSchemeColors(R.color.ColorPrimary);
		swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				//setinItialize(view, l, c);
				getLichHoc();
			}
		});
		expandableList = (ExpandableListView) v.findViewById(R.id.elvDays);
		expandableList.setOnScrollListener(new OnScrollListener() {

		    @Override
		    public void onScroll(AbsListView view, int firstVisibleItem,
		            int visibleItemCount, int totalItemCount) {
		        boolean enable = false;
		        if(expandableList != null && expandableList.getChildCount() > 0){
		            // check if the first item of the list is visible
		            boolean firstItemVisible = expandableList.getFirstVisiblePosition() == 0;
		            // check if the top of the first item is visible
		            boolean topOfFirstItemVisible = expandableList.getChildAt(0).getTop() == 0;
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
		expandableList.setGroupIndicator(null);
		expandableList.setClickable(true);
		intTaoMau();
		setinItialize(view, l, c);
		
		tvToday.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				lan = 1;
				Calendar dar = Calendar.getInstance();
				ngayChonDatePicker = dar.getTime();
				
				int index = expandableList.getFirstVisiblePosition();
				View viewLV = expandableList.getChildAt(0);
				int top = (viewLV == null) ? 0 : (viewLV.getTop() - expandableList.getPaddingTop());
				
				setinItialize(view, l, c);
				
				expandableList.setSelectionFromTop(index, top);
			}
		});
		tvWeek.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				lan = 1;
				chonNgayDatetimePicker(c);
			}
		});
		return v;
	}

	private void setinItialize(View v, LayoutInflater l, Activity c) {
		getNgayThang();
		tvToday.setText(resultDate);
		tvWeek.setText(resultWeek);
		
		arlAll = new ArrayList<TietHoc>();
		arlTh2 = new ArrayList<TietHoc>();
		arlTh3 = new ArrayList<TietHoc>();
		arlTh4 = new ArrayList<TietHoc>();
		arlTh5 = new ArrayList<TietHoc>();
		arlTh6 = new ArrayList<TietHoc>();
		arlTh7 = new ArrayList<TietHoc>();
		arlTh8 = new ArrayList<TietHoc>();
		arlAll.addAll(exeQ.getAllTietHocSqLite());

		phanTietHoc(arlAll);

		setChildData();
		setGroupParents(ngayTinh);

		adapter = new HocTapLichNgayAdapter(parentItems, childItems);
		adapter.setInflater(l, c);
		expandableList.setAdapter(adapter);
	}

	private void getNgayThang() {
		Calendar c = Calendar.getInstance();
		if(lan != 0){
			c.setTime(ngayChonDatePicker);
		}
		int date = c.get(Calendar.DATE);
		int day = c.get(Calendar.DAY_OF_WEEK);
		int year = c.get(Calendar.YEAR);
		int m = c.get(Calendar.MONTH);
		
		String month = "";
		switch (m) {
		case Calendar.JANUARY:
			month = getString(R.string.string_January);
			break;
		case Calendar.FEBRUARY:
			month = getString(R.string.string_February);
			break;
		case Calendar.MARCH:
			month = getString(R.string.string_March);
			break;
		case Calendar.APRIL:
			month = getString(R.string.string_April);
			break;
		case Calendar.MAY:
			month = getString(R.string.string_May);
			break;
		case Calendar.JUNE:
			month = getString(R.string.string_June);
			break;
		case Calendar.JULY:
			month = getString(R.string.string_July);
			break;
		case Calendar.AUGUST:
			month = getString(R.string.string_August);
			break;
		case Calendar.SEPTEMBER:
			month = getString(R.string.string_September);
			break;
		case Calendar.OCTOBER:
			month = getString(R.string.string_October);
			break;
		case Calendar.NOVEMBER:
			month = getString(R.string.string_November);
			break;
		case Calendar.DECEMBER:
			month = getString(R.string.string_December);
			break;
		}

		switch (day) {
		case Calendar.MONDAY:
			resultDayName = getString(R.string.string_Monday);
			getTuan(0, 6);
			break;
		case Calendar.TUESDAY:
			resultDayName = getString(R.string.string_Tuesday);
			getTuan(-1, 5);
			break;
		case Calendar.WEDNESDAY:
			resultDayName = getString(R.string.string_Wednesday);
			getTuan(-2, 4);
			break;
		case Calendar.THURSDAY:
			resultDayName = getString(R.string.string_Thursday);
			getTuan(-3, 3);
			break;
		case Calendar.FRIDAY:
			resultDayName = getString(R.string.string_Friday);
			getTuan(-4, 2);
			break;
		case Calendar.SATURDAY:
			resultDayName = getString(R.string.string_Saturday);
			getTuan(-5, 1);
			break;
		case Calendar.SUNDAY:
			resultDayName = getString(R.string.string_Sunday);
			getTuan(-6, 0);
			break;
		}

		
		resultDayName = getString(R.string.string_tvHelloTextView) + " " + resultDayName + ".";
		//resultDate = date + " " + month + " " + "năm " + year;
		resultDate = date + " " + month;
	}

	private void getTuan(int tru, int cong) {
		Calendar cFirstdayOfWeek, cLastdayOfWeek, specifyDate;
		//cFirstdayOfWeek = cLastdayOfWeek = specifyDate = Calendar.getInstance();
		cFirstdayOfWeek = Calendar.getInstance();
		cLastdayOfWeek = Calendar.getInstance();
		specifyDate = Calendar.getInstance();
		if(lan == 1){
			specifyDate.setTime(ngayChonDatePicker);
		}else {
			specifyDate.setTime(Calendar.getInstance().getTime());
		}
		/*cFirstdayOfWeek = specifyDate;
		cLastdayOfWeek = specifyDate;*/
		cFirstdayOfWeek.setTime(specifyDate.getTime());
		cLastdayOfWeek.setTime(specifyDate.getTime());
		int thang1 = 0; int thang2 = 0;

		cFirstdayOfWeek.add(Calendar.DAY_OF_YEAR, tru);
		
		thang1 = cFirstdayOfWeek.getTime().getMonth() + 1;
		firstdayOfWeek = cFirstdayOfWeek.getTime().getDate() + "/" + thang1;
		

		ngayTinh = cFirstdayOfWeek.getTime();
		// cho thu 2 vao de tao listtuan
		// setGroupParents(cFirstdayOfWeek.getTime());

		cLastdayOfWeek.add(Calendar.DAY_OF_YEAR, cong);
		
		thang2 = cLastdayOfWeek.getTime().getMonth() +1;
		lastdayOfWeek = cLastdayOfWeek.getTime().getDate() + "/" + thang2;
		resultWeek = getString(R.string.string_tvWeek) + " " + firstdayOfWeek + " - " + lastdayOfWeek;
		
		
		
		//cFirstdayOfWeek.add(Calendar.DAY_OF_YEAR, -1);
		ngayDauTuanTru1 = cFirstdayOfWeek.getTime();
		//cLastdayOfWeek.add(Calendar.DAY_OF_YEAR, 1);
		ngayCuoiTuanCong1 = cLastdayOfWeek.getTime();
		int t= 0;
		t = t +1;
	}

	private void checkTietCuThe(ArrayList<TietHoc> arlTh, TietHoc th) {
		TietHoc pt = new TietHoc();
		if (arlTh.size() > 0) {
			Date dateTH = epKieuDate(th.getSpecificDate()); 
			if (th.getSpecificDate() != null) {
				//if (dateTH.after(ngayDauTuanTru1) == true && dateTH.before(ngayCuoiTuanCong1) == true) {
					for (int i = 0; i < arlTh.size(); i++) {
						pt = arlTh.get(i);
						if (th.getBuoiHoc() == pt.getBuoiHoc() && th.getMonHoc() == pt.getMonHoc()) {
							arlTh.remove(i);
							arlTh.add(th);
							break;
						}else {
							arlTh.add(th);
							break;
						}
					}
				//}

				/**
				 * for(int i = 0; i < arlTh.size(); i++){ pt = arlTh.get(i);
				 * if(th.getBuoiHoc() == pt.getBuoiHoc() && th.getMonHoc() ==
				 * pt.getMonHoc()){ arlTh.remove(i); arlTh.add(th); } }
				 */

			} else {
				for (int i = 0; i < arlTh.size(); i++) {
					pt = arlTh.get(i);
					if (th.getBuoiHoc() != pt.getBuoiHoc() || th.getMonHoc() != pt.getMonHoc()) {
						arlTh.add(th);
						break;
					}
				}
			}
		} else {
			arlTh.add(th);
		}
	}

	private void phanTietHoc(ArrayList<TietHoc> list) {
		int t = 0;
		for (TietHoc tietHoc : list) {
			t = t +1;
			if(t == 20){
				int z = 0;
			}
			Date date = epKieuDate(tietHoc.getSpecificDate());
			Calendar c = Calendar.getInstance();
			Calendar c2 = Calendar.getInstance();
			c.setTime(ngayDauTuanTru1);
			c2.setTime(date);
			int dautuan = c.get(c.WEEK_OF_YEAR);
			int specDay = c2.get(c2.WEEK_OF_YEAR);
			//if(date.after(ngayDauTuanTru1) == true && date.before(ngayCuoiTuanCong1) == true){
			if(specDay == dautuan){
				for (int i = 1; i < 29; i++) {
					if (tietHoc.getCaHoc() == i) {
						if (i == 1 || i == 2 || i == 3 || i == 4) {
							checkTietCuThe(arlTh2, tietHoc);
							break;
						}
						if (i == 5 || i == 6 || i == 7 || i == 8) {
							checkTietCuThe(arlTh3, tietHoc);
							break;
						}
						if (i == 9 || i == 10 || i == 11 || i == 12) {
							checkTietCuThe(arlTh4, tietHoc);
							break;
						}
						if (i == 13 || i == 14 || i == 15 || i == 16) {
							checkTietCuThe(arlTh5, tietHoc);
							break;
						}
						if (i == 17 || i == 18 || i == 19 || i == 20) {
							checkTietCuThe(arlTh6, tietHoc);
							break;
						}
						if (i == 21 || i == 22 || i == 23 || i == 24) {
							checkTietCuThe(arlTh7, tietHoc);
							break;
						}
						if (i == 25 || i == 26 || i == 27 || i == 28) {
							checkTietCuThe(arlTh7, tietHoc);
							break;
						}
					}
				}
			}
		}		
	}

	private Date congNgay(Date homtruoc) {
		Calendar temp = Calendar.getInstance();
		temp.setTime(homtruoc);
		temp.add(Calendar.DAY_OF_YEAR, 1);
		ngayTinh = temp.getTime();
		return ngayTinh;
	}

	/*private void intTaoMau() {
		Random rnd = new Random();
		Integer mau = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
		if (arlColor.size() == 0) {
			arlColor.add(mau);
		} else {
			for (int i = 0; i < arlColor.size(); i++) {
				if (arlColor.get(i) == mau) {
					intTaoMau();
				} else {
					arlColor.add(mau);
					break;
				}
			}
		}
	}*/
	private void intTaoMau() {
		Random rnd = new Random();
		//Integer mau = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
		for(int j = 0; j < 7; j++){
			Integer mau = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
			if (arlColor.size() == 0) {
				arlColor.add(mau);
			} else {
				for (int i = 0; i < arlColor.size(); i++) {
					int mau2 = arlColor.get(i);
					if (mau2 == mau) {
						intTaoMau();
					} else {
						arlColor.add(mau);
						break;
					}
				}
			}
		}
	}


	public void setGroupParents(Date thu2) {
		/*if (lan != 1) {
			ngayTinh = thu2;
		} else {
			ngayTinh = ngayChonDatePicker;
		}*/
		/*Random rnd = new Random(); 
		arlColor = new ArrayList<Integer>();
		int color;
		for(int i = 0; i < 7; i++){
			 //color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
			intTaoMau();
		}  */ 
		ngayTinh = thu2;
		parentItems = new ArrayList<DayInWeek>();
		DayInWeek day2 = new DayInWeek(ngayTinh, getString(R.string.string_Monday), arlTh2.size(), arlColor.get(0));
		parentItems.add(day2);
		DayInWeek day3 = new DayInWeek(congNgay(ngayTinh), getString(R.string.string_Tuesday), arlTh3.size(), arlColor.get(1));
		parentItems.add(day3);
		DayInWeek day4 = new DayInWeek(congNgay(ngayTinh), getString(R.string.string_Wednesday), arlTh4.size(), arlColor.get(2));
		parentItems.add(day4);
		DayInWeek day5 = new DayInWeek(congNgay(ngayTinh), getString(R.string.string_Thursday), arlTh5.size(), arlColor.get(3));
		parentItems.add(day5);
		DayInWeek day6 = new DayInWeek(congNgay(ngayTinh), getString(R.string.string_Friday), arlTh6.size(), arlColor.get(4));
		parentItems.add(day6);
		DayInWeek day7 = new DayInWeek(congNgay(ngayTinh), getString(R.string.string_Saturday), arlTh7.size(), arlColor.get(5));
		parentItems.add(day7);
		DayInWeek day8 = new DayInWeek(congNgay(ngayTinh), getString(R.string.string_Sunday), arlTh8.size(), arlColor.get(6));
		parentItems.add(day8);
	}

	public void setChildData() {
		childItems = new ArrayList<Object>();
		childItems.add(arlTh2);
		childItems.add(arlTh3);
		childItems.add(arlTh4);
		childItems.add(arlTh5);
		childItems.add(arlTh6);
		childItems.add(arlTh7);
		childItems.add(arlTh8);
	}
	private void chonNgayDatetimePicker(final Activity context){
		final Dialog dialog = new Dialog(context);
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
				ngayChonDatePicker = c.getTime();
				
				int index = expandableList.getFirstVisiblePosition();
				View viewLV = expandableList.getChildAt(0);
				int top = (viewLV == null) ? 0 : (viewLV.getTop() - expandableList.getPaddingTop());
				
				setinItialize(view, l, context);
				
				expandableList.setSelectionFromTop(index, top);
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
	private Date epKieuDate(String ngay){
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
	private void getLichHoc(){
		String masinhvien = Global.getStringPreference(c, "MaSVDN", "0");
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("masinhvien", masinhvien);
		String url = Global.BASE_URI + Global.URI_LICH_HOC;
		client.post(url, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				Log.e("JsonLichHoc", response);
				if (executeWhenGetLichHocSuccess(response)) {
					/*Toast.makeText(getApplicationContext(),
							"Thanh cong", Toast.LENGTH_LONG)
							.show();*/
					swipeRefreshLayout.setRefreshing(false);
					setinItialize(view, l, c);
				} else {
					/*Toast.makeText(getApplicationContext(),
							"That bai", Toast.LENGTH_LONG)
							.show();*/
				}
			}

			public void onFailure(int statusCode, Throwable error,
					String content) {
				Log.e("JsonLichHoc", error+" "+content);
				/*Toast.makeText(getApplicationContext(),
						error+"", Toast.LENGTH_LONG)
						.show();*/
			}
		});
	}
	private boolean executeWhenGetLichHocSuccess(String response) {
		/*Toast.makeText(getApplicationContext(),
				response+"", Toast.LENGTH_LONG)
				.show();*/
		ArrayList<TietHoc> arrTietHoc = new ArrayList<TietHoc>();
		
		try {
			JSONArray arrObj = new JSONArray(response);
			for (int i = 0; i < arrObj.length(); i++) {
				JSONObject lichhocJson = arrObj.getJSONObject(i);

				String malichhoc = lichhocJson.optString("malichhoc");
				String ngay = lichhocJson.optString("ngay");
				String ca = lichhocJson.optString("ca");
				ca = ca.substring(2);
				int cahoc = Integer.parseInt(ca);
				String buoi = lichhocJson.optString("buoi");
				String tenmonhoc = lichhocJson.optString("tenmonhoc");
				String tentrangthai = lichhocJson.optString("tentrangthai");
				String tenphonghoc = lichhocJson.optString("tenphonghoc");
				
				TietHoc th = new TietHoc(cahoc, buoi, ngay, tenmonhoc, tenphonghoc, tentrangthai, malichhoc);
				arrTietHoc.add(th);
			}
			exeQ.updateLichHocSqLite(arrTietHoc);
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}
}
