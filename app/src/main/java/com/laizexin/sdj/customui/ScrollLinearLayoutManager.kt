package com.laizexin.sdj.customui

import android.content.Context
import android.graphics.PointF
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.LinearSmoothScroller
import android.support.v7.widget.RecyclerView
import android.util.DisplayMetrics



/**
 * @Description:
 * @Author: laizexin
 * @Time: 2018/12/18
 */
class ScrollLinearLayoutManager(context: Context?) : LinearLayoutManager(context) {
    companion object {
        const val MILLISECONDS_PER_INCH = 500f
    }

    override fun smoothScrollToPosition(recyclerView: RecyclerView?, state: RecyclerView.State?, position: Int) {
        val linearSmoothScroller = object : LinearSmoothScroller(recyclerView?.context) {

            override fun computeScrollVectorForPosition(targetPosition: Int): PointF? {
                return this@ScrollLinearLayoutManager.computeScrollVectorForPosition(targetPosition)
            }

            override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
                return MILLISECONDS_PER_INCH / displayMetrics.densityDpi
            }

        }
        linearSmoothScroller.targetPosition = position
        startSmoothScroll(linearSmoothScroller)
    }
}