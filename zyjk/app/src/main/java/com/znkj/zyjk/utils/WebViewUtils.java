package com.znkj.zyjk.utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

/**
 * webView统一管理类
 * 
 */
public class WebViewUtils {

	public boolean checkCon(Context applicationContext){
		boolean isConnection = false;
		Log.e("iscon","======"+isConnection);
		if(applicationContext!=null) {
			ConnectivityManager connectivityManager = (ConnectivityManager) applicationContext
					.getSystemService(Context.CONNECTIVITY_SERVICE);//获取系统的连接服务
			//NetworkInfo activeNetInfo = connectivityManager.getActiveNetworkInfo();//获取网络的连接情况
			if (connectivityManager != null) {
				NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
				if (activeNetworkInfo == null || !activeNetworkInfo.isConnected()) {
					isConnection = false;
					System.out.println("activeNetworkInfo获取失败");
				} else {
					isConnection = true;
				}
			} else {
				System.out.println("connectivityManager获取失败");
			}
		}else {
			System.out.println("applicationContext获取失败");
		}
		return isConnection;
	}
}