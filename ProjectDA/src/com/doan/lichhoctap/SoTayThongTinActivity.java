package com.doan.lichhoctap;

import java.util.ArrayList;
import java.util.List;

import com.doan.adapter.DieuLeAdapter;
import com.doan.adapter.SearchAutoCompleteAdapter;
import com.doan.app.Global;
import com.doan.database_handle.ExecuteQuery;
import com.doan.model.DieuLe;
import com.doan.model.DieuLeTag;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class SoTayThongTinActivity extends ActionBarActivity {
	
	private List<DieuLeTag> arrAllTag;
	private ArrayList<DieuLe> arrDieuLe;
	private ArrayList<String> parentItems;
	private ArrayList<Object> childItems;
	private ExecuteQuery exeQ;
	private SearchAutoCompleteAdapter SearchAdapter;
	private AutoCompleteTextView autoSearchTv;
	private DieuLeAdapter dlAdapter;
	private Activity c;
	private int check;//kiểm tra đã dùng search hay chưa

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_so_tay_thong_tin);
		Toolbar toolbar = (Toolbar) findViewById(R.id.SoTay_activity_tool_bar);
		setSupportActionBar(toolbar);
		if (getSupportActionBar() != null) {
			getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		}
		Global g = new Global();
		c = this;
        g.addActivityTitles(c);
        getSupportActionBar().setTitle(null);
        final AutoCompleteTextView mTitle = (AutoCompleteTextView) toolbar.findViewById(R.id.autocpltSearchDieuLe);
        final Button btnClear = (Button) toolbar.findViewById(R.id.btnSoTaySearchClear);
        final TextView tvTag = (TextView) findViewById(R.id.selection);
        //TextView tvContents = (TextView) findViewById(R.id.tvContents);
        final ExpandableListView lvDieuLe = (ExpandableListView) findViewById(R.id.elvAllDieuLe);
        check = 0; 
        
        btnClear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mTitle.setText("");
				if(check == 1){
					batdau(lvDieuLe);
					check = 0;
				}
				btnClear.setVisibility(View.GONE);
			}
		});
        
        mTitle.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
		mTitle.setOnEditorActionListener(new OnEditorActionListener() {

			@Override
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				// TODO Auto-generated method stub
				if (actionId == EditorInfo.IME_ACTION_SEARCH) {
					// Your piece of code on keyboard search click
					//Toast.makeText(c, mTitle.getText().toString(), Toast.LENGTH_SHORT).show();
					setList(searchTag(mTitle.getText().toString()));
					lvDieuLe.setAdapter(dlAdapter);
					dlAdapter.notifyDataSetChanged();
					check = 1;
					return true;
				}
				return false;
			}
		});
        mTitle.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				// TODO Auto-generated method stub
				tvTag.setText(mTitle.getText().toString());
				if(tvTag.getText().toString().matches("")){
					btnClear.setVisibility(View.GONE);
				}else {
					btnClear.setVisibility(View.VISIBLE);
				}
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count, int after) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				
			}
		});
        
        exeQ = new ExecuteQuery(c);
        batdau(lvDieuLe);
        /*arrAllTag = exeQ.getAllTag();
        arrDieuLe = exeQ.getAllDieuLe();
        setList(arrDieuLe);
        lvDieuLe.setGroupIndicator(null);
        lvDieuLe.setClickable(true);
        lvDieuLe.setAdapter(dlAdapter);*/

        SearchAdapter = new SearchAutoCompleteAdapter(c, R.layout.tag_item, arrAllTag);
        //ArrayAdapter<String> adapter = new ArrayAdapter<>(c, android.R.layout.simple_list_item_1, arr);
        mTitle.setAdapter(SearchAdapter);
	}
	private void batdau(ExpandableListView lvDieuLe){
		arrAllTag = exeQ.getAllTag();
        arrDieuLe = exeQ.getAllDieuLe();
        setList(arrDieuLe);
        lvDieuLe.setGroupIndicator(null);
        lvDieuLe.setClickable(true);
        lvDieuLe.setAdapter(dlAdapter);
	}
	private ArrayList<DieuLe> searchTag(String input){
		ArrayList<DieuLe> arrDL = new ArrayList<DieuLe>();
		arrDL = exeQ.resultSearchTag(searchDieuLeTagTuongDoi(input));
		return arrDL;
	}
	private ArrayList<String> searchDieuLeTagTuongDoi(String input){
		String tu[] = input.split(" ");
		ArrayList<String> result = new ArrayList<String>();
		for(int i = 0; i < tu.length; i++){
			for(int j = (i + 1); j < tu.length; j++){
				for (DieuLeTag dlt : arrAllTag) {
					
					String tam = tu[i] + " " + tu[j];
					if(tam.matches(dlt.getTag())){
						result.add(tam);
					}else {
						while((j + 1) < tu.length){
							j = j+1;
							tam = tam + " " + tu[j];
							if(tam.matches(dlt.getTag())){
								result.add(tam);
							}
						}
					}
				}
			}
		}
		return result;
	}
	private void setList(ArrayList<DieuLe> arrDL){
		parentItems = new ArrayList<String>();
		childItems = new ArrayList<Object>();
		for (DieuLe dl : arrDL) {
			parentItems.add(dl.getTieuDe());
			ArrayList<String> arStr = new ArrayList<String>();
			arStr.add(dl.getNoiDung());
			childItems.add(arStr);
		}
		dlAdapter = new DieuLeAdapter(parentItems, childItems, c);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.so_tay_thong_tin, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		switch (item.getItemId()) {
		case android.R.id.home:
			onBackPressed();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
