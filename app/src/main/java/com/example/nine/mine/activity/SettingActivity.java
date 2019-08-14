package com.example.nine.mine.activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.nine.R;
import com.example.nine.mine.mybean.MyInformation;
import com.example.nine.utils.CacheUtils;
import com.example.nine.utils.UpdateMsg;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SettingActivity extends Activity {


    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.ll_change_exit)
    LinearLayout llChangeExit;
    @BindView(R.id.ll_change_details)
    LinearLayout llChangeDetails;
    @BindView(R.id.ll_change_pass)
    LinearLayout llChangePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_setting);
        ButterKnife.bind(this);
    }

    /**
     * 退出登陆的提醒
     */
    public void toastDialog() {
        /**
         * 确定取消对话框
         */
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("提示");
        dialog.setMessage("您确定要退出登陆码？");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //退出登陆之后将登陆的状态设为false
                UpdateMsg updateMsg = new UpdateMsg(getApplicationContext());
                updateMsg.writeAutoStatus(false);
                updateMsg.writeStatus(false);
                if (onCountexitLintener != null) {
                    onCountexitLintener.onCountexit();
                }
                finish();
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //什么也不做
            }
        });
        //记得show
        dialog.show();
    }

    @OnClick({R.id.iv_back, R.id.ll_change_exit, R.id.ll_change_details, R.id.ll_change_pass})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.ll_change_exit:
                //退出登陆
                if (CacheUtils.getBoolean(getApplicationContext(),"loginstatus")){
                    toastDialog();
                }else {
                    Toast.makeText(getApplicationContext(),"您还未登陆",Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.ll_change_details:
                //修改个人信息的页面
                if (CacheUtils.getBoolean(getApplicationContext(),"loginstatus")) {
                    Intent intent1 = new Intent(getApplicationContext(), MyInformationActivity.class);
                    startActivity(intent1);
                } else {
                    //跳转登陆界面
                    Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent1);
                }
                break;
            case R.id.ll_change_pass:
                //修改密码的页面
                if (CacheUtils.getBoolean(getApplicationContext(),"loginstatus")) {
                    Intent intent = new Intent(getApplicationContext(), ChangePassActivity.class);
                    startActivity(intent);
                } else {
                    //跳转登陆界面
                    Intent intent1 = new Intent(getApplicationContext(), LoginActivity.class);
                    startActivity(intent1);
                }
                break;
        }
    }

    /***
     * 使用接口再页面finish的时候另外一个页面重新获取信息
     */

    public static interface onCountexitLintener {
        //接口中回调的方法
        void onCountexit();
    }

    public static SettingActivity.onCountexitLintener onCountexitLintener;

    /**
     * 2.使用者传递接口的实例
     */
    public static void setOnCountexitLintener(SettingActivity.onCountexitLintener l) {
        onCountexitLintener = l;
    }
}
