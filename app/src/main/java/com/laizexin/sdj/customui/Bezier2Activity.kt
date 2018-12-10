package com.laizexin.sdj.customui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.laizexin.sdj.ui_textview.BezierLove
import kotlinx.android.synthetic.main.activity_bezier2.*

class Bezier2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bezier2)
        bezier2.setInitMode(BezierLove.MODE.FAVOURITE);
        bezier2.setOnClickListener{
            if(bezier2.mode == BezierLove.MODE.NORMAL){
                bezier2.changeMode(BezierLove.MODE.FAVOURITE)
            }else{
                bezier2.changeMode(BezierLove.MODE.NORMAL)
            }
        }
    }
}
