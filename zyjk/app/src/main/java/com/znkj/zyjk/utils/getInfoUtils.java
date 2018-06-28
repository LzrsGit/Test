package com.znkj.zyjk.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class getInfoUtils {

    public static String getAddress(Context context){
        String Add = "";
        SharedPreferences sp = context.getSharedPreferences("address", Context.MODE_PRIVATE);
        Add = sp.getString("add", "nothing");
        return Add;
    }

    public static String getUserInfo(Context context, String key){
        String info = "";
        SharedPreferences sp = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        info = sp.getString(key, "nothing");
        return info;
    }

    public static void delUseInfo(Context context){
        SharedPreferences sp = context.getSharedPreferences("user_info", context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("use_name", "");
        editor.putString("use_idcard", "");
        editor.putString("use_id", "");
        editor.putString("use_phonenum", "");
        editor.putString("sys_bb", "02");
        editor.commit();
    }


}
