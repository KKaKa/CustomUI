package com.laizexin.sdj.ui_textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/11/22
 */
public class Moon extends View {
    private int mMoonColor;
    private Paint mPaint;
    private int white;

    public Moon(Context context) {
        super(context);
        init(context,null);
    }

    public Moon(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public Moon(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context,AttributeSet attrs){
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        initAttrs(context, attrs);
        white = ContextCompat.getColor(context,R.color.white);
    }

    private void initAttrs(Context context,AttributeSet attrs){
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.Moon,0,0);
        try {
            mMoonColor = typedArray.getColor(R.styleable.Moon_moon_color, ContextCompat.getColor(context,R.color.yellow));
        }catch (Exception e){

        }finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        int w = getMeasuredWidth();
        int h = getMeasuredHeight();
        int size = Math.min(w,h);
        super.onDraw(canvas);
        mPaint.setStrokeWidth(0);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setColor(mMoonColor);
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,size/2 * 0.7f,mPaint);
        canvas.save();
        canvas.translate(-size/2 * 0.5f,-10);
        Drawable background = getBackground();
        if(background instanceof ColorDrawable){
            ColorDrawable colordDrawable = (ColorDrawable) background;
            int color = colordDrawable.getColor();
            mPaint.setColor(color);
        }else{
            mPaint.setColor(white);
        }
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight()/2,size/2 * 0.7f,mPaint);
        canvas.restore();
    }

    private int lastX;
    private int lastY;
    private boolean first = true;
    private int oldlf;
    private int oldrt;
    private int oldtp;
    private int oldbm;

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(first){
            oldlf = getLeft();
            oldrt = getRight();
            oldtp = getTop();
            oldbm = getBottom();
        }
        first = false;

        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;

            case MotionEvent.ACTION_MOVE:
                int offsetX = x - lastX;
                int offsetY = y - lastY;
                layout(getLeft()+offsetX,getTop()+offsetY,getRight()+offsetX,getBottom()+offsetY);
                break;

            case MotionEvent.ACTION_UP:
                layout(oldlf,oldtp,oldrt,oldbm);
                break;
        }
        return true;
    }
}
