package com.xh3140.metrology.appliance.jjgn1163y2019.widgets

import android.content.Context
import android.util.AttributeSet
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.widgets.MethodsView


/**
 * @Author : xh3140
 * @Time : 2020/7/20 0:15
 * @File : Tab11MethodsView.kt
 */

/**
 * 呼末二氧化碳浓度示值误差计算公式 LaTex 代码
 */

/*
\begin{align*}
\Delta D & = \overline{D} - D_{0} \\
式中 & ： \\
\Delta D & ：呼末CO_{2}浓度示值误差，kPa(mmHg)； \\
\overline{D} & ：呼末CO_{2}浓度3次测量的平均值，kPa(mmHg)； \\
D_{0} & ：呼末CO_{2}浓度标准值，kPa(mmHg)， \\
&　D_{0}=\frac{环境大气压(kPa)}{101.325(kPa)}\times 5kP， \\
&　D_{0}=\frac{环境大气压(mmHg)}{760(mmHg)}\times 38mmHg 。
\end{align*}
 */

class Tab11MethodsView : MethodsView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        addView(createLayout(createTextView("1、呼末二氧化碳参数检测示意图", 15f)))
        addView(createLayout(createSpace(), createImageView(dp2px(350), 1452, 639, R.drawable.jjg_1163_2019_image2), createSpace()))
        addView(createLayout(createTextView("2、呼末二氧化碳浓度示值误差", 15f)))
        val method = "　　    按呼末二氧化碳参数检测示意图，采用浓度为5.0%体积百分比CO2标准气体(相当于大气压为760mmHg的条件下分压为5kPa或38mmHg)，搭建好检测系统。" +
                "呼吸率设为20次/min，等监护仪进入正常工作状态之后，连续读取3次呼吸的测量结果，按公式计算呼末二氧化碳浓度示值误差，应符合技术要求。"
        addView(createLayout(createTextView(method, 13f)))
        addView(createLayout(createTextView("3、呼末二氧化碳浓度示值误差技术要求", 15f)))
        val request = "　　最大允许误差：±(示值的8%+0.43)kPa，或±(示值的8%+0.43%)体积百分比，或±(示值的8%+3.2)mmHg。"
        addView(createLayout(createTextView(request, 13f)))
        addView(createLayout(createTextView("4、呼末二氧化碳浓度示值误差计算公式", 15f)))
        addView(createLayout(createSpace(), createImageView(dp2px(350), 3833, 2017, R.drawable.jjg_1163_2019_formula11), createSpace()))
    }
}