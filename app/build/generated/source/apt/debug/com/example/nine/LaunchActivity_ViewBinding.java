// Generated code from Butter Knife. Do not modify!
package com.example.nine;

import android.view.View;
import android.widget.ImageView;
import androidx.annotation.CallSuper;
import androidx.annotation.UiThread;
import butterknife.Unbinder;
import butterknife.internal.Utils;
import java.lang.IllegalStateException;
import java.lang.Override;

public class LaunchActivity_ViewBinding implements Unbinder {
  private LaunchActivity target;

  @UiThread
  public LaunchActivity_ViewBinding(LaunchActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public LaunchActivity_ViewBinding(LaunchActivity target, View source) {
    this.target = target;

    target.logo = Utils.findRequiredViewAsType(source, R.id.logo, "field 'logo'", ImageView.class);
  }

  @Override
  @CallSuper
  public void unbind() {
    LaunchActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.logo = null;
  }
}
