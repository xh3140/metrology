package com.xh3140.metrology.appliance.jjgn1163y2019.widgets

import android.content.Context
import android.util.AttributeSet
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.widgets.MethodsView


/**
 * @Author : xh3140
 * @Time : 2020/7/19 20:30
 * @File : Tab9MethodsView.kt
 */

/**
 * 脉搏血氧饱和度示值重复性计算公式 LaTex 代码
 */

/*
\begin{align*}
\Delta S & = \sqrt{\frac{\sum_{i=1}^{n}\left(S_{i}-\bar{S}\right)^{2}}{n-1}}  \\
式中 & ： \\
\Delta S & ：脉搏血氧饱和度示值重复性，\%； \\
S_{i} & ：单个测量点第i次脉搏血氧饱和度测量值，\%； \\
\bar{S} & ：单个测量点的多次测量平均值，\%； \\
n & ：测量次数。
\end{align*}
 */
class Tab9MethodsView : MethodsView {

    constructor(context: Context) : super(context)
    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr)

    init {
        addView(createLayout(createTextView("1、脉搏血氧饱和度", 15f)))
        val method1 = "　　通常情况下，任何一款脉搏血氧传感器探头对应有一条脉搏血氧参数的经验定标曲线，即R曲线。" +
                "因此，在检测监护仪的脉搏血氧探头时，需要首先从脉搏血氧饱和度模拟仪中预存的R曲线数据库里选择相对应的R曲线。" +
                "如被检设备血氧探头在脉搏血氧饱和度模拟仪中没有相对应的预存R曲线，可随机选取一条R曲线并进行测量。"
        addView(createLayout(createTextView(method1, 13f)))
        addView(createLayout(createTextView("2、脉搏血氧饱和度示值重复性", 15f)))
        val method2 = "　　脉搏血氧饱和度模拟仪设定脉率为75次/min，设定脉博血氧饱和度测量点分别为70%、90%和100%。" +
                "上述每点至少进行6次测量，按公式计算脉搏血氧饱和度示值重复性，应符合技术要求。"
        addView(createLayout(createTextView(method2, 13f)))
        addView(createLayout(createTextView("3、脉搏血氧饱和度示值重复性技术要求", 15f)))
        val request = "　　在70%～84%测量范围内，示值重复性不大于3%；在85%～100%测量范围内，示值重复性不大于2%。"
        addView(createLayout(createTextView(request, 13f)))
        addView(createLayout(createTextView("4、脉搏血氧饱和度示值重复性计算公式", 15f)))
        addView(createLayout(createSpace(), createImageView(dp2px(350), 3822, 1761, R.drawable.jjg_1163_2019_formula9), createSpace()))
    }
}