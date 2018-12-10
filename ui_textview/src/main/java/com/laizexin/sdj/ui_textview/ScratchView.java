package com.laizexin.sdj.ui_textview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * @Description: 刮刮乐
 * @Author: laizexin
 * @Time: 2018/12/7
 */
public class ScratchView extends View {
    private Canvas mScratchCanvas;
    private Path mPath;
    private Paint mPaint;
    private Bitmap mBottomBitmap,mTopBitmap;

    private float x = 0;// 当前触点X坐标
    private float y = 0;// 当前触点Y坐标

    private boolean isCompleted;
    private OnFinishListener listener;

    public ScratchView(Context context) {
        super(context);
        init(context,null);
    }

    public ScratchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context,attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        mPaint = new Paint();
        mPaint.setAlpha(0);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(30);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));

        mBottomBitmap = BitmapFactory.decodeResource(getResources(),R.drawable.test);
        mTopBitmap = Bitmap.createBitmap(mBottomBitmap.getWidth(),mBottomBitmap.getHeight(), Bitmap.Config.ARGB_8888);

        mPath = new Path();
        mScratchCanvas = new Canvas();

    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        mBottomBitmap = Narrowpicture(mBottomBitmap,measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
        mTopBitmap = Narrowpicture(mTopBitmap,measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
        mScratchCanvas.setBitmap(mTopBitmap);
        mScratchCanvas.drawColor(Color.GRAY);
        setMeasuredDimension(measureWidth(widthMeasureSpec),measureHeight(heightMeasureSpec));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(mBottomBitmap,0,0,null);
        canvas.drawBitmap(mTopBitmap,0,0,null);
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                x = event.getX();
                y = event.getY();
                mPath.reset();
                mPath.moveTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_MOVE:
                mPath.lineTo(event.getX(),event.getY());
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (!isCompleted)
                    new Thread(mRunnable).start();
                else{
                    if(listener != null)
                        listener.onFinish();
                    clearScratchView();
                }
                break;
        }
        mScratchCanvas.drawPath(mPath,mPaint);
        invalidate();
        return true;
    }

    private void clearScratchView(){
        mTopBitmap.eraseColor(Color.TRANSPARENT);
        postInvalidate();
    }

    public static Bitmap Narrowpicture(Bitmap bitmap, int screenWidth, int screenHight) {
        int w = bitmap.getWidth();
        int h = bitmap.getHeight();
        Matrix matrix = new Matrix();
        float scale = (float) screenWidth / w;
        float scale2 = (float) screenHight / h;
        matrix.postScale(scale, scale2);
        Bitmap bmp = Bitmap.createBitmap(bitmap, 0, 0, w, h, matrix, true);
        if (!bitmap.equals(bmp) && !bitmap.isRecycled()) {
            bitmap.recycle();
        }
        return bmp;
    }

    private int measureWidth(int measureSpec){
        int result = 0;
        int spaceMode = MeasureSpec.getMode(measureSpec);
        int spaceSize = MeasureSpec.getSize(measureSpec);

        if(spaceMode == MeasureSpec.EXACTLY){
            result = spaceSize;
        }else{
            result = 200;
            if(spaceMode == MeasureSpec.AT_MOST){
                result = Math.min(result,spaceSize);
            }
        }
        return result;
    }


    private int measureHeight(int measureSpec){
        int result = 0;
        int spaceMode = MeasureSpec.getMode(measureSpec);
        int spaceSize = MeasureSpec.getSize(measureSpec);

        if(spaceMode == MeasureSpec.EXACTLY){
            result = spaceSize;
        }else{
            result = 100;
            if(spaceMode == MeasureSpec.AT_MOST){
                result = Math.min(result,spaceSize);
            }
        }
        return result;
    }

    private Runnable mRunnable = new Runnable()
    {
        @Override
        public void run() {
            int w = getWidth();
            int h = getHeight();

            float wipeArea = 0;
            float totalArea = w * h;
            Bitmap bitmap = mTopBitmap;
            int[] mPixels = new int[w * h];

            bitmap.getPixels(mPixels, 0, w, 0, 0, w, h);
            //计算被擦除的区域（也就是像素值为0）的像素数之和
            for (int i = 0; i < w; i++) {
                for (int j = 0; j < h; j++) {
                    int index = i + j * w;
                    if (mPixels[index] == 0) {
                        wipeArea++;
                    }
                }
            }
            //计算擦除的像素数与总像素数的百分比
            if (wipeArea > 0 && totalArea > 0) {
                int percent = (int) (wipeArea * 100 / totalArea);
                if (percent > 60) {
                    isCompleted = true;
                }
            }
        }
    };

    public interface OnFinishListener{
        void onFinish();
    }

    public void setOnFinishListener(OnFinishListener listener) {
        this.listener = listener;
    }


}
