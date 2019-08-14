package com.example.nine.mine.activity;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.builder.OptionsPickerBuilder;
import com.bigkoo.pickerview.builder.TimePickerBuilder;
import com.bigkoo.pickerview.listener.OnOptionsSelectListener;
import com.bigkoo.pickerview.listener.OnTimeSelectListener;
import com.bigkoo.pickerview.view.OptionsPickerView;
import com.bigkoo.pickerview.view.TimePickerView;
import com.example.nine.R;
import com.example.nine.mine.bean.UpdateMyInforBean;
import com.example.nine.mine.mybean.MyInformation;
import com.example.nine.utils.BaseActivity;
import com.example.nine.utils.CheckCard;
import com.example.nine.utils.Check_Phonenum;
import com.google.gson.Gson;
import com.lljjcoder.citypickerview.widget.CityPicker;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinalApi;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;
import cn.finalteam.rxgalleryfinal.ui.base.IRadioImageCheckedListener;

public class MyInformationActivity extends BaseActivity {

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.rv_mymsg_photo)
    RoundedImageView rvMymsgPhoto;
    @BindView(R.id.et_mymsg_name)
    EditText etMymsgName;
    @BindView(R.id.et_mymsg_phone)
    EditText etMymsgPhone;
    @BindView(R.id.tv_mymsg_address)
    TextView tvMymsgAddress;
    @BindView(R.id.tv_mymsg_sex)
    TextView tvMymsgSex;
    @BindView(R.id.tv_mymsg_birth)
    TextView tvMymsgBirth;
    @BindView(R.id.et_mymsg_cardcode)
    EditText etMymsgCardcode;
    @BindView(R.id.btn_savemsg)
    Button btnSavemsg;

    //是否变换头像的标志
    private Boolean changePhoto = false;
    private Bitmap bitmap;//头像的bitmap
    private File headImg; //将裁剪完成的图片转为File类型
    public Boolean haveHead = false; //判断是否有图片

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_information);
        ButterKnife.bind(this);
        initData();
    }

    @OnClick({R.id.iv_back, R.id.rv_mymsg_photo, R.id.tv_mymsg_address, R.id.tv_mymsg_sex, R.id.tv_mymsg_birth, R.id.btn_savemsg})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_back:
                finish();
                break;
            case R.id.rv_mymsg_photo:
                selectPhoto();

                break;
            case R.id.tv_mymsg_address:
                //判断输入法的隐藏状态
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(view.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    selectAddress();//调用CityPicker选取区域
                }
                break;
            case R.id.tv_mymsg_sex:
                InputMethodManager imm1 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm1.isActive()) {
                    imm1.hideSoftInputFromWindow(view.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    sexSelect();
                }
                break;
            case R.id.tv_mymsg_birth:
                InputMethodManager imm2 = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm2.isActive()) {
                    imm2.hideSoftInputFromWindow(view.getWindowToken(),
                            InputMethodManager.HIDE_NOT_ALWAYS);
                    dataSelect();
                }

                break;
            case R.id.btn_savemsg:
                //保存信息
                saveMsg();
                getJsonMsg();
                break;
        }
    }



    /**
     * 初始化数据
     */
    private void initData() {
        //将后台获取的信息展示
        setMyinfor();
        //将自己的头像信息展示
        Bitmap userphoto = MyInformation.getInstance().getUserphoto();
        if (userphoto == null) {
            rvMymsgPhoto.setImageResource(R.drawable.nophoto);
        } else {
            rvMymsgPhoto.setImageBitmap(userphoto);
        }
    }

    /**
     * 进来之后应该先将信息展示
     */
    private void setMyinfor() {
//        etMymsgName.setText(MyMsg.getIntence().getName());
//        etMymsgPhone.setText(MyMsg.getIntence().getUserPhone());
//        rvMymsgPhoto.setImageBitmap(MyMsg.getIntence().getUserphoto());
        //住址：
        String address = MyInformation.getInstance().getProvince()+"-"+MyInformation.getInstance().getCity()+"-"+MyInformation.getInstance().getArea();
        etMymsgName.setText(MyInformation.getInstance().getNickName());
        etMymsgPhone.setText(MyInformation.getInstance().getPhone());
        tvMymsgAddress.setText(address);
        String sex = MyInformation.getInstance().getSex();
        if (!TextUtils.isEmpty(sex)){
            if (sex.equals(0)){
                tvMymsgSex.setText("男");
            }else {
                tvMymsgSex.setText("女");
            }
        }
        etMymsgCardcode.setText(MyInformation.getInstance().getIdentityNum());
        rvMymsgPhoto.setImageBitmap(MyInformation.getInstance().getUserphoto());
    }

    //保存信息
    private void saveMsg() {
        String name = etMymsgName.getText().toString().trim();
        String phonenum = etMymsgPhone.getText().toString().trim();
        String address = tvMymsgAddress.getText().toString().trim();
        String sex = tvMymsgSex.getText().toString().trim();
        String birth = tvMymsgBirth.getText().toString().trim();
        String cardcode = etMymsgCardcode.getText().toString().trim();
        //确保照片信息不为空
        if (headImg == null) {
            Toast.makeText(getApplicationContext(), "请上传照片", Toast.LENGTH_SHORT).show();
            return;
        }

        //填写必要的信息
        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(phonenum) || TextUtils.isEmpty(address) || TextUtils.isEmpty(sex) ||
                TextUtils.isEmpty(birth) || TextUtils.isEmpty(cardcode)) {
            Toast.makeText(getApplicationContext(), "请填写必要的个人信息", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean resultphone = new Check_Phonenum().isPhone(phonenum);
        if (resultphone == false) {
            Toast.makeText(getApplicationContext(), "请输入正确的手机号", Toast.LENGTH_SHORT).show();
            return;
        }
        boolean result = CheckCard.checkIdcard(cardcode);
        if (result == false) {
            Toast.makeText(getApplicationContext(), "请填写正确的身份证号码", Toast.LENGTH_SHORT).show();
            return;
        }
        //将信息传递给后台


        if (onUpdataInformationLintener != null) {
            onUpdataInformationLintener.onUpdataInfor();
        }
        Toast.makeText(getApplicationContext(), "保存成功！", Toast.LENGTH_SHORT).show();
        finish();
    }

    /**
     * 解析json数据
     */
    public void getJsonMsg() {
        String jsondata = "{\n" +
                "  \"msg\": \"操作成功\",\n" +
                "  \"code\": 0,\n" +
                "  \"data\": {}\n" +
                "}";

        Gson gson = new Gson();
        UpdateMyInforBean updateMyInforBean = gson.fromJson(jsondata, UpdateMyInforBean.class);
        int code = updateMyInforBean.getCode();
        String msg = updateMyInforBean.getMsg();
        System.out.println("更新我的信息json数据为  "+code +"  "+msg);
    }

    /**
     * 性别选择器
     */
    public void sexSelect() {
        final ArrayList list = new ArrayList();
        list.add("男");
        list.add("女");
        final OptionsPickerView majorselect = new OptionsPickerBuilder(MyInformationActivity.this, new OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3, View v) {
                tvMymsgSex.setText(list.get(options1).toString());
            }
        })
                .build();
        majorselect.setPicker(list);
        majorselect.show();
    }

    /**
     * 日期选择器
     */
    private void dataSelect() {
        @SuppressLint("ResourceAsColor") final TimePickerView pvTime = new TimePickerBuilder(MyInformationActivity.this, new OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date, View v) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String data = sdf.format(date);
                tvMymsgBirth.setText(data);
            }
        }).setContentTextSize(18)
                .build();
        pvTime.show();
    }

    /**
     * 三级联动选择地区的选择器
     */
    private void selectAddress() {
        CityPicker cityPicker = new CityPicker.Builder(MyInformationActivity.this)
                .textSize(16)
                .title("地址选择")
                .titleBackgroundColor("#FFFFFF")
                .confirTextColor("#696969")
                .cancelTextColor("#696969")
                .province("江苏省")
                .city("常州市")
                .district("天宁区")
                .textColor(Color.parseColor("#000000"))
                .provinceCyclic(true)
                .cityCyclic(false)
                .districtCyclic(false)
                .visibleItemsCount(7)
                .itemPadding(15)
                .onlyShowProvinceAndCity(false)
                .build();
        cityPicker.show();
        //监听方法，获取选择结果
        cityPicker.setOnCityItemClickListener(new CityPicker.OnCityItemClickListener() {
            @Override
            public void onSelected(String... citySelected) {
                //省份
                String province = citySelected[0];
                //城市
                String city = citySelected[1];
                //区县（如果设定了两级联动，那么该项返回空）
                String district = citySelected[2];
                //为TextView赋值
                tvMymsgAddress.setText(province.trim() + "-" + city.trim() + "-" + district.trim());
            }
        });
    }

    private void selectPhoto() {
        RxGalleryFinalApi instance = RxGalleryFinalApi.getInstance(MyInformationActivity.this);
        instance.openGalleryRadioImgDefault(
                new RxBusResultDisposable<ImageRadioResultEvent>() {
                    @Override
                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                        Log.i("只要选择图片就会触发", "触发");
                        changePhoto = true;
                    }
                })
                .onCropImageResult(
                        new IRadioImageCheckedListener() {
                            @Override
                            public void cropAfter(Object t) {
                                Log.i("", "裁剪完成");
                                System.out.println(t);
                                bitmap = BitmapFactory.decodeFile(t.toString());
                                rvMymsgPhoto.setImageBitmap(bitmap);
                                headImg = new File(t.toString());//裁剪完成的图片转为File类型（可以直接给后台传递）
                                haveHead = true;
                            }

                            @Override
                            public boolean isActivityFinish() {
                                Log.i("", "返回false不关闭，返回true则为关闭");
                                return true;
                            }
                        });
    }

    /***
     * 使用接口再页面finish的时候另外一个页面重新获取信息
     */

    public static interface onUpdataInformationLintener {
        //接口中回调的方法
        void onUpdataInfor();
    }

    public static MyInformationActivity.onUpdataInformationLintener onUpdataInformationLintener;

    /**
     * 2.使用者传递接口的实例
     */
    public static void SetOnUpdataInformationLintener(MyInformationActivity.onUpdataInformationLintener l) {
        onUpdataInformationLintener = l;
    }


}
