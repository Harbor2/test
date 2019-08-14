package com.example.nine.live.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.nine.base.BaseFragment;


public class LiveFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextSize(40);
        textView.setGravity(Gravity.CENTER);


        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("这是直播页面");
    }
}
