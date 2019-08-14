package com.example.nine.mine.activity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
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
import com.example.nine.mine.bean.MsgcodeLogin_Bean;
import com.example.nine.mine.mybean.MyInformation;
import com.example.nine.utils.CacheUtils;
import com.example.nine.utils.Check_Phonenum;
import com.example.nine.utils.UpdateMsg;
import com.example.nine.view.LoadView;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MsgLoginActivity extends Activity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_msglogin_userphone)
    EditText etMsgloginUserphone;
    @BindView(R.id.et_msglogin_code)
    EditText etMsgloginCode;
    @BindView(R.id.tv_msglogin_getcode)
    TextView tvMsgloginGetcode;
    @BindView(R.id.btn_msglogin_login)
    Button btnMsgloginLogin;
    private LoadView loadView;

    private int count = 60;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                if (count > 0) {
                    tvMsgloginGetcode.setText(count + "秒后重发");
                    count--;
                    handler.sendEmptyMessageDelayed(100, 1000);
                } else {
                    tvMsgloginGetcode.setText("重新发送");
                    count = 60;
                }
            }
        }
    };
    private String phonenum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_msg_login);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.iv_back, R.id.tv_msglogin_getcode, R.id.btn_msglogin_login})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.tv_msglogin_getcode:
                //获取验证码的按钮
                Toast.makeText(getApplicationContext(), "请稍后", Toast.LENGTH_SHORT).show();
                phonenum = etMsgloginUserphone.getText().toString().trim();
                boolean resultphone = new Check_Phonenum().isPhone(phonenum);
                if (phonenum == null || resultphone == false) {
                    Toast.makeText(getApplicationContext(), "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tvMsgloginGetcode.getText().equals("获取验证码") || tvMsgloginGetcode.getText().equals("重新发送")) {
                    handler.sendEmptyMessageDelayed(100, 0);
                }
                break;
            case R.id.btn_msglogin_login:
                //验证码登陆按钮
                 phonenum = etMsgloginUserphone.getText().toString().trim();
                String code = etMsgloginCode.getText().toString().trim();
                if (TextUtils.isEmpty(code) || TextUtils.isEmpty(phonenum)){
                    Toast.makeText(getApplicationContext(),"请输入完整的信息",Toast.LENGTH_SHORT).show();
                    return;
                }
                //模仿登陆成功
                msgLogin();
                writeMsg();
                getJsonMsg();

                break;
        }
    }
    /**
     * 登陆成功之后将状态记录
     */
    public void writeMsg(){
        UpdateMsg updateMsg = new UpdateMsg(getApplicationContext());
        updateMsg.writeAutoStatus(true);
        updateMsg.writeStatus(true);
    }

    /**
     * 短信登陆的方法
     */
    private void msgLogin() {
        //访问后台进行登陆
        loadView = new LoadView(getApplicationContext(), null, this, btnMsgloginLogin);
        loadView.setLoad("正在登陆");
        loadView.show();
        //hander将以前的消息清空
        handler.removeCallbacksAndMessages(null);
        //hander设置消息延迟时间
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                loadView.dismiss();
                //使用接口回调更新信息
                if (onDataChangeLintener != null) {
                    onDataChangeLintener.onDataChange();
                }
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        }, 2000);
    }

    /**
     * 解析json数据
     */
    public void getJsonMsg(){
        String jsondata = "{\n" +
                "  \"code\": 0,\n" +
                "  \"data\": {\n" +
                "    \"token\": \"\",\n" +
                "    \"userId\": 1\n" +
                "  },\n" +
                "  \"msg\": \"success\"\n" +
                "}";
        Gson gson = new Gson();
        MsgcodeLogin_Bean msgcodeLogin_bean = gson.fromJson(jsondata, MsgcodeLogin_Bean.class);
        int code = msgcodeLogin_bean.getCode();
        String msg = msgcodeLogin_bean.getMsg();
        int userId = msgcodeLogin_bean.getData().getUserId();
        msgcodeLogin_bean.getData().getToken();

        System.out.println("短信登陆解析json获得的数据信息为"+userId+"  "+msg+"  "+code);
    }

    /***
     * 使用接口再页面finish的时候另外一个页面重新获取信息
     */

    public static interface onDataChangeLintener {
        //接口中回调的方法
        void onDataChange();
    }

    public static MsgLoginActivity.onDataChangeLintener onDataChangeLintener;

    /**
     * 2.使用者传递接口的实例
     */
    public static void setOnDataChangeLintener(MsgLoginActivity.onDataChangeLintener l) {
        onDataChangeLintener = l;
    }

}