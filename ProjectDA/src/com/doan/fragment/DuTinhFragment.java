package com.doan.fragment;

import java.text.DecimalFormat;

import com.doan.lichhoctap.R;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;

public class DuTinhFragment extends Fragment {

	TextView tvSotinchitichluy, tvDiemtbtichluy, tvDiemtbtichluycandat;
	TextView tv1, tv2, tv3, tv4, tv5;
	EditText edtSotinchitichluysehoc;
	SeekBar sbSotinchitichluysehoc;
	Spinner spDiemtichluymongmuon;
	Button btnTinh;
	private float diemtbtichluy, diemTBmongmuon;
	private int soTinchitichluy, soTinchisehoc;
	private String arraydiemTBmongmuon[] = { "   7   ", "   6.5   ", "   8   ",
			"   9  " };
	private float diemcanduoi = (float) 5.00;
	private float diemcantren = (float) 10.00;

	private Context c;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_du_tinh_hoc_tap, container,
				false);
		c = getActivity();
		getInfo();
		init(v);
		diemTBmongmuon = (float) 7.0;
		sbSotinchitichluysehoc
				.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {

					public void onStopTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub
						edtSotinchitichluysehoc.setText(soTinchisehoc + "");
					}

					public void onStartTrackingTouch(SeekBar seekBar) {
						// TODO Auto-generated method stub

					}

					public void onProgressChanged(SeekBar seekBar,
							int progress, boolean fromUser) {
						// TODO Auto-generated method stub
						soTinchisehoc = progress + 120;
					}
				});
		edtSotinchitichluysehoc.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1,
					int arg2, int arg3) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				// Toast.makeText(getApplicationContext(), s,
				// Toast.LENGTH_SHORT).show();
				String chu = s.toString();
				if (chu.equals("")) {

					// sbSotinchitichluysehoc.setProgress(0);
					edtSotinchitichluysehoc.setText(120 + "");
				} else {

					// String chu = s.toString();
					int so = Integer.parseInt(chu);
					if (so < 120) {
						so = 120;
					}
					if (so > 140) {
						so = 140;
						edtSotinchitichluysehoc.setText(140 + "");
					}
					sbSotinchitichluysehoc.setProgress(so - 120);
				}

			}
		});
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),R.layout.spin1, arraydiemTBmongmuon);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
		spDiemtichluymongmuon.setAdapter(adapter);

		spDiemtichluymongmuon
				.setOnItemSelectedListener(new OnItemSelectedListener() {

					@Override
					public void onItemSelected(AdapterView<?> arg0, View arg1,
							int position, long arg3) {
						// TODO Auto-generated method stub
						if (position == 0) {

						}
						switch (position) {
						case 0:
							diemTBmongmuon = (float) 7.0;

							break;
						case 1:
							diemTBmongmuon = (float) 6.5;

							break;
						case 2:
							diemTBmongmuon = (float) 8.0;

							break;
						case 3:
							diemTBmongmuon = (float) 9.0;

							break;
						default:
							break;
						}

					}

					@Override
					public void onNothingSelected(AdapterView<?> arg0) {
						// TODO Auto-generated method stub

					}
				});
		btnTinh.setOnClickListener(new OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub
				int sotinchimongmuon = sbSotinchitichluysehoc.getProgress() + 120;
				float diemtbcandat = tinhDiem(diemtbtichluy, diemTBmongmuon,
						soTinchitichluy, sotinchimongmuon);
				DecimalFormat df = new DecimalFormat("#.00");
				if (diemtbcandat < diemcanduoi) {
					tvDiemtbtichluycandat.setText(df.format(diemcanduoi) + "");
				} else {
					tvDiemtbtichluycandat.setText(df.format(diemtbcandat) + "");
				}

				if (diemtbcandat > diemcantren) {
					tv5.setText(getResources().getString(
							R.string.tv5_tbKetquafalse));
				} else {
					tv5.setText(getResources().getString(
							R.string.tv5_tbKetquasusses));
				}

			}

			private float tinhDiem(float diemtbtichluy, float diemttbmongmuon,
					int sotinchitichluy, int sotinchimongmuon) {
				float result;
				result = diemttbmongmuon
						- (((diemtbtichluy - diemttbmongmuon) * sotinchitichluy) / (sotinchimongmuon - sotinchitichluy));
				return result;
			}
		});
		return v;
	}

	private void init(View v) {
		// TODO Auto-generated method stub
		tvSotinchitichluy = (TextView) v.findViewById(R.id.tvSotinchitichluy);
		tvDiemtbtichluy = (TextView) v.findViewById(R.id.tvDiemtbtichluy);
		edtSotinchitichluysehoc = (EditText) v
				.findViewById(R.id.edtSotinchitichluysehoc);
		sbSotinchitichluysehoc = (SeekBar) v
				.findViewById(R.id.sbSotinchitichluysehoc);
		tvDiemtbtichluycandat = (TextView) v
				.findViewById(R.id.tvDiemtbtichluycandat);
		spDiemtichluymongmuon = (Spinner) v
				.findViewById(R.id.spDiemtichluymongmuon);
		tv5 = (TextView) v.findViewById(R.id.tv5);
		btnTinh = (Button) v.findViewById(R.id.btnTinh);

		tvSotinchitichluy.setText(soTinchitichluy + "");
		tvDiemtbtichluy.setText(diemtbtichluy + "");
		edtSotinchitichluysehoc.setText(120 + "");
		sbSotinchitichluysehoc.setProgress(0);
		tvDiemtbtichluycandat.setText(diemtbtichluy + "");

	}

	private void getInfo() {
		// TODO Auto-generated method stub
		soTinchitichluy = 112;
		diemtbtichluy = (float) 6.92;
	}
}
