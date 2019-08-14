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

public class MsgLoginActivity_ViewBinding implements Unbinder {
  private MsgLoginActivity target;

  private View view7f0900a5;

  private View view7f090188;

  private View view7f090032;

  @UiThread
  public MsgLoginActivity_ViewBinding(MsgLoginActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MsgLoginActivity_ViewBinding(final MsgLoginActivity target, View source) {
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
    target.etMsgloginUserphone = Utils.findRequiredViewAsType(source, R.id.et_msglogin_userphone, "field 'etMsgloginUserphone'", EditText.class);
    target.etMsgloginCode = Utils.findRequiredViewAsType(source, R.id.et_msglogin_code, "field 'etMsgloginCode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_msglogin_getcode, "field 'tvMsgloginGetcode' and method 'onViewClicked'");
    target.tvMsgloginGetcode = Utils.castView(view, R.id.tv_msglogin_getcode, "field 'tvMsgloginGetcode'", TextView.class);
    view7f090188 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_msglogin_login, "field 'btnMsgloginLogin' and method 'onViewClicked'");
    target.btnMsgloginLogin = Utils.castView(view, R.id.btn_msglogin_login, "field 'btnMsgloginLogin'", Button.class);
    view7f090032 = view;
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
    MsgLoginActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.etMsgloginUserphone = null;
    target.etMsgloginCode = null;
    target.tvMsgloginGetcode = null;
    target.btnMsgloginLogin = null;

    view7f0900a5.setOnClickListener(null);
    view7f0900a5 = null;
    view7f090188.setOnClickListener(null);
    view7f090188 = null;
    view7f090032.setOnClickListener(null);
    view7f090032 = null;
  }
}
