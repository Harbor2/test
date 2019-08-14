package com.example.nine.home.fragment;

import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.example.nine.R;
import com.example.nine.base.BaseFragment;
import com.example.nine.base.BasePager;

import java.util.ArrayList;

import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment {


    private ArrayList list;
    private ArrayList<BasePager> basePagers;

    private TextView textView;

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.home_fragment, null);
        ButterKnife.bind(this, view);
        //  initListener();
        textView = new TextView(mContext);
        textView.setGravity(Gravity.CENTER);
        return view;
    }

    @Override
    public void initData() {
        super.initData();
        textView.setText("这是首页");

    }



}
