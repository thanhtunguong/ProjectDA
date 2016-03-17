package com.doan.database_handle;

import java.io.IOException;
import java.util.ArrayList;
import com.doan.model.SinhVien;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
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
	
//___/tbl_SinhVien
	
}
