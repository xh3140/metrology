package com.xh3140.core.widgets.dialog.view

import android.content.Context
import android.view.Gravity
import android.view.View
import android.widget.LinearLayout
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import com.xh3140.core.extensions.dp2px
import com.xh3140.core.widgets.dialog.params.HeaderParams
import com.xh3140.core.widgets.dialog.params.SummaryParams
import com.xh3140.core.widgets.dialog.params.TitleParams


class HeadView(context: Context) : LinearLayout(context) {

    private var mHeaderParams: HeaderParams = HeaderParams()

    private val mIconView: AppCompatImageView by lazy { AppCompatImageView(context) }

    private val mTitleView: AppCompatTextView by lazy { AppCompatTextView((context)) }

    private val mSummaryView: AppCompatTextView by lazy { AppCompatTextView(context) }

    private val mLinearLayout: LinearLayout by lazy { LinearLayout(context) }

    fun isEmpty(): Boolean {
        return mIconView.visibility == View.GONE
                && mTitleView.visibility == View.GONE
                && mSummaryView.visibility == View.GONE
    }

    fun setHeaderParams(params: HeaderParams): HeadView {
        mHeaderParams = params
        return this
    }

    fun configView(): HeadView {
        orientation = VERTICAL
        layoutParams = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        configLayout(mLinearLayout)
        configIconView(mHeaderParams.icon)
        configTitleView(mHeaderParams.mTitleParams)
        configSummaryView(mHeaderParams.mSummaryParams)
        return this
    }

    private fun configLayout(layout: LinearLayout) {
        layout.orientation = HORIZONTAL
        layout.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.CENTER_HORIZONTAL
        }
        layout.setPadding(50, 0, 50, 0)
        addView(layout)
    }

    private fun configIconView(icon: Int) {
        mIconView.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.CENTER_VERTICAL
        }
        if (icon == 0) {
            mIconView.visibility = View.GONE
        } else {
            mIconView.setImageResource(icon)
            mIconView.setPadding(dp2px(10), dp2px(10), dp2px(10), dp2px(10))
            mIconView.visibility = View.VISIBLE
        }
        mLinearLayout.addView(mIconView)
    }

    private fun configTitleView(params: TitleParams) {
        mTitleView.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.CENTER_VERTICAL
        }
        mTitleView.id = android.R.id.title
        if (params.text == null) {
            mTitleView.visibility = View.GONE
        } else {
            mTitleView.gravity = params.gravity
            mTitleView.text = params.text
            mTitleView.textSize = params.textSize
            mTitleView.setTextColor(params.textColor)
            mTitleView.setBackgroundColor(params.backgroundColor)
            if (params.height != 0) {
                mTitleView.height = dp2px(params.height)
            }
            params.padding?.also { padding ->
                mTitleView.setPadding(dp2px(padding[0]), dp2px(padding[1]), dp2px(padding[2]), dp2px(padding[3]))
            }
            mTitleView.setTypeface(mTitleView.typeface, params.textStyle)
            mTitleView.visibility = View.VISIBLE
        }
        mLinearLayout.addView(mTitleView)
    }

    private fun configSummaryView(params: SummaryParams) {
        mSummaryView.layoutParams = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT).apply {
            gravity = Gravity.CENTER_HORIZONTAL
        }
        if (params.text == null) {
            mSummaryView.visibility = View.GONE
        } else {
            mSummaryView.gravity = params.gravity
            mSummaryView.text = params.text
            mSummaryView.textSize = params.textSize
            mSummaryView.setTextColor(params.textColor)
            mSummaryView.setBackgroundColor(params.backgroundColor)
            if (params.height != 0) {
                mSummaryView.height = dp2px(params.height)
            }
            params.padding?.also { padding ->
                mSummaryView.setPadding(dp2px(padding[0]), dp2px(padding[1]), dp2px(padding[2]), dp2px(padding[3]))
            }
            mSummaryView.setTypeface(mSummaryView.typeface, params.textStyle)
            mSummaryView.visibility = View.VISIBLE
        }
        addView(mSummaryView)
    }
}
