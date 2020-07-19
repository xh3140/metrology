package com.xh3140.metrology.appliance.jjgn1163y2019.widgets

import android.content.Context
import android.util.AttributeSet
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.widgets.MethodsView

/**
 * @Author : xh3140
 * @Time : 2020/7/19 20:30
 * @File : Tab7MethodsView.kt
 */

/**
 * 血压示值重复性计算公式 LaTex 代码
 */

/*
\begin{align*}
S_{S(D)} & = \frac{R_{S(D)}}{C}  \\
式中 & ： \\
S_{S(D)} & ：收缩压/舒张压示值重复性，kPa(mmHg)； \\
R_{S(D)} & ：收缩压/舒张压5次测量结果中最大值和 \\
&　最小值之差（称为极差），kPa(mmHg)； \\
C & ：极差系数，测量次数为5时，C=2.33。
\end{align*}
 */

class Tab7MethodsView : MethodsView {

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
        addView(createLayout(createTextView("3、血压示值重复性", 15f)))
        val method2 = "　　按示意图的方法连接检定系统，将无创血压模拟仪的心率参数设置为80次/min，设置血压(收缩压/舒张压)检定点:20.0kPa/13.3kPa(150mmHg/100mmHg)。" +
                "血压检定点的设置可根据使用的血压模拟仪尽可能接近推荐值。对上述检定点进行5次测量，按公式计算收缩压和舒张压示值重复性，应符合技术要求。"
        addView(createLayout(createTextView(method2, 13f)))
        addView(createLayout(createTextView("4、血压示值重复性技术要求", 15f)))
        val request = "　　血压示值重复性不大于0.4kPa(3mmHg)。"
        addView(createLayout(createTextView(request, 13f)))
        addView(createLayout(createTextView("5、血压示值重复性计算公式", 15f)))
        addView(createLayout(createSpace(), createImageView(dp2px(350), 3825, 1625, R.drawable.jjg_1163_2019_formula7), createSpace()))
    }
}


