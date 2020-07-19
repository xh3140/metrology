package com.xh3140.metrology.appliance.jjgn1163y2019.widgets

import android.content.Context
import android.util.AttributeSet
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.widgets.MethodsView


/**
 * @Author : xh3140
 * @Time : 2020/7/19 18:57
 * @File : Tab5MethodsView.kt
 */

class Tab5MethodsView : MethodsView {

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
        addView(createLayout(createTextView("3、静态压力测量范围", 15f)))
        val method2 = "　　有无创血压模拟仪选择适当的压力值对监护仪加压，目测检查静态压力测量范围，应符合技术要求。"
        addView(createLayout(createTextView(method2, 13f)))
        addView(createLayout(createTextView("4、技术要求", 15f)))
        val request = "　　测量范围：(0.0～34.7)kPa[(0～260)mmHg]。"
        addView(createLayout(createTextView(request, 13f)))
    }
}