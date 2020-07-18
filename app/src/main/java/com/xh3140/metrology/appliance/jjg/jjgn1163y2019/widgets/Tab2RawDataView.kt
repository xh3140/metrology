package com.xh3140.metrology.appliance.jjg.jjgn1163y2019.widgets

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
 * @Time : 2020/7/18 21:42
 * @File : Tab2RawDataView.kt
 */

class Tab2RawDataView : RawDataView {
    private val editText1: AppCompatEditText = createEditText(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL, 1f)
    private val editText2: AppCompatEditText = createEditText(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL, 1f)
    private val textView1x1: AppCompatTextView = createTextView(null, 1f)
    private val textView1x2: AppCompatTextView = createTextView(null, 1f)
    private val textView2x1: AppCompatTextView = createTextView(null, 1f)
    private val textView2x2: AppCompatTextView = createTextView(null, 1f)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        orientation = VERTICAL
        dividerDrawable = LinearLayoutDivider(Color.GRAY)
        showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
        addView(createLayout(SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_END, createTextView("扫描速度误差", 1f)))
        addView(
            createLayout(
                SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END,
                createTextView("扫描速度设置值", 1f),
                createTextView("扫描时间", 1f),
                createTextView("显示宽度", 1f),
                createTextView("扫描速度\nmm/s", 1f),
                createTextView("相对误差\n%", 1f)
            )
        )
        addView(
            createLayout(
                SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END,
                createTextView("25 mm/s", 1f),
                createTextView("2s", 1f),
                editText1,
                textView1x1,
                textView1x2
            )
        )
        addView(
            createLayout(
                SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END,
                createTextView("50 mm/s", 1f),
                createTextView("1s", 1f),
                editText2,
                textView2x1,
                textView2x2
            )
        )
    }

    fun calculateAllData() {
        calculateData(editText1, textView1x1, textView1x2, 25.0, 2.0)
        calculateData(editText2, textView2x1, textView2x2, 50.0, 1.0)
    }


    fun randomAllData() {
        val v1 = BigDecimal.valueOf(0.1 * (90..110).random()).setScale(1, BigDecimal.ROUND_HALF_UP)
        val v2 = BigDecimal.valueOf(0.1 * (90..110).random()).setScale(1, BigDecimal.ROUND_HALF_UP)
        editText1.setText("$v1")
        editText2.setText("$v2")
        calculateAllData()
    }

    fun clearAllData() {
        editText1.text?.clear()
        editText2.text?.clear()
        textView1x1.text = ""
        textView1x2.text = ""
        textView2x1.text = ""
        textView2x2.text = ""
    }

    private fun calculateData(editText: AppCompatEditText, textView1: AppCompatTextView, textView2: AppCompatTextView, speed: Double, time: Double) {
        val value = editText.text.toString()
        if (value.isNotEmpty()) {
            val speed1 = BigDecimal(value)
                .divide(BigDecimal(time), 16, BigDecimal.ROUND_HALF_UP)
            val speed2 = speed1.setScale(1, BigDecimal.ROUND_HALF_UP)
            val error1 = BigDecimal(speed)
                .subtract(speed1)
                .divide(speed1, 16, BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal(100))
                .setScale(1, BigDecimal.ROUND_HALF_UP)
            val error2 = error1.toDouble()
            textView1.text = "$speed2"
            textView2.text = "$error1"
            if (error2 >= -10.0 && error2 <= 10.0) {
                textView2.setTextColor(Color.parseColor("#8A000000"))
            } else {
                textView2.setTextColor(Color.RED)
            }
        }
    }
}