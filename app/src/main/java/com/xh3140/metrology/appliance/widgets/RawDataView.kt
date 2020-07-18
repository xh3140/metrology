package com.xh3140.metrology.appliance.widgets

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.setPadding
import com.xh3140.core.extensions.dp2px
import com.xh3140.core.widgets.common.LinearLayoutDivider


/**
 * @Author : xh3140
 * @Time : 2020/7/18 20:19
 * @File : RawDataView.kt
 */

abstract class RawDataView : LinearLayout {
    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    protected fun createLayout(showDividers: Int, vararg views: View): LinearLayout {
        return LinearLayout(context).apply {
            orientation = HORIZONTAL
            layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
            dividerDrawable = LinearLayoutDivider(Color.GRAY)
            this.showDividers = showDividers
            for (view in views) {
                addView(view)
            }
        }
    }

    protected fun createNestLayout(weight: Float, showDividers: Int, vararg views: View): LinearLayout {
        return LinearLayout(context).apply {
            orientation = VERTICAL
            layoutParams = LayoutParams(0, LayoutParams.WRAP_CONTENT, weight)
            dividerDrawable = LinearLayoutDivider(Color.GRAY)
            this.showDividers = showDividers
            for (view in views) {
                addView(view)
            }
        }
    }

    protected fun createTextView(text: String?, weight: Float): AppCompatTextView {
        return AppCompatTextView(context).apply {
            layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, weight)
            this.text = text
            textSize = 12f
            gravity = Gravity.CENTER
            setPadding(dp2px(8))
        }
    }

    protected fun createEditText(inputType: Int, weight: Float): AppCompatEditText {
        return AppCompatEditText(context).apply {
            layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, weight)
            this.inputType = inputType
            textSize = 12f
            background = null
            gravity = Gravity.CENTER
            setPadding(dp2px(8))
        }
    }
}