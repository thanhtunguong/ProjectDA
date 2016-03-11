package com.doan.fragment;
import com.doan.lichhoctap.R;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;


public class OthersFragment extends Fragment {

	LinearLayout otherUser, otherUserMark, otherUserCTDT, otherUserDSMCTDK, otherUserInfoSearch, otherUserNotes;
	
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v =inflater.inflate(R.layout.others_layout_fragment,container,false);
        final Context c = this.getContext();
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
			}
		});
        
        otherUserMark.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(c, "Mark", Toast.LENGTH_SHORT).show();
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
				Toast.makeText(c, "Note", Toast.LENGTH_SHORT).show();
			}
		});
        
        return v;
    }
}