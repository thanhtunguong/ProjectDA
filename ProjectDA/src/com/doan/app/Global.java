package com.doan.app;




import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.doan.database_handle.ExecuteQuery;
import com.doan.lichhoctap.LoginActivity;
import com.doan.lichhoctap.R;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Global {
	
	//__SharedPreference
	public static String XML_FILE_NAME = "ImStudent_XML";
	//__/SharedPreference
	
	public static String activityTitles[];
	public static String MaSVDN;
	//Tung
	//public static String BASE_URI ="http://192.168.3.101:8080/csdlda/";
	//public static String BASE_URI ="http://192.168.100.182:8080/csdlda/";
	public static String BASE_URI ="http://192.168.43.133:8080/csdlda/";
	public static String URI_LICH_HOC ="api_LichHocTheoMaSV.php";
	public static String URI_THONG_BAO = "api_DanhSachThongBaoTheoMaSV.php";
	public static String URI_THONG_BAO_GV = "api_DanhSachThongBaoTheoMaGV.php";
	public static String URI_REPLY_THEO_MA_THONGBAO = "api_ChiTietThongBaoTheoMaThongBao.php";
	public static String URI_DANH_SACH_SINH_VIEN_THEO_MA_LOP = "api_DanhSachSVTheoMaLopHanhChinh.php";
	public static String URI_DANH_SACH_LOP_CHU_NHIEM_THEO_MA_GV = "api_DanhSachLopChuNhiemTheoMAGV.php";
	public static String URI_GUI_THONG_BAO = "api_DangThongBaoTheoMaGVLHC.php";
	public static String URI_GUI_REPLY_GV = "api_DangReplyTheoMaGV.php";
	public static String URI_GUI_REPLY_SV = "api_DangReplyTheoMaSV.php";
	//public static String BASE_URI ="http://192.168.56.1:533";
	//pu
	//blic static String BASE_URI ="http://192.168.56.1:8080";
	
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
	public void DangXuat(Context c){
		Global.saveStringPreference(c, "access_token", "");
		Global.saveStringPreference(c, "MaSVDN", "");
		Global.saveStringPreference(c, "MaGVDN", "");
		ExecuteQuery exeQ = new ExecuteQuery(c);
		exeQ.close();
		exeQ.deleteDB();
		Intent i = new Intent(c, LoginActivity.class);
		c.startActivity(i);
	}
	public void dialogLogoutConfirm(final Activity context){
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.dialog_logout);
		Button dialogButtonOK = (Button) dialog.findViewById(R.id.btnOKDialogLogout);
		dialogButtonOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Global.saveStringPreference(context, "access_token", "");
				Global.saveStringPreference(context, "MaSVDN", "");
				Global.saveStringPreference(context, "MaGVDN", "");
				ExecuteQuery exeQ = new ExecuteQuery(context);
				exeQ.close();
				exeQ.deleteDB();
				dialog.dismiss();
				Intent i = new Intent(context, LoginActivity.class);
				context.startActivity(i);
				/*Global g = new Global();
				g.DangXuat(context);*/
			}
		});
		Button dialogButtonCancel = (Button) dialog.findViewById(R.id.btnCancelDialogLogout);
		dialogButtonCancel.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				dialog.dismiss();
			}
		});

		dialog.show();
	}
}
