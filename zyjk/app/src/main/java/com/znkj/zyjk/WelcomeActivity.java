package com.znkj.zyjk;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.znkj.zyjk.activity.HomeActivity;

public class WelcomeActivity extends Activity {

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);

		if(checkInfo()){
			enterMain();
		}else{
			initView();
		}
	}

	//跳转登录页面
	private void initView(){
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(WelcomeActivity.this,
						MainActivity.class);
				startActivity(intent);
				finish();
			}
		}, 2000);
	}

	//跳转首页
	private void enterMain(){
		new Handler().postDelayed(new Runnable() {
			@Override
			public void run() {
				Intent intent = new Intent(WelcomeActivity.this,
						HomeActivity.class);
				startActivity(intent);
				finish();
			}

		}, 2000);
	}

	//验证用户信息
	private boolean checkInfo(){
		boolean ok=false;
		String u_name = "";
		String u_id = "";
		SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
		u_name = sp.getString("use_name", "");
		u_id = sp.getString("use_idcard", "");
		if (!(u_name.trim().equalsIgnoreCase(""))&&!(u_id.trim()
				.equalsIgnoreCase(""))){
			ok = true;
		}
		return ok;
	}

}
