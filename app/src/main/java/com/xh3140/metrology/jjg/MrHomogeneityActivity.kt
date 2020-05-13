package com.xh3140.metrology.jjg

import android.view.View
import com.xh3140.core.widget.dialog.CircleContentDialog
import com.xh3140.metrology.R
import com.xh3140.metrology.base.BaseActivity
import kotlinx.android.synthetic.main.activity_mr_homogeneity.*
import java.math.BigDecimal
import java.util.*

class MrHomogeneityActivity : BaseActivity() {
    override fun getLayoutResID(): Int = R.layout.activity_mr_homogeneity

    override fun initData() {
        title = "MR图像均匀性"
        setActionBarBackEnabled(true)
        val builder = StringBuilder()
        builder.append("[center]$\\[\\Delta = \\frac{1}{N}\\sum_{i = 1}^{N}\\left | S_i - S \\right |\\]$[/center]")
        builder.append("\r\n")
        builder.append("[center]$\\[U_{\\sum } = \\left [ 1 - \\frac{\\Delta }{S} \\right ] \\times{100\\%}\\]$[/center]")
        builder.append("\r\n")
        builder.append("$$\\[\\Delta :感兴趣区域中信号强度值的绝对误差均值；\\]$$")
        builder.append("\r\n")
        builder.append("$$\\[N:感兴趣区域数量；\\]$$")
        builder.append("\r\n")
        builder.append("$$\\[U_{\\sum }:图像均匀性，\\%；\\]$$")
        builder.append("\r\n")
        builder.append("$$\\[S_i:第i次测量感兴趣区域的信号强度；\\]$$")
        builder.append("\r\n")
        builder.append("$$\\[S:所有感兴趣区域内像素信号强度的平均值。\\]$$")
        builder.append("\r\n")
        flexibleRichTextView.setText(builder.toString())
    }

    override fun initListener() {
        buttonCalculate.setOnClickListener(View.OnClickListener {
            val texts = arrayOfNulls<String>(9)
            texts[0] = editText1x1.text.toString()
            texts[1] = editText1x2.text.toString()
            texts[2] = editText1x3.text.toString()
            texts[3] = editText2x1.text.toString()
            texts[4] = editText2x2.text.toString()
            texts[5] = editText2x3.text.toString()
            texts[6] = editText3x1.text.toString()
            texts[7] = editText3x2.text.toString()
            texts[8] = editText3x3.text.toString()
            val datas =
                arrayOfNulls<BigDecimal>(9)
            for (i in texts.indices) {
                if (texts[i]!!.isEmpty()) {
                    toast("请输入完整的数据！")
                    return@OnClickListener
                }
                try {
                    datas[i] = BigDecimal(texts[i])
                } catch (e: Exception) {
                    e.printStackTrace()
                    toast("请输入正确的数据格式！")
                    return@OnClickListener
                }
            }
            val n = BigDecimal.valueOf(datas.size.toLong())
            var sum = BigDecimal.ZERO
            for (big in datas) {
                sum = sum.add(big)
            }
            val avg =
                sum.divide(n, 16, BigDecimal.ROUND_HALF_UP)
            var temp = BigDecimal.ZERO
            for (big in datas) {
                temp = temp.add(big!!.subtract(avg).abs())
            }
            val md =
                temp.divide(n, 16, BigDecimal.ROUND_HALF_UP)
            val rmd =
                md.divide(avg, 16, BigDecimal.ROUND_HALF_UP)
            val homogeneity = BigDecimal.ONE.subtract(rmd)
                .multiply(BigDecimal.valueOf(100))
            val homogeneity2: Float =
                homogeneity.setScale(6, BigDecimal.ROUND_HALF_UP).toFloat()
            val text =
                String.format(Locale.US, "%.4f%%", homogeneity2)
            textViewValue.text = text
        })
        buttonClear.setOnClickListener {
            val builder = CircleContentDialog.Builder(2)
                .setTitleText("清除数据")
                .setContentText("请问是否清除数据？")
                .setButtonText(0, "取消")
                .setButtonText(1, "确定")
                .setButtonOnClickListener { dialog, _, i ->
                    if (i == 1) {
                        editText1x1.setText("")
                        editText1x2.setText("")
                        editText1x3.setText("")
                        editText2x1.setText("")
                        editText2x2.setText("")
                        editText2x3.setText("")
                        editText3x1.setText("")
                        editText3x2.setText("")
                        editText3x3.setText("")
                    }
                    dialog.dismiss()
                }
            builder.create().show(supportFragmentManager, null)
        }
    }
}