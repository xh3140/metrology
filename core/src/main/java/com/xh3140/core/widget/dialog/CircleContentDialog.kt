package com.xh3140.core.widget.dialog

import android.content.Context
import android.view.View
import com.xh3140.core.widget.dialog.params.BuilderParams
import com.xh3140.core.widget.dialog.view.BodyContentView
import com.xh3140.core.widget.dialog.view.FooterView
import com.xh3140.core.widget.dialog.view.HeadView
import com.xh3140.core.widget.dialog.view.RootView

/**
 * 内容对话框
 */
class CircleContentDialog(params: BuilderParams) : CircleDialog(params) {

    override fun initRootView(context: Context, dialog: CircleDialog?): RootView {
        val rootView = object : RootView(context) {
            override fun initHeadView(context: Context): HeadView? {
                return HeadView(context)
                    .setHeaderParams(mParams.headerParams)
                    .configView()
            }

            override fun initBodyView(context: Context): View? {
                return BodyContentView(context)
                    .setContentParams(mParams.bodyParams.contentParams)
                    .configView()
            }

            override fun initFooterView(context: Context, dialog: CircleDialog?): FooterView? {
                return FooterView(context)
                    .setCircleDialog(dialog)
                    .setFooterParams(mParams.footerParams)
                    .configView()
            }
        }
        rootView.setCircleDialog(dialog).configView()
        return rootView
    }

    class Builder(buttonCount: Int = 1) : CircleDialog.Builder<CircleContentDialog>(buttonCount) {

        override fun create(): CircleContentDialog {
            return CircleContentDialog(mParams)
        }
    }
}
