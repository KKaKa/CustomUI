package com.laizexin.sdj.customui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.laizexin.sdj.ui_textview.Bezier3
import kotlinx.android.synthetic.main.activity_bezier3.*

class Bezier3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bezier3)

        switch1.textOff = "右"
        switch1.textOn = "左"
        switch1.showText = true
        switch1.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                bezier3.setMode(Bezier3.MODE.LEFT)
            else
                bezier3.setMode(Bezier3.MODE.RIGHT)
        }
        switch1.isChecked = true
    }
}
