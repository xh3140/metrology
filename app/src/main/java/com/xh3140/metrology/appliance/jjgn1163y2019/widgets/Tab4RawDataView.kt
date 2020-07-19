package com.xh3140.metrology.appliance.jjgn1163y2019.widgets

import android.content.Context
import android.graphics.Color
import android.text.InputType
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import com.xh3140.core.widgets.common.LinearLayoutDivider
import com.xh3140.metrology.appliance.widgets.RawDataView
import java.math.BigDecimal


/**
 * @Author : xh3140
 * @Time : 2020/7/19 0:57
 * @File : Tab4RawDataView.kt
 */

class Tab4RawDataView : RawDataView {

    private val editTextArray1 = Array(18) { newEditText(InputType.TYPE_CLASS_NUMBER, 1f) }
    private val editTextArray2 = Array(18) { newEditText(InputType.TYPE_CLASS_NUMBER, 1f) }
    private val textViewArray = Array(18) { newTextView(null, 0.997f) }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        orientation = VERTICAL
        dividerDrawable = LinearLayoutDivider(Color.GRAY)
        showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
        addView(newLayout(SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_END, newTextView("心率示值误差(单位：次/min)", 1f)))
        addView(
            newLayout(
                SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END,
                newTextView("标准值", 1f),
                newNestLayout(
                    2f, SHOW_DIVIDER_MIDDLE,
                    newLayout(SHOW_DIVIDER_MIDDLE, newTextView("监护仪测量的心率示值", 2f)),
                    newLayout(SHOW_DIVIDER_MIDDLE, newTextView("0.5 mV", 1f), newTextView("2.0 mV", 1f))
                ),
                newTextView("相对误差\n%", 1f)
            )
        )
        for (i in 0..17) {
            addView(
                newLayout(
                    SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END,
                    newTextView("${30 + i * 10}", 1.003f),
                    editTextArray1[i],
                    editTextArray2[i],
                    textViewArray[i]
                )
            )
        }
    }

    override fun calculateAllData() {
        for (i in 0..17) {
            calculateData(editTextArray1[i], editTextArray2[i], textViewArray[i], 30 + i * 10)
        }
    }


    override fun randomAllData() {
        for (i in 0..17) {
            val v0 = 30 + i * 10
            val v1 = BigDecimal.valueOf(0.1 * (v0 - 5..v0 + 5).random()).toBigInteger()
            val v2 = BigDecimal.valueOf(0.1 * (v0 - 5..v0 + 5).random()).toBigInteger()
            editTextArray1[i].setText("$v1")
            editTextArray2[i].setText("$v2")
        }
        calculateAllData()
    }

    override fun clearAllData() {
        for (i in 0..17) {
            editTextArray1[i].text?.clear()
            editTextArray2[i].text?.clear()
            textViewArray[i].text = ""
        }
    }

    private fun calculateData(editText1: AppCompatEditText, editText2: AppCompatEditText, textView: AppCompatTextView, heartRate: Int) {
        val value1 = editText1.text.toString()
        val value2 = editText2.text.toString()
        if (value1.isNotEmpty() && value2.isNotEmpty()) {
            val error1 = BigDecimal(value1)
                .subtract(BigDecimal(heartRate))
                .setScale(0, BigDecimal.ROUND_HALF_UP)
            val error2 = BigDecimal(value2)
                .subtract(BigDecimal(heartRate))
                .setScale(0, BigDecimal.ROUND_HALF_UP)
            val error = if (error1.abs().toInt() > error2.abs().toInt()) error1 else error2
            val display = if (error1.abs().toInt() > error2.abs().toInt()) value1 else value2
            val limit = BigDecimal(display)
                .multiply(BigDecimal(0.05))
                .add(BigDecimal.ONE)
                .toInt()
            val error3 = error.toInt()
            textView.text = "$error3"
            setTextViewErrorColor(textView, error3 >= -limit && error3 <= limit)
        }
    }
}