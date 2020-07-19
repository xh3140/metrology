package com.xh3140.metrology.appliance.widgets

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.view.setMargins
import androidx.core.view.setPadding
import com.xh3140.core.extensions.dp2px
import com.xh3140.core.widgets.common.LinearLayoutDivider


/**
 * @Author : xh3140
 * @Time : 2020/7/18 20:19
 * @File : RawDataView.kt
 */

abstract class RawDataView : LinearLayout {

    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        orientation = VERTICAL
        dividerDrawable = LinearLayoutDivider(Color.GRAY)
        showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT).apply { setMargins(dp2px(8)) }
    }

    abstract fun calculateAllData()

    abstract fun randomAllData()

    abstract fun clearAllData()

    protected fun newLayout(showDividers: Int, vararg views: View): LinearLayout {
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

    protected fun newNestLayout(weight: Float, showDividers: Int, vararg views: View): LinearLayout {
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

    protected fun newTextView(text: String?, weight: Float): AppCompatTextView {
        return AppCompatTextView(context).apply {
            layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, weight)
            this.text = text
            textSize = 12f
            gravity = Gravity.CENTER
            setPadding(dp2px(8))
        }
    }

    protected fun newEditText(inputType: Int, weight: Float): AppCompatEditText {
        return AppCompatEditText(context).apply {
            layoutParams = LayoutParams(0, LayoutParams.MATCH_PARENT, weight)
            this.inputType = inputType
            textSize = 12f
            background = null
            gravity = Gravity.CENTER
            setPadding(dp2px(8))
        }
    }

    protected fun setTextViewErrorColor(textView: AppCompatTextView, flag: Boolean) {
        textView.setTextColor(if (flag) Color.parseColor("#8A000000") else Color.RED)
    }
}