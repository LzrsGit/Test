package com.znkj.zyjk.activity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextPaint;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import com.znkj.zyjk.MainActivity;
import com.znkj.zyjk.R;
import com.znkj.zyjk.utils.getInfoUtils;
import com.githang.statusbar.StatusBarCompat;

public class HomeActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private CoordinatorLayout right;
    private NavigationView left;
    private boolean isDrawer=false;
    private ImageView stzy;
    private ImageView dszy;
    private getInfoUtils getInfo;
    private int infoInt = 0;
    private ImageView img ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_main);
        StatusBarCompat.setStatusBarColor(this, Color.parseColor("#002f67") , false);
        //获取用户信息
        String u_name = "";
        String u_id = "";
        SharedPreferences sp = getSharedPreferences("user_info", MODE_PRIVATE);
        u_name = sp.getString("use_name", "");
        u_id = sp.getString("use_id", "");
        img = findViewById(R.id.main_img);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //文本加粗
        NavigationView navigationView1 = (NavigationView) findViewById(R.id.nav_view);
        View headerLayout = navigationView1.getHeaderView(0);
        TextView tv = (TextView)headerLayout.findViewById(R.id.user_name);
        tv.setText("姓名："+u_name);
        TextPaint tp = tv.getPaint();
        tp.setFakeBoldText(true);
        TextView tv1 = (TextView)headerLayout.findViewById(R.id.user_id);
        tv1.setText("警号："+u_id);
        TextPaint tp1 = tv1.getPaint();
        tp1.setFakeBoldText(true);

        stzy = (ImageView)findViewById(R.id.stzy);
        dszy = (ImageView)findViewById(R.id.dszy);

        stzy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,
                        DszyActivity.class);
                startActivity(intent);
            }
        });
        dszy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(HomeActivity.this,
                        StzyActivity.class);
                startActivity(intent);
            }
        });


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        right = (CoordinatorLayout) findViewById(R.id.right);
        left = (NavigationView) findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        right.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if(isDrawer){
                    return left.dispatchTouchEvent(motionEvent);
                }else{
                    return false;
                }
            }
        });
        drawer.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                isDrawer=true;
                //获取屏幕的宽高
                WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
                Display display = manager.getDefaultDisplay();
                //设置右面的布局位置  根据左面菜单的right作为右面布局的left   左面的right+屏幕的宽度（或者right的宽度这里是相等的）为右面布局的right
                right.layout(left.getRight(), 0, left.getRight() + display.getWidth(), display.getHeight());
            }
            @Override
            public void onDrawerOpened(View drawerView) {}
            @Override
            public void onDrawerClosed(View drawerView) {
                isDrawer=false;
            }
            @Override
            public void onDrawerStateChanged(int newState) {}
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_app) {

        }else if (id == R.id.quit_app) {
            infoInt = 1;
            showDialog("确认注销当前用户吗？");
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
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
                    if (1==infoInt) {               //注销用户
                        getInfo.delUseInfo(HomeActivity.this);
                        Intent intent = new Intent(HomeActivity.this,
                                MainActivity.class);
                        startActivity(intent);
                    }
                    infoInt = 0;
                    finish();
                    break;
                case AlertDialog.BUTTON_NEGATIVE:// "取消"第二个按钮取消对话框
                    infoInt = 0;
                    break;
                default:
                    break;
            }
        }
    };

    //back键监听
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            infoInt = 2;
            showDialog("确认退出资源监控吗？");
        }
        return false;
    }

}
