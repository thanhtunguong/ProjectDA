package com.doan.lichhoctap;

import com.doan.adapter.PhanTichHocTapViewPagerAdapter;
import com.doan.database_handle.ExecuteQuery;
import com.doan.slydingtab.PhanTichHocTapSlidingTabLayout;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

public class PhanTichHocTapActivity extends ActionBarActivity {
	private Toolbar toolbar;
	private Context context;
	private ViewPager pager;
	private PhanTichHocTapViewPagerAdapter adapter;
    private PhanTichHocTapSlidingTabLayout tabs;
    private CharSequence Titles[];
    private CharSequence ActivityTitles[];
    int Numboftabs = 3;
    private ExecuteQuery exeQ;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_phan_tich_hoc_tap);
		toolbar = (Toolbar) findViewById(R.id.phan_tich_hoc_tap_activity_tool_bar);
        setSupportActionBar(toolbar);
        
        context = this;
        exeQ = new ExecuteQuery(context);
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new PhanTichHocTapViewPagerAdapter(getSupportFragmentManager(),Numboftabs, context);
 
        // Assigning ViewPager View and setting the adapter
		pager = (ViewPager) findViewById(R.id.ptht_pager);
		pager.setAdapter(adapter);
        
 
        // Assiging the Sliding Tab Layout View
        tabs = (PhanTichHocTapSlidingTabLayout) findViewById(R.id.ptht_tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
 
        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new PhanTichHocTapSlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
 
        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
        
        
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.phan_tich_hoc_tap, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
