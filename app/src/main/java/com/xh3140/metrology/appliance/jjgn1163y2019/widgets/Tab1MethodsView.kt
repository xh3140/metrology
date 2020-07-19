package com.xh3140.metrology.appliance.jjgn1163y2019.widgets

import android.content.Context
import android.util.AttributeSet
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.widgets.MethodsView


/**
 * @Author : xh3140
 * @Time : 2020/7/18 18:28
 * @File : Tab1MethodsView.kt
 */

/**
 * 电压测量误差计算公式 LaTex 代码
 */

/*
\begin{align*}
\delta_{V} & = \frac{V_{i}-V_{0}}{V_{0}} \times 100 \% \\
式中 & ： \\
\delta_{V} & ：电压测量误差，\%； \\
V_{i} & ：监护仪测量电压值，mV； \\
V_{0} & ：心电模拟仪输出电压值，mV。
\end{align*}
 */

class Tab1MethodsView : MethodsView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        addView(createLayout(createTextView("1、心电", 15f)))
        val method0 = "　　电压测量误差、幅频特性一般在监护仪的Ⅱ导联进行测量。" +
                "对于具有记录输出的监护仪，可在监护仪显示屏幕上对波形进行测量，也可在记录纸上对输出的波形进行测量。"
        addView(createLayout(createTextView(method0, 13f)))
        addView(createLayout(createTextView("2、步进增转换式", 15f)))
        val method1 = "　　将监护仪增益设置为10mm/mV，使心电模拟仪输出电压为1.0mV、频率为2.0Hz的方波信号到监护仪。" +
                "测量监护仪屏幕上的方波信号幅值长度，根据所设置的增益，计算监护仪所测量到的方波信号电压值。" +
                "按公式计算电压测量误差，应符合技术要求。\n" +
                "　　按上述方法分别检定监护仪的5mm/mV、20mm/mV增益挡（心电模拟仪对应输出电压在5mm/mV挡时为2.0mV、在20mm/mV挡时为0.5mV）。" +
                "按公式计算电压测量误差，应符合技术要求。"
        addView(createLayout(createTextView(method1, 13f)))
        addView(createLayout(createTextView("3、连续可调增益转换式", 15f)))
        val method2 = "　　用监护仪的内部电压校准源（如定标电压或标尺）将增益校准设置为20mm/mV。" +
                "心电模拟仪分别输出电压为1.0mV、0.5mV，频率为2.0Hz的方波信号到监护仪。" +
                "测量监护仪屏幕上的方波信号幅值长度，根据所设置的增益，计算监护仪所测量到的方波信号电压值。" +
                "按公式计算电压测量误差，应符合技术要求。"
        addView(createLayout(createTextView(method2, 13f)))
        addView(createLayout(createTextView("4、电压测量误差技术要求", 15f)))
        val request = "　　电压测量最大允许误差为±10%。"
        addView(createLayout(createTextView(request, 13f)))
        addView(createLayout(createTextView("5、电压测量误差计算公式", 15f)))
        addView(createLayout(createSpace(), createImageView(dp2px(350), 3786, 1833, R.drawable.jjg_1163_2019_formula1), createSpace()))
    }
}