package com.example.nine.mine.fragment;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nine.R;
import com.example.nine.base.BaseFragment;
import com.example.nine.mine.activity.Dcteam_ApreocrdActivity;
import com.example.nine.mine.activity.FeedBackActivity;
import com.example.nine.mine.activity.LoginActivity;
import com.example.nine.mine.activity.MsgLoginActivity;
import com.example.nine.mine.activity.MyInformationActivity;
import com.example.nine.mine.activity.NewUserActivity;
import com.example.nine.mine.activity.SettingActivity;
import com.example.nine.mine.activity.SignActivity;
import com.example.nine.mine.bean.QueryMyInfoBean;
import com.example.nine.mine.myUtil.getNetDate;
import com.example.nine.mine.mybean.MyInformation;
import com.example.nine.utils.CacheUtils;
import com.google.gson.Gson;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.IOException;
import java.net.ConnectException;
import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import static com.example.nine.R.drawable.nophoto;

public class MineFragment extends BaseFragment {

    @BindView(R.id.ll_page_mine)
    LinearLayout llPageMine;
    @BindView(R.id.ll_record_mine)
    LinearLayout llRecordMine;
    @BindView(R.id.photo_mine)
    RoundedImageView photoMine;
    @BindView(R.id.tv_name_mine)
    TextView tvNameMine;
    @BindView(R.id.iv_setting)
    ImageView ivSetting;
    @BindView(R.id.funsnum_mine)
    TextView funsnumMine;
    @BindView(R.id.attentionnum_mine)
    TextView attentionnumMine;
    @BindView(R.id.goldnum_mine)
    TextView goldnumMine;
    @BindView(R.id.daysign_mine)
    TextView daysignMine;
    @BindView(R.id.ll_danceteam_mine)
    LinearLayout llDanceteamMine;
    @BindView(R.id.ll_feedback_mine)
    LinearLayout llFeedbackMine;
    private Unbinder mUnbinder;
//    public static Boolean isNewUser = false;
    @Override
    public void initData() {
        super.initData();
        initEvent();
        //请求网络资源是耗时操作。放到子线程中进行
        new Thread(new Runnable() {
            @Override
            public void run() {
                getNetTime();
            }
        }).start();
        //点击我的页面之后就判断登陆状态
        getMyMsg();
//        //如果是新用户就完善信息
//        if (isNewUser){
//            toastDialog();
//        }
    }

    /**
     * 将我的信息展示
     */
    private void getMyMsg() {
        Boolean loginStatus = CacheUtils.getBoolean(getContext(),"loginstatus");
        System.out.println("loginStatus的状态为"+loginStatus);
        if (loginStatus == true ) {
            //请求后台数据获取信息
            connectToHttp();
            //解析json数据
            getJsonData();
            //登陆状态
            Bitmap userphoto = MyInformation.getInstance().getUserphoto();
            String name = MyInformation.getInstance().getNickName();
            System.out.println("头像的信息为" + userphoto + "  " + "昵称信息" + name);
            if (userphoto == null) {
                photoMine.setImageResource(nophoto);
            } else {
                photoMine.setImageBitmap(userphoto);
            }
            if (name == null) {
                tvNameMine.setText("获取信息失败，请重新登陆");
            } else {
                tvNameMine.setText(name);
            }
        } else {
            //未登陆状态
            photoMine.setImageResource(nophoto);
            tvNameMine.setText("请登录");
        }
    }

    /**
     * 点击创建新用户和登陆之后更新状态
     */
    private void initEvent() {
        LoginActivity.setOnDataChangeLintener(new LoginActivity.onDataChangeLintener() {
            @Override
            public void onDataChange() {
                getMyMsg();
            }
        });
        MsgLoginActivity.setOnDataChangeLintener(new MsgLoginActivity.onDataChangeLintener() {
            @Override
            public void onDataChange() {
                //将登陆的状态设为true
                getMyMsg();
            }
        });
        NewUserActivity.setOnDataChangeLintener(new NewUserActivity.onCreateUserLintener() {
            @Override
            public void onCreateUser() {
                getMyMsg();
            }
        });
        SettingActivity.setOnCountexitLintener(new SettingActivity.onCountexitLintener() {
            @Override
            public void onCountexit() {
                getMyMsg();
            }
        });
        MyInformationActivity.SetOnUpdataInformationLintener(new MyInformationActivity.onUpdataInformationLintener() {
            @Override
            public void onUpdataInfor() {
                getMyMsg();
            }
        });
    }

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.fragment_mine, null);
        mUnbinder = ButterKnife.bind(this, view);
        return view;
    }

    @OnClick({R.id.photo_mine, R.id.tv_name_mine, R.id.iv_setting, R.id.daysign_mine, R.id.ll_danceteam_mine, R.id.ll_feedback_mine,
            R.id.ll_page_mine, R.id.ll_record_mine})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.tv_name_mine:
            case R.id.photo_mine:
                if (CacheUtils.getBoolean(getContext(),"loginstatus")) {
                    //登陆的状态进入之后查看个人信息
                    Intent intent = new Intent(mContext, MyInformationActivity.class);
                    startActivity(intent);
                } else {
                    //进入登陆页面或者查看个人信息的页面
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.iv_setting:
                //进入设置页面
                Intent intent2 = new Intent(mContext, SettingActivity.class);
                startActivity(intent2);
                break;
            case R.id.daysign_mine:
                //每日签到
                if (CacheUtils.getBoolean(getContext(),"loginstatus")) {
                    if (getNetDate.getInstance().getNetdate() == null) {
                        getNetTime();
                    }
                    Intent intent1 = new Intent(mContext, SignActivity.class);
                    startActivity(intent1);
                } else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_danceteam_mine:
                //我的舞队
                if (CacheUtils.getBoolean(getContext(),"loginstatus")) {

                } else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_page_mine:
                //我的主页
                if (CacheUtils.getBoolean(getContext(),"loginstatus")) {

                } else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_feedback_mine:
                //反馈与帮助
                if (CacheUtils.getBoolean(getContext(),"loginstatus")) {
                    Intent intent_feed = new Intent(mContext, FeedBackActivity.class);
                    startActivity(intent_feed);
                } else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                }
                break;
            case R.id.ll_record_mine:
                //舞队申请记录
                if (CacheUtils.getBoolean(getContext(),"loginstatus")) {
                    Intent intent = new Intent(mContext, Dcteam_ApreocrdActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(mContext, LoginActivity.class);
                    startActivity(intent);
                }
                break;
        }
    }


//    /**
//     * 提醒用户完善信息
//     */
//    public void toastDialog() {
//        /**
//         * 确定取消对话框
//         */
//        isNewUser = false;
//        android.app.AlertDialog.Builder dialog = new android.app.AlertDialog.Builder(mContext);
//        dialog.setTitle("提示");
//        dialog.setMessage("目前您尚未完善信息，是否现在去完善？");
//        dialog.setPositiveButton("完善信息", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//                //去完善个人信息
//                Toast.makeText(getActivity(), "跳转完善个人信息的页面", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getActivity(), MyInformationActivity.class);
//                startActivity(intent);
//            }
//        });
//        dialog.setNegativeButton("稍后完善", new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialogInterface, int i) {
//
//            }
//        });
//        //记得show
//        dialog.show();
//    }

    /**
     * 链接后台
     */
    public void connectToHttp(){
        String url = "http://111.11.7.208:8080/app/mock/76/user/appUser/app/userMsg";
        OkHttpClient okHttpClient = new OkHttpClient();
        HttpUrl.Builder builder = HttpUrl.parse(url).newBuilder();
        builder.setQueryParameter("member_id",String.valueOf(1))
                .setQueryParameter("isAgent",String.valueOf(0));
        final Request request = new Request.Builder()
                .url(builder.build())
                .get()//默认就是GET请求，可以不写
                .build();
        Call call = okHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                System.out.println("onFailure");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext,"onFailure",Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                System.out.println("onResponse" + response.body().string());
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(mContext,"onResponse",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    /**
     * 解析json数据
     */
    public void getJsonData() {
        String jsondata = "{\n" +
                "  \"code\": 0,\n" +
                "  \"data\": {\n" +
                "    \"appUserMsg\": {\n" +
                "      \"area\": \"3\",\n" +
                "      \"attentionNum\": 23,\n" +
                "      \"country\": \"中国\",\n" +
                "      \"address\": \"测试地址\",\n" +
                "      \"fansNum\": 34,\n" +
                "      \"city\": \"2\",\n" +
                "      \"level\": 1,\n" +
                "      \"nickName\": \"阿七\",\n" +
                "      \"sex\": \"0\",\n" +
                "      \"icon\": \"/icon\",\n" +
                "      \"goldCoin\": 43,\n" +
                "      \"levelName\": \"一级\",\n" +
                "      \"pkTotal\": 14,\n" +
                "      \"identityNum\": \"133208166996581365\",\n" +
                "      \"province\": \"1\",\n" +
                "      \"phone\": \"15535327416\",\n" +
                "      \"levelCoin\": \"/level\",\n" +
                "      \"pkSurplus\": 5\n" +
                "    }\n" +
                "  },\n" +
                "  \"msg\": \"\"\n" +
                "}";
        Gson gson = new Gson();
        QueryMyInfoBean queryMyInfoBean = gson.fromJson(jsondata, QueryMyInfoBean.class);
        int code = queryMyInfoBean.getCode();
        String msg = queryMyInfoBean.getMsg();

        String nickName = queryMyInfoBean.getData().getAppUserMsg().getNickName();
        String phone = queryMyInfoBean.getData().getAppUserMsg().getPhone();
        String area = queryMyInfoBean.getData().getAppUserMsg().getArea();
        int attentionNum = queryMyInfoBean.getData().getAppUserMsg().getAttentionNum();
        String country = queryMyInfoBean.getData().getAppUserMsg().getCountry();
        String address = queryMyInfoBean.getData().getAppUserMsg().getAddress();
        int fansNum = queryMyInfoBean.getData().getAppUserMsg().getFansNum();
        String city = queryMyInfoBean.getData().getAppUserMsg().getCity();
        int level = queryMyInfoBean.getData().getAppUserMsg().getLevel();
        String sex = queryMyInfoBean.getData().getAppUserMsg().getSex();
        String icon = queryMyInfoBean.getData().getAppUserMsg().getIcon();
        int goldCoin = queryMyInfoBean.getData().getAppUserMsg().getGoldCoin();
        String levelName = queryMyInfoBean.getData().getAppUserMsg().getLevelName();
        int pkTotal = queryMyInfoBean.getData().getAppUserMsg().getPkTotal();
        String identityNum = queryMyInfoBean.getData().getAppUserMsg().getIdentityNum();
        String province = queryMyInfoBean.getData().getAppUserMsg().getProvince();
        String levelCoin = queryMyInfoBean.getData().getAppUserMsg().getLevelCoin();
        int pkSurplus = queryMyInfoBean.getData().getAppUserMsg().getPkSurplus();
        System.out.println("查询个人信息得到的json数据为 "+ queryMyInfoBean.getData().getAppUserMsg().toString());

        MyInformation.getInstance().setNickName(nickName);
        MyInformation.getInstance().setPhone(phone);
        MyInformation.getInstance().setArea(area);
        MyInformation.getInstance().setAttentionNum(attentionNum);
        MyInformation.getInstance().setCountry(country);
        MyInformation.getInstance().setAddress(address);
        MyInformation.getInstance().setFansNum(fansNum);
        MyInformation.getInstance().setCity(city);
        MyInformation.getInstance().setLevel(level);
        MyInformation.getInstance().setSex(sex);
        MyInformation.getInstance().setIcon(icon);
        MyInformation.getInstance().setGoldCoin(goldCoin);
        MyInformation.getInstance().setLevelName(levelName);
        MyInformation.getInstance().setPkTotal(pkTotal);
        MyInformation.getInstance().setIdentityNum(identityNum);
        MyInformation.getInstance().setProvince(province);
        MyInformation.getInstance().setLevelCoin(levelCoin);
        MyInformation.getInstance().setPkSurplus(pkSurplus);
    }

    /**
     * 获取网络的时间
     */
    private void getNetTime() {
        URL url = null;//取得资源对象
        try {
            url = new URL("http://www.baidu.com");//获取百度的日期
            URLConnection uc = url.openConnection();//生成连接对象
            uc.connect(); //发出连接
            long ld = uc.getDate(); //取得网站日期时间
            DateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(ld);
            final String format = formatter.format(calendar.getTime());
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    System.out.println("当前的网络日期为" + format);
                    //截取网络当前的日
                    System.out.println(format.substring(8, 10));
                    getNetDate.getInstance().setDay(format.substring(8, 10).trim());
                    //将格式化好的时间存到单例类中
                    getNetDate.getInstance().setNetdate(format);
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

}
