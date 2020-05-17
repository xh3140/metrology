package com.xh3140.core.widget.dialog.params

import com.xh3140.core.widget.dialog.listener.ButtonOnClickListener

class FooterParams(buttonCount: Int) {
    /**
     * 按钮属性组
     */
    val buttonParamsList: List<ButtonParams> = List(buttonCount) { ButtonParams() }

    /**
     * 按钮点击监听器
     */
    var buttonOnClickListener: ButtonOnClickListener? = null
}