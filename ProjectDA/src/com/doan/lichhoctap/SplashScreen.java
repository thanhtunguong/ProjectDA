package com.doan.lichhoctap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

public class SplashScreen extends Activity {
	private ProgressBar secondBar = null;
	private ProgressDialog progressBar;
	private int i = 0;
	private final int SPLASH_DISPLAY_LENGTH = 2000;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_splash_screen);
		secondBar = (ProgressBar) findViewById(R.id.secondBar);
		secondBar.setVisibility(View.VISIBLE);
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(SplashScreen.this, HocTapActivity.class);
				startActivity(intent);
			}
		}, SPLASH_DISPLAY_LENGTH);
		}
		/*if (i == 0 || i == 10) {
		    //make the progress bar visible
		    firstBar.setVisibility(View.VISIBLE);
		    firstBar.setMax(150);
		    secondBar.setVisibility(View.VISIBLE);
		   }else if ( i< firstBar.getMax() ) {
		    //Set first progress bar value
		    firstBar.setProgress(i);
		    //Set the second progress bar value
		    firstBar.setSecondaryProgress(i + 10);
		   }else {
		    firstBar.setProgress(0);
		    firstBar.setSecondaryProgress(0);
		    i = 0;
		    firstBar.setVisibility(View.GONE);
		    secondBar.setVisibility(View.GONE);
		   }
		   i = i + 10;
		  }*/
	}

	/*class SplashThread extends Thread{
        
        @Override
        public void run(){
            // xử lý công việc cần hoàn thành
            for(int i =0; i< 30; i++){
                // Tạm dừng 1s, thực tế thì chỗ này là xử lý công việc
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
                 
                // tính xem công việc đã hoàn thành bao nhiêu phần trăm và đưa lên progressbar
                firstBar.setProgress((i * 100) / 30);
            }
            // đóng brogressbar.
            firstBar.destroyDrawingCache();
        }
    }*/
