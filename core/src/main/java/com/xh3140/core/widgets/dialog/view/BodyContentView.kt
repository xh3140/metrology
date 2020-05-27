package com.xh3140.core.widgets.dialog.view

import android.content.Context
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.xh3140.core.extensions.dp2px
import com.xh3140.core.widgets.dialog.params.ContentParams

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
            layoutParams = LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, 1F)
            movementMethod = ScrollingMovementMethod.getInstance()
            gravity = params.gravity
            text = params.text
            textSize = params.textSize
            setTextColor(params.textColor)
            setBackgroundColor(params.backgroundColor)
            if (params.height != 0) {
                minHeight = dp2px(params.height)
            }
            params.padding?.also { padding ->
                setPadding(dp2px(padding[0]), dp2px(padding[1]), dp2px(padding[2]), dp2px(padding[3]))
            }
            setTypeface(typeface, params.textStyle)
            visibility = View.VISIBLE
        }
    }
}
