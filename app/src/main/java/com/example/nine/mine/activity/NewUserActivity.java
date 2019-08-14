package com.example.nine.mine.activity;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.IpSecManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nine.MainActivity;
import com.example.nine.R;
import com.example.nine.mine.bean.NewuserBean;
import com.example.nine.mine.mybean.MyInformation;
import com.example.nine.utils.BaseActivity;
import com.example.nine.utils.Check_Phonenum;
import com.example.nine.utils.UpdateMsg;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;

import org.jetbrains.annotations.NotNull;

import java.io.File;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import cn.finalteam.rxgalleryfinal.ui.base.IRadioImageCheckedListener;
import cn.smssdk.EventHandler;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.Response;

public class NewUserActivity extends BaseActivity {

//    @BindView(R.id.rv_new_photo)
//    RoundedImageView rvNewPhoto;
    @BindView(R.id.et_new_phone)
    EditText etNewPhone;
    @BindView(R.id.et_new_username)
    EditText etNewUsername;
    @BindView(R.id.et_new_password)
    EditText etNewPassword;
    @BindView(R.id.et_new_msgcode)
    EditText etNewMsgcode;
    @BindView(R.id.tv_get_msgcode)
    TextView tvGetMsgcode;
    @BindView(R.id.btn_new_user)
    Button btnNewUser;
    @BindView(R.id.iv_back)
    ImageView ivBack;

    private Bitmap bitmap;//头像的bitmap
    private File headImg; //将裁剪完成的图片转为File类型
    public Boolean haveHead = false; //判断是否有图片
    private String username;
    //    private boolean canCreateUser = false;
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
    private EventHandler eh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_new_user);
        ButterKnife.bind(this);

//        initMgsCode();
    }

//

    /**
     * //     * 短信验证码的回调
     * //
     */
//    private void initMgsCode() {
//        eh = new EventHandler() {
//            public void afterEvent(int event, int result, Object data) {
//                // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
//                Message msg = new Message();
//                msg.arg1 = event;
//                msg.arg2 = result;
//                msg.obj = data;
//                new Handler(Looper.getMainLooper(), new Handler.Callback() {
//                    @Override
//                    public boolean handleMessage(Message msg) {
//                        int event = msg.arg1;
//                        int result = msg.arg2;
//                        Object data = msg.obj;
//                        if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
//                            if (result == SMSSDK.RESULT_COMPLETE) {
//                                // TODO 处理成功得到验证码的结果
//                                System.out.println("得到验证码");
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Toast.makeText(getApplicationContext(), "" + "获取成功", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                                // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
//                            } else {
//                                // TODO 处理错误的结果
//                                System.out.println("未得到验证码");
//                                ((Throwable) data).printStackTrace();
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        Toast.makeText(getApplicationContext(), "获取失败", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            }
//                        } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
//                            if (result == SMSSDK.RESULT_COMPLETE) {
//                                // TODO 处理验证码验证通过的结果
//                                System.out.println("验证码校验成功");
//                                System.out.println("外被执行");
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        canCreateUser = true;
//                                        System.out.println("内被执行");
//                                        Toast.makeText(getApplicationContext(), "验证码校验成功", Toast.LENGTH_SHORT).show();
//                                        System.out.println(canCreateUser+"");
//                                        if (canCreateUser) {
//                                            //验证码校验成功
//                                            //利用http请求发送数据
//                                            //将账号密码保存到本地
//                                            writeMsg();
//                                            /**
//                                             * 将头像昵称个人信息传给后台保存在登陆的时候从后台获取
//                                             *  这里先保存到个人信息类中再下次安装会消失
//                                             */
//                                            MyMsg.getIntence().setUserphoto(bitmap);
//                                            MyMsg.getIntence().setName(username);
////                                            MyMsg.getIntence().setUserPhone(userphone);
//                                            LoginActivity.isChange = false;
//                                            //调用接口回调更新信息
//                                            if (onCreateUserLintener != null) {
//                                                onCreateUserLintener.onCreateUser();
//                                            }
//                                            Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
//                                            toastDialog();
//
//                                        } else {
//                                            //验证码错误
//                                            Toast.makeText(getApplicationContext(), "验证码错误", Toast.LENGTH_SHORT).show();
//                                        }
//                                        canCreateUser = false;
//                                    }
//                                });
//                            } else {
//                                // TODO 处理错误的结果
//                                System.out.println("验证码校验失败");
//                                System.out.println("外被执行wong");
//                                ((Throwable) data).printStackTrace();
//                                runOnUiThread(new Runnable() {
//                                    @Override
//                                    public void run() {
//                                        System.out.println("内被执行wong");
//                                        Toast.makeText(getApplicationContext(), "验证码校验失败", Toast.LENGTH_SHORT).show();
//                                    }
//                                });
//                            }
//                        }
//                        // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
//                        return false;
//                    }
//                }).sendMessage(msg);
//            }
//        };
//    }
    @OnClick({R.id.tv_get_msgcode, R.id.btn_new_user, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_get_msgcode:
                //获取短信验证码
                Toast.makeText(getApplicationContext(), "请稍后", Toast.LENGTH_SHORT).show();
                String phonenum = etNewPhone.getText().toString().trim();
                boolean resultphone = new Check_Phonenum().isPhone(phonenum);
                if (phonenum == null || resultphone == false) {
                    Toast.makeText(getApplicationContext(), "请输入正确的手机号码", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (tvGetMsgcode.getText().equals("获取验证码") || tvGetMsgcode.getText().equals("重新发送")) {
                    handler.sendEmptyMessageDelayed(100, 0);
//                    SMSSDK.registerEventHandler(eh);
//                    SMSSDK.getVerificationCode("86", phonenum);
                }
                break;
            case R.id.btn_new_user:
                //创建新用户
                username = etNewUsername.getText().toString().trim();
                String password = etNewPassword.getText().toString().trim();
                String userphone = etNewPhone.getText().toString().trim();
                String msgCode = etNewMsgcode.getText().toString().trim();
                if (TextUtils.isEmpty(userphone)) {
                    Toast.makeText(getApplicationContext(), "请输入手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                boolean result = new Check_Phonenum().isPhone(userphone);
                if (result == false) {
                    Toast.makeText(getApplicationContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(username) || TextUtils.isEmpty(password)) {
                    Toast.makeText(getApplicationContext(), "注册的昵称密码不能为空！", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password.length() <8){
                    Toast.makeText(getApplicationContext(), "您输入的密码太简单,请重新输入。", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if (msgCode == null){
//                    Toast.makeText(getApplicationContext(),"请输入短信验证码！",Toast.LENGTH_SHORT).show();
//                    return;
//                }

//                //开始请求校验
//                SMSSDK.submitVerificationCode("86", userphone, msgCode);

                //利用http请求发送数据
                sendMsgToHttp(userphone,password,msgCode,username);

                //将账号密码保存到本地
                writeMsg();
                LoginActivity.isChange = false;
                //调用接口回调更新信息
                if (onCreateUserLintener != null) {
                    onCreateUserLintener.onCreateUser();
                }
                //设置登陆的状态
                Toast.makeText(getApplicationContext(), "注册成功", Toast.LENGTH_SHORT).show();
                getJsonMsg();

                toastDialog();
                break;
            case R.id.iv_back:
                finish();
                break;
        }
    }

//    // 使用完EventHandler需注销，否则可能出现内存泄漏
//    protected void onDestroy() {
//        super.onDestroy();
//        SMSSDK.unregisterEventHandler(eh);
//    }

    /**
     *将注册的信息传给后台
     */
    public void sendMsgToHttp(String userPhone,String password,String vercode,String nickName){
        String url = "http://111.11.7.208:8080/app/mock/76/user/login/app/phoneRegister";

        OkHttpClient client = new OkHttpClient();
        RequestBody body = new FormBody.Builder()
                .add("phoneNumber",userPhone)
                .add("password",password)
                .add("vercode",vercode)
                .add("nickName",nickName)
                .build();
        okhttp3.Request request = new okhttp3.Request.Builder()
                .url(url)
                .post(body)
                .build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                System.out.println("响应失败"+e);
            }

            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                System.out.println("访问成功,数据为： "+response.toString());
            }
        });

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

        NewuserBean newuserBean = gson.fromJson(jsondata, NewuserBean.class);
        int code = newuserBean.getCode();
        String msg = newuserBean.getMsg();
        int userId = newuserBean.getData().getUserId();
        System.out.println("新建用户解析json获得的数据信息为  "+userId+"  "+msg+"  "+code);
    }


    /**
     * 登陆成功之后将新的信息记录
     */
    private void writeMsg() {
        String userPhone = etNewPhone.getText().toString().trim();
        String userPass = etNewPassword.getText().toString().trim();
        UpdateMsg updateMsg = new UpdateMsg(getApplicationContext());
        updateMsg.writeMsg(userPhone, userPass);
        updateMsg.writeStatus(true);
        updateMsg.writeAutoStatus(true);
    }

    public void toastDialog() {
        /**
         * 确定取消对话框
         */
        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(this);
        dialog.setCancelable(false);
        dialog.setTitle("提示");
        dialog.setMessage("目前您尚未完善信息，是否现在去完善？");
        dialog.setPositiveButton("完善信息", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //去完善个人信息
                Toast.makeText(getApplicationContext(), "跳转完善个人信息的页面", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MyInformationActivity.class);
                startActivity(intent);
                finish();
            }
        });
        dialog.setNegativeButton("稍后完善", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //记得show
        dialog.show();
    }
    /***
     * 使用接口再页面finish的时候另外一个页面重新获取信息
     */

    public static interface onCreateUserLintener {
        //接口中回调的方法
        void onCreateUser();
    }

    public static NewUserActivity.onCreateUserLintener onCreateUserLintener;

    /**
     * 2.使用者传递接口的实例
     */
    public static void setOnDataChangeLintener(NewUserActivity.onCreateUserLintener l) {
        onCreateUserLintener = l;
    }


    private void selectPhoto() {
        RxGalleryFinalApi instance = RxGalleryFinalApi.getInstance(NewUserActivity.this);
        instance.openGalleryRadioImgDefault(
                new RxBusResultDisposable<ImageRadioResultEvent>() {
                    @Override
                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                        Log.i("只要选择图片就会触发", "触发");
                    }
                })
                .onCropImageResult(
                        new IRadioImageCheckedListener() {
                            @Override
                            public void cropAfter(Object t) {
                                Log.i("", "裁剪完成");
                                System.out.println(t);
                                bitmap = BitmapFactory.decodeFile(t.toString());
//                                rvNewPhoto.setImageBitmap(bitmap);
                                headImg = new File(t.toString());//裁剪完成的图片转为File类型（可以直接给后台传递）
                                haveHead = true;
                            }

                            @Override
                            public boolean isActivityFinish() {
                                Log.i("", "返回false不关闭，返回true则为关闭");
                                return true;
                            }
                        });
        Toast.makeText(getApplicationContext(),"准备拍照",Toast.LENGTH_SHORT).show();
    }

}
