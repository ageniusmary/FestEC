package com.example.mary.mary.util.storage;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.mary.mary.app.Mary;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;


/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.util.storage
 * 创建者：   Mary
 * 创建时间:  2018/2/4 14:42
 * 描述：     TODO
 */

public final class MaryPerference {

    private static final SharedPreferences PREFERENCES =
            PreferenceManager.getDefaultSharedPreferences(Mary.getApplicationContext());
    private static final String APP_PREFERENCES_KEY = "profile";

    private static SharedPreferences getAppPreference(){
        return PREFERENCES;
    }

    public static void setAppProfile(String val){
        getAppPreference()
                .edit()
                .putString(APP_PREFERENCES_KEY,val)
                .apply();
    }

    public static String getAppProfile(){
        return getAppPreference().getString(APP_PREFERENCES_KEY,null);
    }

    public static JSONObject getAppProfileJson(){
        final String profile = getAppProfile();
        return JSON.parseObject(profile);
    }

    public static void removeAppProfile(){
        getAppPreference()
                .edit()
                .remove(APP_PREFERENCES_KEY)
                .apply();
    }

    public static void clearAppPreference(){
        getAppPreference()
                .edit()
                .clear()
                .apply();
    }

    public static void setAppFlag(String key,boolean flag){
        getAppPreference()
                .edit()
                .putBoolean(key,flag)
                .apply();
    }

    public static boolean getAppFlag(String key){
        return getAppPreference()
                .getBoolean(key,false);
    }

}
