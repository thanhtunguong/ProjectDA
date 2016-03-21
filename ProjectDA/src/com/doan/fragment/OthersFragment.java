package com.doan.fragment;
import com.doan.lichhoctap.DanhSachMonCoTheDangKiActivity;
import com.doan.lichhoctap.DiemHocTapActivity;
import com.doan.lichhoctap.GhiChuActivity;
import com.doan.lichhoctap.R;
import com.doan.lichhoctap.ThongTinCaNhanActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


public class OthersFragment extends Fragment {

	LinearLayout otherUser, otherUserMark, otherUserCTDT, otherUserDSMCTDK, otherUserInfoSearch, otherUserNotes;
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
				Intent i = new Intent(getContext(), DiemHocTapActivity.class);
				startActivity(i);
			}
		});
        
        otherUserCTDT.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(c, "CTDT", Toast.LENGTH_SHORT).show();
			}
		});
        
        otherUserDSMCTDK.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(c, "DSMDK", Toast.LENGTH_SHORT).show();
				Intent i = new Intent(getContext(), DanhSachMonCoTheDangKiActivity.class);
				startActivity(i);
			}
		});
        
        otherUserInfoSearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(c, "InfoSearch", Toast.LENGTH_SHORT).show();
				
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
        
        return v;
    }
    public void setTitleActivity(int titleID){
    	/*toolbar = (Toolbar) findViewById(R.id.tool_bar);            
    	setSupportActionBar(toolbar);*/
		((ActionBarActivity)c).getSupportActionBar().setTitle(titleID);
    	//getSupportActionBar().setTitle(getString(titleID));
    }
}