package com.doan.fragment;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.doan.adapter.HocTapDiemAdapter;
import com.doan.app.Global;
import com.doan.database_handle.ExecuteQuery;
import com.doan.lichhoctap.R;
import com.doan.model.DiemHocTap;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Display;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnScrollChangeListener;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class DiemFragment extends Fragment {
	private Toolbar toolbar;
	private ArrayList<DiemHocTap> arlDiemQuaCaoNhat = new ArrayList<DiemHocTap>();
	private ArrayList<DiemHocTap> arlDiemAll = new ArrayList<DiemHocTap>();
	private ArrayList<DiemHocTap> arlDiemQuaLanMot = new ArrayList<DiemHocTap>();
	private int TC = 0;
	private float DTB = 0;
    private double tongDiem = 0;
    private Context c;
    private ExecuteQuery exeQ;
    private LinearLayout.LayoutParams lp1;
    private int biencheck;
    private LinearLayout lnDTB;
    private TextView tvDTBprogess, tvDTBpc, tvDTB;
    private int width;
    private ImageButton imgBtnNext, imgBtnPrev;
    private SwipeRefreshLayout swipeRefreshLayout;
    private TextView tvTC, tvSTCpc, tvSTCprogess;
    private LinearLayout lnSTC;
    private ListView lvDiem;
    private Point size;
    private Display display;
	
	@Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.fragment_ket_qua_hoc_tap,container,false);
		
		c = getActivity();
		String masv = Global.getStringPreference(c, "MaSVDN", "0");
		
        tvTC = (TextView) v.findViewById(R.id.tvSoTinChiDaHoc);
        tvTC.setText(getString(R.string.string_SoTinChiDaHoc));
        tvDTB = (TextView) v.findViewById(R.id.tvDiemTrungBinhTichLuy);
        tvDTB.setText(getString(R.string.string_DiemTrungBinhQuaCaoNhat));
        lnSTC = (LinearLayout) v.findViewById(R.id.lnSTC);
        tvSTCprogess = (TextView) v.findViewById(R.id.tvTCprogess);
        lnDTB = (LinearLayout) v.findViewById(R.id.lnDTB);
        tvSTCpc = (TextView) v.findViewById(R.id.tvTCpc);
        tvDTBprogess = (TextView) v.findViewById(R.id.tvDTBprogess);
        tvDTBpc = (TextView) v.findViewById(R.id.tvDTBpc);
        tvSTCpc = (TextView) v.findViewById(R.id.tvTCpc);
        lvDiem = (ListView) v.findViewById(R.id.lvDiem);
        imgBtnNext = (ImageButton) v.findViewById(R.id.btnDiemOptionNext);
    	imgBtnPrev = (ImageButton) v.findViewById(R.id.btnDiemOptionPrev);
        biencheck = 1;
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout_diem);
        swipeRefreshLayout.setRefreshing(false);
        
        display = getActivity().getWindowManager().getDefaultDisplay();
        size = new Point();
        display.getSize(size);
        
        exeQ = new ExecuteQuery(c); 
		//
        loadDuLieu();
        
        swipeRefreshLayout.setOnRefreshListener(new OnRefreshListener() {
			
			@Override
			public void onRefresh() {
				// TODO Auto-generated method stub
				exeQ.open();
				swipeRefreshLayout.setRefreshing(true);
				getDiem();
			}
		});
        
    	imgBtnPrev.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(biencheck > 1){
					biencheck = biencheck - 1;
				}else {
					biencheck = 3;
				}
				
				hienThiTinhDiem();
			}
		});
    	imgBtnNext.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				if(biencheck < 3){
					biencheck = biencheck + 1;
				}else {
					biencheck = 1;
				}
				
				hienThiTinhDiem();
			}
		});	
        
        return v;
    }
	private void loadDuLieu(){
		exeQ.open();
		arlDiemAll = exeQ.getDiemSV(c); //Điểm trung bình của các môn - trung bình học tập
		getAllDiemQuaMonDiemQuaCaonhat(); //điểm qua cao nhất - trung bình tích lũy
		getAllDiemQuaMonDiemQuaLanMot(); //điểm qua lần 1 - trung bình học kì
		exeQ.close();
        for (DiemHocTap diemHocTap : arlDiemQuaCaoNhat) {
        	double sdtb = ((diemHocTap.getDiemCC() + diemHocTap.getDiemKT()*2 + diemHocTap.getDiemThi()*7)/10)*1.0;
        	sdtb = Math.floor(sdtb*10)/10;
        	//trạng thái = 0 tức là môn đã có điểm
        	if(diemHocTap.getMaTrangThaiDK().matches("0")){
        		tinhTongKetHocTap(diemHocTap.getSoTinChi(), sdtb);
        	}
		}       
        width = size.x;
        int height = size.y;
        float width2 = (float)(width/1.5);
        width = Math.round(width2);
        lp1 = new LayoutParams(width, 12);
        lp1.gravity = Gravity.CENTER_VERTICAL;
        lnSTC.setLayoutParams(lp1); 
        hienThiTinhDiem();
    	float STCpc = TC/120;
    	int xSTC = 220;
    	float x = (TC*width)/140;
    	tvSTCprogess.setLayoutParams(new LayoutParams(Math.round(x), 12));
    	tvSTCpc.setText(x + " %");
    	//getResultHocTap(arlDiemQuaCaoNhat, lnDTB, tvDTBprogess, tvDTBpc);
    	
		tvSTCpc.setText(TC + "/140");
        HocTapDiemAdapter adapter = new HocTapDiemAdapter(getActivity() , R.layout.hoctap_diem_diemso_item_test, arlDiemAll);
        lvDiem.setAdapter(adapter);
        lvDiem.setOnScrollChangeListener(new OnScrollChangeListener() {
			
			@Override
			public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
				// TODO Auto-generated method stub
				boolean enable = false;
		        if(lvDiem != null && lvDiem.getChildCount() > 0){
		            // check if the first item of the list is visible
		            boolean firstItemVisible = lvDiem.getFirstVisiblePosition() == 0;
		            // check if the top of the first item is visible
		            boolean topOfFirstItemVisible = lvDiem.getChildAt(0).getTop() == 0;
		            // enabling or disabling the refresh layout
		            enable = firstItemVisible && topOfFirstItemVisible;
		        }
		        swipeRefreshLayout.setEnabled(enable);
			}
		});
	}
	private void hienThiTinhDiem(){
		/*if(biencheck == 1){
			imgBtnPrev.setVisibility(View.INVISIBLE);
			imgBtnPrev.setEnabled(false);
		}else {
			imgBtnPrev.setVisibility(View.VISIBLE);
			imgBtnPrev.setEnabled(true);
		}
		if(biencheck == 3){
			imgBtnNext.setVisibility(View.INVISIBLE);
			imgBtnNext.setEnabled(false);
		}else {
			imgBtnNext.setVisibility(View.VISIBLE);
			imgBtnNext.setEnabled(true);
		}*/
		switch (biencheck) {
		case 1:
			getResultHocTap(arlDiemQuaCaoNhat, lnDTB, tvDTBprogess, tvDTBpc);
			tvDTB.setText(getString(R.string.string_DiemTrungBinhQuaCaoNhat));
			break;
		case 2:
			getResultHocTap(arlDiemQuaLanMot, lnDTB, tvDTBprogess, tvDTBpc);
			tvDTB.setText(getString(R.string.string_DiemTrungBinhQuaLanMot));
			break;
		case 3:
			getResultHocTap(arlDiemAll, lnDTB, tvDTBprogess, tvDTBpc);
			tvDTB.setText(getString(R.string.string_DiemTrungBinhHocTap));
			break;
		default:
			break;
		}
	}
	private void getResultHocTap(ArrayList<DiemHocTap> arlDiem, LinearLayout lnDTB, TextView tvDTBprogess, TextView tvDTBpc){
		TC = 0;
		DTB = 0;
		tongDiem = 0;
		for (DiemHocTap diemHocTap : arlDiem) {
        	//double sdtb = ((diemHocTap.getDiemCC() + diemHocTap.getDiemKT()*2 + diemHocTap.getDiemThi()*7)/10)*1.0;
			double sdtb = diemHocTap.getDiemTongKet();
        	//sdtb = Math.floor(sdtb*10)/10;
        	//trạng thái = 0 tức là môn đã có điểm, 1 là đang học
        	if(diemHocTap.getMaTrangThaiDK().matches("0")){
        		tinhTongKetHocTap(diemHocTap.getSoTinChi(), sdtb);
        	}
		}
        lnDTB.setLayoutParams(new LayoutParams(width, 12));
    	lnDTB.setGravity(Gravity.CENTER_VERTICAL);
       
        lnDTB.setLayoutParams(lp1);
        DecimalFormat df = new DecimalFormat("#.00");
    	float DTBpc = DTB/10;
    	int xDTB = 220;
    	float y = (width*DTB)/10;
    	tvDTBprogess.setLayoutParams(new LayoutParams(Math.round(y), 12));	
    	//tvDTBpc.setText(DTB + "/10");
    	tvDTBpc.setText(df.format(DTB) + "/10");
	}

	private void tinhTongKetHocTap(int stc, double sdtb){
    	TC = TC + stc;
    	tongDiem = tongDiem +  sdtb*stc;
    	DTB = (float)(tongDiem/TC);
    	//DTB = (double) df.format(tongDiem/TC);
    	//DTB = (float)Math.floor(DTB*10)/10;    	
    }
	/*private void tinhTongKetHocTap2(int stc, double sdtb){
    	TC2 = TC2 + stc;
    	tongDiem2 = tongDiem2 +  sdtb*stc;
    	DTB2 = (float)(tongDiem2/TC2);
    	DTB2 = (float)Math.floor(DTB2*10)/10;    	
    }*/
	private void getAllDiemQuaMonDiemQuaCaonhat() {
		arlDiemQuaCaoNhat = new ArrayList<DiemHocTap>();
		arlDiemQuaCaoNhat = exeQ.getDiemQuaMonSV(c);
		int x = arlDiemQuaCaoNhat.size();
		for (int i = 0; i < arlDiemQuaCaoNhat.size(); i++) {
			DiemHocTap diem1 = arlDiemQuaCaoNhat.get(i);
			for (int j = 0; j < arlDiemQuaCaoNhat.size(); j++) {
				DiemHocTap diem2 = arlDiemQuaCaoNhat.get(j);
				if (diem1.getMaDiem().matches(diem2.getMaDiem()) == false) {
					if (diem1.getTenMonHoc().matches(diem2.getTenMonHoc())) {
						if (tinhDiemTrungBinhMon(diem1.getDiemCC(), diem1.getDiemKT(),
								diem1.getDiemThi()) > tinhDiemTrungBinhMon(diem2.getDiemCC(), diem2.getDiemKT(),
										diem2.getDiemThi())) {
							arlDiemQuaCaoNhat.remove(j);
						} else {
							if (tinhDiemTrungBinhMon(diem1.getDiemCC(), diem1.getDiemKT(),
									diem1.getDiemThi()) < tinhDiemTrungBinhMon(diem2.getDiemCC(), diem2.getDiemKT(),
											diem2.getDiemThi())) {
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
	private void getAllDiemQuaMonDiemQuaLanMot() {
		arlDiemQuaLanMot= new ArrayList<DiemHocTap>();
		arlDiemQuaLanMot = exeQ.getDiemQuaMonSV(c);
		int x = arlDiemQuaLanMot.size();
		for (int i = 0; i < arlDiemQuaLanMot.size(); i++) {
			DiemHocTap diem1 = arlDiemQuaLanMot.get(i);
			for (int j = 0; j < arlDiemQuaLanMot.size(); j++) {
				DiemHocTap diem2 = arlDiemQuaLanMot.get(j);
				if (diem1.getMaDiem().matches(diem2.getMaDiem()) == false) {
					if (diem1.getTenMonHoc().matches(diem2.getTenMonHoc())) {
						//
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
							arlDiemQuaLanMot.remove(j);
						} else {
							if (date1.before(date2)) {
								arlDiemQuaLanMot.remove(i);
							}
						}
					}
				}
			}
		}
	}
	private float tinhDiemTrungBinhMon(float d1, float d2, float d7){
		float dtb = (d1 + d2*2 + d7*7)/10;
		return dtb;
	}
	private void getDiem(){
		String masinhvien = Global.getStringPreference(c, "MaSVDN", "0");
		AsyncHttpClient client = new AsyncHttpClient();
		RequestParams params = new RequestParams();
		params.put("masinhvien", masinhvien);
		String url = Global.BASE_URI + Global.URI_DIEMTHEOMASV;
		client.post(url, params, new AsyncHttpResponseHandler() {
			public void onSuccess(String response) {
				Log.e("JsonDiem", response);
				if (executeWhenGetDiemSuccess(response)) {
					exeQ.close();
					loadDuLieu();
					swipeRefreshLayout.setRefreshing(false);
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
	
}
