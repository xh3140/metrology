package com.xh3140.metrology.appliance.jjgn1163y2019.widgets

import android.content.Context
import android.util.AttributeSet
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.widgets.MethodsView


/**
 * @Author : xh3140
 * @Time : 2020/7/19 20:29
 * @File : Tab6MethodsView.kt
 */

/**
 * 静态压力示值误差计算公式 LaTex 代码
 */

/*
\begin{align*}
\Delta P_{i} & = P_{i} - P_{0} \\
式中 & ： \\
\Delta P_{i} & ：静态压力示值误差，kPa(mmHg)； \\
P_{i} & ：监护仪静态压力示值，kPa(mmHg)； \\
P_{0} & ：无创血压模拟仪输出压力值，kPa(mmHg)。
\end{align*}
 */


class Tab6MethodsView : MethodsView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        addView(createLayout(createTextView("1、无创血压", 15f)))
        val method1 = "　　将监护仪的袖带卷扎在一个圆柱体上，圆柱体直径为(70～102)mm，其松紧程度以能刚好插人一指为宜。" +
                "用医用橡胶管和三通把监护仪、袖带及无创血压模拟仪连接起来组成检定系统。"
        addView(createLayout(createTextView(method1, 13f)))
        addView(createLayout(createTextView("2、无创血压检定系统示意图", 15f)))
        val aspectRatioHeight1 = (350.0 / (1658.0 / 577.0)).toInt()
        addView(createLayout(createSpace(), createImageView(dp2px(350), dp2px(aspectRatioHeight1), R.drawable.jjg_1163_2019_image1), createSpace()))
        addView(createLayout(createTextView("3、静态压力示值误差", 15f)))
        val method2 = "　　按示意图的方法连接检定系统，在规定的静态压力范围内测量点不得少于5个(不含零点)，并均匀分布在全量程上。" +
                "按公式计算静态压力示值误差，每个测量点的静态压力示值误差应符合技术要求。"
        addView(createLayout(createTextView(method2, 13f)))
        addView(createLayout(createTextView("4、静态压力示值误差技术要求", 15f)))
        val request = "　　最大允许误差：±0.4kPa(±3mmHg)或者±2%读数(两者取其大)。"
        addView(createLayout(createTextView(request, 13f)))
        addView(createLayout(createTextView("5、静态压力示值误差计算公式", 15f)))
        val aspectRatioHeight2 = (350.0 / (1658.0 / 577.0)).toInt()
        addView(createLayout(createSpace(), createImageView(dp2px(350), dp2px(aspectRatioHeight2), R.drawable.jjg_1163_2019_formula6), createSpace()))
    }
}