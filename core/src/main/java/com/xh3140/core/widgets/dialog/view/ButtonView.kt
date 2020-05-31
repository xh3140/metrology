package com.xh3140.core.widgets.dialog.view

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatTextView
import com.xh3140.core.extensions.dp2px
import com.xh3140.core.widgets.dialog.params.ButtonParams

class ButtonView(context: Context) : AppCompatTextView(context) {
    fun configButtonView(params: ButtonParams) {
        if (params.text == null) {
            visibility = View.GONE
        } else {
            layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f)
            gravity = Gravity.CENTER
            text = params.text
            isEnabled = !params.disable
            setTextColor(if (!params.disable) params.textColor else params.textColorDisable)
            textSize = params.textSize
            if (params.height != 0F) {
                height = dp2px(params.height)
            }
            setTypeface(typeface, params.textStyle)
            visibility = View.VISIBLE
        }
    }
}