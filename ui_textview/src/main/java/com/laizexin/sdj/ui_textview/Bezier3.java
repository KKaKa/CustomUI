package com.laizexin.sdj.ui_textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/12/5
 */
public class Bezier3 extends View {
    private Paint mPaint;
    private PointF startPoint,endPoint,controlPoint1,controlPoint2;
    private float centerX,centerY;
    private MODE mode = MODE.LEFT;
    private Path mPath;

    public enum MODE{
        LEFT,RIGHT
    }

    public Bezier3(Context context) {
        super(context);
        init(context,null);
    }

    public Bezier3(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context,AttributeSet attrs){
        mPaint = new Paint();
        mPaint.setDither(true);
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);

        startPoint = new PointF(0,0);
        endPoint = new PointF(0,0);
        controlPoint1 = new PointF(0,0);
        controlPoint2 = new PointF(0,0);

        mPath = new Path();
    }

    public void setMode(MODE mode){
        this.mode = mode;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w/2;
        centerY = h/2;

        startPoint.set(centerX - 200,centerY);
        endPoint.set(centerX + 200,centerY);
        controlPoint1.set(centerX - 200,centerY - 100);
        controlPoint2.set(centerX + 200,centerY - 100);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if(mode == MODE.LEFT){
            controlPoint1.set(event.getX(),event.getY());
        }else{
            controlPoint2.set(event.getX(),event.getY());
        }
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(8);

        canvas.drawPoint(startPoint.x,startPoint.y,mPaint);
        canvas.drawPoint(endPoint.x,endPoint.y,mPaint);
        canvas.drawPoint(controlPoint1.x,controlPoint1.y,mPaint);
        canvas.drawPoint(controlPoint2.x,controlPoint2.y,mPaint);

        mPaint.setColor(Color.BLUE);
        mPaint.setStrokeWidth(1);
        canvas.drawText("("+startPoint.x+","+startPoint.y+")",startPoint.x,startPoint.y + 10,mPaint);
        canvas.drawText("("+endPoint.x+","+endPoint.y+")",endPoint.x,endPoint.y + 10,mPaint);
        canvas.drawText("("+controlPoint1.x+","+controlPoint1.y+")",controlPoint1.x,controlPoint1.y - 10,mPaint);
        canvas.drawText("("+controlPoint2.x+","+controlPoint2.y+")",controlPoint2.x,controlPoint2.y - 10,mPaint);

        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(4);
        canvas.drawLine(startPoint.x,startPoint.y,controlPoint1.x,controlPoint1.y,mPaint);
        canvas.drawLine(controlPoint1.x,controlPoint1.y,controlPoint2.x,controlPoint2.y,mPaint);
        canvas.drawLine(controlPoint2.x,controlPoint2.y,endPoint.x,endPoint.y,mPaint);

        mPaint.setColor(Color.RED);
        mPath.reset();
        mPath.moveTo(startPoint.x,startPoint.y);
        mPath.cubicTo(controlPoint1.x,controlPoint1.y,controlPoint2.x,controlPoint2.y,endPoint.x,endPoint.y);
        canvas.drawPath(mPath,mPaint);

    }
}
