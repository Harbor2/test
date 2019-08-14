package com.example.nine.mine.activity;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nine.R;
import com.example.nine.mine.adapter.FeedBack_RecycleView_Adapter;
import com.example.nine.mine.mybean.Dcteam_Recoed_Item;
import com.example.nine.mine.mybean.FeedBack_Item;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedBackActivity extends Activity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rv_feedback)
    RecyclerView rvFeedback;
    @BindView(R.id.sm_feedback)
    SmartRefreshLayout smFeedBack;
    @BindView(R.id.et_feedback)
    EditText etFeedback;
    @BindView(R.id.btn_submit)
    Button btnSubmit;

    private FeedBack_RecycleView_Adapter feedBack_recycleView_adapter;
    private List<FeedBack_Item> feedback_list = new ArrayList<>();//再加载之前获得全部数据
    private List<FeedBack_Item> feedback_list_last = new ArrayList<>();//最后的数据
    private List<FeedBack_Item> feedback_list_middle;//将开始的数据进行变换（其实是用到了两个list）
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
    private int m = 0, length = 10;
    private int n = 1;

    /**
     * 数据分页的操作
     */
    private void getdata() {
        if ((n * length) <= feedback_list.size()) {
            feedback_list_middle = feedback_list.subList(m * length, n * length);
            for (int i = 0; i < feedback_list_middle.size(); i++) {
                feedback_list_last.add(feedback_list_middle.get(i));
            }
            m++;
            n++;
        } else {
            for (int i = 0; i < feedback_list.size(); i++) {
                if (!feedback_list_last.contains(feedback_list.get(i)))
                    feedback_list_last.add(feedback_list.get(i));
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_feed_back);
        ButterKnife.bind(this);

        initData();
        //刷新的监听
        smartRefreshLintener();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        for (int i = 0; i < 23; i++) {
            FeedBack_Item feedBack_item = new FeedBack_Item("工单的内容、、、、、、", "工单的回复回复回复、、、、、、、");
            feedback_list.add(feedBack_item);
        }
        //通知加载adapter
        handler.sendEmptyMessage(0);
    }

    /**
     * 初始化适配器
     */
    private void initAdapter() {
        rvFeedback.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        feedBack_recycleView_adapter = new FeedBack_RecycleView_Adapter(getApplicationContext(), feedback_list_last);
        rvFeedback.setAdapter(feedBack_recycleView_adapter);
    }

    @OnClick({R.id.iv_back, R.id.btn_submit})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.btn_submit:
                //提交工单的按钮
                submitFeed();
                break;
        }
    }

    /**
     * 提交工单
     */
    private void submitFeed() {
        String text = etFeedback.getText().toString().trim();
        if (TextUtils.isEmpty(text)){
            Toast.makeText(getApplicationContext(),"请输入内容",Toast.LENGTH_SHORT).show();
            return;
        }
        FeedBack_Item submit_feed = new FeedBack_Item(text);
        feedback_list.add(0,submit_feed);
        feedback_list_last.add(0,submit_feed);
        feedBack_recycleView_adapter.notifyDataSetChanged();
        Toast.makeText(getApplicationContext(),"提交成功",Toast.LENGTH_SHORT).show();
        //隐藏软键盘
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
        }
        etFeedback.setText("");
    }

    /**
     * 上拉加载下拉刷新的监听
     */
    private void smartRefreshLintener() {
        smFeedBack.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
                feedback_list_last.clear();
                m = 0;
                n = 1;
                getdata();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        feedBack_recycleView_adapter.notifyDataSetChanged();
                    }
                });

                refreshlayout.finishRefresh(1500);
            }
        });
        smFeedBack.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                getdata();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        feedBack_recycleView_adapter.notifyDataSetChanged();
                    }
                });
                refreshLayout.finishLoadMore(1500);
            }
        });
    }
}
