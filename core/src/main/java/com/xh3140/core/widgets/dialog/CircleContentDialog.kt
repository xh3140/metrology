package com.xh3140.core.widgets.dialog

import android.content.Context
import android.view.View
import com.xh3140.core.widgets.dialog.params.BuilderParams
import com.xh3140.core.widgets.dialog.view.BodyContentView
import com.xh3140.core.widgets.dialog.view.FooterView
import com.xh3140.core.widgets.dialog.view.HeadView
import com.xh3140.core.widgets.dialog.view.RootView

/**
 * 内容对话框
 */
class CircleContentDialog(params: BuilderParams<CircleContentDialog>) : CircleDialog(params.mDialogParams) {

    private val mParams: BuilderParams<CircleContentDialog> = params

    override fun initRootView(context: Context): View {
        val rootView = object : RootView<CircleContentDialog>(context) {
            override fun initHeadView(context: Context): HeadView? {
                return HeadView(context)
                    .setHeaderParams(mParams.mHeaderParams)
                    .configView()
            }

            override fun initBodyView(context: Context): View? {
                return BodyContentView(context)
                    .setContentParams(mParams.mBodyParams.mContentParams)
                    .configView()
            }

            override fun initFooterView(context: Context, dialog: CircleContentDialog?): FooterView<CircleContentDialog>? {
                return FooterView<CircleContentDialog>(context)
                    .setCircleDialog(dialog)
                    .setFooterParams(mParams.mFooterParams)
                    .configView()
            }
        }
        rootView.setCircleDialog(this).configView()
        return rootView
    }

    class Builder(buttonCount: Int = 1) : CircleDialog.Builder<CircleContentDialog>(buttonCount) {

        override fun create(): CircleContentDialog {
            return CircleContentDialog(mParams)
        }
    }
}
