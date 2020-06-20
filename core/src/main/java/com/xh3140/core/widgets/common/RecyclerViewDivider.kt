package com.xh3140.core.widgets.common

import android.content.Context
import android.graphics.Canvas
import android.graphics.Rect
import android.graphics.drawable.Drawable
import androidx.recyclerview.widget.RecyclerView
import kotlin.math.roundToInt

/**
 * RecyclerView分割线,去除最后的一条
 */
class RecyclerViewDivider(context: Context) : RecyclerView.ItemDecoration() {

    private val mBounds = Rect()

    private val mDivider: Drawable?

    init {
        val array = intArrayOf(android.R.attr.listDivider)
        val typedArray = context.obtainStyledAttributes(array)
        mDivider = typedArray.getDrawable(0)
        typedArray.recycle()
    }

    override fun onDraw(canvas: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        mDivider?.also { divider ->
            canvas.save()
            val left: Int
            val right: Int
            if (!parent.clipToPadding) {
                left = 0
                right = parent.width
            } else {
                left = parent.paddingLeft
                right = parent.width - parent.paddingRight
                val top = parent.paddingTop
                val bottom = parent.height - parent.paddingBottom
                canvas.clipRect(left, top, right, bottom)
            }
            for (i in 0 until parent.childCount - 1) {
                val child = parent.getChildAt(i)
                parent.getDecoratedBoundsWithMargins(child, mBounds)
                val bottom: Int = mBounds.bottom + child.translationY.roundToInt()
                val top = bottom - divider.intrinsicHeight
                divider.setBounds(left, top, right, bottom)
                divider.draw(canvas)
            }
            canvas.restore()
        }
    }
}