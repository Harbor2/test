package com.example.nine.mine.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nine.R;
import com.example.nine.mine.mybean.MyInformation;
import com.example.nine.utils.CacheUtils;
import com.example.nine.utils.Check_Phonenum;
import com.makeramen.roundedimageview.RoundedImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.example.nine.R.drawable.nophoto;

public class ChangePassActivity extends Activity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.et_phonenum)
    EditText etPhonenum;
    @BindView(R.id.tv_get_msgcode)
    TextView tvGetMsgcode;
    @BindView(R.id.rl_phone_willgone)
    RelativeLayout rlPhoneWillgone;
    @BindView(R.id.et_change_msgcode)
    EditText etChangeMsgcode;
    @BindView(R.id.rl_code_willgone)
    RelativeLayout rlCodeWillgone;
    @BindView(R.id.et_change_pass)
    EditText etChangePass;
    @BindView(R.id.btn_change)
    Button btnChange;
    @BindView(R.id.et_check_pass)
    EditText etCheckPass;
    @BindView(R.id.photo_change)
    RoundedImageView photoChange;
    @BindView(R.id.tv_name_change)
    TextView tvNameChange;
    private int count = 60;
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 100) {
                if (count > 0) {
                    tvGetMsgcode.setText(count + "秒后重发");
                    count--;
                    handler.sendEmptyMessageDelayed(100, 1000);
                } else {
                    tvGetMsgcode.setText("重新发送");
                    count = 60;
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_change_pass);
        ButterKnife.bind(this);

        getMyMsg();
    }

    /**
     * 获取个人信息
     */
    private void getMyMsg() {
        Boolean loginState = CacheUtils.getBoolean(getApplicationContext(),"loginstatus");
        if (loginState) {
            //登陆状态
            Bitmap userphoto = MyInformation.getInstance().getUserphoto();
            String name = MyInformation.getInstance().getNickName();
            if (userphoto == null) {
                photoChange.setImageResource(nophoto);
            } else {
                photoChange.setImageBitmap(userphoto);
            }
            if (name == null) {
                tvNameChange.setText("获取信息失败");
            } else {
                tvNameChange.setText(name);
            }
        } else {
            //未登录状态
            photoChange.setImageResource(nophoto);
            tvNameChange.setText("请登录");
        }

    }

    @OnClick({R.id.iv_back, R.id.et_phonenum, R.id.tv_get_msgcode, R.id.et_change_msgcode, R.id.btn_change})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                //返回键
                break;
            case R.id.tv_get_msgcode:
                //获取短信验证码的按钮
                String phonenum = etPhonenum.getText().toString().trim();
                boolean result = new Check_Phonenum().isPhone(phonenum);
                System.out.println("result的状态为" + result + "   手机号码为 " + phonenum);
                if (phonenum == null || result == false) {
                    Toast.makeText(getApplicationContext(), "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tvGetMsgcode.getText().equals("获取验证码") || tvGetMsgcode.getText().equals("重新发送")) {
                    handler.sendEmptyMessageDelayed(100, 0);
                }
                break;

            case R.id.btn_change:
                //确认修改
                String new_pass = etChangePass.getText().toString().trim();
                String new_check_pass = etCheckPass.getText().toString().trim();
                String code = etChangeMsgcode.getText().toString().trim();
                if (!new_pass.equals(new_check_pass)) {
                    Toast.makeText(getApplicationContext(), "两次密码输入不一致", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (code == null) {
                    Toast.makeText(getApplicationContext(), "请填写验证码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (new_pass.length() <8){
                    Toast.makeText(getApplicationContext(), "您输入的密码太简单！", Toast.LENGTH_SHORT).show();
                    return;
                }
                Toast.makeText(getApplicationContext(), "修改成功！", Toast.LENGTH_SHORT).show();
                finish();
                //修改密码之后要将isChange改为true
                LoginActivity.isChange = true;

                break;
        }
    }

}

