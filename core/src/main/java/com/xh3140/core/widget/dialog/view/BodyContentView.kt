package com.xh3140.core.widget.dialog.view

import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.xh3140.core.widget.dialog.params.ContentParams
import com.xh3140.core.widget.utils.PixelUtil

class BodyContentView(context: Context) : AppCompatTextView(context) {

    private var mContentParams: ContentParams = ContentParams()

    fun setContentParams(params: ContentParams): BodyContentView {
        mContentParams = params
        return this
    }

    fun configView(): BodyContentView {
        configContentView(mContentParams)
        return this
    }

    private fun configContentView(params: ContentParams) {
        id = android.R.id.content
        if (params.text == null) {
            visibility = View.GONE
        } else {
            layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT, 1F
            )
            movementMethod = ScrollingMovementMethod.getInstance()
            gravity = params.gravity
            text = params.text
            textSize = params.textSize
            setTextColor(params.textColor)
            setBackgroundColor(params.backgroundColor)
            if (params.height != 0) {
                minHeight = PixelUtil.dp2px(context, params.height)
            }
            if (params.padding != null) {
                val padding = params.padding as IntArray
                setPadding(
                    PixelUtil.dp2px(context, padding[0]),
                    PixelUtil.dp2px(context, padding[1]),
                    PixelUtil.dp2px(context, padding[2]),
                    PixelUtil.dp2px(context, padding[3])
                )
            }
            setTypeface(typeface, params.textStyle)
            visibility = View.VISIBLE
        }
    }
}
