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

public class NewUserActivity_ViewBinding implements Unbinder {
  private NewUserActivity target;

  private View view7f090182;

  private View view7f090033;

  private View view7f0900a5;

  @UiThread
  public NewUserActivity_ViewBinding(NewUserActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public NewUserActivity_ViewBinding(final NewUserActivity target, View source) {
    this.target = target;

    View view;
    target.etNewPhone = Utils.findRequiredViewAsType(source, R.id.et_new_phone, "field 'etNewPhone'", EditText.class);
    target.etNewUsername = Utils.findRequiredViewAsType(source, R.id.et_new_username, "field 'etNewUsername'", EditText.class);
    target.etNewPassword = Utils.findRequiredViewAsType(source, R.id.et_new_password, "field 'etNewPassword'", EditText.class);
    target.etNewMsgcode = Utils.findRequiredViewAsType(source, R.id.et_new_msgcode, "field 'etNewMsgcode'", EditText.class);
    view = Utils.findRequiredView(source, R.id.tv_get_msgcode, "field 'tvGetMsgcode' and method 'onViewClicked'");
    target.tvGetMsgcode = Utils.castView(view, R.id.tv_get_msgcode, "field 'tvGetMsgcode'", TextView.class);
    view7f090182 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.btn_new_user, "field 'btnNewUser' and method 'onViewClicked'");
    target.btnNewUser = Utils.castView(view, R.id.btn_new_user, "field 'btnNewUser'", Button.class);
    view7f090033 = view;
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
  }

  @Override
  @CallSuper
  public void unbind() {
    NewUserActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.etNewPhone = null;
    target.etNewUsername = null;
    target.etNewPassword = null;
    target.etNewMsgcode = null;
    target.tvGetMsgcode = null;
    target.btnNewUser = null;
    target.ivBack = null;

    view7f090182.setOnClickListener(null);
    view7f090182 = null;
    view7f090033.setOnClickListener(null);
    view7f090033 = null;
    view7f0900a5.setOnClickListener(null);
    view7f0900a5 = null;
  }
}
