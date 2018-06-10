package com.example.test.floatingwindow;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;

/**
 * Created by Administrator on 2018/6/9.
 */

public class CustomToolBar extends LinearLayoutCompat implements View.OnClickListener {

    private Button btn_fullscreen,btn_close;
    private ToolBarOnClickListener toolBarClickListener;
    private boolean isFull = false;

    public interface ToolBarOnClickListener{
        public void setFullScreen();
        public void setDefaultScreen();
        public void closeWindow();
    }

    public void setToolBarClickListener(ToolBarOnClickListener clickListener){
        toolBarClickListener = clickListener;
    }

    public CustomToolBar(Context context) {
        this(context,null);
    }

    public CustomToolBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public void initView(){
        View barLayoutView = View.inflate(getContext(), R.layout.layout_common_toolbar, null);
        btn_fullscreen = barLayoutView.findViewById(R.id.btn_fullscreen);
        btn_close = barLayoutView.findViewById(R.id.btn_close);
        btn_fullscreen.setOnClickListener(this);
        btn_close.setOnClickListener(this);
        addView(barLayoutView,0);
    }

    @Override
    public void onClick(View view) {
        if(toolBarClickListener == null) return;
        switch (view.getId()){
            case R.id.btn_fullscreen:
                if(isFull){
                    toolBarClickListener.setDefaultScreen();
                    isFull = false;
                } else {
                    toolBarClickListener.setFullScreen();
                    isFull = true;
                }
                break;
            case R.id.btn_close:
                toolBarClickListener.closeWindow();
                break;
            default:
                break;
        }
    }
}
