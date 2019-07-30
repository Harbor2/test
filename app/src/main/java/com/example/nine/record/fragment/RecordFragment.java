package com.example.nine.record.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nine.base.BaseFragment;


public class RecordFragment extends BaseFragment {
    private TextView textView;
    @Override
    public View initView() {
        textView = new TextView(mContext);
        textView.setTextSize(40);
        textView.setGravity(Gravity.CENTER);

        Toast.makeText(mContext, "RecordFragment被初始化了", Toast.LENGTH_SHORT).show();
        return textView;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("这是录制视频或者直播页面");
    }
}
