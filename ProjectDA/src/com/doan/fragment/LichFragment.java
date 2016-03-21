package com.doan.fragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.Random;

import com.doan.adapter.HocTapLichNgayAdapter;
import com.doan.lichhoctap.R;
import com.doan.model.DayInWeek;
import com.doan.model.TietHoc;

import android.R.integer;
import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ExpandableListView;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

public class LichFragment extends Fragment {
	
	private ArrayList<DayInWeek> parentItems;
	private ArrayList<Object> childItems;
	private ArrayList<TietHoc> arlAll;
	private ArrayList<TietHoc> arlTh2, arlTh3, arlTh4, arlTh5, arlTh6, arlTh7, arlTh8;
	private ArrayList<Integer> arlColor;
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
	int lan;
	private Activity c;

	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
			@Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.hoctap_lichhoc_layout_test, container, false);
		c = getActivity();
		view = v;
		l = inflater;
		lan = 0;
		tvToday = (TextView) v.findViewById(R.id.tvResetCalendar);
		tvWeek = (TextView) v.findViewById(R.id.tvWeek);
		setinItialize(view, l, c);
		
		tvToday.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				lan = 1;
				Calendar dar = Calendar.getInstance();
				ngayChonDatePicker = dar.getTime();
				setinItialize(view, l, c);
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
		
		TietHoc th1 = new TietHoc(1, null, "Toan roi rac", "P23", 1);
		TietHoc th2 = new TietHoc(2, null, "Tin dai cuong", "P22", 1);
		TietHoc th3 = new TietHoc(3, null, "Thiet ke web", "P33", 1);
		TietHoc th4 = new TietHoc(4, null, "Lap trinh mobile", "P24", 1);
		Date specLTMB, specLTHDT;
		Calendar calendar = Calendar.getInstance();
		calendar.set(2016, 1, 23); //tháng bắt đầu từ 0
		specLTMB = calendar.getTime();
		TietHoc th5 = new TietHoc(4, specLTMB, "Lap trinh mobile", "P24", 2);
		TietHoc th6 = new TietHoc(5, null, "Ki thuat lap trinh huong doi tuong", "P24", 1);

		TietHoc th7 = new TietHoc(1, null, "Toan roi rac", "P23", 1);
		TietHoc th8 = new TietHoc(2, null, "Tin dai cuong", "P22", 1);
		TietHoc th9 = new TietHoc(3, null, "Thiet ke web", "P33", 1);
		TietHoc th10 = new TietHoc(4, null, "Lap trinh mobile", "P24", 1);
		TietHoc th11 = new TietHoc(5, null, "Ki thuat lap trinh huong doi tuong", "P24", 1);
		calendar.set(2016, 2, 1);
		specLTHDT = calendar.getTime();
		TietHoc th12 = new TietHoc(5, specLTHDT, "Ki thuat lap trinh huong doi tuong", "P25", 2);
		
		tvWeek.setText(resultWeek);
		
		arlAll = new ArrayList<TietHoc>();
		arlTh2 = new ArrayList<TietHoc>();
		arlTh3 = new ArrayList<TietHoc>();
		arlTh4 = new ArrayList<TietHoc>();
		arlTh5 = new ArrayList<TietHoc>();
		arlTh6 = new ArrayList<TietHoc>();
		arlTh7 = new ArrayList<TietHoc>();
		arlTh8 = new ArrayList<TietHoc>();
		if (lan != 1) {
			arlAll.add(th1);
			arlAll.add(th2);
			arlAll.add(th3);
			arlAll.add(th4);
			arlAll.add(th5);
			arlAll.add(th6);
		} else {
			arlAll.add(th7);
			arlAll.add(th8);
			arlAll.add(th9);
			arlAll.add(th10);
			arlAll.add(th11);
			arlAll.add(th12);
		}

		phanTietHoc(arlAll);

		expandableList = (ExpandableListView) v.findViewById(R.id.elvDays);
		expandableList.setGroupIndicator(null);
		expandableList.setClickable(true);

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

		resultWeek = getString(R.string.string_tvWeek) + " " + firstdayOfWeek + " - " + lastdayOfWeek;
		resultDayName = getString(R.string.string_tvHelloTextView) + " " + resultDayName + ".";
		//resultDate = date + " " + month + " " + "năm " + year;
		resultDate = date + " " + month;
	}

	private void getTuan(int tru, int cong) {
		Calendar cFirstdayOfWeek, cLastdayOfWeek, specifyDate;
		cFirstdayOfWeek = cLastdayOfWeek = specifyDate = Calendar.getInstance();
		if(lan == 1){
			specifyDate.setTime(ngayChonDatePicker);
		}else {
			specifyDate.setTime(Calendar.getInstance().getTime());
		}
		cFirstdayOfWeek = specifyDate;
		cLastdayOfWeek = specifyDate;
		int thang1 = 0; int thang2 = 0;

		cFirstdayOfWeek.add(Calendar.DAY_OF_YEAR, tru);
		ngayDauTuanTru1 = cFirstdayOfWeek.getTime();
		thang1 = cFirstdayOfWeek.getTime().getMonth() + 1;
		firstdayOfWeek = cFirstdayOfWeek.getTime().getDate() + "/" + thang1;

		ngayTinh = cFirstdayOfWeek.getTime();
		// cho thu 2 vao de tao listtuan
		// setGroupParents(cFirstdayOfWeek.getTime());

		cLastdayOfWeek.add(Calendar.DAY_OF_YEAR, cong - tru);
		ngayCuoiTuanCong1 = cLastdayOfWeek.getTime();
		thang2 = cLastdayOfWeek.getTime().getMonth() +1;
		lastdayOfWeek = cLastdayOfWeek.getTime().getDate() + "/" + thang2;
	}

	private void checkTietCuThe(ArrayList<TietHoc> arlTh, TietHoc th) {
		TietHoc pt = new TietHoc();
		if (arlTh.size() > 0) {
			if (th.getSpecificDate() != null) {
				if (th.getSpecificDate().after(ngayDauTuanTru1) == true && th.getSpecificDate().before(ngayCuoiTuanCong1) == true) {
					for (int i = 0; i < arlTh.size(); i++) {
						pt = arlTh.get(i);
						if (th.getBuoiHoc() == pt.getBuoiHoc() && th.getMonHoc() == pt.getMonHoc()) {
							arlTh.remove(i);
							arlTh.add(th);
							break;
						}
					}
				}

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
		for (TietHoc tietHoc : list) {
			switch (tietHoc.getBuoiHoc()) {
			case 1:
				checkTietCuThe(arlTh2, tietHoc);
				break;
			case 2:
				checkTietCuThe(arlTh2, tietHoc);
				break;
			case 3:
				checkTietCuThe(arlTh3, tietHoc);
				break;
			case 4:
				checkTietCuThe(arlTh3, tietHoc);
				break;
			case 5:
				checkTietCuThe(arlTh4, tietHoc);
				break;
			case 6:
				checkTietCuThe(arlTh4, tietHoc);
				break;
			case 7:
				checkTietCuThe(arlTh5, tietHoc);
				break;
			case 8:
				checkTietCuThe(arlTh5, tietHoc);
				break;
			case 9:
				checkTietCuThe(arlTh6, tietHoc);
				break;
			case 10:
				checkTietCuThe(arlTh6, tietHoc);
				break;
			case 11:
				checkTietCuThe(arlTh7, tietHoc);
				break;
			case 12:
				checkTietCuThe(arlTh7, tietHoc);
				break;
			case 13:
				checkTietCuThe(arlTh8, tietHoc);
				break;
			case 14:
				checkTietCuThe(arlTh8, tietHoc);
				break;
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

	private void intTaoMau() {
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
	}


	public void setGroupParents(Date thu2) {
		/*if (lan != 1) {
			ngayTinh = thu2;
		} else {
			ngayTinh = ngayChonDatePicker;
		}*/
		Random rnd = new Random(); 
		arlColor = new ArrayList<Integer>();
		int color;
		for(int i = 0; i < 7; i++){
			 //color = Color.argb(255, rnd.nextInt(256), rnd.nextInt(256), rnd.nextInt(256));
			intTaoMau();
		}   
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
				setinItialize(view, l, context);
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
}
