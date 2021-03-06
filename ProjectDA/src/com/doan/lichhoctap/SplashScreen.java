package com.doan.lichhoctap;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.doan.app.Global;
import com.doan.database_handle.ExecuteQuery;
import com.doan.model.BaiViet;
import com.doan.model.DiemHocTap;
import com.doan.model.ItemGhiChu;
import com.doan.model.SinhVienThongBao;
import com.doan.model.ThongBao;
import com.doan.model.TietHoc;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class SplashScreen extends Activity {
	private ProgressBar secondBar = null;
	private ProgressDialog progressBar;
	private int i = 0;
	private final int SPLASH_DISPLAY_LENGTH = 4000;
	private Context c;
	private ExecuteQuery exeQ;
	private ArrayList<String> arrMaLop;
	private int malopso = 0;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		c = this;
		exeQ = new ExecuteQuery(c);
		secondBar = (ProgressBar) findViewById(R.id.secondBar);
		secondBar.setVisibility(View.VISIBLE);
		exeQ.createDatabase();
		exeQ.open();
		String masinhvien = Global.getStringPreference(c, "MaSVDN", "");
		String magiangvien = Global.getStringPreference(c, "MaGVDN", "");
		if(magiangvien.matches("") == false && masinhvien.matches("")){
			arrMaLop = new ArrayList<String>();
			//getDanhSachLopTheoGiangVien();
			getThongBaoGiangVien();
		}else if (magiangvien.matches("") && masinhvien.matches("") == false) {
			getThongtinSV(masinhvien);
		}
		/*getLichHoc();
		getBaiViet(); //getBaiViet() cuoi cung, Intent o do;
		getThongBao();
		getGhiChu();*/
	}
	/*
	 * if (i == 0 || i == 10) { //make the progress bar visible
	 * firstBar.setVisibility(View.VISIBLE); firstBar.setMax(150);
	 * secondBar.setVisibility(View.VISIBLE); }else if ( i< firstBar.getMax() )
	 * { //Set first progress bar value firstBar.setProgress(i); //Set the
	 * second progress bar value firstBar.setSecondaryProgress(i + 10); }else {
	 * firstBar.setProgress(0); firstBar.setSecondaryProgress(0); i = 0;
	 * firstBar.setVisibility(View.GONE); secondBar.setVisibility(View.GONE); }
	 * i = i + 10; }
	 */
	private void getThongBao(){
		String masinhvien = Global.getStringPreference(c, "MaSVDN", "0");
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("masinhvien", masinhvien);
		params.put("access_token", Global.getStringPreference(c, "access_token", ""));
		String url = Global.BASE_URI + Global.URI_THONG_BAO;
		client.post(url, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				Log.e("JsonThongBao", response);
				if (executeWhenGetThongBaoSuccess(response)) {
					/*Toast.makeText(getApplicationContext(),	"Thanh cong", Toast.LENGTH_LONG).show();*/
					getLichHoc();
				} else {
					/*Toast.makeText(getApplicationContext(),
							"That bai", Toast.LENGTH_LONG)
							.show();*/
				}
			}

			public void onFailure(int statusCode, Throwable error,
					String content) {
				Log.e("JsonThongBao", error+" "+content);
				/*Toast.makeText(getApplicationContext(),
						error+"", Toast.LENGTH_LONG)
						.show();*/
			}
		});
	}
	private boolean executeWhenGetThongBaoSuccess(String response) {
		/*Toast.makeText(getApplicationContext(),
				response+"", Toast.LENGTH_LONG)
				.show();*/
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
				
				ThongBao tb = new ThongBao(mathongbao, tieudethongbao, noidungthongbao, ngaytaothongbao, magv, songuoinhanthongbao);
				arrThongBao.add(tb);
			}
			exeQ.insert_tbl_ThongBao_multi(arrThongBao);
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}
	private void getThongBaoGiangVien(){
		String magiangvien = Global.getStringPreference(c, "MaGVDN", "0");
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("magiangvien", magiangvien);
		params.put("access_token", Global.getStringPreference(c, "access_token", ""));
		String url = Global.BASE_URI + Global.URI_THONG_BAO_GV;
		client.post(url, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				Log.e("JsonThongBao", response);
				if (executeWhenGetThongBaoGiangVienSuccess(response)) {
					/*Toast.makeText(getApplicationContext(),	"Thanh cong", Toast.LENGTH_LONG).show();*/
					getDanhSachLopTheoGiangVien();
				} else {
					/*Toast.makeText(getApplicationContext(),
							"That bai", Toast.LENGTH_LONG)
							.show();*/
				}
			}

			public void onFailure(int statusCode, Throwable error,
					String content) {
				Log.e("JsonThongBao", error+" "+content);
				/*Toast.makeText(getApplicationContext(),
						error+"", Toast.LENGTH_LONG)
						.show();*/
			}
		});
	}
	private boolean executeWhenGetThongBaoGiangVienSuccess(String response) {
		/*Toast.makeText(getApplicationContext(),
				response+"", Toast.LENGTH_LONG)
				.show();*/
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
				
				ThongBao tb = new ThongBao(mathongbao, tieudethongbao, noidungthongbao, ngaytaothongbao, magv, songuoinhanthongbao);
				arrThongBao.add(tb);
			}
			exeQ.insert_tbl_ThongBao_multi(arrThongBao);
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}
	//SinhVienThongBao
	private void getDanhSachLopTheoGiangVien(){
		String magiangvien = Global.getStringPreference(c, "MaGVDN", "0");
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("magiangvien", magiangvien);
		params.put("access_token", Global.getStringPreference(c, "access_token", ""));
		String url = Global.BASE_URI + Global.URI_DANH_SACH_LOP_CHU_NHIEM_THEO_MA_GV;
		client.post(url, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				Log.e("DanhSachLop", response);
				if (executeWhenGetDanhSachLopTheoGiangVienSuccess(response)) {
					/*Toast.makeText(getApplicationContext(),	"Thanh cong", Toast.LENGTH_LONG).show();*/
					getDanhSachSinhVienTheoMaLop(arrMaLop.get(malopso));
				} else {
					/*Toast.makeText(getApplicationContext(),
							"That bai", Toast.LENGTH_LONG)
							.show();*/
				}
			}

			public void onFailure(int statusCode, Throwable error,
					String content) {
				Log.e("DanhSachLop", error+" "+content);
				/*Toast.makeText(getApplicationContext(),
						error+"", Toast.LENGTH_LONG)
						.show();*/
			}
		});
	}
	private boolean executeWhenGetDanhSachLopTheoGiangVienSuccess(String response) {
		ArrayList<String> arr = new ArrayList<String>();
		ArrayList<String> arr2 = new ArrayList<String>();
		try {
			JSONArray arrObj = new JSONArray(response);
			for (int i = 0; i < arrObj.length(); i++) {
				JSONObject thongbaoJson = arrObj.getJSONObject(i);

				String malop = thongbaoJson.optString("pk_lophanhchinh");
				/*exeQ.insert_tbl_LopHanhChinh_single(malop);
				arr = exeQ.getAllLopHanhChinhSqLite();*/
				arrMaLop.add(malop);
			}
			exeQ.insert_tbl_LopHanhChinh_multi(arrMaLop);
			exeQ.deleteAllRowTblSinhVienThongBao();
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}
	private void getDanhSachSinhVienTheoMaLop(String malop){
		String magiangvien = Global.getStringPreference(c, "MaGVDN", "0");
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("magiangvien", magiangvien);
		params.put("malop", malop);
		params.put("access_token", Global.getStringPreference(c, "access_token", ""));
		String url = Global.BASE_URI + Global.URI_DANH_SACH_SINH_VIEN_THEO_MA_LOP;
		client.post(url, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				Log.e("DanhSachSinhVienTheoMaLop", response);
				if (executeWhenGetDanhSachSinhVienTheoMaLopSuccess(response)) {
					malopso = malopso + 1;
					if(malopso < arrMaLop.size()){
						getDanhSachSinhVienTheoMaLop(arrMaLop.get(malopso));
					}else {
						exeQ.close();
						//Intent intent = new Intent(SplashScreen.this, TaoThongBaoGVActivity.class);
						Intent intent = new Intent(SplashScreen.this, ThongBaoActivity.class);
						startActivity(intent);
					}
					/*Toast.makeText(getApplicationContext(),	"Thanh cong", Toast.LENGTH_LONG).show();*/
				} else {
					/*Toast.makeText(getApplicationContext(),
							"That bai", Toast.LENGTH_LONG)
							.show();*/
				}
			}

			public void onFailure(int statusCode, Throwable error,
					String content) {
				Log.e("DanhSachSinhVienTheoMaLop", error+" "+content);
				/*Toast.makeText(getApplicationContext(),
						error+"", Toast.LENGTH_LONG)
						.show();*/
			}
		});
	}
	private boolean executeWhenGetDanhSachSinhVienTheoMaLopSuccess(String response) {
		/*Toast.makeText(getApplicationContext(),
				response+"", Toast.LENGTH_LONG)
				.show();*/
		ArrayList<SinhVienThongBao> arrSinhVienThongBao = new ArrayList<SinhVienThongBao>();
		
		try {
			JSONArray arrObj = new JSONArray(response);
			for (int i = 0; i < arrObj.length(); i++) {
				JSONObject svtbJson = arrObj.getJSONObject(i);

				String mathongbao = svtbJson.optString("PK_ThongBao");
				SinhVienThongBao sv = new SinhVienThongBao();
				sv.setMaSV(svtbJson.optString("PK_SV"));
				sv.setHoTenSinhVien(svtbJson.optString("hoten"));
				sv.setNgaySinhSinhVien(svtbJson.optString("ngaysinh"));
				sv.setGioiTinhSinhVien(svtbJson.optString("gioitinh"));
				sv.setMaLopHanhChinh(svtbJson.optString("malophanhchinh"));
				
				arrSinhVienThongBao.add(sv);
			}
			exeQ.insert_tbl_SinhVienThongBao_multi(arrSinhVienThongBao);
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}
	//Lich_Hoc
	private void getLichHoc(){
		String masinhvien = Global.getStringPreference(c, "MaSVDN", "0");
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("masinhvien", masinhvien);
		params.put("access_token", Global.getStringPreference(c, "access_token", ""));
		String url = Global.BASE_URI + Global.URI_LICH_HOC;
		client.post(url, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				Log.e("JsonLichHoc", response);
				if (executeWhenGetLichHocSuccess(response)) {
					/*Toast.makeText(getApplicationContext(),
							"Thanh cong", Toast.LENGTH_LONG)
							.show();*/
					getGhiChu();
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
			exeQ.insert_tbl_TietHoc_multi(arrTietHoc);
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
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
	//___tbl_Diem
	private void getDiem(){
		String masinhvien = Global.getStringPreference(c, "MaSVDN", "0");
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("masinhvien", masinhvien);
		params.put("access_token", Global.getStringPreference(c, "access_token", ""));
		String url = Global.BASE_URI + Global.URI_DIEMTHEOMASV;
		client.post(url, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				Log.e("JsonDiem", response);
				if (executeWhenGetDiemSuccess(response)) {
					/*Toast.makeText(getApplicationContext(),
							"Thanh cong", Toast.LENGTH_LONG)
							.show();*/
					getChiTietMonHoc();
				} else {
					/*Toast.makeText(getApplicationContext(),
							"That bai", Toast.LENGTH_LONG)
							.show();*/
				}
			}

			public void onFailure(int statusCode, Throwable error,
					String content) {
				Log.e("JsonDiem", error+" "+content);
				/*Toast.makeText(getApplicationContext(),
						error+"", Toast.LENGTH_LONG)
						.show();*/
			}
		});
	}
	private boolean executeWhenGetDiemSuccess(String response) {
		/*Toast.makeText(getApplicationContext(),
				response+"", Toast.LENGTH_LONG)
				.show();*/
		ArrayList<DiemHocTap> arrTietHoc = new ArrayList<DiemHocTap>();
		
		try {
			JSONArray arrObj = new JSONArray(response);
			for (int i = 0; i < arrObj.length(); i++) {
				JSONObject diemJson = arrObj.getJSONObject(i);
				DiemHocTap diem = new DiemHocTap();
				diem.setDiemCC(diemJson.optInt("diem1"));
				diem.setDiemKT(diemJson.optInt("diem2"));
				diem.setDiemThi(diemJson.optInt("diem3"));
				diem.setTenMonHoc(diemJson.optString("tenmonhoc"));
				diem.setSoTinChi(diemJson.optInt("sotinchi"));
				diem.setMaLopTinChi(diemJson.optString("maloptinchi"));
				diem.setMaDiem(diemJson.optString("pk_diem"));
				diem.setMaTrangThaiDK(diemJson.optString("trangthaidk"));
				diem.setThoiGianDK(diemJson.optString("Thoigiandk"));
				arrTietHoc.add(diem);
			}
			exeQ.insert_tbl_DiemHocTap_multi(arrTietHoc);
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}
	//___/tbl_Diem
	
	//___tbl_MonHoc
	private void getChiTietMonHoc(){
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		String url = Global.BASE_URI + Global.URI_DIEM_TRUNG_BINH_SINHVIEN;
		client.post(url, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				Log.e("JsonChiTietMonHoc", response);
				if (executeWhenGetChiTietMonHocSuccess(response)) {
					/*Toast.makeText(getApplicationContext(),
							"Thanh cong", Toast.LENGTH_LONG)
							.show();*/
					exeQ.close();
					Intent intent = new Intent(SplashScreen.this, HocTapActivity.class);
					startActivity(intent);
				} else {
					/*Toast.makeText(getApplicationContext(),
							"That bai", Toast.LENGTH_LONG)
							.show();*/
				}
			}

			public void onFailure(int statusCode, Throwable error,
					String content) {
				Log.e("JsonDiem", error+" "+content);
				/*Toast.makeText(getApplicationContext(),
						error+"", Toast.LENGTH_LONG)
						.show();*/
			}
		});
	}
	private boolean executeWhenGetChiTietMonHocSuccess(String response) {
		/*Toast.makeText(getApplicationContext(),
				response+"", Toast.LENGTH_LONG)
				.show();*/
		String mamon = "";
		Double diem = null;
		try {
			JSONArray arrObj = new JSONArray(response);
			for (int i = 0; i < arrObj.length(); i++) {
				JSONObject chiTietMonHocJson = arrObj.getJSONObject(i);
				mamon = chiTietMonHocJson.optString("pk_monhoc");
				diem = chiTietMonHocJson.optDouble("DiemTrungBinhSV");
				exeQ.update_tbl_diemTB_single(mamon, diem);
			}
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	//----- Lay thong tin sinh vien
	public void getThongtinSV(String masinhvien) {

		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("masinhvien", masinhvien);
		params.put("access_token", Global.getStringPreference(c, "access_token", ""));
		client.post(Global.BASE_URI + Global.URI_THONGTINTHEOMASV, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				// Log.e("loginToServer", response);
				if (executeWhenGetThongTinSuccess(response)) {
					/*
					 * Toast.makeText(getApplicationContext(), "Thanh cong",
					 * Toast.LENGTH_LONG) .show();
					 */
					// setThongTin();
					getThongBao();
				} else {
					/*
					 * Toast.makeText(getApplicationContext(), "That bai",
					 * Toast.LENGTH_LONG) .show();
					 */
				}
			}

			public void onFailure(int statusCode, Throwable error, String content) {
				Log.e("loginToServer", error + " " + content);
				/*
				 * Toast.makeText(getApplicationContext(), error+"",
				 * Toast.LENGTH_LONG) .show();
				 */
				Toast.makeText(c, c.getString(R.string.internet_error), Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(SplashScreen.this, HocTapActivity.class);
				startActivity(intent);
			}
		});
	}

	private boolean executeWhenGetThongTinSuccess(String response) {

		String masinhvien = Global.getStringPreference(c, "MaSVDN", "0");
		String HoTen = "";
		String NgaySinh = "";
		String GioiTinh = "";
		String DiaChi = "";
		String SDT = "";
		String Email = "";
		String tenlophanhchinh = "";
		/*Toast.makeText(getApplicationContext(), response + "", Toast.LENGTH_LONG).show();*/
		try {
			JSONArray arrObj = new JSONArray(response);
			for (int i = 0; i < arrObj.length(); i++) {
				JSONObject userJson = arrObj.getJSONObject(i);

				HoTen = userJson.optString("HoTen");
				NgaySinh = userJson.optString("NgaySinh");
				GioiTinh = userJson.optString("GioiTinh");
				DiaChi = userJson.optString("DiaChi");
				SDT = userJson.optString("SDT");
				Email = userJson.optString("Email");
				tenlophanhchinh = userJson.optString("tenlophanhchinh");
			}
			exeQ.insert_tbl_SinhVien(masinhvien, Email, HoTen, tenlophanhchinh, NgaySinh, GioiTinh, DiaChi, SDT);
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			Log.e("JsonThongTinSinhVien", e + "");
			return false;
		}
	}

//--------- tbl_GhiChu
//--- Get all ghi chu
	private void getGhiChu() {
		String masinhvien = Global.getStringPreference(c, "MaSVDN", "0");
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("masinhvien", masinhvien);
		params.put("access_token", Global.getStringPreference(c, "access_token", ""));
		String url = Global.BASE_URI + Global.URI_GHICHUTHEOMATHEOMASV;
		client.post(url, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				// Log.e("JsonGhiChu", response);
				if (executeWhenGetGhiChuSuccess(response)) {
					// ArrayList<ThongBao> arrThongBao =
					// exeQ.getAllThongBaoSqLite();
					/*
					 * Toast.makeText(getApplicationContext(), "Thanh cong",
					 * Toast.LENGTH_LONG) .show();
					 */
					// setGhiChu();
					getBaiViet();
				} else {
					/*
					 * Toast.makeText(getApplicationContext(), "That bai",
					 * Toast.LENGTH_LONG) .show();
					 */
				}
			}

			public void onFailure(int statusCode, Throwable error, String content) {
				Log.e("JsonThongBao", error + " " + content);
				/*
				 * Toast.makeText(getApplicationContext(), error+"",
				 * Toast.LENGTH_LONG) .show();
				 */
			}
		});
	}

	private boolean executeWhenGetGhiChuSuccess(String response) {

		String masinhvien = Global.getStringPreference(c, "MaSVDN", "0");
		// ArrayList<ThongBao> arrThongBao = new ArrayList<ThongBao>();
		ArrayList<ItemGhiChu> arrItemghichu = new ArrayList<ItemGhiChu>();
		try {
			JSONArray arrObj = new JSONArray(response);
			for (int i = 0; i < arrObj.length(); i++) {
				JSONObject ghichuJson = arrObj.getJSONObject(i);

				String maghichu = ghichuJson.optString("pk_ghichu");
				String tieudeghichu = ghichuJson.optString("TieuDeGhiChu");
				String noidungghichu = ghichuJson.optString("NoiDungGhiChu");
				String thoigiannhacghichu = ghichuJson.optString("ThoiGianNhacGhiChu");
				String thoigianchinhsuaghichu = ghichuJson.optString("ThoiGianChinhSuaGhiChu");
				/*
				 * Toast.makeText(getApplicationContext(), maghichu,
				 * Toast.LENGTH_LONG) .show();
				 */
				ItemGhiChu gc = new ItemGhiChu(maghichu, tieudeghichu, thoigiannhacghichu, thoigianchinhsuaghichu,
						noidungghichu);
				arrItemghichu.add(gc);
			}

			exeQ.insert_tbl_GhiChu_multi(arrItemghichu, masinhvien);
			return true;
		} catch (JSONException e) {
			e.printStackTrace();
			return false;
		}
	}
//--------- tbl_BaiViet
//--- Get all Bai viet
	private void getBaiViet() {

		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		
		String url = Global.BASE_URI + Global.URI_DANHSACHBAIVIET;
		client.post(url, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				// Log.e("JsonGhiChu", response);
				if (executeWhenGetBaiVietSuccess(response)) {
					getDiem();
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
