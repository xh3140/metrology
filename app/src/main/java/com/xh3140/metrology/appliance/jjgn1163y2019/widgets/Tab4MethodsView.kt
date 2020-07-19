package com.xh3140.metrology.appliance.jjgn1163y2019.widgets

import android.content.Context
import android.util.AttributeSet
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.widgets.MethodsView


/**
 * @Author : xh3140
 * @Time : 2020/7/19 14:17
 * @File : Tab4MethodsView.kt
 */

/**
 * 心率示值误差计算公式 LaTex 代码
 */

/*
\begin{align*}
\Delta B_{i} & = B_{i} - B_{0} \\
式中 & ： \\
\Delta B_{i} & ：心率示值误差，次/min； \\
B_{i} & ：监护仪测量的心率示值，次/min； \\
B_{0} & ：心电模拟仪输出心率值，次/min。
\end{align*}
 */

class Tab4MethodsView : MethodsView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        addView(createLayout(createTextView("1、心率示值误差", 15f)))
        val method = "　　将监护仪增益设置为10mm/mV，心电模拟仪输出信号幅值分别设置为0.5mV、2.0mV。" +
                "在(30～200)次/min范围内设置心电模拟仪输出模拟窦性心律信号(或标准心律信号)的心率，" +
                "首次检定测量间隔应不大于10次/min，后续检定测量点应较均匀且不少于4个。" +
                "用公式计算监护仪心率示值误差，每个测量点的心率示值误差应符合技术要求。"
        addView(createLayout(createTextView(method, 13f)))
        addView(createLayout(createTextView("2、技术要求", 15f)))
        val request = "　　在(30～200)次/min范围内，最大允许误差为±(示值的5%+1)次/min。"
        addView(createLayout(createTextView(request, 13f)))
        addView(createLayout(createTextView("3、计算公式", 15f)))
        val aspectRatioHeight = (350.0 / (3801.0 / 1424.0)).toInt()
        addView(createLayout(createSpace(), createImageView(dp2px(350), dp2px(aspectRatioHeight), R.drawable.jjg_1163_2019_formula5), createSpace()))
    }
}