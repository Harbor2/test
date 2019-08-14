// Generated code from Butter Knife. Do not modify!
package com.example.nine.mine.activity;

import android.view.View;
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

public class FeedBack_ReplyActivity_ViewBinding implements Unbinder {
  private FeedBack_ReplyActivity target;

  private View view7f0900a5;

  private View view7f0901a2;

  private View view7f09017b;

  private View view7f090199;

  @UiThread
  public FeedBack_ReplyActivity_ViewBinding(FeedBack_ReplyActivity target) {
    this(target, target.getWindow().getDecorView());
  }

  @UiThread
  public FeedBack_ReplyActivity_ViewBinding(final FeedBack_ReplyActivity target, View source) {
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
    view = Utils.findRequiredView(source, R.id.tv_title_feedback_reply, "field 'tvTitleFeedbackReply' and method 'onViewClicked'");
    target.tvTitleFeedbackReply = Utils.castView(view, R.id.tv_title_feedback_reply, "field 'tvTitleFeedbackReply'", TextView.class);
    view7f0901a2 = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_content_feedback_reply, "field 'tvContentFeedbackReply' and method 'onViewClicked'");
    target.tvContentFeedbackReply = Utils.castView(view, R.id.tv_content_feedback_reply, "field 'tvContentFeedbackReply'", TextView.class);
    view7f09017b = view;
    view.setOnClickListener(new DebouncingOnClickListener() {
      @Override
      public void doClick(View p0) {
        target.onViewClicked(p0);
      }
    });
    view = Utils.findRequiredView(source, R.id.tv_reply_feedback_reply, "field 'tvReplyFeedbackReply' and method 'onViewClicked'");
    target.tvReplyFeedbackReply = Utils.castView(view, R.id.tv_reply_feedback_reply, "field 'tvReplyFeedbackReply'", TextView.class);
    view7f090199 = view;
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
    FeedBack_ReplyActivity target = this.target;
    if (target == null) throw new IllegalStateException("Bindings already cleared.");
    this.target = null;

    target.ivBack = null;
    target.tvTitleFeedbackReply = null;
    target.tvContentFeedbackReply = null;
    target.tvReplyFeedbackReply = null;

    view7f0900a5.setOnClickListener(null);
    view7f0900a5 = null;
    view7f0901a2.setOnClickListener(null);
    view7f0901a2 = null;
    view7f09017b.setOnClickListener(null);
    view7f09017b = null;
    view7f090199.setOnClickListener(null);
    view7f090199 = null;
  }
}
