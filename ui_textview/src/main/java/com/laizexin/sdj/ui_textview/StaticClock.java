package com.laizexin.sdj.ui_textview;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @Description:静态仪表盘
 * @Author: laizexin
 * @Time: 2018/12/4
 */
public class StaticClock extends View{

    public StaticClock(Context context) {
        super(context);
    }

    public StaticClock(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StaticClock(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //画外圆
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setDither(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(3);
        canvas.drawCircle(getWidth()/2,getHeight()/2,getWidth()/2,paint);
        //画刻度
        paint.setStrokeWidth(3);
        for(int i =0;i<24;i++){
            if(i == 0 || i == 6 || i == 12 || i == 18){
                //大刻度
                paint.setStrokeWidth(6);
                paint.setTextSize(16);
                canvas.drawLine(getWidth()/2,getHeight()/2 - getWidth()/2,getWidth()/2,getHeight()/2 - getWidth()/2 + 60,paint);
            }else{
                //小刻度
                paint.setStrokeWidth(3);
                paint.setTextSize(12);
                canvas.drawLine(getWidth()/2,getHeight()/2 - getWidth()/2,getWidth()/2,getHeight()/2 - getWidth()/2 + 30,paint);
            }
            canvas.rotate(15,getWidth()/2,getHeight()/2);
        }
        //画指针
        canvas.translate(getWidth()/2,getHeight()/2);
        paint.setStrokeWidth(20);
        canvas.drawLine(0,0,100,100,paint);
        paint.setStrokeWidth(14);
        canvas.save();
        canvas.drawLine(0,0,100,200,paint);
        canvas.restore();
    }
}
