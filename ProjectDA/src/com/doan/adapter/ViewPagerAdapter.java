package com.doan.adapter;

import com.doan.fragment.DiemFragment;
import com.doan.fragment.LichFragment;
import com.doan.fragment.OthersFragment;
import com.doan.lichhoctap.HocTapActivity;
import com.doan.lichhoctap.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
	
	private Context context;
	CharSequence Titles[]; // This will Store the Titles of the Tabs which are
							// Going to be passed when ViewPagerAdapter is
							// created
	int NumbOfTabs; // Store the number of tabs, this will also be passed when
					// the ViewPagerAdapter is created
	private int[] imageResId = {R.color.selector_calendar, R.color.selector_nf, R.color.selector_menu};
	// Build a Constructor and assign the passed Values to appropriate values in
	// the class
	public ViewPagerAdapter(FragmentManager fm, CharSequence mTitles[], int mNumbOfTabsumb, Context c) {
		super(fm);
		this.context = c;
		this.Titles = mTitles;
		this.NumbOfTabs = mNumbOfTabsumb;

	}

	// This method return the fragment for the every position in the View Pager
	@Override
	public Fragment getItem(int position) {
		String title = "";
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
		Drawable image = context.getResources().getDrawable(imageResId[position]);
		image.setBounds(0, 0, 10, 10);
		SpannableString sb = new SpannableString(" ");
		ImageSpan imageSpan = new ImageSpan(image, ImageSpan.ALIGN_BOTTOM);
		sb.setSpan(imageSpan, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
		return sb;
		// return Titles[position];
	}
	/*@Override
	public CharSequence getPageTitle(int position) {
		return Titles[position];
	}*/

	// This method return the Number of tabs for the tabs Strip

	@Override
	public int getCount() {
		//return NumbOfTabs;
		return imageResId.length;
	}
	/*@Override
	public int getCount() {
		return NumbOfTabs;
	}*/
	public int getDrawableId(int position){
        return imageResId[position];
    }
	
}