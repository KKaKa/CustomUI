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

        btn3.setOnClickListener{
            intent = Intent(this,FlickerTextViewActivity::class.java)
            startActivity(intent)
        }

        btn4.setOnClickListener{
            intent = Intent(this,StaticBlockActivity::class.java)
            startActivity(intent)
        }

        btn5.setOnClickListener {
            intent = Intent(this,Bezier2Activity::class.java)
            startActivity(intent)
        }

        btn6.setOnClickListener{
            intent = Intent(this,Bezier3Activity::class.java)
            startActivity(intent)
        }

        btn7.setOnClickListener {
            intent = Intent(this,ScratchViewActivity::class.java)
            startActivity(intent)
        }
    }
}
