package com.xh3140.core.widget.button

import android.content.Context
import android.graphics.Color
import android.util.AttributeSet
import androidx.annotation.ColorInt
import androidx.appcompat.widget.AppCompatButton
import com.xh3140.core.widget.utils.DrawableUtil
import com.xh3140.core.widget.utils.PixelUtil

abstract class BaseNiceButton : AppCompatButton {
    // 文本颜色
    @ColorInt
    protected var mTextColor: Int = Color.BLACK

    // 背景颜色
    @ColorInt
    protected var mBackgroundColor: Int = Color.WHITE

    // 按下状态时的背景颜色
    @ColorInt
    protected var mPressedBackgroundColor: Int = Color.WHITE

    // 是否是波纹背景
    protected var mIsRipple: Boolean = true

    // 是否是内嵌背景
    protected var mIsInset: Boolean = true

    // 圆角半径，左上、右上、右下、左下 px
    protected val mRadius: IntArray = intArrayOf(0, 0, 0, 0)

    // 内嵌距离，左、上、右、下 px
    protected val mInset: IntArray = intArrayOf(0, 0, 0, 0)

    /**
     * 初始化
     */
    init {
        // 设置默认圆角半径
        mRadius[0] = PixelUtil.dp2px(context, 5)
        mRadius[1] = PixelUtil.dp2px(context, 5)
        mRadius[2] = PixelUtil.dp2px(context, 5)
        mRadius[3] = PixelUtil.dp2px(context, 5)
        // 设置默认内嵌距离
        mInset[0] = PixelUtil.dp2px(context, 3)
        mInset[1] = PixelUtil.dp2px(context, 5)
        mInset[2] = PixelUtil.dp2px(context, 3)
        mInset[3] = PixelUtil.dp2px(context, 5)
    }

    /**
     * 构造函数
     */
    constructor(context: Context) : super(context)

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs)

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr)

    /**
     * 设置是否是波纹背景
     * true RippleDrawable
     * false StateListDrawable
     */
    fun setIsRippleBackground(ripple: Boolean = true) {
        mIsRipple = ripple
    }

    /**
     * 设置是否是内嵌背景
     * true InsetDrawable
     * false GradientDrawable
     */
    fun setIsInsetBackground(inset: Boolean = true) {
        mIsInset = inset
    }

    /**
     * 设置圆角半径，左上、右上、右下、左下 px
     */
    fun setRadius(radius: IntArray) {
        mRadius[0] = maxOf(0, radius[0])
        mRadius[1] = maxOf(0, radius[1])
        mRadius[2] = maxOf(0, radius[2])
        mRadius[3] = maxOf(0, radius[3])
    }

    /**
     * 设置圆角半径 px
     */
    fun setRadius(radius: Int) {
        setRadius(intArrayOf(radius, radius, radius, radius))
    }

    /**
     * 设置内嵌距离，左、上、右、下 px
     */
    fun setInset(inset: IntArray) {
        mInset[0] = maxOf(0, inset[0])
        mInset[1] = maxOf(0, inset[1])
        mInset[2] = maxOf(0, inset[2])
        mInset[3] = maxOf(0, inset[3])
    }

    /**
     * 设置内嵌距离 px
     */
    fun setInset(inset: Int) {
        setInset(intArrayOf(inset, inset, inset, inset))
    }

    /**
     * 完成主题设置并更新背景
     */
    fun done() {
        // 设置字体颜色
        setTextColor(mTextColor)
        // 设置背景
        background = if (mIsRipple) {
            DrawableUtil.getRippleDrawable(mBackgroundColor, mPressedBackgroundColor, mRadius, if (mIsInset) mInset else null)
        } else {
            DrawableUtil.getStateListDrawable(mBackgroundColor, mPressedBackgroundColor, mRadius, if (mIsInset) mInset else null)
        }
    }
}