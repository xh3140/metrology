package com.xh3140.metrology.appliance.jjgn1163y2019.widgets

import android.content.Context
import android.util.AttributeSet
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.widgets.MethodsView


/**
 * @Author : xh3140
 * @Time : 2020/7/20 0:15
 * @File : Tab10MethodsView.kt
 */

/**
 * 脉率示值误差计算公式 LaTex 代码
 */

/*
\begin{align*}
\Delta b_{i} & = b_{i} - b_{0} \\
式中 & ： \\
\Delta b_{i} & ：脉率示值误差，次/min； \\
b_{i} & ：监护仪测量的脉率示值，次/min； \\
b_{0} & ：脉搏血氧饱和度模拟仪输出脉率值，次/min。
\end{align*}
 */


class Tab10MethodsView : MethodsView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        addView(createLayout(createTextView("1、脉搏血氧饱和度", 15f)))
        val method1 = "　　通常情况下，任何一款脉搏血氧传感器探头对应有一条脉搏血氧参数的经验定标曲线，即R曲线。" +
                "因此，在检测监护仪的脉搏血氧探头时，需要首先从脉搏血氧饱和度模拟仪中预存的R曲线数据库里选择相对应的R曲线。" +
                "如被检设备血氧探头在脉搏血氧饱和度模拟仪中没有相对应的预存R曲线，可随机选取一条R曲线并进行测量。"
        addView(createLayout(createTextView(method1, 13f)))
        addView(createLayout(createTextView("2、脉率示值误差", 15f)))
        val method2 = "　　脉搏血氧饱和度模拟仪设定脉搏血氧饱和度值为95%，在(30～200)次/min的测量范围内，" +
                "测量点较均匀且不少于3个，按公式计算脉率示值误差，每个测量点的脉率示值误差应符合技术要求。"
        addView(createLayout(createTextView(method2, 13f)))
        addView(createLayout(createTextView("3、脉率示值误差技术要求", 15f)))
        val request = "　　在(30～200)次/min测量范围内，最大允许误差为±(示值的5%+1)次/min。"
        addView(createLayout(createTextView(request, 13f)))
        addView(createLayout(createTextView("4、脉率示值误差计算公式", 15f)))
        addView(createLayout(createSpace(), createImageView(dp2px(350), 3833, 1197, R.drawable.jjg_1163_2019_formula10), createSpace()))
    }
}