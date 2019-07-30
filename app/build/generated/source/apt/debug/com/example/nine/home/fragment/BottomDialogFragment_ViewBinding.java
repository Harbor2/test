// Generated code from Butter Knife. Do not modify!
package com.example.nine.home.fragment;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nine.R;
import com.gcssloop.widget.RCRelativeLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class BottomDialogFragment_ViewBinding implements Unbinder {
  private BottomDialogFragment target;

  @UiThread
  public BottomDialogFragment_ViewBinding(BottomDialogFragment target, View source) {
    this.target = target;

    target.ivLuzhi = Utils.findRequiredViewAsType(source, R.id.iv_luzhi, "field 'ivLuzhi'", ImageView.class);
    target.rcr = Utils.findRequiredViewAsType(source, R.id.rcr, "field 'rcr'", RCRelativeLayout.class);
    target.ivZhibo = Utils.findRequiredViewAsType(source, R.id.iv_zhibo, "field 'ivZhibo'", ImageView.class);
    target.rcr2 = Utils.findRequiredViewAsType(source, R.id.rcr2, "field 'rcr2'", RCRelativeLayout.class);
    target.ivExit = Utils.findRequiredViewAsType(source, R.id.iv_exit, "field 'ivExit'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    BottomDialogFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivLuzhi = null;
    target.rcr = null;
    target.ivZhibo = null;
    target.rcr2 = null;
    target.ivExit = null;
  }
}
