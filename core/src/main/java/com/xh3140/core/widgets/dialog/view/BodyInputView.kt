package com.xh3140.core.widgets.dialog.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import com.xh3140.core.extensions.dp2px
import com.xh3140.core.widgets.dialog.params.InputParams

class BodyInputView(context: Context) : AppCompatEditText(context) {

    private var mInputParams: InputParams = InputParams()

    fun setInputParams(params: InputParams): BodyInputView {
        mInputParams = params
        return this
    }

    fun configView(): BodyInputView {
        configInputView(mInputParams)
        return this
    }

    private fun configInputView(params: InputParams) {
        id = android.R.id.input
        if (params.type != InputType.TYPE_NULL) {
            inputType = params.type
        }
        textSize = params.textSize
        setTextColor(params.textColor)
        gravity = params.gravity
        hint = params.hint
        setHintTextColor(params.hintColor)
        viewTreeObserver.addOnGlobalLayoutListener(object : OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                viewTreeObserver.removeOnGlobalLayoutListener(this)
                val newHeight = dp2px(params.height)
                if (newHeight > measuredHeight) {
                    height = newHeight
                }
            }
        })
        params.text?.also { text ->
            setText(text)
            setSelection(text.length)
        }
        isSingleLine = params.isSingleLine
        if (params.maxLines > 0) {
            maxLines = params.maxLines
        }
        maxEms = params.maxEms
        if (params.maxLength > 0) {
            filters = arrayOf<InputFilter>(LengthFilter(params.maxLength))
        }
        if (params.backgroundResourceId != 0) {
            setBackgroundResource(params.backgroundResourceId)
        } else {
            val strokeWidth: Int = dp2px(params.strokeWidth)
            val inputDrawable = GradientDrawable()
            inputDrawable.setColor(params.backgroundColor)
            inputDrawable.setStroke(strokeWidth, params.strokeColor)
            inputDrawable.cornerRadius = params.cornerRadius
            background = inputDrawable
        }
        layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply {
            params.margins?.also { margins ->
                setMargins(dp2px(margins[0]), dp2px(margins[1]), dp2px(margins[2]), dp2px(margins[3]))
            }
        }
        params.padding?.also { padding ->
            setPadding(dp2px(padding[0]), dp2px(padding[1]), dp2px(padding[2]), dp2px(padding[3]))
        }
        setTypeface(typeface, params.textStyle)
    }
}