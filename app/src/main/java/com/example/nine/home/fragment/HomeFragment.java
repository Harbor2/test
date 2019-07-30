package com.example.nine.home.fragment;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.nine.R;
import com.example.nine.base.BaseFragment;
import com.example.nine.base.BasePager;
import com.example.nine.home.pager.ComPager;
import com.example.nine.home.pager.LivePager;
import com.example.nine.home.pager.VideoPager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.ib_search)
    ImageButton ibSearch;
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    //    @BindView(R.id.tv_search_home)
//    TextView tvSearchHome;
//    @BindView(R.id.tv_message_home)
//    TextView tvMessageHome;
//    @BindView(R.id.rv_home)
//    RecyclerView rvHome;
//    @BindView(R.id.ib_top)
//    ImageButton ibTop;
//    private HomeBean.ResultBean beanResult;
//    private HomeBeanAdapter homeBeanAdapter;
    private ArrayList list;
    private ArrayList<BasePager> basePagers;

//    private Handler mHandler = new Handler(){
//        @Override
//        public void handleMessage(Message msg) {
//            switch (msg.what){
//                case 0:
//                    homeBeanAdapter = new HomeBeanAdapter(mContext, beanResult);
//                    rvHome.setAdapter(homeBeanAdapter);
//                    GridLayoutManager manager = new GridLayoutManager(mContext, 1);
//
//                    manager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//                        @Override
//                        public int getSpanSize(int position) {
//                            if (position<=4){
//                                //隐藏
//                                ibTop.setVisibility(View.GONE);
//                            }else{
//                                //显示
//                                ibTop.setVisibility(View.VISIBLE);
//                            }
//
//                            return 1;
//                        }
//                    });
//                    rvHome.setLayoutManager(manager);
//
//
//                    break;
//            }
//        }
//    };

    @Override
    public View initView() {
        View view = View.inflate(mContext, R.layout.home_fragment, null);
        ButterKnife.bind(this, view);
        //  initListener();

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        list = new ArrayList();
        list.add(0,"直播");
        list.add(1,"视频");
        list.add(2,"同城");
        basePagers = new ArrayList<>();
        basePagers.add(0,new ComPager(mContext));
        basePagers.add(0,new VideoPager(mContext));
        basePagers.add(0,new LivePager(mContext));
        viewpager.setAdapter(new MyNewsmenuDetailPagerAdapter());

        tablayout.setupWithViewPager(viewpager);

        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());

        tablayout.setTabMode(TabLayout.MODE_FIXED);

        //    getDataFromNet();
    }
    class MyNewsmenuDetailPagerAdapter extends PagerAdapter {

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return (CharSequence) list.get(position);
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
//            TabDetailPager tabDetailPager = tabDetailPagers.get(position);
//            View  rootView= tabDetailPager.rootView;
//            tabDetailPager.initData();
//            container.addView(rootView);
            BasePager basePager = basePagers.get(position);
            View view = basePager.rootView;
            basePager.initData();
            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view==object;
        }
    }

    class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

//            if (position ==0){
//                //设置可以滑动
//                isEnableSliding(SlidingMenu.TOUCHMODE_FULLSCREEN);
//            }else {
//                //设置不可以滑动
//                isEnableSliding(SlidingMenu.TOUCHMODE_NONE);
//            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }
//    private void getDataFromNet() {
////        String url = "http://wwww.baidu.com";
//        OkHttpClient okHttpClient = new OkHttpClient();
//        final Request request = new Request.Builder()
//                .url(Contast.base_home_url)
//                .get()//默认就是GET请求，可以不写
//                .build();
//        Call call = okHttpClient.newCall(request);
//
//        call.enqueue(new Callback() {
//            @Override
//            public void onFailure(Call call, IOException e) {
//                Log.d("fail", "onFailure: " + e.getMessage());
//            }
//
//            @Override
//            public void onResponse(Call call, Response response) throws IOException {
//                //   Log.d("sucess", "onResponse: " + response.body().toString());
//                //    System.out.println("解析成功"+response.body().string());
//
//                String result = response.body().string();
//                System.out.println("解析成功" + result);
//
//                if (result != null) {
//                    processJson(result);
//
//                }
//            }
//        });
//
//    }

//    private void processJson(String result) {
//        //     Gson gson = new Gson();
//        HomeBean homeBean = JSON.parseObject(result, HomeBean.class);
//        // = gson.fromJson(result, HomeBean.class);
//        beanResult = homeBean.getResult();
//
//        if (beanResult != null) {
//
//            mHandler.sendEmptyMessage(0);
//
//        }
//
//    }
//
//    private void initListener() {
//        ibTop.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                rvHome.scrollToPosition(0);
//            }
//        });
//
//
//        //搜素的监听
//        tvSearchHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        //消息的监听
//        tvMessageHome.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                Intent intent = new Intent(mContext, MessageCenterActivity.class);
////                mContext.startActivity(intent);
//            }
//        });
//
//    }
}
