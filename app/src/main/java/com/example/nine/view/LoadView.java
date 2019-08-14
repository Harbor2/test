package com.example.nine.view;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.example.nine.R;


public class LoadView extends RelativeLayout {

    TextView load;
    PopupWindow pw;
    Activity mActivity;
    Context context;
    View view;

    public LoadView(Context context, AttributeSet artts, Activity mActivity, View view) {
        super(context,artts);

        //加载布局
        LayoutInflater.from(context).inflate(R.layout.view_load,this);
        load = findViewById(R.id.load_tv_load);
        this.mActivity=mActivity;
        this.context=context;
        this.view=view;

        pw = new PopupWindow(this, WindowManager.LayoutParams.MATCH_PARENT, WindowManager.LayoutParams.MATCH_PARENT);
        pw.setFocusable(false);
        pw.setOutsideTouchable(false);
        //pw.setAnimationStyle(R.style.MyPopupWindow_anim_style);

        pw.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                darkenBackgroud(1f);
            }
        });
    }


    public void setLoad(String loadtext) {
        load.setText(loadtext);
    }

    public void show(){
        pw.showAtLocation(view, Gravity.CENTER, 0,0);
        darkenBackgroud(0.4f);
    }

    public void dismiss(){
        pw.dismiss();
    }

    //1.使变暗：darkenBackgroud(0.4f);
    //2.使恢复：darkenBackgroud(1f);
    private void darkenBackgroud(Float bgcolor) {
        WindowManager.LayoutParams lp = mActivity.getWindow().getAttributes();
        lp.alpha = bgcolor;
        mActivity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        mActivity.getWindow().setAttributes(lp);
    }
}
