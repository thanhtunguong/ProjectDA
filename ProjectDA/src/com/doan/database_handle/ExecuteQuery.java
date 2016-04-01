package com.doan.database_handle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.doan.app.Global;
import com.doan.model.DiemHocTap;
import com.doan.model.DieuLe;
import com.doan.model.DieuLeTag;
import com.doan.model.MonHoc;
import com.doan.model.MonHocTienQuyet;
import com.doan.model.SinhVien;
import com.doan.model.ThongBao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.util.Log;

public class ExecuteQuery {
	protected static final String TAG = "Execute Query";
	SQLiteDatabase database;
	DatabaseHelper mDbHelper;
	private final Context mContext;

	public ExecuteQuery(Context context) {
		this.mContext = context;
		mDbHelper = new DatabaseHelper(mContext);
	}

	public ExecuteQuery createDatabase() throws SQLException {
		try {
			mDbHelper.createDataBase();
		} catch (IOException mIOException) {
			Log.e(TAG, mIOException.toString() + "  UnableToCreateDatabase");
			throw new Error("UnableToCreateDatabase");
		}
		return this;
	}

	public ExecuteQuery open() throws SQLException {
		try {
			mDbHelper.openDataBase();
		} catch (SQLException mSQLException) {
			Log.e(TAG, "open >>" + mSQLException.toString());
			throw mSQLException;
		}
		return this;
	}

	public void close() {
		mDbHelper.close();
	}
	
//___tbl_SinhVien
	public ArrayList<SinhVien> getAllSinhVien(){
		ArrayList<SinhVien> list = new ArrayList<SinhVien>();
		String selectQuery = "SELECT * FROM "
				+ ColumnName.SV_TABLE;
		database = mDbHelper.getReadableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				SinhVien sv = new SinhVien();

				sv.setMaSV(cursor.getString(0));
				sv.setTenSV(cursor.getString(1));
				sv.setNgaySinhSV(cursor.getString(2));
				sv.setGioiTinhSV(cursor.getInt(3));
				sv.setDiaChiSV(cursor.getString(4));
				sv.setSDTSV(cursor.getString(5));
				sv.setEmailSV(cursor.getString(6));
				sv.setPwdSV(cursor.getString(7));
				sv.setSoLanDangNhapSV(cursor.getInt(8));
				sv.setMaLopHanhChinh(cursor.getString(9));

				list.add(sv);
			} while (cursor.moveToNext());
		}
		return list;
	}
	public boolean getSvUser(String email, String pwd, SinhVien sv){
		String selectQuery = "SELECT * FROM "
				+ ColumnName.SV_TABLE 
				+ " WHERE " + ColumnName.SV_EMAIL_SV + " = '" + email + "'"
				+ " and " + ColumnName.SV_PWD_SV + " = '" + pwd + "'";
		database = mDbHelper.getReadableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				sv = new SinhVien();

				sv.setMaSV(cursor.getString(0));
				Global.saveStringPreference(mContext, "MaSVDN", cursor.getString(0));
				sv.setTenSV(cursor.getString(1));
				sv.setNgaySinhSV(cursor.getString(2));
				sv.setGioiTinhSV(cursor.getInt(3));
				sv.setDiaChiSV(cursor.getString(4));
				sv.setSDTSV(cursor.getString(5));
				sv.setEmailSV(cursor.getString(6));
				sv.setPwdSV(cursor.getString(7));
				sv.setSoLanDangNhapSV(cursor.getInt(8));
				sv.setMaLopHanhChinh(cursor.getString(9));

				return true;
			} while (cursor.moveToNext());
		}else {
			return false;
		}
	}
	
//___/tbl_SinhVien

//___tbl_Diem
	public ArrayList<DiemHocTap> getDiemSV(Context c){
		ArrayList<DiemHocTap> arrDiem = new ArrayList<DiemHocTap>();
		String selectQuery = "SELECT " + ColumnName.DIEM_DIEM_1 + "," + ColumnName.DIEM_DIEM_2 + "," + ColumnName.DIEM_DIEM_7 + ","
										+ ColumnName.DIEM_MA_LOP_TIN_CHI + "," + ColumnName.DIEM_MA_LICH_THI + ","
										+ ColumnName.DIEM_TRANG_THAI_DK + ","
										+ ColumnName.MON_HOC_TEN_MON_HOC + "," + ColumnName.MON_HOC_SO_TIN_CHI + "," + ColumnName.DIEM_THOI_GIAN_DK + " "
				+ "FROM " + ColumnName.DIEM_TABLE + ", " + ColumnName.LopTINCHI_TABLE + ", " + ColumnName.MON_HOC_TABLE + " "
				+ " WHERE " + ColumnName.DIEM_MA_SV + " = '" + Global.getStringPreference(c, "MaSVDN", "0") + "'" 
					+ " and " + ColumnName.DIEM_MA_LOP_TIN_CHI + " = " + ColumnName.LopTINCHI_MA_LOP_TIN_CHI
					+ " and " + ColumnName.LopTINCHI_MA_MON_HOC + " = " + ColumnName.MON_HOC_MA_MON_HOC;
		database = mDbHelper.getReadableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				DiemHocTap diem = new DiemHocTap();

				diem.setDiemCC(cursor.getInt(0));
				diem.setDiemKT(cursor.getInt(1));
				diem.setDiemThi(cursor.getInt(2));
				diem.setMaLopTinChi(cursor.getString(3));
				diem.setMaLichThi(cursor.getString(4));
				diem.setMaTrangThaiDK(cursor.getString(5));
				diem.setTenMonHoc(cursor.getString(6));
				diem.setSoTinChi(cursor.getInt(7));
				diem.setThoiGianDK(cursor.getString(8));
				
				arrDiem.add(diem);
			} while (cursor.moveToNext());
		}
		return arrDiem;
	}
	public ArrayList<DiemHocTap> getDiemQuaMonSV(Context c){
		ArrayList<DiemHocTap> arrDiem = new ArrayList<DiemHocTap>();
		String selectQuery = "SELECT " + ColumnName.DIEM_DIEM_1 + "," + ColumnName.DIEM_DIEM_2 + "," + ColumnName.DIEM_DIEM_7 + ","
										+ ColumnName.DIEM_MA_LOP_TIN_CHI + "," + ColumnName.DIEM_MA_LICH_THI + ","
										+ ColumnName.DIEM_TRANG_THAI_DK + ","
										+ ColumnName.MON_HOC_TEN_MON_HOC + "," + ColumnName.MON_HOC_SO_TIN_CHI + "," + ColumnName.DIEM_THOI_GIAN_DK + "," + ColumnName.DIEM_MA_DIEM + " "
				+ "FROM " + ColumnName.DIEM_TABLE + ", " + ColumnName.LopTINCHI_TABLE + ", " + ColumnName.MON_HOC_TABLE + " "
				+ " WHERE " + ColumnName.DIEM_MA_SV + " = '" + Global.getStringPreference(c, "MaSVDN", "0") + "'" 
					+ " and " + ColumnName.DIEM_MA_LOP_TIN_CHI + " = " + ColumnName.LopTINCHI_MA_LOP_TIN_CHI
					+ " and " + ColumnName.LopTINCHI_MA_MON_HOC + " = " + ColumnName.MON_HOC_MA_MON_HOC
					+ " and (" + ColumnName.DIEM_DIEM_1 + "+" + ColumnName.DIEM_DIEM_2 + "*2+" + ColumnName.DIEM_DIEM_7 + "*7)/10 > 5"; 
		database = mDbHelper.getReadableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				DiemHocTap diem = new DiemHocTap();

				diem.setDiemCC(cursor.getInt(0));
				diem.setDiemKT(cursor.getInt(1));
				diem.setDiemThi(cursor.getInt(2));
				diem.setMaLopTinChi(cursor.getString(3));
				diem.setMaLichThi(cursor.getString(4));
				diem.setMaTrangThaiDK(cursor.getString(5));
				diem.setTenMonHoc(cursor.getString(6));
				diem.setSoTinChi(cursor.getInt(7));
				diem.setThoiGianDK(cursor.getString(8));
				diem.setMaDiem(cursor.getString(9));
				
				arrDiem.add(diem);
			} while (cursor.moveToNext());
		}
		return arrDiem;
	}
	/*public ArrayList<DiemHocTap> getDiemSV(String MaSV){
		ArrayList<DiemHocTap> arrDiem = new ArrayList<DiemHocTap>();
		String selectQuery = "SELECT " + ColumnName.DIEM_DIEM_1 + "," + ColumnName.DIEM_DIEM_2 + "," + ColumnName.DIEM_DIEM_7 + ","
										+ ColumnName.DIEM_MA_LOP_TIN_CHI + "," + ColumnName.DIEM_MA_LICH_THI + ","
										+ ColumnName.DIEM_TRANG_THAI_DK + ","
										+ ColumnName.MON_HOC_TEN_MON_HOC + "," + ColumnName.MON_HOC_SO_TIN_CHI + " "
				+ "FROM " + ColumnName.DIEM_TABLE + ", " + ColumnName.LopTINCHI_TABLE + ", " + ColumnName.MON_HOC_TABLE + " "
				+ " WHERE " + ColumnName.DIEM_MA_SV + " = '" + MaSV + "'" 
					+ " and " + ColumnName.DIEM_MA_LOP_TIN_CHI + " = " + ColumnName.LopTINCHI_MA_LOP_TIN_CHI
					+ " and " + ColumnName.LopTINCHI_MA_MON_HOC + " = " + ColumnName.MON_HOC_MA_MON_HOC;
		database = mDbHelper.getReadableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				DiemHocTap diem = new DiemHocTap();

				diem.setDiemCC(cursor.getInt(0));
				diem.setDiemKT(cursor.getInt(1));
				diem.setDiemThi(cursor.getInt(2));
				diem.setMaLopTinChi(cursor.getString(3));
				diem.setMaLichThi(cursor.getString(4));
				diem.setMaTrangThaiDK(cursor.getString(5));
				diem.setTenMonHoc(cursor.getString(6));
				diem.setSoTinChi(cursor.getInt(7));
				
				arrDiem.add(diem);
			} while (cursor.moveToNext());
		}
		return arrDiem;
	}*/
//___/tbl_Diem
	
//___tbl_MonHoc
	public ArrayList<MonHoc> getAllMonHoc() {
		ArrayList<MonHoc> arrMonHoc = new ArrayList<MonHoc>();
		
		String selectQuery =
				"select " + ColumnName.CTDTmon_MA_MON_CTDT + "," + ColumnName.CTDTmon_MA_MON_HOC + "," + ColumnName.MON_HOC_TEN_MON_HOC + " "
				+ "from " + ColumnName.CTDTmon_TABLE + "," + ColumnName.MON_HOC_TABLE + " "
				+ "where " + ColumnName.CTDTmon_MA_MON_HOC + "=" + ColumnName.MON_HOC_MA_MON_HOC;
		database = mDbHelper.getReadableDatabase();
		
		
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				MonHoc mh = new MonHoc();
				mh.setMaMonCTDT(cursor.getString(0));
				mh.setMaMonHoc(cursor.getString(1));
				mh.setTenMon(cursor.getString(2));
				//__danh sách môn tiên quyết
				String selectQuery2 = 
						"select " + ColumnName.MonTIENQUYET_MA_MON_CTDT_TIEN_QUYET + "," + ColumnName.CTDTmon_MA_MON_HOC + "," + ColumnName.MON_HOC_TEN_MON_HOC + " "
								+ "from " + ColumnName.CTDTmon_TABLE + "," + ColumnName.MON_HOC_TABLE + "," + ColumnName.MonTIENQUYET_TABLE +" "
								+ "where " + ColumnName.CTDTmon_MA_MON_HOC + "=" + ColumnName.MON_HOC_MA_MON_HOC
										+ " and " + ColumnName.MonTIENQUYET_MA_MON_CTDT_TIEN_QUYET + "=" + ColumnName.CTDTmon_TABLE + "." + ColumnName.CTDTmon_MA_MON_CTDT
										+ " and " + ColumnName.MonTIENQUYET_TABLE + "." + ColumnName.MonTIENQUYET_MA_MON_CTDT + "= '" + cursor.getString(0) + "'";
				Cursor cursor2 = database.rawQuery(selectQuery2, null);
				ArrayList<MonHocTienQuyet> arrMonTienQuyet = new ArrayList<MonHocTienQuyet>();
				if(cursor2.moveToFirst()){
					do {
						MonHocTienQuyet mtq = new MonHocTienQuyet();
						mtq.setMaMonCTDT_MHTQ(cursor2.getString(0));
						mtq.setMaMonHoc_MHTQ(cursor2.getString(1));
						mtq.setTenMon_MHTQ(cursor2.getString(2));
						arrMonTienQuyet.add(mtq);
					} while (cursor2.moveToNext());
				}
				//__/danh sách môn tiên quyết
				mh.setArrMonTienQuyet(arrMonTienQuyet);
				arrMonHoc.add(mh);
			} while (cursor.moveToNext());
		}
		return arrMonHoc;
	}
//___/tbl_MonHoc
	
//___tbl_DieuLeTag
	public ArrayList<DieuLeTag> getAllTag(){
		String arr[];
		ArrayList<DieuLeTag> arrTag = new ArrayList<DieuLeTag>();
		String selectQuery = "SELECT *"
				+ "FROM " + ColumnName.DIEULEtag_TABLE + " "
				+ "GROUP BY " + ColumnName.DIEULEtag_TAG; 
		database = mDbHelper.getReadableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				DieuLeTag dlt = new DieuLeTag();
				
				dlt.setTag(cursor.getString(1));
				dlt.setMaDieuLe(cursor.getString(2));
				dlt.setMucUuTien(cursor.getInt(3));
				
				arrTag.add(dlt);
			} while (cursor.moveToNext());
		}
		return arrTag;
	}
	public ArrayList<DieuLeTag> getTagTheoSearch(ArrayList<String> input){
		String para = "";
		if(input.size() > 0){
			para = " where ";
			for(int i = 0; i < input.size(); i++){
				if((i + 1) == input.size()){
					para = para + "Tag ='" + input.get(i).toString() + "' ";
				}else {
					para = para + "Tag ='" + input.get(i).toString() + "' or ";
				}
			}
		}
		ArrayList<DieuLeTag> arrTag = new ArrayList<DieuLeTag>();
		String selectQuery = "SELECT *"
				+ "FROM " + ColumnName.DIEULEtag_TABLE + " "
				+ para; 
		database = mDbHelper.getReadableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				DieuLeTag dlt = new DieuLeTag();
				
				dlt.setTag(cursor.getString(1));
				dlt.setMaDieuLe(cursor.getString(2));
				dlt.setMucUuTien(cursor.getInt(3));
				
				arrTag.add(dlt);
			} while (cursor.moveToNext());
		}
		return arrTag;
	}
//__/tbl_DieuLeTag
//___tbl_DieuLe
	public ArrayList<DieuLe> getAllDieuLe(){
		ArrayList<DieuLe> arrTag = new ArrayList<DieuLe>();
		String selectQuery = "SELECT *"
				+ "FROM " + ColumnName.DIEULE_TABLE + " ";
		database = mDbHelper.getReadableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				DieuLe dl = new DieuLe();
				
				dl.setTieuDe(cursor.getString(1));
				dl.setNoiDung(cursor.getString(2));
				dl.setMaChuong(cursor.getInt(4));
				
				arrTag.add(dl);
			} while (cursor.moveToNext());
		}
		return arrTag;
	}
	public ArrayList<DieuLe> resultSearchTag(ArrayList<String> arrDLT){
		ArrayList<DieuLe> arDL = new ArrayList<DieuLe>();
		if(arrDLT.size() > 0){
			for (int i = 0; i < arrDLT.size(); i++) {
				arDL.addAll(getDieuLeTheoTag(arrDLT.get(i).toString()));
			}
		}
		return arDL;
	}
	public ArrayList<DieuLe> getDieuLeTheoTag(String TagInput){
		ArrayList<DieuLe> arrTag = new ArrayList<DieuLe>();
		String selectQuery = "SELECT *"
				+ "FROM " + ColumnName.DIEULEtag_TABLE + "," + ColumnName.DIEULE_TABLE + " "
				+ "WHERE " + ColumnName.DIEULE_MA_DIEU_LE + "=" + ColumnName.DIEULEtag_MA_DIEU_LE + ""
							+ " and " + ColumnName.DIEULEtag_TAG + "= '" + TagInput + "'"
							+ " and " + ColumnName.DIEULEtag_MUC_UU_TIEN + "=1";
		database = mDbHelper.getReadableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				DieuLe dl = new DieuLe();
				
				dl.setTieuDe(cursor.getString(5));
				dl.setNoiDung(cursor.getString(6));
				dl.setMaChuong(cursor.getInt(8));
				
				arrTag.add(dl);
			} while (cursor.moveToNext());
		}
		return arrTag;
	}
	
//___/tbl_DieuLe
	
//___tbl_ThongBao
	public boolean insert_tbl_ThongBao_multi(ArrayList<ThongBao> listTB) {
		try {
			database = mDbHelper.getWritableDatabase();
			for (ThongBao tb : listTB) {
				ContentValues cv = new ContentValues();

				cv.put(ColumnName.THONG_BAO_MA_THONG_BAO, tb.getMaThongBao());
				cv.put(ColumnName.THONG_BAO_TIEU_DE, tb.getTieuDeThongBao());
				cv.put(ColumnName.THONG_BAO_NOI_DUNG, tb.getNoiDungThongBao());
				cv.put(ColumnName.THONG_BAO_NGAY_TAO_THONG_BAO, tb.getNgayTaoThongBao());
				cv.put(ColumnName.THONG_BAO_MA_GIAO_VIEN, tb.getMaGVGui());

				database.insert(ColumnName.THONG_BAO_TABLE, null, cv);
			}
			return true;
		} catch (SQLiteException e) {
			Log.e("insert_tbl_thongbao_multi", e.getMessage());
			return false;
		}
	}
	public ArrayList<ThongBao> getAllThongBaoSqLite(){
		ArrayList<ThongBao> arrThongBao = new ArrayList<ThongBao>();
		String selectQuery = "SELECT *"
				+ "FROM " + ColumnName.THONG_BAO_TABLE;
		database = mDbHelper.getReadableDatabase();
		Cursor cursor = database.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				ThongBao tb = new ThongBao();
				
				tb.setMaThongBao(cursor.getString(0));
				tb.setTieuDeThongBao(cursor.getString(1));
				tb.setNoiDungThongBao(cursor.getString(2));
				tb.setNgayTaoThongBao(cursor.getString(3));
				tb.setMaGVGui(cursor.getString(4));
				
				arrThongBao.add(tb);
			} while (cursor.moveToNext());
		}
		return arrThongBao;
	}
//___/tbl_ThongBao
}
