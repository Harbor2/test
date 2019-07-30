package com.example.nine.mine;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nine.base.BaseFragment;


public class MineFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextSize(40);
        textView.setGravity(Gravity.CENTER);
        Toast.makeText(mContext, "mainFragment被初始化了", Toast.LENGTH_SHORT).show();


        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("这是我的页面");
    }
}
