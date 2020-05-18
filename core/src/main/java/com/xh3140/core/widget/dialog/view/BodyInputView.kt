package com.xh3140.core.widget.dialog.view

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.text.InputFilter
import android.text.InputFilter.LengthFilter
import android.text.InputType
import android.view.ViewTreeObserver.OnGlobalLayoutListener
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatEditText
import com.xh3140.core.widget.dialog.params.InputParams
import com.xh3140.core.widget.utils.PixelUtil


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
                if (params.height > measuredHeight) {
                    height = PixelUtil.dp2px(context, params.height)
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
            val strokeWidth: Int = PixelUtil.dp2px(context, params.strokeWidth)
            val inputDrawable = GradientDrawable()
            inputDrawable.setColor(params.backgroundColor)
            inputDrawable.setStroke(strokeWidth, params.strokeColor)
            inputDrawable.cornerRadius = params.cornerRadius
            background = inputDrawable
        }
        layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            params.margins?.also { margins ->
                setMargins(
                    PixelUtil.dp2px(context, margins[0]),
                    PixelUtil.dp2px(context, margins[1]),
                    PixelUtil.dp2px(context, margins[2]),
                    PixelUtil.dp2px(context, margins[3])
                )
            }
        }
        params.padding?.also { padding ->
            setPadding(
                PixelUtil.dp2px(context, padding[0]),
                PixelUtil.dp2px(context, padding[1]),
                PixelUtil.dp2px(context, padding[2]),
                PixelUtil.dp2px(context, padding[3])
            )
        }
        setTypeface(typeface, params.textStyle)
    }
}