package com.example.mary.mary.util.dimen;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.example.mary.mary.app.Mary;

/**
 * 项目名:    FestEC
 * 包名：     com.example.mary.mary.util
 * 创建者：   Mary
 * 创建时间:  2018/1/26 13:24
 * 描述：     测量
 */

public class DimenUtil {
    //获取屏幕宽度
    public static int getScreenWidth(){
        final Resources resources = Mary.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.widthPixels;
    }
    //获取屏幕高度
    public static int getScreenHeight(){
        final Resources resources = Mary.getApplicationContext().getResources();
        final DisplayMetrics dm = resources.getDisplayMetrics();
        return dm.heightPixels;
    }
}
