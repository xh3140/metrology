package com.xh3140.core.widgets.dialog

import android.content.Context
import android.view.View
import com.xh3140.core.widgets.dialog.params.BuilderParams
import com.xh3140.core.widgets.dialog.view.BodyListView
import com.xh3140.core.widgets.dialog.view.FooterView
import com.xh3140.core.widgets.dialog.view.HeadView
import com.xh3140.core.widgets.dialog.view.RootView


/**
 * 列表选择对话框
 */

class CircleListDialog(params: BuilderParams<CircleListDialog>) : CircleDialog(params.mDialogParams) {

    private val mParams: BuilderParams<CircleListDialog> = params

    private var mBodyView: BodyListView? = null

    fun getCheckedIndex(): Int = mBodyView?.getCheckedIndex() ?: -1

    fun getCheckedIndexList(): List<Int> = mBodyView?.getCheckedIndexList() ?: ArrayList()

    override fun initRootView(context: Context): View {
        val rootView = object : RootView<CircleListDialog>(context) {
            override fun initHeadView(context: Context): HeadView? {
                return HeadView(context)
                    .setHeaderParams(mParams.mHeaderParams)
                    .configView()
            }

            override fun initBodyView(context: Context): View? {
                mBodyView = BodyListView(context)
                    .setListItemParams(mParams.mBodyParams.mListParams)
                    .configView()
                return mBodyView
            }

            override fun initFooterView(context: Context, dialog: CircleListDialog?): FooterView<CircleListDialog>? {
                return FooterView<CircleListDialog>(context)
                    .setCircleDialog(dialog)
                    .setFooterParams(mParams.mFooterParams)
                    .configView()
            }
        }
        rootView.setCircleDialog(this).configView()
        return rootView
    }

    class Builder(buttonCount: Int = 1) : CircleDialog.Builder<CircleListDialog>(buttonCount) {

        override fun create(): CircleListDialog {
            return CircleListDialog(mParams)
        }
    }
}