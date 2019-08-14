// Generated code from Butter Knife. Do not modify!
package com.example.nine.mine.fragment;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

public class MineFragment_ViewBinding implements Unbinder {
  private MineFragment target;

  private View view7f0900c7;

  private View view7f0900cc;

  private View view7f0900f6;

  private View view7f09018f;

  private View view7f0900af;

  private View view7f090057;

  private View view7f0900c4;

  private View view7f0900c6;

  @UiThread
  public MineFragment_ViewBinding(final MineFragment target, View source) {
    this.target = target;

    View view;
    view = Utils.findRequiredView(source, R.id.ll_page_mine, "field 'llPageMine' and method 'onViewClicked'");
    target.llPageMine = Utils.castView(view, R.id.ll_page_mine, "field 'llPageMine'", LinearLayout.class);
    view7f0900c7 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_record_mine, "field 'llRecordMine' and method 'onViewClicked'");
    target.llRecordMine = Utils.castView(view, R.id.ll_record_mine, "field 'llRecordMine'", LinearLayout.class);
    view7f0900cc = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.photo_mine, "field 'photoMine' and method 'onViewClicked'");
    target.photoMine = Utils.castView(view, R.id.photo_mine, "field 'photoMine'", RoundedImageView.class);
    view7f0900f6 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_name_mine, "field 'tvNameMine' and method 'onViewClicked'");
    target.tvNameMine = Utils.castView(view, R.id.tv_name_mine, "field 'tvNameMine'", TextView.class);
    view7f09018f = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.iv_setting, "field 'ivSetting' and method 'onViewClicked'");
    target.ivSetting = Utils.castView(view, R.id.iv_setting, "field 'ivSetting'", ImageView.class);
    view7f0900af = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    target.funsnumMine = Utils.findRequiredViewAsType(source, R.id.funsnum_mine, "field 'funsnumMine'", TextView.class);
    target.attentionnumMine = Utils.findRequiredViewAsType(source, R.id.attentionnum_mine, "field 'attentionnumMine'", TextView.class);
    target.goldnumMine = Utils.findRequiredViewAsType(source, R.id.goldnum_mine, "field 'goldnumMine'", TextView.class);
    view = Utils.findRequiredView(source, R.id.daysign_mine, "field 'daysignMine' and method 'onViewClicked'");
    target.daysignMine = Utils.castView(view, R.id.daysign_mine, "field 'daysignMine'", TextView.class);
    view7f090057 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_danceteam_mine, "field 'llDanceteamMine' and method 'onViewClicked'");
    target.llDanceteamMine = Utils.castView(view, R.id.ll_danceteam_mine, "field 'llDanceteamMine'", LinearLayout.class);
    view7f0900c4 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.ll_feedback_mine, "field 'llFeedbackMine' and method 'onViewClicked'");
    target.llFeedbackMine = Utils.castView(view, R.id.ll_feedback_mine, "field 'llFeedbackMine'", LinearLayout.class);
    view7f0900c6 = view;
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
    MineFragment target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.llPageMine = null;
    target.llRecordMine = null;
    target.photoMine = null;
    target.tvNameMine = null;
    target.ivSetting = null;
    target.funsnumMine = null;
    target.attentionnumMine = null;
    target.goldnumMine = null;
    target.daysignMine = null;
    target.llDanceteamMine = null;
    target.llFeedbackMine = null;

    view7f0900c7.setOnClickListener(null);
    view7f0900c7 = null;
    view7f0900cc.setOnClickListener(null);
    view7f0900cc = null;
    view7f0900f6.setOnClickListener(null);
    view7f0900f6 = null;
    view7f09018f.setOnClickListener(null);
    view7f09018f = null;
    view7f0900af.setOnClickListener(null);
    view7f0900af = null;
    view7f090057.setOnClickListener(null);
    view7f090057 = null;
    view7f0900c4.setOnClickListener(null);
    view7f0900c4 = null;
    view7f0900c6.setOnClickListener(null);
    view7f0900c6 = null;
  }
}
