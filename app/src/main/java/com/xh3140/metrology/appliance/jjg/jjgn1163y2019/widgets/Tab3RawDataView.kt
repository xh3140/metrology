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
 * @Time : 2020/7/19 0:35
 * @File : Tab3RawDataView.kt
 */

class Tab3RawDataView : RawDataView {
    private val editText1: AppCompatEditText = createEditText(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL, 0.8f)
    private val editText2: AppCompatEditText = createEditText(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL, 0.8f)
    private val editText3: AppCompatEditText = createEditText(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL, 0.8f)
    private val editText4: AppCompatEditText = createEditText(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL, 0.8f)
    private val editText5: AppCompatEditText = createEditText(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL, 0.8f)
    private val textView1: AppCompatTextView = createTextView(null, 0.8f)
    private val textView2: AppCompatTextView = createTextView(null, 0.8f)
    private val textView3: AppCompatTextView = createTextView(null, 0.8f)
    private val textView4: AppCompatTextView = createTextView(null, 0.8f)
    private val textView5: AppCompatTextView = createTextView(null, 0.8f)

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        orientation = VERTICAL
        dividerDrawable = LinearLayoutDivider(Color.GRAY)
        showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
        addView(createLayout(SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_END, createTextView("幅频特性", 1f)))
        addView(
            createLayout(
                SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END,
                createTextView("频率", 1f),
                createNestLayout(
                    2f, SHOW_DIVIDER_MIDDLE,
                    createLayout(SHOW_DIVIDER_MIDDLE, createTextView("输入标准值", 2f)),
                    createLayout(SHOW_DIVIDER_MIDDLE, createTextView("增益", 1f), createTextView("电压", 1f))
                ),
                createTextView("幅值测得值\nmm", 0.8f),
                createTextView("相对误差\n%", 0.8f)
            )
        )
        addView(
            createLayout(
                SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END,
                createNestLayout(
                    1f, SHOW_DIVIDER_MIDDLE,
                    createLayout(SHOW_DIVIDER_MIDDLE, createTextView("1 Hz", 1f)),
                    createLayout(SHOW_DIVIDER_MIDDLE, createTextView("2 Hz", 1f)),
                    createLayout(SHOW_DIVIDER_MIDDLE, createTextView("5 Hz", 1f)),
                    createLayout(SHOW_DIVIDER_MIDDLE, createTextView("10 Hz", 1f)),
                    createLayout(SHOW_DIVIDER_MIDDLE, createTextView("25 Hz", 1f))
                ),
                createTextView("10 mm/mV", 1f),
                createTextView("1.0 mV", 1f),
                createNestLayout(
                    1.6f, SHOW_DIVIDER_MIDDLE,
                    createLayout(SHOW_DIVIDER_MIDDLE, editText1, textView1),
                    createLayout(SHOW_DIVIDER_MIDDLE, editText2, textView2),
                    createLayout(SHOW_DIVIDER_MIDDLE, editText3, textView3),
                    createLayout(SHOW_DIVIDER_MIDDLE, editText4, textView4),
                    createLayout(SHOW_DIVIDER_MIDDLE, editText5, textView5)
                )
            )
        )
    }

    fun calculateAllData() {
        calculateData(editText1, textView1)
        calculateData(editText2, textView2)
        calculateData(editText3, textView3)
        calculateData(editText4, textView4)
        calculateData(editText5, textView5)
    }


    fun randomAllData() {
        val v1 = BigDecimal.valueOf(0.1 * (90..110).random()).setScale(1, BigDecimal.ROUND_HALF_UP)
        val v2 = BigDecimal.valueOf(0.1 * (90..110).random()).setScale(1, BigDecimal.ROUND_HALF_UP)
        val v3 = BigDecimal.valueOf(0.1 * (90..110).random()).setScale(1, BigDecimal.ROUND_HALF_UP)
        val v4 = BigDecimal.valueOf(0.1 * (180..220).random()).setScale(1, BigDecimal.ROUND_HALF_UP)
        val v5 = BigDecimal.valueOf(0.1 * (90..110).random()).setScale(1, BigDecimal.ROUND_HALF_UP)
        editText1.setText("$v1")
        editText2.setText("$v2")
        editText3.setText("$v3")
        editText4.setText("$v4")
        editText5.setText("$v5")
        calculateAllData()
    }

    fun clearAllData() {
        editText1.text?.clear()
        editText2.text?.clear()
        editText3.text?.clear()
        editText4.text?.clear()
        editText5.text?.clear()
        textView1.text = ""
        textView2.text = ""
        textView3.text = ""
        textView4.text = ""
        textView5.text = ""
    }

    private fun calculateData(editText: AppCompatEditText, textView: AppCompatTextView) {
        val value = editText.text.toString()
        val value0 = editText4.text.toString()
        if (value.isNotEmpty() && value0.isNotEmpty()) {
            val error1 = BigDecimal(value)
                .subtract(BigDecimal(value0))
                .divide(BigDecimal(value0), 16, BigDecimal.ROUND_HALF_UP)
                .multiply(BigDecimal(100))
                .setScale(1, BigDecimal.ROUND_HALF_UP)
            val error2 = error1.toDouble()
            textView.text = "$error1"
            if (error2 >= -30.0 && error2 <= 5.0) {
                textView.setTextColor(Color.parseColor("#8A000000"))
            } else {
                textView.setTextColor(Color.RED)
            }
        }
    }
}