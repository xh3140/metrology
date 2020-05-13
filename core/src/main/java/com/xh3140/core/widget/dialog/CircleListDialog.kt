package com.xh3140.core.widget.dialog

import android.content.Context
import android.view.View
import com.xh3140.core.widget.dialog.params.BuilderParams
import com.xh3140.core.widget.dialog.view.BodyListView
import com.xh3140.core.widget.dialog.view.FooterView
import com.xh3140.core.widget.dialog.view.HeadView
import com.xh3140.core.widget.dialog.view.RootView


/**
 * 列表选择对话框
 */

class CircleListDialog(params: BuilderParams) : CircleDialog(params) {

    private var mBodyView: BodyListView? = null

    fun getCheckedIndex(): Int = mBodyView?.getCheckedIndex() ?: -1

    fun getCheckedIndexList(): List<Int> = mBodyView?.getCheckedIndexList() ?: ArrayList()

    override fun initRootView(context: Context, dialog: CircleDialog?): RootView {
        val rootView = object : RootView(context) {
            override fun initHeadView(context: Context): HeadView? {
                return HeadView(context)
                    .setHeaderParams(mParams.headerParams)
                    .configView()
            }

            override fun initBodyView(context: Context): View? {
                mBodyView = BodyListView(context)
                    .setListItemParams(mParams.bodyParams.listParams)
                    .configView()
                return mBodyView
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

    class Builder(buttonCount: Int = 1) : CircleDialog.Builder<CircleListDialog>(buttonCount) {

        override fun create(): CircleListDialog {
            return CircleListDialog(mParams)
        }
    }
}