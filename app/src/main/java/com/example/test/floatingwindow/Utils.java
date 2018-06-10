package com.example.test.floatingwindow;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Administrator on 2018/6/9.
 */

public class Utils {

    //获取屏幕的宽度
    public static int  getScreenWidth(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.widthPixels;
    }

    //获取屏幕的高度
    public static int getScreenHeight(Context context){
        DisplayMetrics dm = context.getResources().getDisplayMetrics();
        return dm.heightPixels;
    }
}
