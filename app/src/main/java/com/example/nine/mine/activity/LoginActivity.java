package com.example.nine.mine.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nine.MainActivity;
import com.example.nine.R;
import com.example.nine.mine.bean.Phonenum_PassLogin_Bean;
import com.example.nine.mine.mybean.MyInformation;
import com.example.nine.utils.AES;
import com.example.nine.utils.BaseActivity;
import com.example.nine.utils.CacheUtils;
import com.example.nine.utils.UpdateMsg;
import com.example.nine.utils.getMsgDisplay;
import com.example.nine.view.LoadView;
import com.google.gson.Gson;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.et_userphone)
    EditText etUserphone;
    @BindView(R.id.et_password)
    EditText etPassword;
    @BindView(R.id.tv_newuser)
    TextView tvNewuser;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.iv_wechat_login)
    ImageView ivWechatLogin;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_forgetpass)
    TextView tvForgetpass;
    @BindView(R.id.iv_msg_login)
    ImageView ivMsgLogin;
    private LoadView loadView;
    private Handler handler = new Handler();
    //密码是否修改的状态
    public static Boolean isChange = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        initData();
        initEvent();
        getpermision();
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //将账号密码进行回显
        msg msg = new msg();
        msg.run();
    }

    /**
     * 初始化事件
     */
    private void initEvent() {

    }

    @OnClick({R.id.et_userphone, R.id.et_password, R.id.tv_newuser, R.id.btn_login, R.id.iv_wechat_login,
            R.id.iv_back, R.id.tv_forgetpass, R.id.iv_msg_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_newuser:
                //创建新用户
                Intent intent = new Intent(getApplicationContext(), NewUserActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.btn_login:
                //开始账号密码方式登陆
                login();
                getJsonMsg();
                break;
            case R.id.iv_wechat_login:
                //调用第三方的接口实现登陆

                break;
            case R.id.iv_back:
                Intent intent1 = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent1);
                finish();
                break;
            case R.id.tv_forgetpass:
                //忘记密码
                Intent intent2 = new Intent(getApplicationContext(), ChangePassActivity.class);
                startActivity(intent2);
                break;
            case R.id.iv_msg_login:
                //短信验证登陆
                Intent intent3 = new Intent(getApplicationContext(), MsgLoginActivity.class);
                startActivity(intent3);
                break;
        }
    }

    /**
     * 获取拍照的权限
     */
    private void getpermision() {
        performCodeWithPermission("调用相机、相册的权限", new PermissionCallback() {
            @Override
            public void hasPermission() {
                System.out.println("获取相机、相册的权限成功");
            }

            @Override
            public void noPermission() {
                System.out.println("没有相机、相册的权限");
            }
        }, Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE);
    }

    //获取保存的账号密码信息
    class msg {
        public void run() {
            String string = CacheUtils.getString(getApplicationContext(), "info.txt");
            System.out.println("sting存储的内容是" + string);
            if (string != null && !TextUtils.isEmpty(string)) {
                String enuserPhone = string.split("##")[0];
                String enuserPass = string.split("##")[1];
                //解密
                AES mAes = new AES();
                String userPhone = mAes.decrypt(enuserPhone);
                String userPass = mAes.decrypt(enuserPass);
                etUserphone.setText(userPhone);
                if (!isChange) {
                    etPassword.setText(userPass);
                }
            }
        }
    }

    /**
     * 发送数据到http验证登陆
     */
    public void sendMsgToHttp() {
//        String url = "http://111.11.7.208:8080/app/mock/76/user/login/app/phoneLogin";


    }

    /**
     * 用账号密码方式进行登陆
     */
    private void login() {
        final String userphone = etUserphone.getText().toString().trim();
        final String password = etPassword.getText().toString().trim();
        if (TextUtils.isEmpty(userphone) || TextUtils.isEmpty(password)) {
            Toast.makeText(LoginActivity.this, "账号或密码不能为空！", Toast.LENGTH_SHORT).show();
            return;
        } else {
            //访问后台进行登陆
            loadView = new LoadView(getApplicationContext(), null, this, btnLogin);
            loadView.setLoad("正在登陆");
            loadView.show();
            //hander将以前的消息清空
            handler.removeCallbacksAndMessages(null);
            //hander设置消息延迟时间
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    loadView.dismiss();
                    //登陆成功之后  将账号密码重新保存
                    //同时状态发生变化
                    writeMsg();
                    //登陆成功之后获取到后台的头像昵称信息
                    isChange = false;
                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    //使用接口回调
                    if (onDataChangeLintener != null) {
                        onDataChangeLintener.onDataChange();
                    }
                    startActivity(intent);
                }
            }, 2000);


        }
    }

    /**
     * 解析json获得数据
     */
    public void getJsonMsg() {
        String jsondata = "{\n" +
                "  \"code\": 0,\n" +
                "  \"data\": {\n" +
                "    \"token\": \"\",\n" +
                "    \"userId\": 1\n" +
                "  },\n" +
                "  \"msg\": \"success\"\n" +
                "}";
        Gson gson = new Gson();
        Phonenum_PassLogin_Bean phonenum_pass_loginBean = gson.fromJson(jsondata, Phonenum_PassLogin_Bean.class);
        int code = phonenum_pass_loginBean.getCode();
        String msg = phonenum_pass_loginBean.getMsg();
        int userId = phonenum_pass_loginBean.getData().getUserId();
        phonenum_pass_loginBean.getData().getToken();

        System.out.println("账号密码登陆解析json获得的数据信息为" + userId + "  " + msg + "  " + code);
    }

    /**
     * 登陆成功之后将新的信息记录
     */
    private void writeMsg() {
        isChange = false;
        String userPhone = etUserphone.getText().toString().trim();
        String userPass = etPassword.getText().toString().trim();
        UpdateMsg updateMsg = new UpdateMsg(getApplicationContext());
        updateMsg.writeMsg(userPhone, userPass);
        updateMsg.writeStatus(true);
        updateMsg.writeAutoStatus(true);
    }

    /***
     * 使用接口再页面finish的时候另外一个页面重新获取信息
     */

    public static interface onDataChangeLintener {
        //接口中回调的方法
        void onDataChange();
    }

    public static onDataChangeLintener onDataChangeLintener;

    /**
     * 2.使用者传递接口的实例
     */
    public static void setOnDataChangeLintener(onDataChangeLintener l) {
        onDataChangeLintener = l;
    }
}
