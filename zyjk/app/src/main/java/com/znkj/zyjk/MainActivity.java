package com.znkj.zyjk;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.znkj.zyjk.activity.HomeActivity;
import com.znkj.zyjk.model.User;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

	private AutoCompleteTextView userName;
	private EditText userIdcard;
	private EditText userId;
	private EditText userPhoneNum;
	private Button btn_login;

	private Toast toast;
	private List<String> lists = new ArrayList<String>();
    private String originAddress = "http://df9e0d5d.ngrok.io/weixin/servlet/LoginServlet";

    private Button button;
    private EditText edit1,edit2;
    public static final String TAG="MainActivity";

	private void showToast(String message) {
		if (toast != null) {
			toast.cancel();
			toast = null;
		}
		toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
		toast.show();
	}

	Handler mHandler = new Handler(){
		@Override
		public void handleMessage(Message msg) {
			super.handleMessage(msg);
			String result = "";

			if ("OK".equals(msg.obj.toString())){
				result = "登录成功！";
			}else if ("Wrong".equals(msg.obj.toString())){
				result = "您输入的用户名或密码错误！";
			}//else {
			// result = msg.obj.toString();
			//}
			Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
		}
	};

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		initView();
		btn_login = (Button) findViewById(R.id.login);
		btn_login.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				saveInfo();
				login();
			}
		});

	}
	// 点击空白区域 自动隐藏软键盘
	public boolean onTouchEvent(MotionEvent event) {
		if(null != this.getCurrentFocus()){
			/**
			 * 点击空白位置 隐藏软键盘
			 */
			InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
			return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
		}
		return super .onTouchEvent(event);
	}

	//获取用户信息
	private void initView(){
		// System.out.println(lists.get(0).toString());
		userName = (AutoCompleteTextView) findViewById(R.id.userName);
		userName.setThreshold(1);
		ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(
				MainActivity.this, android.R.layout.simple_list_item_1, lists);
		userName.setAdapter(arrayAdapter);

		userIdcard = (EditText) findViewById(R.id.userIdcard);
		userId = (EditText) findViewById(R.id.userId);
		userPhoneNum = (EditText) findViewById(R.id.userPhoneNum);
	}

	//保存用户信息
	private void saveInfo(){
    	boolean ok = true;
    	if(userName.getText().toString().trim().isEmpty()){
    		showToast("姓名不能为空");
    		ok = false;
		}
		if(userIdcard.getText().toString().trim().isEmpty()){
			showToast("身份证号不能为空");
			ok = false;
		}
		if(userId.getText().toString().trim().isEmpty()){
			showToast("警号/协警号不能为空");
			ok = false;
		}
		if(userPhoneNum.getText().toString().trim().isEmpty()){
			showToast("用户姓名不能为空");
			ok = false;
		}
		if(ok) {
			SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
			SharedPreferences.Editor editor = sp.edit();
			editor.putString("use_name", userName.getText().toString());
			editor.putString("use_idcard", userIdcard.getText().toString());
			editor.putString("use_id", userId.getText().toString());
			editor.putString("use_phonenum", userPhoneNum.getText().toString());
			editor.commit();
			showToast("用户信息保存成功");
		}
	}

	//back键监听
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			showDialog("确认退出人车核录系统吗？");
		}
		return false;
	}
	private void showDialog(String info){
		// 创建退出对话框
		AlertDialog isExit = new AlertDialog.Builder(this).create();
		// 设置对话框标题
		isExit.setTitle("提示");
		// 设置对话框消息
		isExit.setMessage(info);
		// 添加选择按钮并注册监听
		isExit.setButton("确定", listener);
		isExit.setButton2("取消", listener);
		// 显示对话框
		isExit.show();
		isExit.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(Color.RED);
		isExit.getButton(DialogInterface.BUTTON_NEGATIVE).setTextColor(Color.BLACK);
	}
	/**
	 * 监听对话框里面的button点击事件
	 */
	DialogInterface.OnClickListener listener = new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			switch (which) {
				case AlertDialog.BUTTON_POSITIVE:// "确认"按钮退出登录
					finish();
					break;
				case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
					break;
				default:
					break;
			}
		}
	};

	//登录
	public void login() {

		Intent intent = new Intent(MainActivity.this,
				HomeActivity.class);
		//StudentInformationManagerActivity.class);
		startActivity(intent);
		finish();

		//取得用户输入的账号和密码
       /* if (!isInputValid()){
            return;
        }
        HashMap<String, String> params = new HashMap<String, String>();
        params.put(User.USERNAME, userName.getText().toString());
        params.put(User.PASSWORD, MD5(userPassword.getText().toString()));
        try {
            String compeletedURL = HttpUtil.getURLWithParams(originAddress, params);
            HttpUtil.sendHttpRequest(compeletedURL, new HttpCallbackListener() {
                @Override
                public void onFinish(String response) {
                    Message message = new Message();
                    message.obj = response;
                    mHandler.sendMessage(message);
					User user = getUserInfo("model",(String)message.obj.toString());
                    //Log.i("sssssss=====",user.getUserName());
					Intent intent = new Intent(MainActivity.this,
                            AddStudentActivity.class);
                    //StudentInformationManagerActivity.class);
                    intent.putExtra("xm", user.getXm());
                    intent.putExtra("sjh", user.getSjh());
                    intent.putExtra("sfzh", user.getUserName());

                    startActivity(intent);
                }

                @Override
                public void onError(Exception e) {
                    Message message = new Message();
                    message.obj = e.toString();
                    mHandler.sendMessage(message);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }*/
	}


	private boolean isInputValid() {
		//检查用户输入的合法性，这里暂且默认用户输入合法
		return true;
	}

	// MD5加密
	public static String MD5(String string) {
		return encodeMD5String(string);
	}

	public static String encodeMD5String(String str) {
		return encode(str, "MD5");
	}

	private static String encode(String str, String method) {
		MessageDigest md = null;
		String dstr = null;
		try {
			md = MessageDigest.getInstance(method);
			md.update(str.getBytes());
			dstr = new BigInteger(1, md.digest()).toString(16);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return dstr;
	}

	public static User getUserInfo(String key, String jsonString) {
		User user = new User();
		// 将json字符串转换成json对象
		try {
			JSONObject jsonObject = new JSONObject(jsonString);
			// 将json对象根据key(person)，拿到对应的value(Person对象)值
			//JSONObject jsonObject2 = jsonObject.getJSONObject(key);
			// 将拿到的对象set到一个person对象中
			user.setXm(jsonObject.getString("xm"));
			user.setSjh(jsonObject.getString("sjh"));
			user.setUserName(jsonObject.getString("userName"));

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return user;

	}

}
