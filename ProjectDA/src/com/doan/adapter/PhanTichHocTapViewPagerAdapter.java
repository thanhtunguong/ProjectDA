package com.doan.adapter;

import com.doan.fragment.BaiVietFragment;
import com.doan.fragment.DanhGiaKetQuaHocTapFragment;
import com.doan.fragment.DiemFragment;
import com.doan.fragment.DuTinhFragment;
import com.doan.fragment.LichFragment;
import com.doan.fragment.OthersFragment;
import com.doan.lichhoctap.R;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBarActivity;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;

public class PhanTichHocTapViewPagerAdapter extends FragmentStatePagerAdapter {
	
	private Context context;
	//CharSequence Titles[]; // This will Store the Titles of the Tabs which are
							// Going to be passed when ViewPagerAdapter is
							// created
	int NumbOfTabs; // Store the number of tabs, this will also be passed when
					// the ViewPagerAdapter is created
	private int[] imageResId = {R.color.selector_ketqua, R.color.selector_danhgia, R.color.selector_du_tinh};
	// Build a Constructor and assign the passed Values to appropriate values in
	// the class
	public PhanTichHocTapViewPagerAdapter(FragmentManager fm, int mNumbOfTabsumb, Context c) {
		super(fm);
		this.context = c;
		this.NumbOfTabs = mNumbOfTabsumb;

	}

	// This method return the fragment for the every position in the View Pager
	@Override
	public Fragment getItem(int position) {
		/*String title = "";
		((ActionBarActivity)context).getSupportActionBar().setTitle(R.string.title_activity_for_selector_Lich);*/
		Fragment tab = null;
		switch(position){
		case 0:
			tab = new DiemFragment();
			break;
		case 1:
			tab = new DanhGiaKetQuaHocTapFragment();
			break;
		case 2:
			tab = new DuTinhFragment();
			break;
		}
		return tab;
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
	public void setTitleActivity(int titleID){
		((ActionBarActivity)context).getSupportActionBar().setTitle(titleID);
    }
}