package com.example.nine.utils;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.view.View;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;


public class AnimaUtils {


    public static void CircleAnimaLogo(View logo, Context context) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(logo, "scaleX", 0.4f, 1f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(logo, "scaleY", 0.6f, 1f);
        ObjectAnimator animatorz = ObjectAnimator.ofFloat(logo, "alpha", 0.0f, 1f);
        animatorz.setInterpolator(new LinearInterpolator());
        animatorz.setDuration(1000);
        animatorX.setInterpolator(new SpringScaleInterpolator(0.2f));
        animatorY.setInterpolator(new SpringScaleInterpolator(0.2f));
        animatorX.setDuration(2300);
        animatorY.setDuration(2300);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorX, animatorY, animatorz);
        set.start();

    }

    public static void CircleAnima(View logo, Context context) {
        ObjectAnimator animatorX = ObjectAnimator.ofFloat(logo, "scaleX", 0.4f, 1f);
        ObjectAnimator animatorY = ObjectAnimator.ofFloat(logo, "scaleY", 0.6f, 1f);

        animatorX.setInterpolator(new SpringScaleInterpolator(0.9f));
        animatorY.setInterpolator(new SpringScaleInterpolator(0.9f));
        animatorX.setDuration(500);
        animatorY.setDuration(500);
        AnimatorSet set = new AnimatorSet();
        set.playTogether(animatorX, animatorY);
        set.start();

    }

    //弹跳动画
    public static class SpringScaleInterpolator implements Interpolator {
        //弹性因数
        private float factor;

        public SpringScaleInterpolator(float factor) {
            this.factor = factor;
        }

        @Override
        public float getInterpolation(float input) {

            return (float) (Math.pow(2, -10 * input) * Math.sin((input - factor / 4) * (2 * Math.PI) / factor) + 1);
        }

    }
}
