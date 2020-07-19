package com.xh3140.metrology.appliance.jjgn1163y2019.widgets

import android.content.Context
import android.util.AttributeSet
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.widgets.MethodsView


/**
 * @Author : xh3140
 * @Time : 2020/7/18 20:32
 * @File : Tab2MethodsView.kt
 */

/**
 * 扫描速度计算公式 LaTex 代码
 */

/*
\begin{align*}
v_{s} & = \frac{L}{t} \\
式中 & ： \\
v_{s} & ：扫描速度，mm/s； \\
L & ：所测量个数的完整周期波形所对应 \\
&　的显示宽度，mm； \\
t & ：所测量个数的完整周期波形所对应 \\
&　的时间，s。
\end{align*}
 */

/**
 * 扫描速度误差计算公式 LaTex 代码
 */

/*
\begin{align*}
\delta_{v_{s}} & = \frac{v_{0}-v_{s}}{v_{s}} \times 100 \% \\
式中 & ： \\
\delta_{v_{s}} & ：扫描速度误差，\%； \\
v_{0} & ：被检设备扫描速度设置值，mm/s； \\
v_{s} & ：扫描速度测得值（计算所得），mm/s。
\end{align*}
 */


class Tab2MethodsView : MethodsView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        addView(createLayout(createTextView("1、扫描速度误差", 15f)))
        val method = "　　监护仪扫描速度设置为25mm/s，模拟仪输出频率为2Hz，幅度为1mV的标准方波信号到监护仪。" +
                "有冻结功能的被检设备启用冻结功能，在显示波形中，测量4个完整周期波形所对应的显示宽度。" +
                "按公式计算扫描速度和扫描速度误差，应符合技术要求。\n" +
                "　　具有50mm/s扫描速度的监护仪，应按上述方法，测量2个完整周期波形所对应的显示宽度，检定扫描速度，应符合技术要求。"
        addView(createLayout(createTextView(method, 13f)))
        addView(createLayout(createTextView("2、扫描速度误差技术要求", 15f)))
        val request = "　　扫描速度最大允许误差为±10%。"
        addView(createLayout(createTextView(request, 13f)))
        addView(createLayout(createTextView("3、扫描速度计算公式", 15f)))
        val aspectRatioHeight1 = (350.0 / (3750.0 / 2223.0)).toInt()
        addView(createLayout(createSpace(), createImageView(dp2px(350), dp2px(aspectRatioHeight1), R.drawable.jjg_1163_2019_formula2), createSpace()))
        addView(createLayout(createTextView("4、扫描速度误差计算公式", 15f)))
        val aspectRatioHeight2 = (350.0 / (3815.0 / 1473.0)).toInt()
        addView(createLayout(createSpace(), createImageView(dp2px(350), dp2px(aspectRatioHeight2), R.drawable.jjg_1163_2019_formula3), createSpace()))
    }
}