// Generated code from Butter Knife. Do not modify!
package com.example.nine.home.pager;

import android.view.View;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nine.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class ComPager_ViewBinding implements Unbinder {
  private ComPager target;

  @UiThread
  public ComPager_ViewBinding(ComPager target, View source) {
    this.target = target;

    target.tablayout = Utils.findRequiredViewAsType(source, R.id.tablayout, "field 'tablayout'", TabLayout.class);
    target.viewpager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'viewpager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    ComPager target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.tablayout = null;
    target.viewpager = null;
  }
}
