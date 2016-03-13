package com.doan.app;

import com.doan.lichhoctap.R;

import android.content.Context;

public class Global {
	public static String activityTitles[];
	
	public static void addActivityTitles(Context c){
		activityTitles = new String[3];
		activityTitles[0] = c.getString(R.string.title_activity_for_selector_Lich);
        activityTitles[1] = c.getString(R.string.title_activity_for_selector_NF);
        activityTitles[2] = c.getString(R.string.title_activity_for_selector_General);
	}
}
