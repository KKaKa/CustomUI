package com.laizexin.sdj.ui_textview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/11/26
 */
public class FlickerTextView extends android.support.v7.widget.AppCompatTextView {
    private int mViewWidth = 0;
    private Paint mPaint;
    private LinearGradient mLinearGradient;
    private int flickerColor;
    private Matrix mGradientMatrix;
    private int mTranslate = 0;

    public FlickerTextView(Context context) {
        super(context);
        intAttrs(context,null);
    }

    public FlickerTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        intAttrs(context,attrs);
    }

    public FlickerTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        intAttrs(context,attrs);
    }

    private void intAttrs(Context context,AttributeSet attrs){
        mGradientMatrix = new Matrix();
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.FlickerTextView,0,0);
        if(typedArray == null)
            return;
        try {
            flickerColor = typedArray.getColor(R.styleable.FlickerTextView_ftv_color,ContextCompat.getColor(context,R.color.red));
        }catch (Exception e){

        }finally {
            typedArray.recycle();
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        if(mViewWidth == 0) {
            mViewWidth = getMeasuredWidth();
            if(mViewWidth > 0){
                mPaint = getPaint();
                mLinearGradient = new LinearGradient(0, 0, mViewWidth, 0, new int[]{flickerColor, 0xffffff, flickerColor}, null, Shader.TileMode.CLAMP);
                mPaint.setShader(mLinearGradient);
            }
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (mGradientMatrix != null){
            mTranslate += mViewWidth/5;
            if (mTranslate > 2 * mViewWidth){
                mTranslate = - mViewWidth;
            }
            mGradientMatrix.setTranslate(mTranslate, 0);
            mLinearGradient.setLocalMatrix(mGradientMatrix);
            postInvalidateDelayed(100);
        }
    }
}
