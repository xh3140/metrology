package com.xh3140.core.widgets.button

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatButton
import com.xh3140.core.R
import com.xh3140.core.extensions.dp2px
import com.xh3140.core.utils.DrawableUtil

/**
 * Copyright (C), 2020-2020
 * FileName: NiceButton.kt
 * ClassName: NiceButton(主题按钮类)
 * Description: 提供了多种主题的美观按钮，提供了组成按钮组的方法
 * CreateDate: 2020-02-06
 * @author xh3140
 * @version 1.00
 */

class NiceButton : AppCompatButton {
    // 颜色主题
    private var mColorStyle: ColorStyle = ColorStyle.GRAY

    // 是否是波纹背景
    private var mIsRippleDrawable: Boolean = true

    // 是否是内嵌背景
    private var mIsInsetDrawable: Boolean = true

    // 圆角半径，左上、右上、右下、左下 px
    private val mRadius: IntArray = intArrayOf(dp2px(5), dp2px(5), dp2px(5), dp2px(5))

    // 内嵌距离，左、上、右、下 px
    private val mInset: IntArray = intArrayOf(dp2px(3), dp2px(5), dp2px(3), dp2px(5))

    // 在按钮组中的位置
    private var mLocation: Location = Location.NULL

    /**
     * 颜色风格枚举类
     * 可以添加更多好看的主题
     * @property textColor 字体颜色 argb
     * @property defaultColor 默认状态下的背景颜色 argb
     * @property pressedColor 按下状态下的背景颜色 argb
     */
    enum class ColorStyle(val textColor: Int, val defaultColor: Int, val pressedColor: Int) {
        // 红色
        RED(0xFFFFFFFF.toInt(), 0xFFDC3545.toInt(), 0xFFC82333.toInt()),

        // 绿色
        GREEN(0xFFFFFFFF.toInt(), 0xFF28A745.toInt(), 0xFF218838.toInt()),

        // 蓝色
        BLUE(0xFFFFFFFF.toInt(), 0xFF007BFF.toInt(), 0xFF0069A9.toInt()),

        // 蓝绿色
        CYAN(0xFFFFFFFF.toInt(), 0xFF17A2B8.toInt(), 0xFF138496.toInt()),

        // 黄色
        YELLOW(0xFF212529.toInt(), 0xFFFFC107.toInt(), 0xFFE0A800.toInt()),

        // 灰色
        GRAY(0xFFFFFFFF.toInt(), 0xFF6C757D.toInt(), 0xFF5A6268.toInt()),

        // 亮色
        LIGHT(0xFF212529.toInt(), 0xFFF8F9FA.toInt(), 0xFFE2E6EA.toInt()),

        // 暗色
        DARK(0xFFFFFFFF.toInt(), 0xFF343A40.toInt(), 0xFF23272B.toInt());
    }

    /**
     * 在按钮组中的位置枚举类
     * @property factory radius,inset调节因子
     */
    enum class Location(val factory: IntArray) {
        // 空，表示不是按钮组中的按钮
        NULL(intArrayOf(1, 1, 1, 1, 1, 1, 1, 1)),

        // 最左边，表示在水平按钮组的最左边
        LEFT(intArrayOf(1, 0, 0, 1, 1, 1, 0, 1)),

        // 水平之间，表示在水平按钮组的最左和最右按钮之间
        HORIZONTAL_BETWEEN(intArrayOf(0, 0, 0, 0, 0, 1, 0, 1)),

        // 最右边，表示在水平按钮组的最右边
        RIGHT(intArrayOf(0, 1, 1, 0, 0, 1, 1, 1)),

        // 顶部，表示在竖直按钮组的顶部
        TOP(intArrayOf(1, 1, 0, 0, 1, 1, 1, 0)),

        // 竖直之间，表示在竖直按钮组的顶部和底部按钮之间
        VERTICAL_BETWEEN(intArrayOf(0, 0, 0, 0, 1, 0, 1, 0)),

        // 底部，表示在竖直按钮组的底部
        BOTTOM(intArrayOf(0, 0, 1, 1, 1, 0, 1, 1)),
    }

    /**
     * 构造函数
     */
    constructor(context: Context?) : super(context) {
        configView(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        configView(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        configView(context, attrs)
    }

    constructor(context: Context?, text: String, style: ColorStyle) : super(context) {
        this.text = text
        mColorStyle = style
    }

    /**
     * 由XML初始化视图
     */
    private fun configView(context: Context?, attrs: AttributeSet?) {
        if (context != null && attrs != null) {
            val a = context.obtainStyledAttributes(attrs, R.styleable.NiceButton)
            // 设置是否是波纹背景
            val isRipple = a.getBoolean(R.styleable.NiceButton_isRipple, true)
            setIsRippleDrawable(isRipple)
            // 设置是否是内嵌背景
            val isInset = a.getBoolean(R.styleable.NiceButton_isInset, true)
            setIsInsetDrawable(isInset)
            // 设置圆角半径
            val radius = a.getDimensionPixelSize(R.styleable.NiceButton_radius, -1)
            if (radius >= 0) {
                setRadius(radius)
            } else {
                setRadius(
                    a.getDimensionPixelSize(R.styleable.NiceButton_radius_leftTop, mRadius[0]),
                    a.getDimensionPixelSize(R.styleable.NiceButton_radius_rightTop, mRadius[1]),
                    a.getDimensionPixelSize(R.styleable.NiceButton_radius_rightBottom, mRadius[2]),
                    a.getDimensionPixelSize(R.styleable.NiceButton_radius_leftBottom, mRadius[3])
                )
            }
            // 设置内嵌距离
            val inset = a.getDimensionPixelSize(R.styleable.NiceButton_inset, -1)
            if (inset >= 0) {
                setInset(inset)
            } else {
                setInset(
                    a.getDimensionPixelSize(R.styleable.NiceButton_inset_left, mInset[0]),
                    a.getDimensionPixelSize(R.styleable.NiceButton_inset_top, mInset[1]),
                    a.getDimensionPixelSize(R.styleable.NiceButton_inset_right, mInset[2]),
                    a.getDimensionPixelSize(R.styleable.NiceButton_inset_bottom, mInset[3])
                )
            }
            // 设置主题颜色
            val themeIndex = a.getInt(R.styleable.NiceButton_colorStyle, -1)
            if (themeIndex >= 0) {
                setColorStyle(ColorStyle.values()[themeIndex])
            }
            // 设置按钮位置
            val locationIndex = a.getInt(R.styleable.NiceButton_location, -1)
            if (locationIndex >= 0) {
                setLocation(Location.values()[locationIndex])
            }
            a.recycle()
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        // 调节圆角半径
        mRadius[0] *= mLocation.factory[0]
        mRadius[1] *= mLocation.factory[1]
        mRadius[2] *= mLocation.factory[2]
        mRadius[3] *= mLocation.factory[3]
        // 调节内嵌距离
        mInset[0] *= mLocation.factory[4]
        mInset[1] *= mLocation.factory[5]
        mInset[2] *= mLocation.factory[6]
        mInset[3] *= mLocation.factory[7]
        // 设置字体颜色
        setTextColor(mColorStyle.textColor)
        // 设置背景
        background = if (mIsRippleDrawable) {
            DrawableUtil.getRippleDrawable(mColorStyle.defaultColor, mColorStyle.pressedColor, mRadius, if (mIsInsetDrawable) mInset else null)
        } else {
            DrawableUtil.getStateListDrawable(mColorStyle.defaultColor, mColorStyle.pressedColor, mRadius, if (mIsInsetDrawable) mInset else null)
        }
        super.onLayout(changed, left, top, right, bottom)
    }

    /**
     * 设置主题颜色 enum
     */
    fun setColorStyle(style: ColorStyle): NiceButton {
        if (style != mColorStyle) {
            mColorStyle = style
            requestLayout()
        }
        return this
    }

    /**
     * 设置是否是波纹背景
     * true RippleDrawable
     * false StateListDrawable
     */
    fun setIsRippleDrawable(flag: Boolean): NiceButton {
        if (flag != mIsRippleDrawable) {
            mIsRippleDrawable = flag
            requestLayout()
        }
        return this
    }

    /**
     * 设置是否是内嵌背景
     * true InsetDrawable
     * false GradientDrawable
     */
    fun setIsInsetDrawable(flag: Boolean): NiceButton {
        if (flag != mIsInsetDrawable) {
            mIsInsetDrawable = flag
            requestLayout()
        }
        return this
    }

    /**
     * 设置圆角半径，左上、右上、右下、左下 px
     */
    fun setRadius(radius: IntArray): NiceButton {
        for ((i, v) in radius.withIndex()) {
            if (v < 0) radius[i] = 0
        }
        if (!radius.contentEquals(mRadius)) {
            for (index in radius.indices) {
                mRadius[index] = radius[index]
            }
            requestLayout()
        }
        return this
    }

    /**
     * 设置圆角半径 px
     */
    fun setRadius(radius: Int): NiceButton {
        setRadius(intArrayOf(radius, radius, radius, radius))
        return this
    }

    /**
     * 设置圆角半径 px
     */
    fun setRadius(leftTop: Int, rightTop: Int, rightBottom: Int, leftBottom: Int): NiceButton {
        setRadius(intArrayOf(leftTop, rightTop, rightBottom, leftBottom))
        return this
    }

    /**
     * 设置内嵌距离，左、上、右、下 px
     */
    fun setInset(inset: IntArray): NiceButton {
        for ((i, v) in inset.withIndex()) {
            if (v < 0) inset[i] = 0
        }
        if (!inset.contentEquals(mInset)) {
            for (index in inset.indices) {
                mInset[index] = inset[index]
            }
            requestLayout()
        }
        return this
    }

    /**
     * 设置内嵌距离 px
     */
    fun setInset(inset: Int): NiceButton {
        setInset(intArrayOf(inset, inset, inset, inset))
        return this
    }

    /**
     * 设置内嵌距离 px
     */
    fun setInset(left: Int, top: Int, right: Int, bottom: Int): NiceButton {
        setInset(intArrayOf(left, top, right, bottom))
        return this
    }

    /**
     * 设置在按钮组中的位置 enum
     */
    fun setLocation(location: Location): NiceButton {
        if (location != mLocation) {
            mLocation = location
            requestLayout()
        }
        return this
    }


}