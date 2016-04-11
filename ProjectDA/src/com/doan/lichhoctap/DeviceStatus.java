package com.doan.lichhoctap;

import java.net.InetAddress;
import java.net.UnknownHostException;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.BatteryManager;
import android.telephony.TelephonyManager;

/*
 * Created by: Uong Thanh Tung
 * connection status, battery, ...
 */

public class DeviceStatus {
	public int getApiLevel() {
		int apiLevel = android.os.Build.VERSION.SDK_INT;
		return apiLevel;
	}

	public String getDeviceCodeName() {
		String deviceCodeName = android.os.Build.DEVICE;
		return deviceCodeName;
	}

	public String getDeviceName() {
		String deviceName = android.os.Build.MODEL;
		return deviceName;
	}

	public String getDeviceProduct() {
		String deviceProduct = android.os.Build.PRODUCT;
		return deviceProduct;
	}

	public String getSimNumber(Context c) {
		TelephonyManager telemamanger = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
		String getSimNumber = telemamanger.getLine1Number();
		return getSimNumber;
	}

	public String getSimSerial(Context c) {
		TelephonyManager telemamanger = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
		String getSimSerialNumber = telemamanger.getSimSerialNumber();
		return getSimSerialNumber;
	}

	public String getEMEI(Context c) {
		TelephonyManager telemamanger = (TelephonyManager) c.getSystemService(Context.TELEPHONY_SERVICE);
		String getImei = telemamanger.getDeviceId();
		return getImei;
	}

	public int getBatteryLevel(Context c) {
		Intent batteryIntent = c.getApplicationContext().registerReceiver(null,
				new IntentFilter(Intent.ACTION_BATTERY_CHANGED));
		int batLevel = batteryIntent.getIntExtra(BatteryManager.EXTRA_LEVEL, 0);
		return batLevel;
	}

	public String getCurrentConnection(Context c) {
		String Stt = "";
		ConnectivityManager cm = (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);

		NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
		if (wifiNetwork != null && wifiNetwork.isConnected()) {
			Stt += "Wifi";

		}

		NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
		if (mobileNetwork != null && mobileNetwork.isConnected()) {
			Stt += "3G";
		}

		NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		if (activeNetwork == null) {
			Stt += "NoInternetAccess";
		}

		return Stt;
	}
	public boolean isInternetAvailable() {
        try {
            InetAddress ipAddr = InetAddress.getByName("www.google.com"); //You can replace it with your name

            if (ipAddr.isReachable(2000)) {
                return false;
            } else {
                return true;
            }

        } catch (Exception e) {
            return false;
        }
    }
	public boolean checkOnlineState(Context c) {
	    ConnectivityManager CManager =
	        (ConnectivityManager) c.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo NInfo = CManager.getActiveNetworkInfo();
	    if (NInfo != null && NInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
	public Boolean isOnline() {
	    try {
	        Process p1 = java.lang.Runtime.getRuntime().exec("ping -c 1 www.google.com");
	        int returnVal = p1.waitFor();
	        boolean reachable = (returnVal==0);
	        return reachable;
	    } catch (Exception e) {
	        // TODO Auto-generated catch block
	        e.printStackTrace();
	    }
	    return false;
	}
	public void checkTest(){
		String netAddress = null;
		 try
		  {
		   netAddress = new NetTask().execute("www.google.com").get();
		  }
		  catch (Exception e1)
		   {
		    e1.printStackTrace();
		   }
	}
	class NetTask extends AsyncTask<String, Integer, String>
    {
        @Override
        protected String doInBackground(String... params)
        {
            InetAddress addr = null;
            try
            {
                addr = InetAddress.getByName(params[0]);
            }

            catch (UnknownHostException e)
            {
                            e.printStackTrace();
            }
            return addr.getHostAddress();
        }
    }
}