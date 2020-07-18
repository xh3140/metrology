package com.xh3140.metrology.appliance.jjg.jjgn1163y2019.widgets

import android.content.Context
import android.util.AttributeSet
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.widgets.MethodsView

/**
 * @Author : xh3140
 * @Time : 2020/7/18 23:48
 * @File : Tab3MethodsView.kt
 */

/**
 * 电压测量误差计算公式 LaTex 代码
 */

/*
\begin{align*}
\delta_{f} & = \frac{H_{x}-H_{10}}{H_{10}} \times 100 \% \\
式中 & ： \\
\delta_{f} & ：幅频特性相对误差，\%； \\
H_{x} & ：频率为非10Hz时，偏离H_{10}最大者 \\
&　所对应的波形幅度值，mm； \\
H_{10} & ：频率为10Hz时，监护仪屏幕显示的波 \\
&　形幅度值，mm。
\end{align*}
 */

class Tab3MethodsView : MethodsView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        addView(createLayout(createTextView("1、幅频特性", 15f)))
        val method = "　　将监护仪设置为监护模式，增益设置为10mm/mV，使心电模拟仪输出频率为10.0Hz，电压幅值为1.0mV的正弦波信号到监护仪，测量监护仪屏幕显示的波形幅度H10。\n" +
                "　　保持心电模拟仪输出的正弦波信号电压幅值不变，仅改变频率进行测量。" +
                "在(1～25)Hz频率范围内，选取不少于5个测量点进行测量，包含幅频特性的频率下限(1Hz)和上限(25Hz)，并保证测量点的频率发布较均匀。" +
                "测量监护仪显示的波形幅度，取偏离H10最大者为Hx，按公式计算幅频特性相对误差。"
        addView(createLayout(createTextView(method, 13f)))
        addView(createLayout(createTextView("2、技术要求", 15f)))
        val request = "　　在监护模式下，以10Hz正弦波为参考值，在(1～25)Hz频率范围内，幅度变化应在+5%～-30%。"
        addView(createLayout(createTextView(request, 13f)))
        addView(createLayout(createTextView("3、计算公式", 15f)))
        val aspectRatioHeight = (350.0 / (3914.0 / 2126.0)).toInt()
        addView(createLayout(createSpace(), createImageView(dp2px(350), dp2px(aspectRatioHeight), R.drawable.jjg_1163_2019_formula4), createSpace()))
    }
}