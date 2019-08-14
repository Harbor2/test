// Generated code from Butter Knife. Do not modify!
package com.example.nine.mine.activity;

import android.view.View;
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

public class SignActivity_ViewBinding implements Unbinder {
  private SignActivity target;

  private View view7f0900b0;

  private View view7f090183;

  private View view7f09019d;

  private View view7f0900a5;

  @UiThread
  public SignActivity_ViewBinding(SignActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SignActivity_ViewBinding(final SignActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.iv_sign, "field 'ivSign' and method 'onViewClicked'");
    target.ivSign = Utils.castView(view, R.id.iv_sign, "field 'ivSign'", RoundedImageView.class);
    view7f0900b0 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_goldnum, "field 'tvGoldnum' and method 'onViewClicked'");
    target.tvGoldnum = Utils.castView(view, R.id.tv_goldnum, "field 'tvGoldnum'", TextView.class);
    view7f090183 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_signrule, "field 'tvSignrule' and method 'onViewClicked'");
    target.tvSignrule = Utils.castView(view, R.id.tv_signrule, "field 'tvSignrule'", TextView.class);
    view7f09019d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.tvSign = Utils.findRequiredViewAsType(source, R.id.tv_sign, "field 'tvSign'", TextView.class);
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onViewClicked'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view7f0900a5 = view;
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
    SignActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivSign = null;
    target.tvGoldnum = null;
    target.tvSignrule = null;
    target.tvSign = null;
    target.ivBack = null;

    view7f0900b0.setOnClickListener(null);
    view7f0900b0 = null;
    view7f090183.setOnClickListener(null);
    view7f090183 = null;
    view7f09019d.setOnClickListener(null);
    view7f09019d = null;
    view7f0900a5.setOnClickListener(null);
    view7f0900a5 = null;
  }
}
