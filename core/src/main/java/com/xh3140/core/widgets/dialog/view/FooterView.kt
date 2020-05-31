package com.xh3140.core.widgets.dialog.view

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.RippleDrawable
import android.view.View
import android.widget.LinearLayout
import com.xh3140.core.widgets.dialog.CircleDialog
import com.xh3140.core.widgets.dialog.params.ButtonParams
import com.xh3140.core.widgets.dialog.params.FooterParams

class FooterView<D : CircleDialog>(context: Context) : LinearLayout(context) {

    private var mCircleDialog: D? = null

    private var mFooterParams: FooterParams<D> = FooterParams(0)

    fun isEmpty(): Boolean {
        return childCount == 0
    }

    fun setCircleDialog(dialog: D?): FooterView<D> {
        mCircleDialog = dialog
        return this
    }

    fun setFooterParams(params: FooterParams<D>): FooterView<D> {
        mFooterParams = params
        return this
    }

    fun configView(): FooterView<D> {
        orientation = HORIZONTAL
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        if (childCount > 0) removeAllViews()
        for (index in mFooterParams.mButtonParamsList.indices) {
            addButtonView(index, mFooterParams.mButtonParamsList[index])
        }
        return this
    }


    private fun onButtonClick(button: ButtonView, index: Int) {
        mCircleDialog?.also { dialog ->
            mFooterParams.buttonOnClickListener?.also { listener ->
                listener.onClick(dialog, button, index)
            } ?: also {
                dialog.dismiss()
            }
        }
    }

    private fun addButtonView(index: Int, params: ButtonParams) {
        val buttonView = ButtonView(context)
        buttonView.configButtonView(params)
        buttonView.setOnClickListener { onButtonClick(buttonView, index) }
        if (buttonView.visibility != View.GONE) {
            if (childCount > 0) {
                addDividerView()
            }
            val gradientDrawable = GradientDrawable()
            gradientDrawable.setColor(params.backgroundColor)
            val pressedColor = ColorStateList.valueOf(params.backgroundColorPressed)
            buttonView.background = RippleDrawable(pressedColor, gradientDrawable, null)
            addView(buttonView)
        }
    }

    private fun addDividerView() {
        val divider = View(context)
        divider.layoutParams = LayoutParams(1, LayoutParams.MATCH_PARENT)
        divider.setBackgroundColor(0xFFD7D7DB.toInt())
        addView(divider)
    }
}
