package com.example.nine.home.pager;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nine.R;
import com.example.nine.base.BasePager;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ComPager extends BasePager {
    @BindView(R.id.tablayout)
    TabLayout tablayout;
    @BindView(R.id.viewpager)
    ViewPager viewpager;
    //  private TextView textView;

    public ComPager(Context context) {
        super(context);
    }
    private ArrayList list;
    @Override
    public View initView() {
        View view = View.inflate(context, R.layout.home_pager, null);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void initData() {
        super.initData();
        list = new ArrayList();
        list.add(0,"同城");
        list.add(1,"小窍门");
        list.add(2,"运动");
        list.add(3,"美食");
        list.add(4,"热舞");
        list.add(5,"看世界");
//        textView.setText("这是同城页面");

        viewpager.setAdapter(new MyNewsmenuDetailPagerAdapter());

        tablayout.setupWithViewPager(viewpager);

        viewpager.addOnPageChangeListener(new MyOnPageChangeListener());

        tablayout.setTabMode(TabLayout.MODE_FIXED);
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
//
            TextView textView = new TextView(context);
            textView.setText("nihao");
            container.addView(textView);
//            BasePager basePager = basePagers.get(position);
//            View view = basePager.rootView;
//            basePager.initData();
//            container.addView(view);
            return textView;
        }

        @Override
        public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            container.removeView((View) object);
        }

        @Override
        public int getCount() {
            return list.size();
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
}
