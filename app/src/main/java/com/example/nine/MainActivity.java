package com.example.nine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nine.activity.fragment.RecordFragment;
import com.example.nine.base.BaseFragment;
import com.example.nine.home.fragment.HomeFragment;
import com.example.nine.live.fragment.LiveFragment;
import com.example.nine.mine.activity.LoginActivity;
import com.example.nine.mine.fragment.MineFragment;
import com.example.nine.mine.mybean.MyInformation;
import com.example.nine.utils.AES;
import com.example.nine.utils.AnimaUtils;
import com.example.nine.utils.CacheUtils;
import com.example.nine.utils.getMsgDisplay;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends FragmentActivity {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;
    @BindView(R.id.rg_main)
    RadioGroup rgMain;
    @BindView(R.id.rb_activity)
    RadioButton rbActivity;
    @BindView(R.id.rb_live)
    RadioButton rbLive;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;
    @BindView(R.id.rb_luzhi)
    RadioButton rbLuzhi;
    @BindView(R.id.rb_home)
    RadioButton rbHome;

    private BaseFragment mContext;
    private ArrayList<BaseFragment> baseFragments;
    private static int position = 0;
    private List idList;
    private String userPhone = null, userPass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();
        initListener();
        judgeLoginMsg();

    }

    /**
     * 判断当前用户的登陆状态
     */
    private void judgeLoginMsg() {
        //获取保存的账号密码信息
        msg msg = new msg();
        msg.run();
        //判断是否需要自动登陆
        if (CacheUtils.getBoolean(getApplicationContext(),"autologin")){
            autoLogin();
        }
    }


    private void initFragment() {
        baseFragments = new ArrayList<>();
        baseFragments.add(new HomeFragment());
        baseFragments.add(new RecordFragment());
        baseFragments.add(new LiveFragment());
        baseFragments.add(new MineFragment());
    }

    private void initListener() {
        rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_home:
                        position = 0;
                        AnimaUtils.CircleAnima(rbHome, MainActivity.this);

                        break;
                    case R.id.rb_activity:

                        position = 1;
                        AnimaUtils.CircleAnima(rbActivity, MainActivity.this);


                        break;
                    case R.id.rb_luzhi:

                        Toast.makeText(MainActivity.this, "录制视频", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.rb_live:
                        position = 2;
                        AnimaUtils.CircleAnima(rbLive, MainActivity.this);


//
                        break;
                    case R.id.rb_mine:
                        position = 3;
                        AnimaUtils.CircleAnima(rbMine, MainActivity.this);
//
                        break;
//                    case R.id.rb_user:
//                        position = 4;
//
//                        break;
                }

                BaseFragment baseFragment = getFragment(position);
                switchFragment(mContext, baseFragment);
            }
        });

        rgMain.check(R.id.rb_home);
//        rgMain.check();

//        ivLuzhi.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                FragmentManager fm = getSupportFragmentManager();
//                BottomDialogFragment editNameDialog = new BottomDialogFragment();
//                editNameDialog.show(fm, "fragment_bottom_dialog");
//            }
//        });

    }

    /**
     * @param position
     * @return
     */
    private BaseFragment getFragment(int position) {
        if (baseFragments != null && baseFragments.size() > 0) {
            BaseFragment baseFragment = baseFragments.get(position);
            return baseFragment;
        }
        return null;
    }

    private void switchFragment(Fragment fromFragment, BaseFragment nextFragment) {
        if (mContext != nextFragment) {
            mContext = nextFragment;
            if (nextFragment != null) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                //判断nextFragment是否添加
                if (!nextFragment.isAdded()) {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.add(R.id.frameLayout, nextFragment).commit();
                } else {
                    //隐藏当前Fragment
                    if (fromFragment != null) {
                        transaction.hide(fromFragment);
                    }
                    transaction.show(nextFragment).commit();
                }
            }
        }
    }

    /**
     * 是否已经退出
     */
    private boolean isExit = false;

    //双击退出
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (position != 0) {//不是第一页面
                position = 0;
                rgMain.check(R.id.rb_home);//首页
                return true;
            } else if (!isExit) {
                isExit = true;
                Toast.makeText(MainActivity.this, "再按一次推出", Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit = false;
                    }
                }, 2000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    public void toastDialog() {
        /**
         * 确定取消对话框
         */
        LoginActivity.isChange = true;
        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("提示");
        dialog.setMessage("您的密码信息已发生变化，是否重新登陆？");
        dialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
        dialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "取消登陆！", Toast.LENGTH_SHORT).show();
                //退出登陆之后将状态变为false
                CacheUtils.putBoolean(getApplicationContext(),"loginstatus",false);
            }
        });
        //记得show
        dialog.show();
    }

    //获取保存的账号密码信息
    class msg {
        public void run() {
            String string = CacheUtils.getString(getApplicationContext(), "info.txt");
            System.out.println("sting存储的内容是"+string);
            if (string != null && !TextUtils.isEmpty(string) ){
                String enuserPhone = string.split("##")[0];
                String enuserPass = string.split("##")[1];
                //解密
                AES mAes = new AES();
                userPhone = mAes.decrypt(enuserPhone);
                userPass = mAes.decrypt(enuserPass);
            }
        }
    }

    public void autoLogin() {
        //自动登陆再次验证保存的账号密码信息和服务器的密码信息是否一致
        //本地的账号密码  userPhone userPass
        if (TextUtils.isEmpty(userPhone) || TextUtils.isEmpty(userPass)) {
            //首次登陆没有信息什么也不做
        } else {
            if (userPhone.equals("15226513672") && userPass.equals("123456")) {
                //自动跳转到登陆后的状态
                Toast.makeText(getApplicationContext(), "自动登陆", Toast.LENGTH_SHORT).show();
                //登陆成功之后获取到后台的头像昵称信息

            } else {
                //账号密码信息匹配失败可能已经修改密码
                //弹出dialog让用户自己选择是否重新登陆
                toastDialog();
            }
        }
    }

}
