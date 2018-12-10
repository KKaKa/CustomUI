package com.laizexin.sdj.ui_textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/12/5
 */
public class BezierLove extends View {
    private static final float C = 0.551915024494f;     // 一个常量，用来计算绘制圆形贝塞尔曲线控制点的位置
    private Paint mPaint;
    private int mCenterX, mCenterY;
    private float mCircleRadius = 50;                   // 圆的半径
    private float mDifference = mCircleRadius*C;        // 圆形的控制点与数据点的差值
    private float[] mData = new float[8];               // 顺时针记录绘制圆形的四个数据点
    private float[] mCtrl = new float[16];              // 顺时针记录绘制圆形的八个控制点
    private MODE mode;
    private boolean isInit;
    private Path path;

    public MODE getMode() {
        return mode;
    }

    public void setMode(MODE mode) {
        this.mode = mode;
    }

    public enum MODE{
        NORMAL,FAVOURITE
    }

    public void changeMode(MODE mode){
        if(mode == MODE.FAVOURITE){
            if(getMode() == MODE.FAVOURITE)
                return;
            setViewEnable(false);
            AnimManager manager = createAnimManager(
                    mData[1],mData[1]-(0.6f*mCircleRadius),
                    mCtrl[7],mCtrl[7]+(0.4f*mCircleRadius),
                    mCtrl[9],mCtrl[9]+(0.4f*mCircleRadius),
                    mCtrl[4],mCtrl[4]-(0.1f*mCircleRadius),
                    mCtrl[10],mCtrl[10]+(0.1f*mCircleRadius));
            manager.start();
            manager.setListener(new AnimManager.AnimListener() {
                @Override
                public void onAnimEnd() {
                    isInit = false;
                    setMode(MODE.FAVOURITE);
                    setViewEnable(true);
                }
            });
        }else{
            if(getMode() == MODE.NORMAL)
                return;
            setViewEnable(false);
            AnimManager manager = createAnimManager
                    (mData[1],mData[1]+(0.6f*mCircleRadius),
                            mCtrl[7],mCtrl[7]-(0.4f*mCircleRadius),
                            mCtrl[9],mCtrl[9]-(0.4f*mCircleRadius),
                            mCtrl[4],mCtrl[4]+(0.1f*mCircleRadius),
                            mCtrl[10],mCtrl[10]-(0.1f*mCircleRadius)
                    );
            manager.start();
            manager.setListener(new AnimManager.AnimListener() {
                @Override
                public void onAnimEnd() {
                    isInit = false;
                    setMode(MODE.NORMAL);
                    setViewEnable(true);
                }
            });
        }
    }

    public void setInitMode(MODE mode){
        isInit = true;
        changeMode(mode);
    }

    private AnimManager createAnimManager(float fromData,float toData,
                                          float fromControl,float toControl,
                                          float fromControl2,float toControl2,
                                          float fromControl3,float toControl3,
                                          float fromControl4,float toControl4){
        AnimManager animManager = new AnimManager(this,1000);
        animManager.setFromData(fromData);
        animManager.setToData(toData);
        animManager.setFromControlData(fromControl);
        animManager.setToControlData(toControl);

        animManager.setFromControlData2(fromControl2);
        animManager.setToControlData2(toControl2);

        animManager.setFromControlData3(fromControl3);
        animManager.setToControlData3(toControl3);

        animManager.setFromControlData4(fromControl4);
        animManager.setToControlData4(toControl4);

        if(isInit){
            animManager.setmDuration(AnimManager.IMMEDIATELY);
        }else{
            animManager.setmDuration(AnimManager.DURATION);
        }
        return animManager;
    }

    public BezierLove(Context context) {
        this(context, null);

    }

    public BezierLove(Context context, AttributeSet attrs) {
        super(context, attrs);

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);

        // 初始化数据点

        mData[0] = 0;
        mData[1] = mCircleRadius;

        mData[2] = mCircleRadius;
        mData[3] = 0;

        mData[4] = 0;
        mData[5] = -mCircleRadius;

        mData[6] = -mCircleRadius;
        mData[7] = 0;

        // 初始化控制点

        mCtrl[0]  = mData[0]+mDifference;
        mCtrl[1]  = mData[1];

        mCtrl[2]  = mData[2];
        mCtrl[3]  = mData[3]+mDifference;

        mCtrl[4]  = mData[2];
        mCtrl[5]  = mData[3]-mDifference;

        mCtrl[6]  = mData[4]+mDifference;
        mCtrl[7]  = mData[5];

        mCtrl[8]  = mData[4]-mDifference;
        mCtrl[9]  = mData[5];

        mCtrl[10] = mData[6];
        mCtrl[11] = mData[7]-mDifference;

        mCtrl[12] = mData[6];
        mCtrl[13] = mData[7]+mDifference;

        mCtrl[14] = mData[0]-mDifference;
        mCtrl[15] = mData[1];

        path = new Path();

    }


    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mCenterX = w / 2;
        mCenterY = h / 2;
    }

    protected void changeVauleData(float valueData){
        mData[1] = valueData;
        invalidate();
    }

    protected void changeValueControl(float valueControl,int flag){
        switch (flag){
            case 1:
                mCtrl[7] = valueControl;
                break;

            case 2:
                mCtrl[9] = valueControl;
                break;

            case 3:
                mCtrl[4] = valueControl;
                break;

            case 4:
                mCtrl[10] = valueControl;
                break;
        }
        invalidate();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(mCenterX, mCenterY); // 将坐标系移动到画布中央
        canvas.scale(1,-1);                 // 翻转Y轴

        mPaint.setColor(Color.RED);
        if(mode == MODE.NORMAL) {
            mPaint.setStyle(Paint.Style.STROKE);
        }else {
            mPaint.setStyle(Paint.Style.FILL);
        }
        mPaint.setStrokeWidth(4);

        path.reset();
        path.moveTo(mData[0],mData[1]);

        path.cubicTo(mCtrl[0],  mCtrl[1],  mCtrl[2],  mCtrl[3],     mData[2], mData[3]);
        path.cubicTo(mCtrl[4],  mCtrl[5],  mCtrl[6],  mCtrl[7],     mData[4], mData[5]);
        path.cubicTo(mCtrl[8],  mCtrl[9],  mCtrl[10], mCtrl[11],    mData[6], mData[7]);
        path.cubicTo(mCtrl[12], mCtrl[13], mCtrl[14], mCtrl[15],    mData[0], mData[1]);

        canvas.drawPath(path, mPaint);
    }

    private void setViewEnable(boolean enable){
        this.setEnabled(enable);
    }

}
