package com.doan.lichhoctap;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import com.doan.service.ScheduleClient;


import com.doan.adapter.GhiChuAdapter;
import com.doan.model.ItemGhiChu;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
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



public class GhiChuActivity extends Activity implements OnClickListener{

	
	ListView lvGhichu;
	Button btnThemghichu;
	private static String thoigian_ghichu = "";

	ArrayList<ItemGhiChu> arrItemghichu = new ArrayList<ItemGhiChu>();
	GhiChuAdapter adapter = null;
	private static int gio = 23;
	private static int phut = 40;
	private static int ngay = 29;
	private static int thang = 2;
	private static int nam = 2016;

	private ScheduleClient scheduleClient;  
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ghi_chu);
		
		lvGhichu = (ListView) findViewById(R.id.lvGhichu);
		btnThemghichu = (Button) findViewById(R.id.btnThemghichu);

		arrItemghichu = new ArrayList<ItemGhiChu>();

		ItemGhiChu gc = new ItemGhiChu("Cong viec 01", "13:40 25/02/2016",
				"Lam bai ve nha");
		arrItemghichu.add(gc);
		ItemGhiChu gc1 = new ItemGhiChu("Bai tap lon", "15:00 26/02/2016",
				"Hoan thanh bai tap lon mon lap trinh di dong");
		arrItemghichu.add(gc1);
		ItemGhiChu gc2 = new ItemGhiChu("Chuyen de co so", "17:30 27/02/2016",
				"Di hoc chuyen de");
		arrItemghichu.add(gc2);

		adapter = new GhiChuAdapter(getBaseContext(), R.layout.ghichu_item,
				arrItemghichu);
		lvGhichu.setAdapter(adapter);
		 scheduleClient = new ScheduleClient(this);
	     scheduleClient.doBindService();
	     


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
		tvTime.setText(arrItemghichu.get(position).getTime());
		edtContent.setText(arrItemghichu.get(position).getContent());
		
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
						suaGhichu(edtTitle.getText().toString(), tvTime
								.getText().toString(), edtContent.getText()
								.toString(),position);
						
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

	private AlertDialog taoMotAlertDialog() {

		LayoutInflater inflater = this.getLayoutInflater();
		View alertLayout = inflater.inflate(R.layout.dialog_themghichu, null);

		final EditText edtTitle = (EditText) alertLayout
				.findViewById(R.id.edtTitle);
		final TextView tvTime = (TextView) alertLayout
				.findViewById(R.id.tvTime);
		final EditText edtContent = (EditText) alertLayout
				.findViewById(R.id.edtContent);

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

						// ItemGhiChu gc = new
						// ItemGhiChu("Cong viec 01","13:40 25/02/2016","Lam bai tap ve nha");
						themGhichu(edtTitle.getText().toString(), tvTime
								.getText().toString(), edtContent.getText()
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

	protected void themGhichu(String strTitle, String strTime, String strContent) {
		ItemGhiChu gc = new ItemGhiChu();
		gc.setTitle(strTitle);
		gc.setTime(strTime);
		gc.setContent(strContent);
		arrItemghichu.add(gc);
		adapter.notifyDataSetChanged();
	//	datNotify(gio,phut,ngay,thang,nam);

	}
	protected void suaGhichu(String strTitle, String strTime, String strContent, int position) {
		arrItemghichu.get(position).setTitle(strTitle);
		arrItemghichu.get(position).setTime(strTime);
		arrItemghichu.get(position).setContent(strContent);
		adapter.notifyDataSetChanged();
	//	datNotify(gio,phut,ngay,thang,nam);

	}

	public void datNotify(int hour, int minute, int day1, int month1, int year1 ){
//		ScheduleClient scheduleClient;
//		scheduleClient = new ScheduleClient(this);
//	    scheduleClient.doBindService();
    	// Create a new calendar set to the date chosen
    	// we set the time to midnight (i.e. the first minute of that day)
    	Calendar c = Calendar.getInstance();
    	c.set(year1, month1, day1);
    	c.set(Calendar.HOUR_OF_DAY, hour);
    	c.set(Calendar.MINUTE, minute);
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
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy",
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
					res += ":0" + tpTimePK.getCurrentMinute();
					phut = tpTimePK.getCurrentMinute();
				} else {
					res += ":" + tpTimePK.getCurrentMinute();
					phut = tpTimePK.getCurrentMinute();
				}
				
				tv.setText(res + " " + thoigian_ghichu);
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
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}
	 


	
}
