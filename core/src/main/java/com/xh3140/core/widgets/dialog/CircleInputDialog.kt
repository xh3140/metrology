package com.xh3140.core.widgets.dialog

import android.content.Context
import android.view.View
import com.xh3140.core.widgets.dialog.params.BuilderParams
import com.xh3140.core.widgets.dialog.view.BodyInputView
import com.xh3140.core.widgets.dialog.view.FooterView
import com.xh3140.core.widgets.dialog.view.HeadView
import com.xh3140.core.widgets.dialog.view.RootView

class CircleInputDialog(params: BuilderParams<CircleInputDialog>) : CircleDialog(params.mDialogParams) {

    private val mParams: BuilderParams<CircleInputDialog> = params

    private var mBodyInputView: BodyInputView? = null

    fun getInputText(): String = mBodyInputView?.text.toString()

    override fun initRootView(context: Context): View {
        val rootView = object : RootView<CircleInputDialog>(context) {
            override fun initHeadView(context: Context): HeadView? {
                return HeadView(context)
                    .setHeaderParams(mParams.mHeaderParams)
                    .configView()
            }

            override fun initBodyView(context: Context): View? {
                mBodyInputView = BodyInputView(context)
                    .setInputParams(mParams.mBodyParams.mInputParams)
                    .configView()
                return mBodyInputView
            }

            override fun initFooterView(context: Context, dialog: CircleInputDialog?): FooterView<CircleInputDialog>? {
                return FooterView<CircleInputDialog>(context)
                    .setCircleDialog(dialog)
                    .setFooterParams(mParams.mFooterParams)
                    .configView()
            }
        }
        rootView.setCircleDialog(this).configView()
        return rootView
    }

    class Builder(buttonCount: Int = 1) :
        CircleDialog.Builder<CircleInputDialog>(buttonCount) {

        override fun create(): CircleInputDialog {
            return CircleInputDialog(mParams)
        }
    }
}