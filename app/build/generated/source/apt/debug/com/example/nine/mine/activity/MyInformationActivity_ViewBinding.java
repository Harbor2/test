// Generated code from Butter Knife. Do not modify!
package com.example.nine.mine.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.nine.R;
import com.makeramen.roundedimageview.RoundedImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MyInformationActivity_ViewBinding implements Unbinder {
  private MyInformationActivity target;

  private View view7f0900a5;

  private View view7f090119;

  private View view7f09018a;

  private View view7f09018c;

  private View view7f09018b;

  private View view7f090035;

  @UiThread
  public MyInformationActivity_ViewBinding(MyInformationActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MyInformationActivity_ViewBinding(final MyInformationActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onViewClicked'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view7f0900a5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.rv_mymsg_photo, "field 'rvMymsgPhoto' and method 'onViewClicked'");
    target.rvMymsgPhoto = Utils.castView(view, R.id.rv_mymsg_photo, "field 'rvMymsgPhoto'", RoundedImageView.class);
    view7f090119 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etMymsgName = Utils.findRequiredViewAsType(source, R.id.et_mymsg_name, "field 'etMymsgName'", EditText.class);
    target.etMymsgPhone = Utils.findRequiredViewAsType(source, R.id.et_mymsg_phone, "field 'etMymsgPhone'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_mymsg_address, "field 'tvMymsgAddress' and method 'onViewClicked'");
    target.tvMymsgAddress = Utils.castView(view, R.id.tv_mymsg_address, "field 'tvMymsgAddress'", TextView.class);
    view7f09018a = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_mymsg_sex, "field 'tvMymsgSex' and method 'onViewClicked'");
    target.tvMymsgSex = Utils.castView(view, R.id.tv_mymsg_sex, "field 'tvMymsgSex'", TextView.class);
    view7f09018c = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_mymsg_birth, "field 'tvMymsgBirth' and method 'onViewClicked'");
    target.tvMymsgBirth = Utils.castView(view, R.id.tv_mymsg_birth, "field 'tvMymsgBirth'", TextView.class);
    view7f09018b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etMymsgCardcode = Utils.findRequiredViewAsType(source, R.id.et_mymsg_cardcode, "field 'etMymsgCardcode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_savemsg, "field 'btnSavemsg' and method 'onViewClicked'");
    target.btnSavemsg = Utils.castView(view, R.id.btn_savemsg, "field 'btnSavemsg'", Button.class);
    view7f090035 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
  }

  @Override
  @CallSuper
  public void unbind() {
    MyInformationActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.rvMymsgPhoto = null;
    target.etMymsgName = null;
    target.etMymsgPhone = null;
    target.tvMymsgAddress = null;
    target.tvMymsgSex = null;
    target.tvMymsgBirth = null;
    target.etMymsgCardcode = null;
    target.btnSavemsg = null;

    view7f0900a5.setOnClickListener(null);
    view7f0900a5 = null;
    view7f090119.setOnClickListener(null);
    view7f090119 = null;
    view7f09018a.setOnClickListener(null);
    view7f09018a = null;
    view7f09018c.setOnClickListener(null);
    view7f09018c = null;
    view7f09018b.setOnClickListener(null);
    view7f09018b = null;
    view7f090035.setOnClickListener(null);
    view7f090035 = null;
  }
}
