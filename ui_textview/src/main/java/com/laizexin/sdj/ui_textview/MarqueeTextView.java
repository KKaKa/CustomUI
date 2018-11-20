package com.laizexin.sdj.ui_textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;

/**
 * @Description:跑马灯效果
 * @Author: laizexin
 * @Time: 2018/11/20
 */
public class MarqueeTextView extends android.support.v7.widget.AppCompatTextView {
    private int limitNum;

    public MarqueeTextView(Context context) {
        super(context);
        init(context,null);
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    public MarqueeTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context,attrs);
    }

    private void intAttrs(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MarqueeTextView,0,0);
        if(typedArray == null)
            return;
        try {
            limitNum = typedArray.getInteger(R.styleable.MarqueeTextView_mq_marquee_repeat_limit,-1);
        }catch (Exception e){

        }finally {
            typedArray.recycle();
        }
    }

    private void init(Context context,AttributeSet attrs) {
        intAttrs(context, attrs);
        //设置单行
        setSingleLine();
        //设置Ellipsize
        setEllipsize(TextUtils.TruncateAt.MARQUEE);
        //获取焦点
        setFocusable(true);
        //走马灯的重复次数，-1代表无限重复
        setMarqueeRepeatLimit(limitNum);
        //强制获得焦点
        setFocusableInTouchMode(true);
    }

    @Override
    public boolean isFocused() {
        return true;
    }

    @Override
    protected void onFocusChanged(boolean focused, int direction, Rect previouslyFocusedRect) {
        if (focused) {
            super.onFocusChanged(focused, direction, previouslyFocusedRect);
        }
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (hasWindowFocus)
            super.onWindowFocusChanged(hasWindowFocus);
    }

    public void setLimitNum(int limitNum) {
        setMarqueeRepeatLimit(limitNum);
    }
}
