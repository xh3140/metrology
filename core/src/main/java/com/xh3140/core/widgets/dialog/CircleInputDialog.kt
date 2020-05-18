package com.xh3140.core.widgets.dialog

import android.content.Context
import android.view.View
import com.xh3140.core.widgets.dialog.params.BuilderParams
import com.xh3140.core.widgets.dialog.view.BodyInputView
import com.xh3140.core.widgets.dialog.view.FooterView
import com.xh3140.core.widgets.dialog.view.HeadView
import com.xh3140.core.widgets.dialog.view.RootView

class CircleInputDialog(params: BuilderParams) : CircleDialog(params) {
    private var mBodyInputView: BodyInputView? = null

    fun getInputText(): String = mBodyInputView?.text.toString()

    override fun initRootView(context: Context, dialog: CircleDialog?): RootView {
        val rootView = object : RootView(context) {
            override fun initHeadView(context: Context): HeadView? {
                return HeadView(context)
                    .setHeaderParams(mParams.headerParams)
                    .configView()
            }

            override fun initBodyView(context: Context): View? {
                mBodyInputView = BodyInputView(context)
                    .setInputParams(mParams.bodyParams.inputParams)
                    .configView()
                return mBodyInputView
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

    class Builder(buttonCount: Int = 1) :
        CircleDialog.Builder<CircleInputDialog>(buttonCount) {

        override fun create(): CircleInputDialog {
            return CircleInputDialog(mParams)
        }
    }
}