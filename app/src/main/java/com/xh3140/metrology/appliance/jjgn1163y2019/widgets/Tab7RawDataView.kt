package com.xh3140.metrology.appliance.jjgn1163y2019.widgets

import android.content.Context
import android.graphics.Color
import android.text.InputType
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.appcompat.widget.AppCompatTextView
import com.xh3140.core.extensions.kPa2mmHg
import com.xh3140.core.extensions.mmHg2kPa
import com.xh3140.core.widgets.button.FreeRadioGroup
import com.xh3140.core.widgets.common.LinearLayoutDivider
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.widgets.RawDataView
import java.math.BigDecimal


/**
 * @Author : xh3140
 * @Time : 2020/7/19 23:23
 * @File : Tab7RawDataView.kt
 */

class Tab7RawDataView : RawDataView {

    private val radioButton1 = AppCompatRadioButton(context)
    private val radioButton2 = AppCompatRadioButton(context)
    private val radioGroup = FreeRadioGroup(radioButton1, radioButton2)
    private val editTextArray1 = Array(5) { newEditText(InputType.TYPE_CLASS_NUMBER, 1f) }
    private val editTextArray2 = Array(5) { newEditText(InputType.TYPE_CLASS_NUMBER, 1f) }
    private val textViewArray = Array(5) { newTextView(null, 1f) }

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        orientation = VERTICAL
        dividerDrawable = LinearLayoutDivider(Color.GRAY)
        showDividers = SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END
        radioButton1.apply { text = context.getString(R.string.common_unit_kPa) }
        radioButton2.apply { text = context.getString(R.string.common_unit_mmHg);isChecked = true }
        radioGroup.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked) {
                var flag = true
                for (i in 0..4) {
                    if (editTextArray1[i].text.toString().isNotEmpty() || editTextArray2[i].text.toString().isNotEmpty()) {
                        flag = false
                        break
                    }
                }
                if (flag) initSetValues() else changeUnit()
            }
        }
        initSetValues()
        addView(newLayout(SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_END, newTextView("静态压力示值误差", 1f), radioButton1, radioButton2))
        addView(
            newLayout(
                SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END,
                newTextView("测量点", 1f), newTextView("被检设备压力示值", 1f), newTextView("示值误差", 1f)
            )
        )
        for (i in 0..4) {
            addView(newLayout(SHOW_DIVIDER_BEGINNING or SHOW_DIVIDER_MIDDLE or SHOW_DIVIDER_END, editTextArray1[i], editTextArray2[i], textViewArray[i]))
        }
    }

    override fun calculateAllData() {
        for (i in 0..4) {
            calculateData(editTextArray1[i], editTextArray2[i], textViewArray[i])
        }
    }

    override fun randomAllData() {
        calculateAllData()
    }

    override fun clearAllData() {
        for (i in 0..4) {
            editTextArray1[i].text?.clear()
            editTextArray2[i].text?.clear()
            textViewArray[i].text = ""
        }
    }

    private fun calculateData(editText1: AppCompatEditText, editText2: AppCompatEditText, textView: AppCompatTextView) {
        val value1 = editText1.text.toString()
        val value2 = editText2.text.toString()
        if (value1.isNotEmpty() && value2.isNotEmpty()) {
            val scale = if (radioButton1.isChecked) 2 else 0
            val error1: BigDecimal = BigDecimal(value2)
                .subtract(BigDecimal(value1))
                .setScale(scale, BigDecimal.ROUND_HALF_UP)
            val error2 = error1.toDouble()
            val limit = BigDecimal(value2)
                .multiply(BigDecimal(0.02))
                .max(BigDecimal(0.4))
                .toDouble()
            textView.text = "$error1"
            setTextViewErrorColor(textView, error2 >= -limit && error2 <= limit)
        }
    }

    private fun initSetValues() {
        for ((index, editText) in editTextArray1.withIndex()) {
            if (radioButton1.isChecked) {
                editText.setText("${BigDecimal((index + 1) * 50).mmHg2kPa(2)}")
            } else {
                editText.setText("${(index + 1) * 50}")
            }
        }
    }

    private fun changeUnit() {
        var text: String
        var pressure: BigDecimal
        if (radioButton1.isChecked) {
            for (i in 0..4) {
                editTextArray1[i].inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                text = editTextArray1[i].text.toString()
                if (text.isNotEmpty()) {
                    pressure = BigDecimal(text).mmHg2kPa(2)
                    editTextArray1[i].setText("$pressure")
                }
                editTextArray2[i].inputType = InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL
                text = editTextArray2[i].text.toString()
                if (text.isNotEmpty()) {
                    pressure = BigDecimal(text).mmHg2kPa(2)
                    editTextArray2[i].setText("$pressure")
                }
            }
        } else {
            for (i in 0..4) {
                editTextArray1[i].inputType = InputType.TYPE_CLASS_NUMBER
                text = editTextArray1[i].text.toString()
                if (text.isNotEmpty()) {
                    pressure = BigDecimal(text).kPa2mmHg(0)
                    editTextArray1[i].setText("$pressure")
                }
                editTextArray2[i].inputType = InputType.TYPE_CLASS_NUMBER
                text = editTextArray2[i].text.toString()
                if (text.isNotEmpty()) {
                    pressure = BigDecimal(text).kPa2mmHg(0)
                    editTextArray2[i].setText("$pressure")
                }
            }
        }
        calculateAllData()
    }
}