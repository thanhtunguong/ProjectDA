package com.doan.app;




import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
	//Tung
	public static String BASE_URI ="http://192.168.3.101:8080/csdlda/";
	public static String URI_LICH_HOC ="api_LichHocTheoMaSV.php";
	public static String URI_THONG_BAO = "api_DanhSachThongBaoTheoMaSV.php";
	public static String URI_REPLY_THEO_MA_THONGBAO = "api_ChiTietThongBaoTheoMaThongBao.php";
	//public static String BASE_URI ="http://192.168.56.1:533";
	//public static String BASE_URI ="http://192.168.56.1:8080";
	
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
	public static String maHoaMd5(String input){
        String result = input;
        if(input != null) {
            MessageDigest md;
            try {
                md = MessageDigest.getInstance("MD5");
            md.update(input.getBytes());
            BigInteger hash = new BigInteger(1, md.digest());
            result = hash.toString(16);
            if ((result.length() % 2) != 0) {
                result = "0" + result;
            } 
            }catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
                return null;
            }
        }
        return result;
    }
	public static Date epKieuDateAndTime(String ngay){
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = null;
		try {
			date = df.parse(ngay);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return date;
	}
	public static Date epKieuDate(String ngay){
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
}
