package com.laizexin.sdj.customui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_marquee_textview.*

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/11/20
 */
class MarqueeTextViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_marquee_textview)

        tv_mar.setLimitNum(-1)
    }

}