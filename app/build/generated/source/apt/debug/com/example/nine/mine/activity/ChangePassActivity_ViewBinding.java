// Generated code from Butter Knife. Do not modify!
package com.example.nine.mine.activity;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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

public class ChangePassActivity_ViewBinding implements Unbinder {
  private ChangePassActivity target;

  private View view7f0900a5;

  private View view7f090075;

  private View view7f090182;

  private View view7f090066;

  private View view7f09002d;

  @UiThread
  public ChangePassActivity_ViewBinding(ChangePassActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public ChangePassActivity_ViewBinding(final ChangePassActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.et_phonenum, "field 'etPhonenum' and method 'onViewClicked'");
    target.etPhonenum = Utils.castView(view, R.id.et_phonenum, "field 'etPhonenum'", EditText.class);
    view7f090075 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_get_msgcode, "field 'tvGetMsgcode' and method 'onViewClicked'");
    target.tvGetMsgcode = Utils.castView(view, R.id.tv_get_msgcode, "field 'tvGetMsgcode'", TextView.class);
    view7f090182 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rlPhoneWillgone = Utils.findRequiredViewAsType(source, R.id.rl_phone_willgone, "field 'rlPhoneWillgone'", RelativeLayout.class);
    view = Utils.findRequiredView(source, R.id.et_change_msgcode, "field 'etChangeMsgcode' and method 'onViewClicked'");
    target.etChangeMsgcode = Utils.castView(view, R.id.et_change_msgcode, "field 'etChangeMsgcode'", EditText.class);
    view7f090066 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.rlCodeWillgone = Utils.findRequiredViewAsType(source, R.id.rl_code_willgone, "field 'rlCodeWillgone'", RelativeLayout.class);
    target.etChangePass = Utils.findRequiredViewAsType(source, R.id.et_change_pass, "field 'etChangePass'", EditText.class);
    view = Utils.findRequiredView(source, R.id.btn_change, "field 'btnChange' and method 'onViewClicked'");
    target.btnChange = Utils.castView(view, R.id.btn_change, "field 'btnChange'", Button.class);
    view7f09002d = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.etCheckPass = Utils.findRequiredViewAsType(source, R.id.et_check_pass, "field 'etCheckPass'", EditText.class);
    target.photoChange = Utils.findRequiredViewAsType(source, R.id.photo_change, "field 'photoChange'", RoundedImageView.class);
    target.tvNameChange = Utils.findRequiredViewAsType(source, R.id.tv_name_change, "field 'tvNameChange'", TextView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ChangePassActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.etPhonenum = null;
    target.tvGetMsgcode = null;
    target.rlPhoneWillgone = null;
    target.etChangeMsgcode = null;
    target.rlCodeWillgone = null;
    target.etChangePass = null;
    target.btnChange = null;
    target.etCheckPass = null;
    target.photoChange = null;
    target.tvNameChange = null;

    view7f0900a5.setOnClickListener(null);
    view7f0900a5 = null;
    view7f090075.setOnClickListener(null);
    view7f090075 = null;
    view7f090182.setOnClickListener(null);
    view7f090182 = null;
    view7f090066.setOnClickListener(null);
    view7f090066 = null;
    view7f09002d.setOnClickListener(null);
    view7f09002d = null;
  }
}
