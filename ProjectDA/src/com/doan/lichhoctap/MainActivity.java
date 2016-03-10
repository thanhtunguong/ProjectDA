package com.doan.lichhoctap;

import java.io.File;
import com.doan.fragment.DiemFragment;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import it.neokree.materialnavigationdrawer.MaterialNavigationDrawer;
import it.neokree.materialnavigationdrawer.elements.MaterialAccount;
import it.neokree.materialnavigationdrawer.elements.MaterialSection;
import it.neokree.materialnavigationdrawer.elements.listeners.MaterialAccountListener;

public class MainActivity extends MaterialNavigationDrawer implements MaterialAccountListener {

	private MaterialAccount account;
	private MaterialSection<Fragment> mnuInfo, mnuMyMap, mnuMyTrip, mnuDiemPhuot, mnuLogout, mnuAbout, mnuLstTrip,
			mnuSetting;
	Bitmap b;

	private File avaFile;

	public MaterialAccount getAccount() {
		return account;
	}

	public void setAccount(MaterialAccount account) {
		this.account = account;
	}

	@Override
	public void init(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		String fullname = "ThanhTungTuong";
		setAccount(new MaterialAccount(this.getResources(), fullname.toUpperCase(), "", R.drawable.ic_launcher,
				R.drawable.circle_active_icon));
		account = new MaterialAccount(this.getResources(), fullname.toUpperCase(), "", b, R.drawable.abc_action_bar_item_background_material);
		this.addAccount(account);
		this.disableLearningPattern();

		mnuInfo = newSection("Diem", R.drawable.ic_launcher, new DiemFragment());
		this.addSection(mnuInfo);
	}

	@Override
	public void onAccountOpening(MaterialAccount account) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onChangeAccount(MaterialAccount newAccount) {
		// TODO Auto-generated method stub

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
		/*if (id == android.R.id.action_settings) {
			onBackPressed();
		}*/
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		// TODO Auto-generated method stub
		super.onBackPressed();
		finishAffinity();
	}
}
