// Generated code from Butter Knife. Do not modify!
package com.example.nine;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.makeramen.roundedimageview.RoundedImageView;
import java.lang.IllegalStateException;
import java.lang.Override;

public class MainActivity_ViewBinding implements Unbinder {
  private MainActivity target;

  @UiThread
  public MainActivity_ViewBinding(MainActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public MainActivity_ViewBinding(MainActivity target, View source) {
    this.target = target;

    target.frameLayout = Utils.findRequiredViewAsType(source, R.id.frameLayout, "field 'frameLayout'", FrameLayout.class);
    target.rgMain = Utils.findRequiredViewAsType(source, R.id.rg_main, "field 'rgMain'", RadioGroup.class);
    target.rbRecord = Utils.findRequiredViewAsType(source, R.id.rb_record, "field 'rbRecord'", RadioButton.class);
    target.rbTeam = Utils.findRequiredViewAsType(source, R.id.rb_team, "field 'rbTeam'", RadioButton.class);
    target.rbMine = Utils.findRequiredViewAsType(source, R.id.rb_mine, "field 'rbMine'", RadioButton.class);
    target.ivLuzhi = Utils.findRequiredViewAsType(source, R.id.ivLuzhi, "field 'ivLuzhi'", RoundedImageView.class);
    target.rbRecommend = Utils.findRequiredViewAsType(source, R.id.rb_recommend, "field 'rbRecommend'", RadioButton.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    MainActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.frameLayout = null;
    target.rgMain = null;
    target.rbRecord = null;
    target.rbTeam = null;
    target.rbMine = null;
    target.ivLuzhi = null;
    target.rbRecommend = null;
  }
}
