package com.example.nine.mine.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import com.example.nine.R;
import com.example.nine.mine.myUtil.AdapterDate;
import com.example.nine.mine.myUtil.OnSignedSuccess;
import com.example.nine.mine.myUtil.SignDate;
import com.example.nine.mine.myUtil.getNetDate;
import com.example.nine.utils.DensityUtil;
import com.makeramen.roundedimageview.RoundedImageView;

import java.net.URL;
import java.net.URLConnection;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignActivity extends Activity {
    @BindView(R.id.iv_sign)
    RoundedImageView ivSign;
    @BindView(R.id.tv_goldnum)
    TextView tvGoldnum;
    @BindView(R.id.tv_signrule)
    TextView tvSignrule;
    @BindView(R.id.tv_sign)
    TextView tvSign;
    @BindView(R.id.iv_back)
    ImageView ivBack;
    private SignDate signDate;

    //轮播图
    private ViewPager vp_pics;
    private LinearLayout ll_point_group;
    private ArrayList<ImageView> imageList = new ArrayList();//初始化一个数组用来存放图片资源
    private int prePosition = 0;
    private Boolean isDragging = false;//设置一个拖拽的标志
    private int[] imageIds = new int[]{ //定义一个存放图片id的数组
            R.drawable.sf1,
            R.drawable.sf2,
            R.drawable.sf3,
            R.drawable.sf4,
            R.drawable.sf5
    };
    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            //设置自己滑动
            vp_pics.setCurrentItem(vp_pics.getCurrentItem() + 1);
            //发送空消息，并设置延迟时间
            sendEmptyMessageDelayed(0, 4000);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_sign);
        ButterKnife.bind(this);
        changeSignMsg();
        initView();
        initShuffing();
        initData();
        initEvent();
    }

    //接口回调改变信息
    private void changeSignMsg() {
        AdapterDate.setOnSignShowLintener(new AdapterDate.onSignShowLintener() {
            @Override
            public void onSignShowChange() {
                tvSign.setText("明日签到");
            }
        });
    }

    /**
     * 初始化事件
     */
    private void initEvent() {
        //这是签到成功之后的回调
        signDate.setOnSignedSuccess(new OnSignedSuccess() {
            @Override
            public void OnSignedSuccess() {
                tvSign.setText("明日签到");
            }
        });
        AdapterDate.setOnGetDataLintener(new AdapterDate.onGetDataLintener() {
            @Override
            public void onDataChange() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        getNetTime();
                    }
                }).start();
            }
        });
    }

    /**
     * 初始化数据
     */
    private void initData() {
        //模拟数据
        if (onListLintener != null) {
            onListLintener.onListChange();
        }
    }

    /**
     * 初始化控件
     */
    private void initView() {
        signDate = findViewById(R.id.signDate);
        //下面几个为轮播图的属性
        vp_pics = findViewById(R.id.vp_pics);
        ll_point_group = findViewById(R.id.ll_point);
    }


    @OnClick({R.id.iv_sign, R.id.tv_goldnum, R.id.tv_signrule, R.id.iv_back})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_sign:
                if (tvSign.getText().equals("签到")) {
                    //点击之后就便利签到表签到
                    if (onSignChangeLintener != null) {
                        onSignChangeLintener.onSignChange();
                    }
                } else {
                    Toast.makeText(getApplicationContext(), "请明天再来签到", Toast.LENGTH_SHORT).show();
                }

                break;
            case R.id.tv_goldnum:
                break;
            case R.id.tv_signrule:
                break;
            case R.id.iv_back:
                finish();
                break;

        }
    }

    /**
     * 轮播图
     */
    /**
     * 初始化数据的方法
     */
    private void initShuffing() {
        //初始化图片，将图片放入到List集合中
        for (int i = 0; i < imageIds.length; i++) {
            ImageView imageView = new ImageView(this);
            imageView.setBackgroundResource(imageIds[i]);
            imageList.add(imageView);
            //添加小白点指示器
            ImageView point = new ImageView(this);
            point.setBackgroundResource(R.drawable.point);
            //对里面的指示器设置宽高
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(DensityUtil.dip2px(getApplicationContext(),8),
                    DensityUtil.dip2px(getApplicationContext(),8));
            if (i == 0) {
                //让默认第一个显示为高亮
                point.setEnabled(true);
            } else {
                point.setEnabled(false);
                params.leftMargin = 8;
            }
            point.setLayoutParams(params);
            ll_point_group.addView(point);

        }
        //对ViewPager设置监听
        vp_pics.addOnPageChangeListener(new MyViewPagerListener());
        //准备Adapter对象
        vp_pics.setAdapter(new MyPagerAdapter());
        //为了让图片能左右无限滑动在开始加载的时候让页面停留在数量中间的位置
        //必须保证item的数量为页面数的整数倍
        int item = Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % imageList.size();
        //该方法放在setAdapter之后  这个方法也可以设置自动滚动
        vp_pics.setCurrentItem(item);
        //发送空消息，准备滑动view
        handler.sendEmptyMessageDelayed(0, 4000);

    }


    /**
     * 自定义的类对ViewPager的监听实现了OnPageChangeListener接口
     * 对ViewPager设置的监听
     */
    private class MyViewPagerListener implements ViewPager.OnPageChangeListener {
        /**
         * 当页面滑动的监听
         *
         * @param position             当前位置
         * @param positionOffset       页面滑动的百分比
         * @param positionOffsetPixels 页面滑动的像数
         */
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            //使用的时候需要换成当前的position
        }

        /**
         * 当页面滑动后的监听
         *
         * @param position 滑动后的位置
         */
        @Override
        public void onPageSelected(int position) {
            //使用的时候需要换成当前的position
            int realPosition = position % imageList.size();
            //指示器发生变化
            ll_point_group.getChildAt(prePosition).setEnabled(false);
            ll_point_group.getChildAt(realPosition).setEnabled(true);
            //更新position的位置
            prePosition = realPosition;
        }

        /**
         * 当页面的状态改变的时候调用的方法
         *
         * @param state
         */
        @Override
        public void onPageScrollStateChanged(int state) {
            switch (state) {
                case ViewPager.SCROLL_STATE_DRAGGING:
                    //页面拖拽
                    isDragging = true;
                    handler.removeCallbacksAndMessages(null);
                    break;
                case ViewPager.SCROLL_STATE_IDLE:
                    if (isDragging){
                        handler.removeCallbacksAndMessages(null);
                        handler.sendEmptyMessageDelayed(0,4000);
                    }
                    isDragging = false;
                    //页面空闲
                    break;
                case ViewPager.SCROLL_STATE_SETTLING:
                    //页面滑动
                    break;
            }
        }

    }

    /**
     * 自定义的PagerAdapter的类
     * 必须重写下面的4个方法，默认为2个，自己需要添加2个进去
     */
    private class MyPagerAdapter extends PagerAdapter {
        @Override
        public int getCount() {
            //为了能让图片无限滑动设置返回值为无限大
            return Integer.MAX_VALUE;
        }

        /**
         * 详单与getView
         * 返回一个View对象  还需要将view添加到container中
         * @param container ViewPgaer
         * @param position  位置
         * @return
         */
        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, final int position) {
            //设置真正的位置
            int realPosition = position % imageList.size();
            ImageView imageView = imageList.get(realPosition);
            container.addView(imageView);
            return imageView;
        }

        /**
         * 用来判断当前的view是否为instantiateItem（object）返回的对象
         */
        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }

        /**
         * 释放资源
         *
         * @param container ViewPager
         * @param position  要释放的位置
         * @param object    要释放的view
         */
        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            //销毁没用的view
            container.removeView((View) object);
        }
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
            runOnUiThread(new Runnable() {
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

    /***
     * 使用接口再页面finish的时候另外一个页面重新获取信息
     */

    public static interface onSignDataLintener {
        //接口中回调的方法
        void onSignChange();
    }

    public static onSignDataLintener onSignChangeLintener;

    /**
     * 2.使用者传递接口的实例
     */
    public static void setOnSignDataLintener(onSignDataLintener l) {
        onSignChangeLintener = l;
    }


    /***
     * 使用接口再页面finish的时候另外一个页面重新获取信息
     */

    public static interface onListLintener {
        //接口中回调的方法
        void onListChange();
    }

    public static onListLintener onListLintener;

    /**
     * 2.使用者传递接口的实例
     */
    public static void setOnListLintener(onListLintener l) {
        onListLintener = l;
    }
}
