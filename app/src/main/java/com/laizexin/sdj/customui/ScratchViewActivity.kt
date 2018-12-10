package com.laizexin.sdj.customui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_scratch_view.*

class ScratchViewActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_scratch_view)

        scratchView.setOnFinishListener{
            Toast.makeText(this,"中奖",Toast.LENGTH_LONG).show()
        }
    }
}
