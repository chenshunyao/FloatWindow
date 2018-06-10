package com.example.test.floatingwindow;

import android.content.Intent;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnFloatWindow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnFloatWindow = findViewById(R.id.btn_float_window);

    }

    public void showFloatingWindow(View view){
        if(Build.VERSION.SDK_INT >= 23){
            if(Settings.canDrawOverlays(this)){
                Intent intent = new Intent(MainActivity.this, FloatWindowService.class);
                Toast.makeText(MainActivity.this,"已开启",Toast.LENGTH_SHORT).show();
                startService(intent);
                finish();
            } else {
                Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
                Toast.makeText(MainActivity.this,"需要取得权限以使用悬浮窗",Toast.LENGTH_SHORT).show();
                startActivity(intent);
            }
        }else{
            startService(new Intent(this,FloatWindowService.class));
            finish();
        }
    }
}
