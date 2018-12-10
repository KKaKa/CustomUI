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
public class Bezier2 extends View {
    private float centerX,centerY;
    private PointF pointStart,pointEnd,pointControl;
    private Paint mPaint;
    private Path path;

    public Bezier2(Context context) {
        super(context);
        init(context,null);
    }

    public Bezier2(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context,AttributeSet attrs){
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setColor(Color.BLACK);
        mPaint.setStrokeWidth(8);
        mPaint.setStyle(Paint.Style.STROKE);

        pointStart = new PointF(0,0);
        pointEnd = new PointF(0,0);
        pointControl = new PointF(0,0);

        path = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        centerX = w/2;
        centerY = h/2;

        pointStart.set(centerX - 200,centerY);
        pointEnd.set(centerX + 200,centerY);
        pointControl.set(centerX,centerY - 100);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        pointControl.set(event.getX(),event.getY());
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        mPaint.setColor(Color.GRAY);
        mPaint.setStrokeWidth(8);
        canvas.drawPoint(pointStart.x,pointStart.y,mPaint);
        canvas.drawPoint(pointEnd.x,pointEnd.y,mPaint);
        canvas.drawPoint(pointControl.x,pointControl.y,mPaint);

        mPaint.setStrokeWidth(1);
        mPaint.setColor(Color.BLUE);
        canvas.drawText("("+pointStart.x+","+pointStart.y+")",pointStart.x,pointStart.y + 10,mPaint);
        canvas.drawText("("+pointEnd.x+","+pointEnd.y+")",pointEnd.x,pointEnd.y + 10,mPaint);
        canvas.drawText("("+pointControl.x+","+pointControl.y+")",pointControl.x,pointControl.y - 10,mPaint);

        mPaint.setStrokeWidth(4);
        mPaint.setColor(Color.GRAY);
        canvas.drawLine(pointStart.x,pointStart.y,pointControl.x,pointControl.y,mPaint);
        canvas.drawLine(pointControl.x,pointControl.y,pointEnd.x,pointEnd.y,mPaint);

        mPaint.setColor(Color.RED);
        path.reset();
        path.moveTo(pointStart.x,pointStart.y);
        path.quadTo(pointControl.x,pointControl.y,pointEnd.x,pointEnd.y);

        canvas.drawPath(path,mPaint);
    }
}
