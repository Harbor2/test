// Generated code from Butter Knife. Do not modify!
package com.example.nine.mine.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.nine.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class SettingActivity_ViewBinding implements Unbinder {
  private SettingActivity target;

  private View view7f0900a5;

  private View view7f0900c2;

  private View view7f0900c1;

  private View view7f0900c3;

  @UiThread
  public SettingActivity_ViewBinding(SettingActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public SettingActivity_ViewBinding(final SettingActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.ll_change_exit, "field 'llChangeExit' and method 'onViewClicked'");
    target.llChangeExit = Utils.castView(view, R.id.ll_change_exit, "field 'llChangeExit'", LinearLayout.class);
    view7f0900c2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_change_details, "field 'llChangeDetails' and method 'onViewClicked'");
    target.llChangeDetails = Utils.castView(view, R.id.ll_change_details, "field 'llChangeDetails'", LinearLayout.class);
    view7f0900c1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_change_pass, "field 'llChangePass' and method 'onViewClicked'");
    target.llChangePass = Utils.castView(view, R.id.ll_change_pass, "field 'llChangePass'", LinearLayout.class);
    view7f0900c3 = view;
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
    SettingActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.llChangeExit = null;
    target.llChangeDetails = null;
    target.llChangePass = null;

    view7f0900a5.setOnClickListener(null);
    view7f0900a5 = null;
    view7f0900c2.setOnClickListener(null);
    view7f0900c2 = null;
    view7f0900c1.setOnClickListener(null);
    view7f0900c1 = null;
    view7f0900c3.setOnClickListener(null);
    view7f0900c3 = null;
  }
}
