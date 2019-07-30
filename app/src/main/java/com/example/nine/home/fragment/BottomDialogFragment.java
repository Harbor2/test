package com.example.nine.home.fragment;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.nine.R;
import com.gcssloop.widget.RCRelativeLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 底部的DialogFragment
 * <p/>
 * Created by wangchenlong on 16/6/20.
 */
public class BottomDialogFragment extends DialogFragment {


    @BindView(R.id.iv_luzhi)
    ImageView ivLuzhi;
    @BindView(R.id.rcr)
    RCRelativeLayout rcr;
    @BindView(R.id.iv_zhibo)
    ImageView ivZhibo;
    @BindView(R.id.rcr2)
    RCRelativeLayout rcr2;

    @BindView(R.id.iv_exit)
    ImageView ivExit;
    private ArrayList<LinearLayout> mLayouts; // 框架
    private ArrayList<TextView> mTvTypes; // 文字类型
    private ArrayList<ImageView> mIvTypes; // 图像类型
    private int mType = 0; // 当前类型
    private int mCoinCount = 2310; // 当前金币数

    @SuppressLint("ResourceType")
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // 使用不带Theme的构造器, 获得的dialog边框距离屏幕仍有几毫米的缝隙。
        Dialog dialog = new Dialog(getActivity(), R.style.BottomDialog);

        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置Content前设定
        dialog.setContentView(R.layout.fragment_bottom);
        dialog.setCanceledOnTouchOutside(true); // 外部点击取消

        // 设置宽度为屏宽, 靠近屏幕底部。
        Window window = dialog.getWindow();
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.gravity = Gravity.BOTTOM; // 紧贴底部
        lp.width = WindowManager.LayoutParams.MATCH_PARENT; // 宽度持平
        window.setAttributes(lp);
        window.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
   //     window.setWindowAnimations(R.drawable.actionsheet_dialog_in);
        ButterKnife.bind(this, dialog); // Dialog即View

        initClickTypes();

        return dialog;
    }

    /**
     * 初始化点击类型
     */
    private void initClickTypes() {
        ivLuzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "录制被电击了", Toast.LENGTH_SHORT).show();
            }
        });

        ivZhibo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "直播被点击了", Toast.LENGTH_SHORT).show();
            }
        });

        ivExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
    }

}
