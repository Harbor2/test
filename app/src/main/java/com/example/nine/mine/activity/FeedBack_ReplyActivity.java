package com.example.nine.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.nine.R;

import java.nio.file.WatchKey;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class FeedBack_ReplyActivity extends Activity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title_feedback_reply)
    TextView tvTitleFeedbackReply;
    @BindView(R.id.tv_content_feedback_reply)
    TextView tvContentFeedbackReply;
    @BindView(R.id.tv_reply_feedback_reply)
    TextView tvReplyFeedbackReply;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_feed_back__reply);
        ButterKnife.bind(this);
        initData();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        Intent intent = getIntent();
        String content = intent.getStringExtra("content");
        String reply = intent.getStringExtra("reply");
        String title = intent.getStringExtra("title");

        tvTitleFeedbackReply.setText(title);
        tvContentFeedbackReply.setText(content);
        if (TextUtils.isEmpty(reply)){
            tvReplyFeedbackReply.setText("暂未回复");
        }else {
            tvReplyFeedbackReply.setText(reply);
        }
    }

    @OnClick({R.id.iv_back, R.id.tv_title_feedback_reply, R.id.tv_content_feedback_reply, R.id.tv_reply_feedback_reply})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_title_feedback_reply:
                break;
            case R.id.tv_content_feedback_reply:
                break;
            case R.id.tv_reply_feedback_reply:
                break;
        }
    }
}
