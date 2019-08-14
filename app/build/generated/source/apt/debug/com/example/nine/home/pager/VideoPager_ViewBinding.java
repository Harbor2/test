// Generated code from Butter Knife. Do not modify!
package com.example.nine.home.pager;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nine.R;
import java.lang.IllegalStateException;
import java.lang.Override;

public class VideoPager_ViewBinding implements Unbinder {
  private VideoPager target;

  @UiThread
  public VideoPager_ViewBinding(VideoPager target, View source) {
    this.target = target;

    target.homeRecycle = Utils.findRequiredViewAsType(source, R.id.home_recycle, "field 'homeRecycle'", RecyclerView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    VideoPager target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.homeRecycle = null;
  }
}
