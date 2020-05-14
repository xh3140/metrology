package com.xh3140.metrology.jjg.jjg9612017.fragment

import android.app.AlertDialog

import com.xh3140.metrology.R
import com.xh3140.metrology.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_jjg_961_2017_part2.*

class JJG96120171Part2Fragment : BaseFragment() {

    override fun getLayoutResID(): Int = R.layout.fragment_jjg_961_2017_part2

    override fun initData() {
        val builder = StringBuilder()
        builder.append("$$\\[WL=\\frac{CT_M+CT_W}{2}\\]$$\n\n")
        builder.append("$$\\[WW=(CT_M-CT_W)+5SD_{max}\\]$$\n\n")
        builder.append("$$\\[WL:图像的窗位,HU;\\]$$\n")
        builder.append("$$\\[WW:图像的窗宽,HU;\\]$$\n")
        builder.append("$$\\[CT_W:水的CT值;\\]$$\n")
        builder.append("$$\\[CT_M:低对比物质的CT值;\\]$$\n")
        builder.append("$$\\[SD_{max}:两种物质测量区域中较大的那个标准偏差值。\\]$$")
        flexibleRichTextView.setText(builder.toString())


    }

    override fun initListener() {
        // 计算窗宽与窗位
        buttonCalculate.setOnClickListener {
            val ctm = editTextCTm.text.toString()
            val ctw = editTextCTw.text.toString()
            if (ctm.isEmpty() || ctw.isEmpty()) {
                toast("请完整输入测得的CT值")
            } else {
                try {
                    val ctmValue = ctm.toBigDecimal()
                    val ctwValue = ctw.toBigDecimal()
                    val wlValue = (ctmValue + ctwValue) / 2.toBigDecimal()
                    val wlText = wlValue.setScale(3).toString()
                    textViewWLValue.text = wlText
                    val sdm = editTextSDm.text.toString()
                    val sdw = editTextSDw.text.toString()
                    if (sdm.isEmpty() || sdw.isEmpty()) {
                        toast("请完整输入测得的标准偏差")
                    } else {
                        try {
                            val sdmValue = sdm.toBigDecimal()
                            val sdwValue = sdw.toBigDecimal()
                            val wwValue =
                                (ctmValue - ctwValue) + 5.toBigDecimal() * sdmValue.max(sdwValue)
                            val wwText = wwValue.setScale(3).toString()
                            textViewWWValue.text = wwText
                        } catch (e: Exception) {
                            e.printStackTrace()
                            toast("请输入正确的数据格式")
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    toast("请输入正确的数据格式")
                }
            }
        }
        // 清除数据
        buttonClear.setOnClickListener {
            val ctm = editTextCTm.text.toString()
            val ctw = editTextCTw.text.toString()
            val sdm = editTextSDm.text.toString()
            val sdw = editTextSDw.text.toString()
            if (ctm.isEmpty() && ctw.isEmpty() && sdm.isEmpty() && sdw.isEmpty()) {
                return@setOnClickListener
            }
            val dialogBuilder = AlertDialog.Builder(context)
                .setTitle("清除数据")
                .setMessage("请问是否清除数据？")
                .setNegativeButton("取消", null)
                .setPositiveButton("确定") { _, _ ->
                    editTextCTm.text.clear()
                    editTextCTw.text.clear()
                    editTextSDm.text.clear()
                    editTextSDw.text.clear()
                    textViewWLValue.text = ""
                    textViewWWValue.text = ""
                }
            dialogBuilder.create().show()
        }
    }
}
