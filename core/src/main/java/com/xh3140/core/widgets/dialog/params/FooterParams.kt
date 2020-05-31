package com.xh3140.core.widgets.dialog.params

import com.xh3140.core.widgets.dialog.CircleDialog
import com.xh3140.core.widgets.dialog.listener.ButtonOnClickListener

class FooterParams<D : CircleDialog>(buttonCount: Int) {
    /**
     * 按钮属性组
     */
    val mButtonParamsList: List<ButtonParams> = List(buttonCount) { ButtonParams() }

    /**
     * 按钮点击监听器
     */
    var buttonOnClickListener: ButtonOnClickListener<D>? = null
}