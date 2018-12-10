package com.laizexin.sdj.ui_textview;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.view.View;

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/12/5
 */
public class AnimManager {
    public static final int DURATION = 400;
    public static final int IMMEDIATELY = 1;
    private static final String PROPERTY_NAME_X = "translationX";
    private static final String PROPERTY_NAME_Y = "translationY";
    private static final String PROPERTY_BACKGROUND_COLOR = "backgroundColor";
    private static final String PROPERTY_COLOR = "color";
    private static final String PROPERTY_ROTATION_Y = "rotationY";

    private View view;

    private int mDuration;
    private float fromData;
    private float toData;

    private float fromControlData;
    private float toControlData;

    private float fromControlData2;
    private float toControlData2;

    private float fromControlData3;
    private float toControlData3;

    private float fromControlData4;
    private float toControlData4;

    private int fromColor;
    private int toColor;

    private AnimListener listener;

    public interface AnimListener{
        void onAnimEnd();
    }

    public int getmDuration() {
        return mDuration;
    }

    public void setmDuration(int mDuration) {
        this.mDuration = mDuration;
    }

    public float getFromData() {
        return fromData;
    }

    public void setFromData(float fromData) {
        this.fromData = fromData;
    }

    public float getToData() {
        return toData;
    }

    public void setToData(float toData) {
        this.toData = toData;
    }

    public float getFromControlData() {
        return fromControlData;
    }

    public void setFromControlData(float fromControlData) {
        this.fromControlData = fromControlData;
    }

    public float getToControlData() {
        return toControlData;
    }

    public void setToControlData(float toControlData) {
        this.toControlData = toControlData;
    }

    public int getFromColor() {
        return fromColor;
    }

    public void setFromColor(int fromColor) {
        this.fromColor = fromColor;
    }

    public int getToColor() {
        return toColor;
    }

    public void setToColor(int toColor) {
        this.toColor = toColor;
    }

    public AnimListener getListener() {
        return listener;
    }

    public float getFromControlData2() {
        return fromControlData2;
    }

    public void setFromControlData2(float fromControlData2) {
        this.fromControlData2 = fromControlData2;
    }

    public float getToControlData2() {
        return toControlData2;
    }

    public void setToControlData2(float toControlData2) {
        this.toControlData2 = toControlData2;
    }

    public float getFromControlData3() {
        return fromControlData3;
    }

    public void setFromControlData3(float fromControlData3) {
        this.fromControlData3 = fromControlData3;
    }

    public float getToControlData3() {
        return toControlData3;
    }

    public void setToControlData3(float toControlData3) {
        this.toControlData3 = toControlData3;
    }

    public float getFromControlData4() {
        return fromControlData4;
    }

    public void setFromControlData4(float fromControlData4) {
        this.fromControlData4 = fromControlData4;
    }

    public float getToControlData4() {
        return toControlData4;
    }

    public void setToControlData4(float toControlData4) {
        this.toControlData4 = toControlData4;
    }

    public void setListener(AnimListener listener) {
        this.listener = listener;
    }

    public AnimManager(View view, int mDuration) {
        this.view = view;
        this.mDuration = mDuration;
    }

    public void start(){
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(fromData,toData);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                ((BezierLove)view).changeVauleData(value);
            }
        });

        ValueAnimator valueAnimatorControl = ValueAnimator.ofFloat(fromControlData,toControlData);
        valueAnimatorControl.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                ((BezierLove)view).changeValueControl(value,1);
            }
        });

        ValueAnimator valueAnimatorControl2 = ValueAnimator.ofFloat(fromControlData2,toControlData2);
        valueAnimatorControl2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                ((BezierLove)view).changeValueControl(value,2);
            }
        });

        ValueAnimator valueAnimatorControl3 = ValueAnimator.ofFloat(fromControlData3,toControlData3);
        valueAnimatorControl3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                ((BezierLove)view).changeValueControl(value,3);
            }
        });

        ValueAnimator valueAnimatorControl4 = ValueAnimator.ofFloat(fromControlData4,toControlData4);
        valueAnimatorControl4.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                float value = (float) animation.getAnimatedValue();
                ((BezierLove)view).changeValueControl(value,4);
            }
        });

        ObjectAnimator animator = ObjectAnimator.ofFloat(view,PROPERTY_ROTATION_Y,0,180,0);

        ObjectAnimator colorAnimator = ObjectAnimator.ofInt(view,PROPERTY_BACKGROUND_COLOR,fromColor,toColor);
        colorAnimator.setEvaluator(new ArgbEvaluator());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(mDuration);
        animatorSet.playTogether(animator,valueAnimator,valueAnimatorControl,valueAnimatorControl2,valueAnimatorControl3,valueAnimatorControl4);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animation) {
                if(listener != null)
                    listener.onAnimEnd();
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });
        animatorSet.start();
    }
}
