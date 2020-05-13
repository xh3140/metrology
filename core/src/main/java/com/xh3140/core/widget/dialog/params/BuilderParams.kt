package com.xh3140.core.widget.dialog.params


class BuilderParams(buttonCount: Int) {
    /**
     * 对话框属性
     */
    val dialogParams: DialogParams = DialogParams()

    /**
     * 首部视图属性
     */
    val headerParams: HeaderParams = HeaderParams()

    /**
     * 体部视图属性
     */
    val bodyParams: BodyParams = BodyParams()

    /**
     * 底部视图属性
     */
    val footerParams: FooterParams = FooterParams(buttonCount)
}
