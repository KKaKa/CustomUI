package com.laizexin.sdj.customui

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var intent: Intent?

        btn1.setOnClickListener {
            intent = Intent(this,MarqueeTextViewActivity::class.java)
            startActivity(intent)
        }

        btn2.setOnClickListener {
            intent = Intent(this,AutoScrollingTextViewActivity::class.java)
            startActivity(intent)
        }
    }
}
