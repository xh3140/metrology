package com.xh3140.metrology.appliance.widgets

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Space
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.xh3140.core.extensions.dp2px
import com.xh3140.core.widgets.common.LinearLayoutDivider


/**
 * @Author : xh3140
 * @Time : 2020/7/18 17:24
 * @File : Tab1View.kt
 */

abstract class MethodsView : LinearLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        orientation = VERTICAL
        dividerDrawable = LinearLayoutDivider(Color.GRAY)
        showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply { setMargins(dp2px(8)) }
    }

    protected fun createLayout(vararg views: View): LinearLayout {
        return LinearLayout(context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            orientation = HORIZONTAL
            dividerDrawable = LinearLayoutDivider(Color.GRAY)
            showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_END
            for (view in views) {
                addView(view)
            }
        }
    }

    protected fun createTextView(text: String, textSize: Float): AppCompatTextView {
        return AppCompatTextView(context).apply {
            layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT, 1f)
            this.text = text
            this.textSize = textSize
            setPadding(dp2px(8))
        }
    }

    protected fun createImageView(width: Int, height: Int, resId: Int): AppCompatImageView {
        return AppCompatImageView(context).apply {
            layoutParams = LayoutParams(width, height).apply { gravity = Gravity.CENTER }
            maxWidth = width
            maxHeight = height
            adjustViewBounds = true
            scaleType = ImageView.ScaleType.FIT_CENTER
            setImageResource(resId)
            setPadding(dp2px(8))
        }
    }

    protected fun createSpace(weight: Float = 1f): Space {
        return Space(context).apply {
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT, weight)
        }
    }
}