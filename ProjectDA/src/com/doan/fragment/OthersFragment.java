package com.doan.fragment;
import com.doan.app.Global;
import com.doan.database_handle.ExecuteQuery;
import com.doan.lichhoctap.ChuongTrinhDaoTaoActivity;
import com.doan.lichhoctap.DanhSachMonCoTheDangKiActivity;
import com.doan.lichhoctap.DiemHocTapActivity;
import com.doan.lichhoctap.DoiMatKhauActivity;
import com.doan.lichhoctap.GhiChuActivity;
import com.doan.lichhoctap.LoginActivity;
import com.doan.lichhoctap.PhanTichHocTapActivity;
import com.doan.lichhoctap.R;
import com.doan.lichhoctap.SoTayThongTinActivity;
import com.doan.lichhoctap.ThongTinCaNhanActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;


public class OthersFragment extends Fragment {

	LinearLayout otherUser, otherUserMark, otherUserCTDT, 
	otherUserDSMCTDK, otherUserInfoSearch, otherUserNotes, otherLogout, otherChangePwd;
	Activity c;
	
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.others_layout_fragment,container,false);
        c = getActivity();
        //setTitleActivity(R.string.title_activity_for_selector_General);
        otherUser = (LinearLayout) v.findViewById(R.id.otherUser);
        otherUserMark = (LinearLayout) v.findViewById(R.id.otherUserMark);
        otherUserCTDT = (LinearLayout) v.findViewById(R.id.otherUserCTDT);
        otherUserDSMCTDK = (LinearLayout) v.findViewById(R.id.otherUserDSMCTDK);
        otherUserInfoSearch = (LinearLayout) v.findViewById(R.id.otherUserInfoSearch);
        otherUserNotes = (LinearLayout) v.findViewById(R.id.otherUserNotes);
        otherLogout = (LinearLayout) v.findViewById(R.id.otherLogout);
        otherChangePwd = (LinearLayout) v.findViewById(R.id.otherChangePwd);
        TextView otherUserName = (TextView) v.findViewById(R.id.otherUserName);
        otherUserName.setText(Global.getStringPreference(c, "HoTenSV", ""));
        
        otherUser.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(c, "User", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getContext(),ThongTinCaNhanActivity.class);
				startActivity(i);
			}
		});
        
        otherUserMark.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getContext(), PhanTichHocTapActivity.class);
				//Intent i = new Intent(getContext(), DiemHocTapActivity.class);
				startActivity(i);
			}
		});
        
        otherUserCTDT.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getContext(), ChuongTrinhDaoTaoActivity.class);
				startActivity(i);
			}
		});
        
        otherUserDSMCTDK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(c, "DSMDK", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getContext(), DanhSachMonCoTheDangKiActivity.class);
				startActivity(i);
			}
		});
        
        otherUserInfoSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//Toast.makeText(c, "InfoSearch", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getContext(), SoTayThongTinActivity.class);
				startActivity(i);
			}
		});
        
        otherUserNotes.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getContext(), GhiChuActivity.class);
				startActivity(i);
			}
		});
        
        otherLogout.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//dialogLogoutConfirm(c);
				Global g = new Global();
				g.dialogLogoutConfirm(c);
			}
		});
        otherChangePwd.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getContext(), DoiMatKhauActivity.class);
				startActivity(i);
			}
		});
        
        return v;
    }
    private void dialogLogoutConfirm(final Activity context){
		final Dialog dialog = new Dialog(context);
		dialog.setContentView(R.layout.dialog_logout);
		Button dialogButtonOK = (Button) dialog.findViewById(R.id.btnOKDialogLogout);
		dialogButtonOK.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				/*Global.saveStringPreference(c, "access_token", "");
				Global.saveStringPreference(c, "MaSVDN", "");
				Global.saveStringPreference(c, "MaGVDN", "");
				ExecuteQuery exeQ = new ExecuteQuery(c);
				exeQ.close();
				exeQ.deleteDB();
				dialog.dismiss();
				Intent i = new Intent(getContext(), LoginActivity.class);
				startActivity(i);*/
				Global g = new Global();
				g.DangXuat(c);
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
    public void setTitleActivity(int titleID){
    	/*toolbar = (Toolbar) findViewById(R.id.tool_bar);            
    	setSupportActionBar(toolbar);*/
		((ActionBarActivity)c).getSupportActionBar().setTitle(titleID);
    	//getSupportActionBar().setTitle(getString(titleID));
    }
}