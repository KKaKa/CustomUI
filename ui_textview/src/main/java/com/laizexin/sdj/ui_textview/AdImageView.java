package com.laizexin.sdj.ui_textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;

/**
 * @Description: 仿知乎滑动ImageView
 * @Author: laizexin
 * @Time: 2018/12/18
 */
public class AdImageView extends AppCompatImageView{
    private int mMinDy = 0;
    private float mPercent = 0f;

    public enum Orientation{
        ORDER,REVERSE
    }

    public AdImageView(Context context) {
        super(context);
    }

    public AdImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mMinDy = h;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(getDrawable() == null)
            return;
        Drawable drawable = getDrawable();
        int w = getWidth();
        int h = (int) (getWidth()*1f / getDrawable().getIntrinsicWidth() * getDrawable().getIntrinsicHeight());
        drawable.setBounds(0,0,w,h);
        canvas.save();
        canvas.translate(0,-(h - mMinDy) * mPercent);
        super.onDraw(canvas);
        canvas.restore();
    }

    public void setPerCent(int childHeight,int parentHeight,Orientation orientation){
        if(getDrawable() == null)
            return;
        if(orientation == Orientation.ORDER){
            mPercent = childHeight*1f / (parentHeight - mMinDy);
        }else{
            mPercent = (parentHeight - childHeight - mMinDy) * 1f / (parentHeight - mMinDy);
        }
        if(mPercent <= 0 )
            mPercent = 0;
        if(mPercent >= 1)
            mPercent = 1;
        invalidate();
    }


}
