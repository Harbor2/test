// Generated code from Butter Knife. Do not modify!
package com.example.nine.home.fragment;

import android.view.View;
import android.widget.ImageButton;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.viewpager.widget.ViewPager;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import com.example.nine.R;
import com.google.android.material.tabs.TabLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class HomeFragment_ViewBinding implements Unbinder {
  private HomeFragment target;

  @UiThread
  public HomeFragment_ViewBinding(HomeFragment target, View source) {
    this.target = target;

    target.ibSearch = Utils.findRequiredViewAsType(source, R.id.ib_search, "field 'ibSearch'", ImageButton.class);
    target.tablayout = Utils.findRequiredViewAsType(source, R.id.tablayout, "field 'tablayout'", TabLayout.class);
    target.viewpager = Utils.findRequiredViewAsType(source, R.id.viewpager, "field 'viewpager'", ViewPager.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    HomeFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ibSearch = null;
    target.tablayout = null;
    target.viewpager = null;
  }
}
