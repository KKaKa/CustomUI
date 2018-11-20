package com.laizexin.sdj.customui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_auto_textview.*

/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/11/20
 */
class AutoScrollingTextViewActivity : AppCompatActivity() {
    var index = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auto_textview)

        btn_add.setOnClickListener{
            index ++
            addText()
        }
    }

    fun addText(){
        tv_auto.post {
            tv_auto.append("------------------\n")
            tv_auto.append(String.format("这是第%s行\n",index))
        }
    }
}