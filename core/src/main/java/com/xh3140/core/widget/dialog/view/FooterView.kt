package com.xh3140.core.widget.dialog.view

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.widget.AppCompatTextView
import com.xh3140.core.widget.dialog.CircleDialog
import com.xh3140.core.widget.dialog.params.ButtonParams
import com.xh3140.core.widget.dialog.params.FooterParams
import com.xh3140.core.widget.utils.ColorUtil
import com.xh3140.core.widget.utils.ColorUtil.argb
import com.xh3140.core.widget.utils.DrawableUtil
import com.xh3140.core.widget.utils.PixelUtil

class FooterView(context: Context) : LinearLayout(context) {

    private var mCircleDialog: CircleDialog? = null

    private var mFooterParams: FooterParams = FooterParams(0)

    fun isEmpty(): Boolean {
        return childCount == 0
    }

    fun setCircleDialog(dialog: CircleDialog?): FooterView {
        mCircleDialog = dialog
        return this
    }

    fun setFooterParams(params: FooterParams): FooterView {
        mFooterParams = params
        return this
    }

    fun configView(): FooterView {
        orientation = HORIZONTAL
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        if (childCount > 0) removeAllViews()
        for (index in mFooterParams.buttonParamsList.indices) {
            val params = mFooterParams.buttonParamsList[index]
            val buttonView = AppCompatTextView(context)
            configButtonView(buttonView, params)
            buttonView.setOnClickListener { view -> onButtonClick(view, index) }
            if (buttonView.visibility != View.GONE) {
                if (childCount > 0) {
                    addDividerView()
                }
                buttonView.background = DrawableUtil.getRippleDrawable(
                    params.backgroundColor,
                    params.backgroundColorPressed, null
                )
                addView(buttonView)
            }
        }
        return this
    }

    private fun onButtonClick(view: View, index: Int) {
        mCircleDialog?.also { dialog ->
            if (mFooterParams.buttonOnClickListener == null) {
                dialog.dismiss()
            } else {
                mFooterParams.buttonOnClickListener?.onClick(dialog, view, index)
            }
        }
    }

    private fun configButtonView(buttonView: TextView, params: ButtonParams) {
        if (params.text == null) {
            buttonView.visibility = View.GONE
        } else {
            buttonView.layoutParams = LayoutParams(
                LayoutParams.MATCH_PARENT,
                LayoutParams.WRAP_CONTENT, 1f
            )
            buttonView.gravity = Gravity.CENTER
            buttonView.text = params.text
            buttonView.isEnabled = !params.disable
            buttonView.setTextColor(if (!params.disable) params.textColor else params.textColorDisable)
            buttonView.textSize = params.textSize
            if (params.height != 0F) {
                buttonView.height = PixelUtil.dp2px(context, params.height)
            }
            buttonView.setTypeface(buttonView.typeface, params.textStyle)
            buttonView.visibility = View.VISIBLE
        }
    }

    private fun addDividerView() {
        val divider = View(context)
        divider.layoutParams = LayoutParams(1, LayoutParams.MATCH_PARENT)
        divider.setBackgroundColor(ColorUtil argb 0xFFD7D7DB)
        addView(divider)
    }
}
