package com.laizexin.sdj.customui

import android.app.Activity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.RecyclerView
import android.view.View
import com.laizexin.sdj.ui_textview.AdImageView
import com.zhy.adapter.recyclerview.CommonAdapter
import com.zhy.adapter.recyclerview.base.ViewHolder
import kotlinx.android.synthetic.main.activity_ad_image_view.*

class AdImageViewActivity : Activity() {
    var layoutManager : ScrollLinearLayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ad_image_view)

        val datas = mutableListOf<String>()
        for(item in 1 until 100){
            datas.add(""+item)
        }

        layoutManager = ScrollLinearLayoutManager(this)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = object  : CommonAdapter<String>(this,R.layout.item_recycle,datas){
            override fun convert(holder: ViewHolder?, t: String?, position: Int) {
                if(position % 2 == 0){
                    holder?.setBackgroundRes(R.id.view,R.color.colorPrimaryDark)
                }else if(position % 3 == 0){
                    holder?.setBackgroundRes(R.id.view,R.color.colorPrimary)
                }else{
                    holder?.setBackgroundRes(R.id.view,R.color.material_blue_grey_800)
                }
                if(position > 0 && position % 5 == 0){
                    holder?.setVisible(R.id.view,false)
                    holder?.setVisible(R.id.iv_ad,true)
                    if(position % 2 == 0){
                        holder?.setImageResource(R.id.iv_ad,R.mipmap.pic2)
                    }
                }else{
                    holder?.setVisible(R.id.view,true)
                    holder?.setVisible(R.id.iv_ad,false)
                }
            }
        }
        recyclerView.addOnScrollListener(object  : RecyclerView.OnScrollListener(){

            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val fposition = layoutManager!!.findFirstVisibleItemPosition()
                val lposition = layoutManager!!.findLastVisibleItemPosition()
                for(position in fposition until lposition){
                    val view = layoutManager!!.findViewByPosition(position)
                    val adView = view!!.findViewById<AdImageView>(R.id.iv_ad)
                    if(adView!!.visibility == View.VISIBLE){
                        adView.setPerCent(view.top,layoutManager!!.height,AdImageView.Orientation.REVERSE)
                    }
                }
            }
        })

        Handler().postDelayed({
            recyclerView.smoothScrollToPosition(100)
        },500)
    }

}
