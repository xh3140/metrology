package com.xh3140.metrology.appliance.jjgn1163y2019.widgets

import android.content.Context
import android.util.AttributeSet
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.widgets.MethodsView


/**
 * @Author : xh3140
 * @Time : 2020/7/20 0:15
 * @File : Tab12MethodsView.kt
 */
/**
 * 呼吸率示值误差计算公式 LaTex 代码
 */

/*
\begin{align*}
\Delta R & = \overline{R} - R_{0} \\
式中 & ： \\
\Delta R & ：呼吸率示值误差，次/min； \\
\overline{R} & ：呼吸率每点3次测量的平均值，次/min； \\
R_{0} & ：呼吸率标准值，次/min。
\end{align*}
 */

class Tab12MethodsView : MethodsView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        addView(createLayout(createTextView("1、呼末二氧化碳参数检测示意图", 15f)))
        addView(createLayout(createSpace(), createImageView(dp2px(350), 1452, 639, R.drawable.jjg_1163_2019_image2), createSpace()))
        addView(createLayout(createTextView("2、呼吸率示值误差", 15f)))
        val method1 = "　　 采用CO2浓度为5.0%体积百分比的标准气体，在规定的呼吸率测量范围内设定测量点，设置至少3个测量点，测量点的分布较均匀(建议设置呼吸率测量点为10次/min、20次/min、60次/min)。" +
                "每个测量点重复进行3次测量，取3次测量结果的算术平均值，按公式计算呼吸率示值误差，应符合技术要求。"
        addView(createLayout(createTextView(method1, 13f)))
        addView(createLayout(createTextView("3、呼吸率示值误差技术要求", 15f)))
        val request = "　　在(10～60)次/min测量范围内,最大允许误差为±2次/min。"
        addView(createLayout(createTextView(request, 13f)))
        addView(createLayout(createTextView("4、呼吸率示值误差计算公式", 15f)))
        addView(createLayout(createSpace(), createImageView(dp2px(350), 3809, 1374, R.drawable.jjg_1163_2019_formula12), createSpace()))
    }
}