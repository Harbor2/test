package com.example.nine.home.pager;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import com.example.nine.base.BasePager;

public class LivePager extends BasePager {
    private TextView textView;
    public LivePager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
        textView = new TextView(context);

        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("这是直播页面");
    }
}
