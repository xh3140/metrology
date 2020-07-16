package com.xh3140.metrology.appliance.jjg.jjgn1163y2019

import android.graphics.Color
import android.widget.TextView
import com.xh3140.core.widgets.dialog.CircleContentDialog
import com.xh3140.metrology.R
import com.xh3140.metrology.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_jjg_1163_2019.*
import java.math.BigDecimal


/**
 * @Author : xh3140
 * @Time : 2020/7/16 19:00
 * @File : JJGN1163Y2019Activity.kt
 */

class JJGN1163Y2019Activity : BaseActivity() {
    override fun getLayoutResID(): Int = R.layout.activity_jjg_1163_2019

    override fun initListener() {
        // 计算数据
        buttonCalculate.setOnClickListener {
            val s1 = editText1.text.toString()
            if (s1.isNotEmpty()) {
                val e1 = calculateError(10.0, 1.0, BigDecimal(s1))
                textView1.text = "$e1"
                textView1.setErrorTextColor(e1.toDouble())
            }
            val s2 = editText2.text.toString()
            if (s2.isNotEmpty()) {
                val e2 = calculateError(5.0, 2.0, BigDecimal(s2))
                textView2.text = "$e2"
                textView2.setErrorTextColor(e2.toDouble())
            }
            val s3 = editText3.text.toString()
            if (s3.isNotEmpty()) {
                val e3 = calculateError(20.0, 0.5, BigDecimal(s3))
                textView3.text = "$e3"
                textView3.setErrorTextColor(e3.toDouble())
            }
            val s4 = editText4.text.toString()
            if (s4.isNotEmpty()) {
                val e4 = calculateError(20.0, 1.0, BigDecimal(s4))
                textView4.text = "$e4"
                textView4.setErrorTextColor(e4.toDouble())
            }
            val s5 = editText5.text.toString()
            if (s5.isNotEmpty()) {
                val e5 = calculateError(20.0, 0.5, BigDecimal(s5))
                textView5.text = "$e5"
                textView5.setErrorTextColor(e5.toDouble())
            }
        }
        // 随机数据
        buttonRandom.setOnClickListener {
            val v1 = BigDecimal.valueOf(0.1 * (90..110).random()).setScale(1, BigDecimal.ROUND_HALF_UP)
            val e1 = calculateError(10.0, 1.0, v1)
            editText1.setText("$v1")
            textView1.text = "$e1"
            textView1.setErrorTextColor(e1.toDouble())
            val v2 = BigDecimal.valueOf(0.1 * (90..110).random()).setScale(1, BigDecimal.ROUND_HALF_UP)
            val e2 = calculateError(5.0, 2.0, v2)
            editText2.setText("$v2")
            textView2.text = "$e2"
            textView2.setErrorTextColor(e2.toDouble())
            val v3 = BigDecimal.valueOf(0.1 * (90..110).random()).setScale(1, BigDecimal.ROUND_HALF_UP)
            val e3 = calculateError(20.0, 0.5, v3)
            editText3.setText("$v3")
            textView3.text = "$e3"
            textView3.setErrorTextColor(e3.toDouble())
            val v4 = BigDecimal.valueOf(0.1 * (180..220).random()).setScale(1, BigDecimal.ROUND_HALF_UP)
            val e4 = calculateError(20.0, 1.0, v4)
            editText4.setText("$v4")
            textView4.text = "$e4"
            textView4.setErrorTextColor(e4.toDouble())
            val v5 = BigDecimal.valueOf(0.1 * (90..110).random()).setScale(1, BigDecimal.ROUND_HALF_UP)
            val e5 = calculateError(20.0, 0.5, v5)
            editText5.setText("$v5")
            textView5.text = "$e5"
            textView5.setErrorTextColor(e5.toDouble())
        }
        // 清除数据
        buttonClear.setOnClickListener {
            val builder = CircleContentDialog.Builder(2)
                .setTitleText("清除数据")
                .setContentText("请问是否清除数据？")
                .setButtonText(0, "取消")
                .setButtonText(1, "确定")
                .setButtonOnClickListener { dialog, _, i ->
                    if (i == 1) {
                        editText1.text.clear()
                        editText2.text.clear()
                        editText3.text.clear()
                        editText4.text.clear()
                        editText5.text.clear()
                        textView1.text = ""
                        textView2.text = ""
                        textView3.text = ""
                        textView4.text = ""
                        textView5.text = ""
                    }
                    dialog.dismiss()
                }
            builder.create().show(supportFragmentManager, null)
        }

    }


    private fun calculateError(gain: Double, voltage: Double, value: BigDecimal): BigDecimal {
        return value.divide(BigDecimal(gain), 16, BigDecimal.ROUND_HALF_UP)
            .subtract(BigDecimal(voltage))
            .divide(BigDecimal(voltage), 16, BigDecimal.ROUND_HALF_UP)
            .multiply(BigDecimal(100))
            .setScale(1, BigDecimal.ROUND_HALF_UP)
    }


    private fun TextView.setErrorTextColor(error: Double) {
        if (error >= -10.0 && error <= 10.0) {
            setTextColor(Color.parseColor("#8A000000"))
        } else {
            setTextColor(Color.RED)
        }
    }
}