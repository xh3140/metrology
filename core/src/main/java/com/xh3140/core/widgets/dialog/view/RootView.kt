package com.xh3140.core.widgets.dialog.view

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.xh3140.core.widgets.dialog.CircleDialog


abstract class RootView<D : CircleDialog>(context: Context) : LinearLayout(context) {

    private var mCircleDialog: D? = null

    private val mHeadView: HeadView? by lazy { initHeadView(context) }

    private val mBodyView: View? by lazy { initBodyView(context) }

    private val mFooterView: FooterView<D>? by lazy { initFooterView(context, mCircleDialog) }

    abstract fun initHeadView(context: Context): HeadView?

    abstract fun initBodyView(context: Context): View?

    abstract fun initFooterView(context: Context, dialog: D?): FooterView<D>?

    fun setCircleDialog(dialog: D?): RootView<D> {
        mCircleDialog = dialog
        return this
    }

    fun configView(): RootView<D> {
        orientation = VERTICAL
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        mHeadView?.also { headView ->
            if (!headView.isEmpty()) {
                addView(headView)
            }
        }
        mBodyView?.also { bodyView ->
            if (childCount > 0) {
                addDividerView()
            }
            addView(bodyView)
        }
        mFooterView?.also { footerView ->
            if (!footerView.isEmpty()) {
                if (childCount > 0) {
                    addDividerView()
                }
                addView(footerView)
            }
        }
        return this
    }

    private fun addDividerView() {
        val divider = View(context)
        divider.layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, 1)
        divider.setBackgroundColor(0xFFD7D7DB.toInt())
        addView(divider)
    }
}

