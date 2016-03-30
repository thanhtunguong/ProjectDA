package com.doan.app;




import com.doan.lichhoctap.R;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Global {
	
	//__SharedPreference
	public static String XML_FILE_NAME = "ImStudent_XML";
	//__/SharedPreference
	
	public static String activityTitles[];
	public static String MaSVDN;
	public static String BASE_URI ="http://192.168.56.1:8080";
	
	public static String URI_DANGNHAP ="api_DangNhap.php";
	public static String URI_DANGTHONGBAOTHEOGVLHC ="api_DangThongBaoTheoMaGVLHC.php";
	public static String URI_DANHSACHBAIVIET ="api_DanhSachBaiViet.php";
	public static String URI_DANHSACHTHONGBAOTHEOMASV ="api_DanhSachThongBaoTheoMaSV.php";
	public static String URI_DIEMTHEOMASV ="api_DiemTheoMaSV.php";
	public static String URI_DOIMATKHAUTHEOMAGV ="api_DoiMatKhauTheoMaGV.php";
	public static String URI_DOIMATKHAUTHEOMASV ="api_DoiMatKhauTheoMaSV.php";
	public static String URI_DOITHONGTINTHEOMAGV ="api_DoiThongTinTheoMaGV.php";
	public static String URI_DOITHONGTINTHEOMASV ="api_DoiThongTinTheoMaSV.php";
	public static String URI_GHICHUTHEOMATHEOMASV ="api_GhiChuTheoMaSV.php";
	public static String URI_LICHHOCTHEOMASV ="api_LichHocTheoMaSV.php";
	public static String URI_LICHTHITHEOMASV ="api_LichThiTheoMaSV.php";
	public static String URI_SUAGHICHUTHEOMASV ="api_SuaGhiChuTheoMaSV.php";
	public static String URI_TAOGHICHUTHEOMASV ="api_TaoGhiChuTheoMaSV.php";
	public static String URI_THONGTINTHEOMAGV ="api_ThongTinTheoMaGV.php";
	public static String URI_THONGTINTHEOMASV ="api_ThongTinTheoMaSV.php";
	public static String URI_XOAGHICHUTHEOMASVGC ="api_XoaGhiChuTheoMaSVGC.php";
	

	
	
	public static void addActivityTitles(Context c){
		activityTitles = new String[3];
		activityTitles[0] = c.getString(R.string.title_activity_for_selector_Lich);
        activityTitles[1] = c.getString(R.string.title_activity_for_selector_NF);
        activityTitles[2] = c.getString(R.string.title_activity_for_selector_General);
	}
	public static void saveStringPreference(Context mContext, String key, String value) {
		SharedPreferences mSharedPrefences = mContext.getSharedPreferences(
				XML_FILE_NAME, Context.MODE_PRIVATE);

		Editor mEditor = mSharedPrefences.edit();
		mEditor.putString(key, value);
		mEditor.commit();
	}
	public static void saveIntegerPreference(Context mContext, String key, Integer value) {
		SharedPreferences mSharedPrefences = mContext.getSharedPreferences(
				XML_FILE_NAME, Context.MODE_PRIVATE);

		Editor mEditor = mSharedPrefences.edit();
		mEditor.putInt(key, value);
		mEditor.commit();
	}
	public static String getStringPreference(Context mContext, String key,
			String defValue) {
		SharedPreferences mSharedPrefences = mContext.getSharedPreferences(
				XML_FILE_NAME, Context.MODE_PRIVATE);
		return mSharedPrefences.getString(key, defValue);
	}
	public static int getIntegerPreference(Context mContext, String key,
			Integer defValue) {
		SharedPreferences mSharedPrefences = mContext.getSharedPreferences(
				XML_FILE_NAME, Context.MODE_PRIVATE);
		return mSharedPrefences.getInt(key, defValue);
	}
}
