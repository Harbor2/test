package com.example.nine;

import android.os.Bundle;
import android.os.Handler;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.nine.base.BaseFragment;
import com.example.nine.mine.MineFragment;
import com.example.nine.home.fragment.BottomDialogFragment;
import com.example.nine.home.fragment.HomeFragment;
import com.example.nine.record.fragment.RecordFragment;
import com.example.nine.team.fragment.TeamFragment;
import com.example.nine.utils.AnimaUtils;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import butterknife.BindView;
import butterknife.ButterKnife;


public class MainActivity extends FragmentActivity {

    @BindView(R.id.frameLayout)
    FrameLayout frameLayout;

    @BindView(R.id.rg_main)
    RadioGroup rgMain;


    @BindView(R.id.rb_record)
    RadioButton rbRecord;
    @BindView(R.id.rb_team)
    RadioButton rbTeam;
    @BindView(R.id.rb_mine)
    RadioButton rbMine;

    @BindView(R.id.ivLuzhi)
    RoundedImageView ivLuzhi;

    @BindView(R.id.rb_recommend)
    RadioButton rbRecommend;

    private BaseFragment mContext;
    private ArrayList<BaseFragment> baseFragments;
    private int position = 0;
    private List idList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initFragment();

        initListener();



    }

    private void initFragment() {
        baseFragments = new ArrayList<>();
        baseFragments.add(new HomeFragment());
        baseFragments.add(new RecordFragment());
        baseFragments.add(new TeamFragment());

        baseFragments.add(new MineFragment());


    }

    private void initListener() {
       rgMain.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rb_recommend:
                        position = 0;
                        AnimaUtils.CircleAnima(rbRecommend,MainActivity.this);

                        break;
                    case R.id.rb_record:

                        position = 1;
                        AnimaUtils.CircleAnima(rbRecord,MainActivity.this);


//
                        break;
                    case R.id.rb_team:
                        position = 2;
                        AnimaUtils.CircleAnima(rbTeam,MainActivity.this);


//
                        break;
                    case R.id.rb_mine:
                        position = 3;
                        AnimaUtils.CircleAnima(rbMine,MainActivity.this);

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

        rgMain.check(R.id.rb_recommend);

        ivLuzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FragmentManager fm = getSupportFragmentManager();
                BottomDialogFragment editNameDialog = new BottomDialogFragment();
                editNameDialog.show(fm, "fragment_bottom_dialog");
            }
        });

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
        if(keyCode ==KeyEvent.KEYCODE_BACK){
            if(position != 0){//不是第一页面
                position = 0;
                rgMain.check(R.id.rb_recommend);//首页
                return true;
            }else  if(!isExit){
                isExit = true;
                Toast.makeText(MainActivity.this,"再按一次推出",Toast.LENGTH_SHORT).show();
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        isExit  = false;
                    }
                },2000);
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }

}
