package com.doan.fragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;


import com.doan.adapter.DanhGiaKQHocTapChartAdapter;
import com.doan.adapter.DialogChiTietBoMonAdapter;
import com.doan.app.Global;
import com.doan.database_handle.ExecuteQuery;
import com.doan.lichhoctap.ChuongTrinhDaoTaoActivity;
import com.doan.lichhoctap.DanhSachMonCoTheDangKiActivity;
import com.doan.lichhoctap.GhiChuActivity;
import com.doan.lichhoctap.LoginActivity;
import com.doan.lichhoctap.PhanTichHocTapActivity;
import com.doan.lichhoctap.R;
import com.doan.lichhoctap.SoTayThongTinActivity;
import com.doan.lichhoctap.ThongTinCaNhanActivity;
import com.doan.model.BoMon;
import com.doan.model.BoMonBieuDo;
import com.doan.model.DiemHocTap;
import com.doan.model.MonHoc;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Paint.Align;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import android.widget.Toast;

public class DanhGiaKetQuaHocTapFragment extends Fragment {
	private ArrayList<MonHoc> arrAllMonHoc;
	private ArrayList<DiemHocTap> arlDiemQuaCaoNhat;
	private ArrayList<BoMon> arrBoMon;
	private ExecuteQuery exeQ;
	private Context context;
	private LinearLayout chart_container;
	private DanhGiaKQHocTapChartAdapter adapter;
	private ListView lvChart;
	private Point size;
    private Display display;
    private TextView tvGoiYDanhGiaHocTap;
	
	@Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.danh_gia_ket_qua_hoc_tap_fragment,container,false);
        context = getActivity();
        chart_container = (LinearLayout) v.findViewById(R.id.chart_container);
        lvChart = (ListView) v.findViewById(R.id.lvChart);
        tvGoiYDanhGiaHocTap = (TextView) v.findViewById(R.id.tvGoiYDanhGiaHocTap);
        
        loadDuLieu();
        createChart(v);
        lvChart.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				// TODO Auto-generated method stub
				dialogLogoutConfirm(getActivity(), arrBoMon.get(position));
			}
		});
        
        return v;
    }
	private void loadDuLieu(){
		//arrAllMonHoc = new ArrayList<>();
		arrBoMon = new ArrayList<BoMon>();
		exeQ = new ExecuteQuery(context);
		//arrAllMonHoc = exeQ.getAllMonHoc();
		arrBoMon = exeQ.getAllBoMon();
		getAllDiemQuaMonDiemQuaCaonhat();
		getDiemVaoBoMon();
	}
	private void getAllDiemQuaMonDiemQuaCaonhat() {
		arlDiemQuaCaoNhat = new ArrayList<DiemHocTap>();
		arlDiemQuaCaoNhat = exeQ.getDiemQuaMonSV(context);
		int x = arlDiemQuaCaoNhat.size();
		for (int i = 0; i < arlDiemQuaCaoNhat.size(); i++) {
			DiemHocTap diem1 = arlDiemQuaCaoNhat.get(i);
			for (int j = 0; j < arlDiemQuaCaoNhat.size(); j++) {
				DiemHocTap diem2 = arlDiemQuaCaoNhat.get(j);
				if (diem1.getMaDiem().matches(diem2.getMaDiem()) == false) {
					if (diem1.getTenMonHoc().matches(diem2.getTenMonHoc())) {
						if (diem1.getDiemTongKet() > diem2.getDiemTongKet()) {
							arlDiemQuaCaoNhat.remove(j);
						} else {
							if (diem1.getDiemTongKet() < diem2.getDiemTongKet()) {
								arlDiemQuaCaoNhat.remove(i);
							} else {
								//SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");
								SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
								Date date1 = null;
								Date date2 = null;
								String a = diem1.getThoiGianDK();
								String b = diem2.getThoiGianDK();
								try {
									date1 = df.parse(diem1.getThoiGianDK());
									date2 = df.parse(diem2.getThoiGianDK());
								} catch (Exception e) {
									// TODO: handle exception
								}
								if (date1.after(date2)) {
									arlDiemQuaCaoNhat.remove(j);
								} else {
									if (date1.before(date2)) {
										arlDiemQuaCaoNhat.remove(i);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	private void getDiemVaoBoMon(){
		for (BoMon boMon : arrBoMon) {
			for (MonHoc monhoc : boMon.getArrMonThuocBoMon()) {
				for (DiemHocTap diemHocTap : arlDiemQuaCaoNhat) {
					if(diemHocTap.getTenMonHoc().matches(monhoc.getTenMon())){
						monhoc.setDiemTrungBinhSV(diemHocTap.getDiemTongKet());
						break;
					}
				}
			}
		}
	}
	private void createChart(View v){
		//LinearLayout.LayoutParams lp = new LayoutParams(chart_container.getLayoutParams());
		display = getActivity().getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        int widthScreen = ((size.x)/10)*9;
		ArrayList<Float> arrDoRong = new ArrayList<Float>();
		BoMon bmMax = arrBoMon.get(0);
		for (BoMon bm : arrBoMon) {
			float width = (float)(bm.getDiemKiNangBoMon()/40)*widthScreen;
			if(bm.getDiemKiNangBoMon() > bmMax.getDiemKiNangBoMon()){
				bmMax = bm;
			}
			arrDoRong.add(width);
		}
		setTextGoiy(bmMax.getTenBoMon());
		ArrayList<Integer> arrMau = new ArrayList<Integer>();
		arrMau.add(R.color.bomon_CoBan);
		arrMau.add(R.color.bomon_HTTT);
		arrMau.add(R.color.bomon_Khac);
		arrMau.add(R.color.bomon_laptrinh);
		arrMau.add(R.color.bomon_MangBaoMat);
		arrMau.add(R.color.bomon_NgoaiNgu);
		arrMau.add(R.color.bomon_PhanCung);
		arrMau.add(R.color.bomon_Toan);
		arrMau.add(R.color.bomon_DoHoa);
		ArrayList<Integer> arrIcon = new ArrayList<Integer>();
		arrIcon.add(R.drawable.ic_co_ban);
		arrIcon.add(R.drawable.ic_httt);
		arrIcon.add(R.drawable.ic_khac);
		arrIcon.add(R.drawable.ic_laptrinh);
		arrIcon.add(R.drawable.ic_mang_truyenthong);
		arrIcon.add(R.drawable.ic_ngoai_ngu);
		arrIcon.add(R.drawable.ic_phan_cung);
		arrIcon.add(R.drawable.ic_math);
		arrIcon.add(R.drawable.ic_do_hoa);
		
		ArrayList<BoMonBieuDo> arrBieuDo = new ArrayList<BoMonBieuDo>();
		for(int i = 0; i < arrDoRong.size(); i++){
			BoMonBieuDo bmbd = new BoMonBieuDo();
			bmbd.setTenBoMon(arrBoMon.get(i).getTenBoMon());
			bmbd.setMaBoMon(arrBoMon.get(i).getMaBoMon());
			bmbd.setDoRong(arrDoRong.get(i));
			bmbd.setMau(arrMau.get(i));
			bmbd.setIcon(arrIcon.get(i));
			arrBieuDo.add(bmbd);
		}
		adapter = new DanhGiaKQHocTapChartAdapter(getActivity(), arrBieuDo, R.layout.bo_mon_item);
		lvChart.setAdapter(adapter);
    }
	public void dialogLogoutConfirm(final Activity context, BoMon bm){
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.dialog_chi_tiet_bo_mon);
		ListView lv = (ListView) dialog.findViewById(R.id.lvChiTietBoMonList);
		TextView tvChiTietBoMonTenBoMon = (TextView) dialog.findViewById(R.id.tvChiTietBoMonTenBoMon);
		tvChiTietBoMonTenBoMon.setText(getString(R.string.string_chitietKiNangTitle1) + " '" + bm.getTenBoMon() + "' " + getString(R.string.string_chitietKiNangTitle2));
		DialogChiTietBoMonAdapter dialogAdapter = new DialogChiTietBoMonAdapter(context, bm.getArrMonThuocBoMon(), R.layout.dialog_chi_tiet_bo_mon_item);
		lv.setAdapter(dialogAdapter);
		dialog.show();
	}
	private void setTextGoiy(String tenBoMon){
		String tenSV = Global.getStringPreference(context, "HoTenSV", "");
		tvGoiYDanhGiaHocTap.setText(tenSV + ", " + getString(R.string.string_goiY1) + " " + tenBoMon + "!");
	}
}

