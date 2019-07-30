package com.example.nine.utils;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

public class CircleAnimateUtils {
    public static void handleAnimate(final View animateView) {
        Animator animator = CircularRevealLayout.Builder.on(animateView)
                .centerX(animateView.getWidth() / 2)
                .centerY(animateView.getHeight() / 2)
                .startRadius(0)
                .endRadius((float) Math.hypot(animateView.getWidth(), animateView.getHeight()))
                .create();

        animator.setDuration(1000);
        animator.setInterpolator(new AccelerateDecelerateInterpolator());
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
            }

            @Override
            public void onAnimationStart(Animator animation) {
                animateView.setVisibility(View.VISIBLE);
                super.onAnimationStart(animation);
            }
        });
        animator.start();
    }

}
