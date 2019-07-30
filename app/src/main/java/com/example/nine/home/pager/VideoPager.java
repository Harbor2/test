package com.example.nine.home.pager;

import android.content.Context;
import android.view.View;

import com.example.nine.R;
import com.example.nine.base.BasePager;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoPager extends BasePager {
    @BindView(R.id.home_recycle)
    RecyclerView homeRecycle;

    // private TextView textView;
    public VideoPager(Context context) {
        super(context);
    }

    @Override
    public View initView() {
//        textView = new TextView(context);
        View view = View.inflate(context, R.layout.home_videopager, null);
        ButterKnife.bind(this, view);

        GridLayoutManager gridLayoutManager= new GridLayoutManager(context,2);
//设置布局管理器
        homeRecycle.setLayoutManager(gridLayoutManager);

      //  homeRecycle.setAdapter(recycleAdapter);


        return view;
    }

    @Override
    public void initData() {
        super.initData();
        // textView.setText("这是视频页面");
    }
}
