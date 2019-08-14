package com.example.nine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.example.nine.utils.AnimaUtils;

import butterknife.BindView;
import butterknife.ButterKnife;


public class LaunchActivity extends Activity {


    @BindView(R.id.logo)
    ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);
        ButterKnife.bind(this);

        AnimaUtils.CircleAnimaLogo(logo,LaunchActivity.this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 启动登录窗口
                startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                // 关闭启动画面
                finish();
            }
        },2000);
    }

}
