package com.example.nine.mine.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nine.R;
import com.example.nine.mine.adapter.Dcteam_RecycleView_Adapter;
import com.example.nine.mine.mybean.Dcteam_Recoed_Item;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Dcteam_ApreocrdActivity extends Activity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rv_dcteam)
    RecyclerView rvDcteam;
    @BindView(R.id.sm_dcteam)
    SmartRefreshLayout smDcteam;
    private Dcteam_RecycleView_Adapter dcteam_rv_adapter;
    private List<Dcteam_Recoed_Item> recoed_list = new ArrayList<>();//再加载之前获得全部数据
    private List<Dcteam_Recoed_Item> recoed_last = new ArrayList<>();//最后的数据
    private List<Dcteam_Recoed_Item> recoad_middle;//将开始的数据进行变换（其实是用到了两个list）
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    getdata();
                    //初始化适配器
                    initAdapter();
                    break;
            }
        }
    };


    //上拉加载的属性
    private int m = 0,length = 10;
    private int n = 1;
    /**
     * 数据分页的操作
     */
    private void getdata() {
        if ((n*length) <= recoed_list.size() ){
            recoad_middle = recoed_list.subList(m*length, n*length);
            for (int i=0;i<recoad_middle.size();i++){
                recoed_last.add(recoad_middle.get(i));
            }
            m++;
            n++;
            System.out.println("1 :  "+m*length+"      "+n*length+"    "+recoed_list.size()+"   "+recoed_last.size());
        }else {
            for (int i=0;i<recoed_list.size();i++){
                if (!recoed_last.contains(recoed_list.get(i)))
                    recoed_last.add(recoed_list.get(i));
            }
        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_dcteam_apreocrd);
        ButterKnife.bind(this);
        //初始化数据
        initData();
        //刷新的监听
        smartRefreshLintener();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i=0;i<43;i++){
            recoed_list.add(new Dcteam_Recoed_Item("已申请加入舞队","2017-09-07 12：27：47"));
        }
        //通知加载adapter
        handler.sendEmptyMessage(0);
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        rvDcteam.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        dcteam_rv_adapter = new Dcteam_RecycleView_Adapter(getApplicationContext(),recoed_last);
        rvDcteam.setAdapter(dcteam_rv_adapter);
    }


    @OnClick({R.id.iv_back, R.id.sm_dcteam})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.sm_dcteam:
                //下拉刷新上拉加载的控件
                break;
        }
    }

    /**
     * 上拉加载下拉刷新的监听
     */
    private void smartRefreshLintener() {
        smDcteam.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                recoed_last.clear();
                m = 0;
                n = 1;
                getdata();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dcteam_rv_adapter.notifyDataSetChanged();
                    }
                });
                refreshlayout.finishRefresh(1500);
            }
        });
        smDcteam.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getdata();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dcteam_rv_adapter.notifyDataSetChanged();
                    }
                });
                refreshLayout.finishLoadMore(1500);
            }
        });
    }
}
