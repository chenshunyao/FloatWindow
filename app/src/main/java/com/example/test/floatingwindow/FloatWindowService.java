package com.example.test.floatingwindow;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by Administrator on 2018/6/2.
 */

public class FloatWindowService extends Service implements CustomToolBar.ToolBarOnClickListener,View.OnTouchListener{

    private CustomToolBar floatLayout;
    private WindowManager.LayoutParams lp;
    private WindowManager wm;
    private int statusBarHeight;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        installFloatingWindow();
    }

    private void installFloatingWindow() {
        wm = (WindowManager)getSystemService(Context.WINDOW_SERVICE);
        lp = new WindowManager.LayoutParams();
        lp.type = WindowManager.LayoutParams.TYPE_SYSTEM_ALERT;
        lp.format = PixelFormat.RGBA_8888;
        lp.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE | WindowManager.LayoutParams.FLAG_WATCH_OUTSIDE_TOUCH;
        lp.setTitle("Floating Window!");
        lp.gravity = Gravity.LEFT | Gravity.TOP;
        lp.x = 0;
        lp.y = 0;
        lp.width = Utils.getScreenWidth(this) / 3;
        lp.height = Utils.getScreenHeight(this) / 3;
        //用于检测状态栏高度.
        int resourceId = getResources().getIdentifier("status_bar_height","dimen","android");
        if (resourceId > 0)
        {
            statusBarHeight = getResources().getDimensionPixelSize(resourceId);
        }
        LayoutInflater inflater = LayoutInflater.from(getApplication());
        //获取浮动窗口视图所在布局.
        floatLayout = (CustomToolBar) inflater.inflate(R.layout.floatlayout,null);
        floatLayout.setToolBarClickListener(this);
        floatLayout.setOnTouchListener(this);
        wm.addView(floatLayout,lp);
    }

    @Override
    public void setFullScreen() {
        lp.flags = lp.flags | WindowManager.LayoutParams.FLAG_FULLSCREEN;
        lp.width = Utils.getScreenWidth(this);
        lp.height = Utils.getScreenHeight(this);
        wm.updateViewLayout(floatLayout,lp);
    }

    @Override
    public void setDefaultScreen() {
        lp.flags = lp.flags | WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN;
        lp.width = Utils.getScreenWidth(this) / 3;
        lp.height = Utils.getScreenHeight(this) / 3;
        wm.updateViewLayout(floatLayout,lp);
    }

    @Override
    public void closeWindow() {
        wm.removeView(floatLayout);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        lp.x = (int)motionEvent.getRawX();
        lp.y = (int)motionEvent.getRawY() - statusBarHeight;
        wm.updateViewLayout(floatLayout,lp);
        return false;
    }
}
