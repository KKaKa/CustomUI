package com.laizexin.sdj.ui_textview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.text.method.ScrollingMovementMethod;
import android.util.AttributeSet;

/**
 * @Description:自动滚动的TextView
 * @Author: laizexin
 * @Time: 2018/11/20
 */
public class AutoScrollingTextView extends android.support.v7.widget.AppCompatTextView {

    public AutoScrollingTextView(Context context) {
        super(context);
        init();
    }

    public AutoScrollingTextView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public AutoScrollingTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        this.setMovementMethod(ScrollingMovementMethod.getInstance());
    }

    @Override
    public void append(CharSequence text, int start, int end) {
        super.append(text, start, end);
        setTextViewLast();
    }

    private void setTextViewLast(){
        int offset=this.getLineCount()*this.getLineHeight();
        if(offset>(this.getHeight()-this.getLineHeight()-20)){
            this.scrollTo(0,offset-this.getHeight()+this.getLineHeight()+20);
        }
    }
}
