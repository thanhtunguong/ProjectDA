package com.doan.lichhoctap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.doan.service.ScheduleClient;


import com.doan.adapter.GhiChuAdapter;
import com.doan.app.Global;
import com.doan.database_handle.ExecuteQuery;
import com.doan.model.ItemGhiChu;
import com.doan.model.SinhVien;
import com.doan.model.ThongBao;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;


public class GhiChuActivity extends ActionBarActivity implements OnClickListener{

	
	ListView lvGhichu;
	Button btnThemghichu;
	private static String thoigian_ghichu = "";
	private int lastIndex = 0;
	private String status ="";

	ArrayList<ItemGhiChu> arrItemghichu = new ArrayList<ItemGhiChu>();
	GhiChuAdapter adapter = null;
	private static int gio = 23;
	private static int phut = 40;
	private static int ngay = 29;
	private static int thang = 2;
	private static int nam = 2016;
	Toolbar toolbar;

	private ExecuteQuery exeQ;
	private Context context;
	private Global g;
	private ScheduleClient scheduleClient;  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ghi_chu);
		toolbar = (Toolbar) findViewById(R.id.ghichu_activity_tool_bar);
		setSupportActionBar(toolbar);
		g = new Global();
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		context = this;
		exeQ = new ExecuteQuery(context);
		String masinhvien = Global.getStringPreference(context, "MaSVDN", "0");
		lvGhichu = (ListView) findViewById(R.id.lvGhichu);
		btnThemghichu = (Button) findViewById(R.id.btnThemghichu);

		arrItemghichu = new ArrayList<ItemGhiChu>();
	//	getGhiChu(masinhvien);
		getGhiChuSQlite(masinhvien);

//		ItemGhiChu gc = new ItemGhiChu("Cong viec 01", 13,40,25,02,2016,
//				"Lam bai ve nha");
//		arrItemghichu.add(gc);
//		ItemGhiChu gc1 = new ItemGhiChu("Bai tap lon", 15,00,26,02,2016,
//				"Hoan thanh bai tap lon mon lap trinh di dong");
//		arrItemghichu.add(gc1);
//		ItemGhiChu gc2 = new ItemGhiChu("Chuyen de co so",17,30,27,02,2016,
//				"Di hoc chuyen de");
//		arrItemghichu.add(gc2);
//		scheduleClient = new ScheduleClient(this);
//	     scheduleClient.doBindService();
		
		
	     


		btnThemghichu.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				AlertDialog myAlertDialog = taoMotAlertDialog();
				myAlertDialog.show();

			}

		});
		
		lvGhichu.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int position,
					long arg3) {
				// TODO Auto-generated method stub
				AlertDialog myAlertDialog1 = suaGhichulertDialog(position);
				myAlertDialog1.show();
			}
		});

	}
	private void getGhiChuSQlite(String masinhvien) {
		// TODO Auto-generated method stub
		exeQ.open();
		arrItemghichu = exeQ.getAllGhiChuSqLite(masinhvien);
		setGhiChu();
		exeQ.close();
		if(arrItemghichu.size() != 0){
		int sttcuoi = arrItemghichu.size()-1;
		String maghichucuoi = arrItemghichu.get(sttcuoi).getMaghichu();
		
		String result_ma[] = maghichucuoi.split("[_]");
		lastIndex = Integer.valueOf(result_ma[2]);
		}
		else{			
			lastIndex = 0;
		}
	}
	private void setGhiChu(){
		adapter = new GhiChuAdapter(getBaseContext(), R.layout.ghichu_item,
				arrItemghichu);
		lvGhichu.setAdapter(adapter);
//		 scheduleClient = new ScheduleClient(this);
//	     scheduleClient.doBindService();
	}
	
	private AlertDialog suaGhichulertDialog(final int position) {

		LayoutInflater inflater = this.getLayoutInflater();
		View alertLayout = inflater.inflate(R.layout.dialog_themghichu, null);

		final EditText edtTitle = (EditText) alertLayout
				.findViewById(R.id.edtTitle);
		final TextView tvTime = (TextView) alertLayout
				.findViewById(R.id.tvTime);
		final EditText edtContent = (EditText) alertLayout
				.findViewById(R.id.edtContent);

		edtTitle.setText(arrItemghichu.get(position).getTitle());
		tvTime.setText(arrItemghichu.get(position).getThoigiannhac());
		edtContent.setText(arrItemghichu.get(position).getContent());
		
		String thoigian = arrItemghichu.get(position).getThoigiannhac();
		String result[] = thoigian.split("[-,:, ]");
		nam = Integer.valueOf(result[0]);
		thang = Integer.valueOf(result[1]);
		ngay = Integer.valueOf(result[2]);
		gio = Integer.valueOf(result[3]);
		phut = Integer.valueOf(result[4]);
	   
//		gio = arrItemghichu.get(position).getHour();
//		phut = arrItemghichu.get(position).getMinute();
//		ngay = arrItemghichu.get(position).getDay();
//		thang = arrItemghichu.get(position).getMonth();
//		nam = arrItemghichu.get(position).getYear();
		
		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle(R.string.tv_suaghichu);
		builder.setView(alertLayout);
		

		builder.setCancelable(true);

		tvTime.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				timePikerDialog(R.id.tpTimePicker,
						R.id.btnCancelTimePiker,
						R.id.btnDoneTimePiker, tvTime,
						R.layout.time_picker, R.string.titleTimeDialog);
				
				tvTime.setError(null);
				tvTime.setFocusableInTouchMode(true);
				tvTime.setFocusable(true);
				showDatePickerDialog(v);
				return false;
				
			}
		});

		builder.setPositiveButton(R.string.btn_sua,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {

						// ItemGhiChu gc = new
						// ItemGhiChu("Cong viec 01","13:40 25/02/2016","Lam bai tap ve nha");
						suaGhichu(edtTitle.getText().toString(), 
								gio, phut, ngay, thang, nam,
								edtContent.getText()
								.toString(),position);
						
						
					}
				});

		builder.setNegativeButton(R.string.btn_thoat,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		builder.setNeutralButton(R.string.btn_xoa, new DialogInterface.OnClickListener() {
			
			public void onClick(DialogInterface dialog, int which) {
				// TODO Auto-generated method stub
				xoaGhichu(position);
			}
		});
		AlertDialog dialog = builder.create();
		return dialog;

	}

	private AlertDialog taoMotAlertDialog() {

		LayoutInflater inflater = this.getLayoutInflater();
		View alertLayout = inflater.inflate(R.layout.dialog_themghichu, null);

		final EditText edtTitle = (EditText) alertLayout
				.findViewById(R.id.edtTitle);
		final TextView tvTime = (TextView) alertLayout
				.findViewById(R.id.tvTime);
		final EditText edtContent = (EditText) alertLayout
				.findViewById(R.id.edtContent);
		
		Calendar c1 = Calendar.getInstance();
		gio = c1.getTime().getHours();
		phut = c1.getTime().getMinutes();
		ngay = c1.getTime().getDate();
		thang = c1.getTime().getMonth();

		AlertDialog.Builder builder = new AlertDialog.Builder(this);

		builder.setTitle(R.string.tv_themghichu);
		builder.setView(alertLayout);

		builder.setCancelable(true);

		tvTime.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				timePikerDialog(R.id.tpTimePicker,
						R.id.btnCancelTimePiker,
						R.id.btnDoneTimePiker, tvTime,
						R.layout.time_picker, R.string.titleTimeDialog);
				tvTime.setError(null);
				tvTime.setFocusableInTouchMode(true);
				tvTime.setFocusable(true);
				showDatePickerDialog(v);
				return false;
			}
		});

		builder.setPositiveButton(R.string.btn_them,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						String masinhvien = Global.getStringPreference(context, "MaSVDN", "0");
						// ItemGhiChu gc = new
						// ItemGhiChu("Cong viec 01","13:40 25/02/2016","Lam bai tap ve nha");
						themGhichu(masinhvien,edtTitle.getText().toString(), 
								gio, phut, ngay, thang, nam,
								 edtContent.getText()
								.toString());
					}
				});

		builder.setNegativeButton(R.string.btn_thoat,
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						dialog.dismiss();
					}
				});
		AlertDialog dialog = builder.create();
		return dialog;

	}
	
	private String formatDate(int year, int month, int day, int hour, int minute){
		String result ="";
		String thang,ngay,gio,phut;
		if(month < 10){
			thang = "0"+month;
		}else{
			thang = month+"";
		}
		if(day < 10){
			ngay = "0"+day;
		}else{
			ngay = day+"";
		}
		if(hour < 10){
			gio = "0"+hour;
		}else{
			gio = hour+"";
		}
		if(minute < 10){
			phut = "0"+minute;
		}else{
			phut = minute+"";
		}
		result = year+"-"+thang+"-"+ngay+" " +gio+":"+phut+":00";
		return result;
	}

	protected void themGhichu(String masinhvien,String strTitle, int hour, int minute, int day, int month, int year, String strContent) {
		ItemGhiChu gc = new ItemGhiChu();
		String thoigiannhac = "";
		String thoigianchinhsua = "";
		
		thoigiannhac = formatDate(year,month,day,hour,minute);
		
		Calendar c_sua = Calendar.getInstance();
		int nam_sua = c_sua.get(Calendar.YEAR);
		int thang_sua = c_sua.get(Calendar.MONTH);
		int ngay_sua = c_sua.get(Calendar.DAY_OF_MONTH);
		int gio_sua = c_sua.get(Calendar.HOUR_OF_DAY);
		int phut_sua = c_sua.get(Calendar.MINUTE);
		
		thoigianchinhsua = formatDate(nam_sua,thang_sua,ngay_sua,gio_sua,phut_sua);
				
//		gc.setThoigiannhac(thoigiannhac);
//		gc.setThoigiannhac(thoigianchinhsua);
//		gc.setTitle(strTitle);
//		gc.setContent(strContent);
//		arrItemghichu.add(gc);
//		adapter.notifyDataSetChanged();
		createGhiChu(masinhvien,strTitle,strContent,thoigiannhac,thoigianchinhsua);
	//	datNotify(hour,minute,day,month,year);

	}
	protected void suaGhichu(String strTitle, int hour, int minute, int day, int month, int year, String strContent, int position) {
		String maghichu = arrItemghichu.get(position).getMaghichu();
		String masinhvien = Global.getStringPreference(context, "MaSVDN", "0");
//		Toast.makeText(getApplicationContext(),
//				maghichu, Toast.LENGTH_LONG)
//				.show();
		String thoigiannhac = "";
		String thoigianchinhsua = "";
		
		thoigiannhac = formatDate(year,month,day,hour,minute);
		
	    Calendar c_sua = Calendar.getInstance();
		int nam_sua = c_sua.get(Calendar.YEAR);
		int thang_sua = c_sua.get(Calendar.MONTH);
		int ngay_sua = c_sua.get(Calendar.DAY_OF_MONTH);
		int gio_sua = c_sua.get(Calendar.HOUR_OF_DAY);
		int phut_sua = c_sua.get(Calendar.MINUTE);
		
		thoigianchinhsua = formatDate(nam_sua,thang_sua,ngay_sua,gio_sua,phut_sua);
		
//		arrItemghichu.get(position).setTitle(strTitle);
//		arrItemghichu.get(position).setThoigiannhac(thoigiannhac);
//		arrItemghichu.get(position).setThoigiannhac(thoigianchinhsua);
//		arrItemghichu.get(position).setContent(strContent);
		
	
	//	adapter.notifyDataSetChanged();
		updateGhiChu(masinhvien,maghichu,strTitle,strContent,thoigiannhac,thoigianchinhsua);
	//	datNotify(hour,minute,day,month,year);

	}
	protected void xoaGhichu(int position) {
		String maghichu = arrItemghichu.get(position).getMaghichu();
		String masinhvien = Global.getStringPreference(context, "MaSVDN", "0");
		
		deleteGhiChu(masinhvien,maghichu);
//		arrItemghichu.remove(position);
//		adapter.notifyDataSetChanged();
	}

	public void datNotify(int hour1, int minute1, int day1, int month1, int year1 ){
//		ScheduleClient scheduleClient;
//		scheduleClient = new ScheduleClient(this);
//	    scheduleClient.doBindService();
    	// Create a new calendar set to the date chosen
    	// we set the time to midnight (i.e. the first minute of that day)
    	Calendar c = Calendar.getInstance();
    	c.set(year1, month1, day1);
    	c.set(Calendar.HOUR_OF_DAY, hour1);
    	c.set(Calendar.MINUTE, minute1);
    	c.set(Calendar.SECOND, 0);
    	// Ask our service to set an alarm for that date, this activity talks to the client that talks to the service
    	scheduleClient.setAlarmForNotification(c);
    //	Toast.makeText(getApplicationContext(), c.getTime().getMinutes() +"", Toast.LENGTH_LONG).show();
	}

	// ----------------------------------------------------------------------------------------

	public static class DatePickerFragment extends DialogFragment implements
			DatePickerDialog.OnDateSetListener {

		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);

			// Create a new instance of DatePickerDialog and return it
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}

		public void onDateSet(DatePicker view, int year, int month, int day) {
			// Do something with the date chosen by the user
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd",
					Locale.US);
			try {
				thoigian_ghichu = dateFormat
						.format(getDateFromDatePicket(view));
			} catch (Exception e) {
				// TODO: handle exception
			}
			// tvTime.setText(thoigian_ghichu);
			ngay = day;
			thang = month;
        	nam = year;
		}

		public static java.util.Date getDateFromDatePicket(DatePicker datePicker) {
			int day = datePicker.getDayOfMonth();
			int month = datePicker.getMonth();
			int year = datePicker.getYear();

			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, day);

			return calendar.getTime();
		}
	}

	public void timePikerDialog(int timePickerID, int btnCancelID,
			int btnDoneID, final TextView tv, int Layout, int dialogTitle) {
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(Layout);
		dialog.setTitle(dialogTitle);
		dialog.setCancelable(true);
		final TimePicker tpTimePK = (TimePicker) dialog
				.findViewById(timePickerID);
		tpTimePK.setIs24HourView(true);
		Button btnCancelTimePiker = (Button) dialog.findViewById(btnCancelID);
		btnCancelTimePiker.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.dismiss();
			}
		});
		Button btnDoneTimePicker = (Button) dialog.findViewById(btnDoneID);
		btnDoneTimePicker.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// TextView edTimePlace = (TextView) findViewById(textID);
				String res = "";
				if (tpTimePK.getCurrentHour() < 10) {
					res += "0" + tpTimePK.getCurrentHour();
					gio = tpTimePK.getCurrentHour();

				} else {
					res += tpTimePK.getCurrentHour();
					gio = tpTimePK.getCurrentHour();
				}
				if (tpTimePK.getCurrentMinute() < 10) {
					res += ":0" + tpTimePK.getCurrentMinute()+":00";
					phut = tpTimePK.getCurrentMinute();
				} else {
					res += ":" + tpTimePK.getCurrentMinute()+":00";
					phut = tpTimePK.getCurrentMinute();
				}
				
				tv.setText(thoigian_ghichu+ " "+res);
				dialog.dismiss();
			}
		});
		// Show Dialog
		dialog.show();
	}

	public void showDatePickerDialog(View v) {
		DialogFragment newFragment = new DatePickerFragment();
		newFragment.show(getFragmentManager(), "datePicker");
	}

	// ----------------------------------------------------------------------------------------------------

//	class myAdapter extends ArrayAdapter<ItemGhiChu> {
//
//		Context context;
//		ArrayList<ItemGhiChu> myArray = null;
//		int layoutId;
//
//		public myAdapter(Context c, int layoutId, ArrayList<ItemGhiChu> arr) {
//			super(c, layoutId, arr);
//			this.context = c;
//			this.layoutId = layoutId;
//			this.myArray = arr;
//		}
//
//		public View getView(int position, View convertView, ViewGroup parent) {
//			LayoutInflater inflater = (LayoutInflater) context
//					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//			View row = inflater.inflate(R.layout.ghichu_item, parent, false);
//			TextView tvTitle = (TextView) row.findViewById(R.id.tvTitle);
//
//			final ItemGhiChu igc = myArray.get(position);
//			tvTitle.setText(igc.getTitle());
//
//			return row;
//		}
//	}
	//--------------------------------------------------
//	private void getGhiChu(String masinhvien){
//		
//		AsyncHttpClient client = new AsyncHttpClient();
//		RequestParams params = new RequestParams();
//		params.put("masinhvien", masinhvien);
//		String url = Global.BASE_URI + Global.URI_GHICHUTHEOMATHEOMASV;
//		client.post(url, params, new AsyncHttpResponseHandler() {
//			public void onSuccess(String response) {
//				Log.e("JsonThongBao", response);
//				if (executeWhenGetGhiChuSuccess(response)) {
//				//	ArrayList<ThongBao> arrThongBao = exeQ.getAllThongBaoSqLite();
//					Toast.makeText(getApplicationContext(),
//							"Thanh cong", Toast.LENGTH_LONG)
//							.show();
//					setGhiChu();
//				} else {
//					Toast.makeText(getApplicationContext(),
//							"That bai", Toast.LENGTH_LONG)
//							.show();
//				}
//			}
//
//			public void onFailure(int statusCode, Throwable error,
//					String content) {
//				Log.e("JsonThongBao", error+" "+content);
//				Toast.makeText(getApplicationContext(),
//						error+"", Toast.LENGTH_LONG)
//						.show();
//			}
//		});
//	}
	
//	private boolean executeWhenGetGhiChuSuccess(String response) {
//		Toast.makeText(getApplicationContext(),
//				response+"", Toast.LENGTH_LONG)
//				.show();
//	//	ArrayList<ThongBao> arrThongBao = new ArrayList<ThongBao>();
//		
//		try {
//			JSONArray arrObj = new JSONArray(response);
//			for (int i = 0; i < arrObj.length(); i++) {
//				JSONObject ghichuJson = arrObj.getJSONObject(i);
//
//				String maghichu = ghichuJson.optString("pk_ghichu");
//				String tieudeghichu = ghichuJson.optString("TieuDeGhiChu");
//				String noidungghichu = ghichuJson.optString("NoiDungGhiChu");
//				String thoigiannhacghichu = ghichuJson.optString("ThoiGianNhacGhiChu");
//				String thoigianchinhsuaghichu = ghichuJson.optString("ThoiGianChinhSuaGhiChu");
//				
//				Toast.makeText(getApplicationContext(),
//						maghichu, Toast.LENGTH_LONG)
//						.show();
//				ItemGhiChu gc = new ItemGhiChu(maghichu,tieudeghichu,thoigiannhacghichu,thoigianchinhsuaghichu,
//						noidungghichu);
//				arrItemghichu.add(gc);
//			}
//		//	exeQ.insert_tbl_ThongBao_multi(arrThongBao);
//			return true;
//		} catch (JSONException e) {
//			e.printStackTrace();
//			return false;
//		}
//	}
	//-----Them ghi chu
	public void createGhiChu(final String masinhvien,final String tieude,final String noidung,
			final String thoigiannhac,final String thoigianchinhsua) {
		// TODO Auto-generated method stub
		// Toast.makeText(getApplicationContext(), s_ngaysinh +" + " +
		// s_gioitinh +" + " + s_diachi + " + " +
		// s_sdt,Toast.LENGTH_SHORT).show();
		lastIndex+=1;
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("masinhvien", masinhvien);
		params.put("tieude", tieude);
		params.put("noidung", noidung);
		params.put("thoigiannhac", thoigiannhac);
//		params.put("thoigiansua", thoigianchinhsua);
		params.put("maghichu", masinhvien+"_"+lastIndex);
		params.put("access_token", Global.getStringPreference(getApplicationContext(), "access_token", ""));
	//	client.setTimeout(30000);
		

		client.post(Global.BASE_URI + Global.URI_TAOGHICHUTHEOMASV,

				params, new AsyncHttpResponseHandler() {
					public void onSuccess(String response) {
						
						if (checkUpdate(response)) {
//							Toast.makeText(getApplicationContext(),
//									"Thanh cong", Toast.LENGTH_LONG)
//									.show();
							 
							    exeQ.insert_tbl_GhiChu(masinhvien,masinhvien+"_"+lastIndex,tieude,
										noidung, thoigiannhac, thoigianchinhsua);
							
								exeQ.close();
								getGhiChuSQlite(masinhvien);
							
						} else {
							if(status.matches("false_execute") == true){
								Toast.makeText(getApplicationContext(),
										"That bai", Toast.LENGTH_LONG)
										.show();
							}
							if(status.matches("false") == true){
//								Toast.makeText(getApplicationContext(),
//										"Goi ham logout app", Toast.LENGTH_LONG)
//										.show();
								g.DangXuat(context);
							}
//							logoutapp();
						
						}
					}

					public void onFailure(int statusCode, Throwable error,
							String content) {
//						Toast.makeText(getApplicationContext(),
//								error+"", Toast.LENGTH_LONG)
//								.show();
					}
				});
	}
	
	//-----Sua ghi chu
		public void updateGhiChu(final String masinhvien,final String maghichu,final String tieude,final String noidung,
				final String thoigiannhac,final String thoigiansua) {
			// TODO Auto-generated method stub
			// Toast.makeText(getApplicationContext(), s_ngaysinh +" + " +
			// s_gioitinh +" + " + s_diachi + " + " +
			// s_sdt,Toast.LENGTH_SHORT).show();
			
			AsyncHttpClient client = new AsyncHttpClient();
			RequestParams params = new RequestParams();
			params.put("masinhvien", masinhvien);
			params.put("maghichu", maghichu);
			params.put("tieude", tieude);
			params.put("noidung", noidung);
			params.put("thoigiannhac", thoigiannhac);
//			params.put("thoigiansua", thoigiansua);
			params.put("access_token", Global.getStringPreference(getApplicationContext(), "access_token", ""));
		//	client.setTimeout(30000);
			

			client.post(Global.BASE_URI + Global.URI_SUAGHICHUTHEOMASV,

					params, new AsyncHttpResponseHandler() {
						public void onSuccess(String response) {
							
							if (checkUpdate(response)) {
//								Toast.makeText(getApplicationContext(),
//										"Thanh cong", Toast.LENGTH_LONG)
//										.show();
								exeQ.update_tbl_ghichu(maghichu,tieude,noidung,thoigiannhac,thoigiansua);
							
								exeQ.close();
								getGhiChuSQlite(masinhvien);
							} else {
								if(status.matches("false_execute") == true){
									Toast.makeText(getApplicationContext(),
											"That bai", Toast.LENGTH_LONG)
											.show();
								}
								if(status.matches("false") == true){
//									Toast.makeText(getApplicationContext(),
//											"Goi ham logout app", Toast.LENGTH_LONG)
//											.show();
									g.DangXuat(context);
								}
//								logoutapp();
							
							}
						}

						public void onFailure(int statusCode, Throwable error,
								String content) {
							Toast.makeText(getApplicationContext(),
									error+"", Toast.LENGTH_LONG)
									.show();
						}
					});
		}
		
		//-----Xoa ghi chu
				public void deleteGhiChu(final String masinhvien,final String maghichu) {
					// TODO Auto-generated method stub
					// Toast.makeText(getApplicationContext(), s_ngaysinh +" + " +
					// s_gioitinh +" + " + s_diachi + " + " +
					// s_sdt,Toast.LENGTH_SHORT).show();
					
					AsyncHttpClient client = new AsyncHttpClient();
					RequestParams params = new RequestParams();
					params.put("maghichu", maghichu);
					params.put("masinhvien", masinhvien);
					params.put("access_token", Global.getStringPreference(getApplicationContext(), "access_token", ""));
					
				//	client.setTimeout(30000);
					

					client.post(Global.BASE_URI + Global.URI_XOAGHICHUTHEOMASVGC,

							params, new AsyncHttpResponseHandler() {
								public void onSuccess(String response) {
									
									if (checkUpdate(response)) {
										Toast.makeText(getApplicationContext(),
												"Thanh cong", Toast.LENGTH_LONG)
												.show();
										exeQ.delete_tbl_GhiChu(maghichu);
									
										exeQ.close();
										getGhiChuSQlite(masinhvien);
									} else {
										if(status.matches("false_execute") == true){
											Toast.makeText(getApplicationContext(),
													"That bai", Toast.LENGTH_LONG)
													.show();
										}
										if(status.matches("false") == true){
//											Toast.makeText(getApplicationContext(),
//													"Goi ham logout app", Toast.LENGTH_LONG)
//													.show();
											g.DangXuat(context);
										}
//										logoutapp();
									
									}
								}

								public void onFailure(int statusCode, Throwable error,
										String content) {
									Toast.makeText(getApplicationContext(),
											error+"", Toast.LENGTH_LONG)
											.show();
								}
							});
				}	
	
	private boolean checkUpdate(String response) {
		try {
			JSONArray arrObj = new JSONArray(response);
			for (int i = 0; i < arrObj.length(); i++) {
				JSONObject userJson = arrObj.getJSONObject(i);

				status = userJson.optString("status");				
			}
			if(status.matches("success") == true){
				return true;
			}
			if(status.matches("false_execute") == true){
				return false;
			}
			
			else{
				return false;
			}
//			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			Log.e("loi", e + "");
			return false;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ghi_chu, menu);
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

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	 


	
}
