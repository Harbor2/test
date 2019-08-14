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
import java.lang.IllegalStateException;
import java.lang.Override;

public class LoginActivity_ViewBinding implements Unbinder {
  private LoginActivity target;

  private View view7f090077;

  private View view7f090074;

  private View view7f090190;

  private View view7f090031;

  private View view7f0900b1;

  private View view7f0900a5;

  private View view7f090181;

  private View view7f0900ae;

  @UiThread
  public LoginActivity_ViewBinding(LoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LoginActivity_ViewBinding(final LoginActivity target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.et_userphone, "field 'etUserphone' and method 'onViewClicked'");
    target.etUserphone = Utils.castView(view, R.id.et_userphone, "field 'etUserphone'", EditText.class);
    view7f090077 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.et_password, "field 'etPassword' and method 'onViewClicked'");
    target.etPassword = Utils.castView(view, R.id.et_password, "field 'etPassword'", EditText.class);
    view7f090074 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_newuser, "field 'tvNewuser' and method 'onViewClicked'");
    target.tvNewuser = Utils.castView(view, R.id.tv_newuser, "field 'tvNewuser'", TextView.class);
    view7f090190 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_login, "field 'btnLogin' and method 'onViewClicked'");
    target.btnLogin = Utils.castView(view, R.id.btn_login, "field 'btnLogin'", Button.class);
    view7f090031 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_wechat_login, "field 'ivWechatLogin' and method 'onViewClicked'");
    target.ivWechatLogin = Utils.castView(view, R.id.iv_wechat_login, "field 'ivWechatLogin'", ImageView.class);
    view7f0900b1 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_back, "field 'ivBack' and method 'onViewClicked'");
    target.ivBack = Utils.castView(view, R.id.iv_back, "field 'ivBack'", ImageView.class);
    view7f0900a5 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_forgetpass, "field 'tvForgetpass' and method 'onViewClicked'");
    target.tvForgetpass = Utils.castView(view, R.id.tv_forgetpass, "field 'tvForgetpass'", TextView.class);
    view7f090181 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_msg_login, "field 'ivMsgLogin' and method 'onViewClicked'");
    target.ivMsgLogin = Utils.castView(view, R.id.iv_msg_login, "field 'ivMsgLogin'", ImageView.class);
    view7f0900ae = view;
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
    LoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etUserphone = null;
    target.etPassword = null;
    target.tvNewuser = null;
    target.btnLogin = null;
    target.ivWechatLogin = null;
    target.ivBack = null;
    target.tvForgetpass = null;
    target.ivMsgLogin = null;

    view7f090077.setOnClickListener(null);
    view7f090077 = null;
    view7f090074.setOnClickListener(null);
    view7f090074 = null;
    view7f090190.setOnClickListener(null);
    view7f090190 = null;
    view7f090031.setOnClickListener(null);
    view7f090031 = null;
    view7f0900b1.setOnClickListener(null);
    view7f0900b1 = null;
    view7f0900a5.setOnClickListener(null);
    view7f0900a5 = null;
    view7f090181.setOnClickListener(null);
    view7f090181 = null;
    view7f0900ae.setOnClickListener(null);
    view7f0900ae = null;
  }
}
