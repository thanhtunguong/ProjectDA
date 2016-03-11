package com.doan.lichhoctap;

import com.doan.adapter.ViewPagerAdapter;
import com.doan.slydingtab.SlidingTabLayout;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.FrameLayout;
import android.widget.Toast;

public class HocTapActivity extends ActionBarActivity {
 
    // Declaring Your View and Variables
 
    Toolbar toolbar;
    ViewPager pager;
    ViewPagerAdapter adapter;
    SlidingTabLayout tabs;
    CharSequence Titles[];
    int Numboftabs = 3;
    FrameLayout flFloatBtn;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hoc_tap_activity);
        //CharSequence tab1 = getText(R.string.string_title_tab_lich);
        //CharSequence tab2 = getText(R.string.string_title_tab_diem);
        // Creating The Toolbar and setting it as the Toolbar for the activity
        Titles = new String[3];
        Titles[0] = getString(R.string.string_title_tab_lich);
        Titles[1] = getString(R.string.string_title_tab_diem);
        Titles[2] = getString(R.string.string_title_tab_khac);
        
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        setSupportActionBar(toolbar);
 
        context = this;
        // Creating The ViewPagerAdapter and Passing Fragment Manager, Titles fot the Tabs and Number Of Tabs.
        adapter =  new ViewPagerAdapter(getSupportFragmentManager(),Titles,Numboftabs, context);
 
        // Assigning ViewPager View and setting the adapter
        pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
 
        // Assiging the Sliding Tab Layout View
        tabs = (SlidingTabLayout) findViewById(R.id.tabs);
        tabs.setDistributeEvenly(true); // To make the Tabs Fixed set this true, This makes the tabs Space Evenly in Available width
 
        // Setting Custom Color for the Scroll bar indicator of the Tab View
        tabs.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
            @Override
            public int getIndicatorColor(int position) {
                return getResources().getColor(R.color.tabsScrollColor);
            }
        });
 
        // Setting the ViewPager For the SlidingTabsLayout
        tabs.setViewPager(pager);
        final Context c = this.getApplicationContext();
        flFloatBtn = (FrameLayout) findViewById(R.id.flFloatBtn);
		//flFloatBtn.setBackgroundResource(R.drawable.tiet_hoc_circle_background);
		flFloatBtn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(c, "Notes", Toast.LENGTH_SHORT).show();
			}
		});
 
    }
    
 
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
 
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
 
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
 
        return super.onOptionsItemSelected(item);
    }
}