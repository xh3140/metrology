package com.xh3140.metrology.appliance.jjgn1163y2019.widgets

import android.content.Context
import android.util.AttributeSet
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.widgets.MethodsView

/**
 * @Author : xh3140
 * @Time : 2020/7/19 20:30
 * @File : Tab8MethodsView.kt
 */

/**
 * 气密性（压力泄漏率）计算公式 LaTex 代码
 */

/*
\begin{align*}
\eta_{P} & = \frac{P_{b}-P_{a}}{t}  \\
式中 & ： \\
\eta_{P} & ：压力泄漏率，kPa/min(mmHg/min)； \\
P_{b} & ：切断气源1min后（视为压力稳定） \\
&　监护仪的压力示值，kPa(mmHg)； \\
P_{a} & ：压力稳定5min（切断气源6min）后 \\
&　监护仪的压力示值，kPa(mmHg)。 \\
t & ：两次记录压力的时间间隔，t=5min。
\end{align*}
 */

class Tab8MethodsView : MethodsView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        addView(createLayout(createTextView("1、无创血压", 15f)))
        val method1 = "　　将监护仪的袖带卷扎在一个圆柱体上，圆柱体直径为(70～102)mm，其松紧程度以能刚好插人一指为宜。" +
                "用医用橡胶管和三通把监护仪、袖带及无创血压模拟仪连接起来组成检定系统。"
        addView(createLayout(createTextView(method1, 13f)))
        addView(createLayout(createTextView("2、无创血压检定系统示意图", 15f)))
        addView(createLayout(createSpace(), createImageView(dp2px(350), 1658, 577, R.drawable.jjg_1163_2019_image1), createSpace()))
        addView(createLayout(createTextView("3、气密性", 15f)))
        val method2 = "　　通常在检定气密性时,被测监护仪设备应处于“维修或校准”模式下，此时监护仪血压模块的排气阀应处于闭合状态。" +
                "将监护仪加压至测量上限的50%～100%之间的任意值，切断气源并稳压1min，从第2min开始记录监护仪压力示值，" +
                "保持5min，然后再次记录监护仪压力示值，将前后两个压力示值之差除以5min得到气密性值，其测量结果应符合技术要求。"
        addView(createLayout(createTextView(method2, 13f)))
        addView(createLayout(createTextView("4、气密性技术要求", 15f)))
        val request = "　　压力泄漏率不大于0.8kPa/min(或6mmHg/min)。"
        addView(createLayout(createTextView(request, 13f)))
        addView(createLayout(createTextView("5、气密性（压力泄漏率）计算公式", 15f)))
        addView(createLayout(createSpace(), createImageView(dp2px(350), 3800, 2228, R.drawable.jjg_1163_2019_formula8), createSpace()))
    }
}
