package com.xh3140.core.widget.dialog.view

import android.content.Context
import android.view.View
import android.widget.LinearLayout
import com.xh3140.core.widget.dialog.CircleDialog
import com.xh3140.core.widget.utils.ColorUtil
import com.xh3140.core.widget.utils.ColorUtil.argb


abstract class RootView(context: Context) : LinearLayout(context) {

    private var mCircleDialog: CircleDialog? = null

    private val mHeadView: HeadView? by lazy { initHeadView(context) }

    private val mBodyView: View? by lazy { initBodyView(context) }

    private val mFooterView: FooterView? by lazy { initFooterView(context, mCircleDialog) }

    abstract fun initHeadView(context: Context): HeadView?

    abstract fun initBodyView(context: Context): View?

    abstract fun initFooterView(context: Context, dialog: CircleDialog?): FooterView?

    fun setCircleDialog(dialog: CircleDialog?): RootView {
        mCircleDialog = dialog
        return this
    }

    fun configView(): RootView {
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
        divider.setBackgroundColor(ColorUtil argb 0xFFD7D7DB)
        addView(divider)
    }
}

