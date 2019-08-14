// Generated code from Butter Knife. Do not modify!
package com.example.nine.mine.activity;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.Unbinder;
import butterknife.internal.DebouncingOnClickListener;
import butterknife.internal.Utils;
import com.example.nine.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import java.lang.IllegalStateException;
import java.lang.Override;

public class Dcteam_ApreocrdActivity_ViewBinding implements Unbinder {
  private Dcteam_ApreocrdActivity target;

  private View view7f0900a5;

  private View view7f090137;

  @UiThread
  public Dcteam_ApreocrdActivity_ViewBinding(Dcteam_ApreocrdActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public Dcteam_ApreocrdActivity_ViewBinding(final Dcteam_ApreocrdActivity target, View source) {
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
    target.rvDcteam = Utils.findRequiredViewAsType(source, R.id.rv_dcteam, "field 'rvDcteam'", RecyclerView.class);
    view = Utils.findRequiredView(source, R.id.sm_dcteam, "field 'smDcteam' and method 'onViewClicked'");
    target.smDcteam = Utils.castView(view, R.id.sm_dcteam, "field 'smDcteam'", SmartRefreshLayout.class);
    view7f090137 = view;
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
    Dcteam_ApreocrdActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.rvDcteam = null;
    target.smDcteam = null;

    view7f0900a5.setOnClickListener(null);
    view7f0900a5 = null;
    view7f090137.setOnClickListener(null);
    view7f090137 = null;
  }
}
