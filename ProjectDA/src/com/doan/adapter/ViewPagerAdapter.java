package com.doan.adapter;

import com.doan.fragment.DiemFragment;
import com.doan.fragment.LichFragment;
import com.doan.fragment.OthersFragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

	CharSequence Titles[]; // This will Store the Titles of the Tabs which are
							// Going to be passed when ViewPagerAdapter is
							// created
	int NumbOfTabs; // Store the number of tabs, this will also be passed when
					// the ViewPagerAdapter is created

	// Build a Constructor and assign the passed Values to appropriate values in
	// the class
	public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb) {
		super(fm);

		this.Titles = mTitles;
		this.NumbOfTabs = mNumbOfTabsumb;

	}

	// This method return the fragment for the every position in the View Pager
	@Override
	public Fragment getItem(int position) {

		if (position == 0) // if the position is 0 we are returning the First
							// tab
		{
			LichFragment tab1 = new LichFragment();
			return tab1;
		} else{
			if(position == 1){
				DiemFragment tab2 = new DiemFragment();
				return tab2;
			}else {
				OthersFragment tab3 = new OthersFragment();
				return tab3;
			}
		}
	}

	// This method return the titles for the Tabs in the Tab Strip

	@Override
	public CharSequence getPageTitle(int position) {
		return Titles[position];
	}

	// This method return the Number of tabs for the tabs Strip

	@Override
	public int getCount() {
		return NumbOfTabs;
	}
}