package com.xh3140.core.widgets.dialog.params

import com.xh3140.core.widgets.dialog.CircleDialog

class BuilderParams<D : CircleDialog>(buttonCount: Int) {
    /**
     * 对话框属性
     */
    val mDialogParams: DialogParams = DialogParams()

    /**
     * 首部视图属性
     */
    val mHeaderParams: HeaderParams = HeaderParams()

    /**
     * 体部视图属性
     */
    val mBodyParams: BodyParams = BodyParams()

    /**
     * 底部视图属性
     */
    val mFooterParams: FooterParams<D> = FooterParams(buttonCount)
}
