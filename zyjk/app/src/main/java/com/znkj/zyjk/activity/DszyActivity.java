package com.znkj.zyjk.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.githang.statusbar.StatusBarCompat;
import com.znkj.zyjk.R;
import com.znkj.zyjk.utils.WebViewUtils;


public class DszyActivity extends AppCompatActivity {

    private String url = "https://www.baidu.com";
    private String errUrl = "file:///android_asset/error.html";
    private Button backBtn;
    private TextView top_title;

    WebView mWebview;
    private ProgressBar pg1;
    WebSettings mWebSettings;
    WebViewUtils webUtils = new WebViewUtils();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dszy_layout);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#002f67") , false);
        initView();

        //设置不用系统浏览器打开,直接显示在当前Webview
        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        //设置WebChromeClient类
        mWebview.setWebChromeClient(new WebChromeClient() {
            //获取网站标题
            @Override
            public void onReceivedTitle(WebView view, String title) {
                System.out.println("标题在这里");
                top_title.setText(title);
            }
            //获取加载进度
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress < 100) {
                    pg1.setVisibility(View.VISIBLE);//开始加载网页时显示进度条
                    pg1.setProgress(newProgress);//设置进度值

                } else if (newProgress == 100) {
                    pg1.setVisibility(View.GONE);//加载完网页进度条消失
                }
            }
        });
        //设置WebViewClient类
        mWebview.setWebViewClient(new WebViewClient() {
            //设置加载前的函数
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                System.out.println("开始加载了");
            }
            //设置结束加载函数
            @Override
            public void onPageFinished(WebView view, String url) {
                //endLoading.setText("结束加载了");
            }
            //网页时显示错误页面
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                mWebview.loadUrl(errUrl);
            };
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  DszyActivity.this.finish();
              }
        });
    }

    public void initView(){
        top_title = (TextView)findViewById(R.id.text_title);
        pg1=(ProgressBar) findViewById(R.id.progressBar1);
        mWebview = (WebView) findViewById(R.id.webView1);
        mWebSettings = mWebview.getSettings();
        if(webUtils.checkCon(getApplicationContext())){
            mWebSettings.setCacheMode(WebSettings.LOAD_DEFAULT); //设置缓存有网
        }else {
            mWebSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //设置缓存无网
        }
        mWebSettings.setJavaScriptEnabled(true);//设置能够解析Javascript
        mWebSettings.setDomStorageEnabled(true);//设置适应Html5
        mWebview.loadUrl(url);
        backBtn = findViewById(R.id.back_btn);
    }

    //点击返回上一页面而不是退出浏览器
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        // 判断是否可以返回
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebview.canGoBack()) {
                // 可以返回时，就返回
                mWebview.goBack();
                return true;
            } else {
                // 当不能返回时，关闭程序。
                showDialog("确认返回首页吗？");
                //DszyActivity.this.finish();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
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
                    DszyActivity.this.finish();
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    break;
                default:
                    break;
            }
        }
    };



}
