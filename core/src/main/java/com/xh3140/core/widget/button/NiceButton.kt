package com.xh3140.core.widget.button

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import com.xh3140.core.R
import com.xh3140.core.widget.utils.ColorUtil
import com.xh3140.core.widget.utils.ColorUtil.argb

/**
 * Copyright (C), 2020-2020
 * FileName: NiceButton.kt
 * ClassName: NiceButton(主题按钮类)
 * Description: 提供了多种主题的美观按钮，提供了组成按钮组的方法
 * CreateDate: 2020-02-06
 * @author xh3140
 * @version 1.00
 */

class NiceButton : BaseNiceButton {
    // 在按钮组中的位置
    private var mGroupLocation: GroupLocation = GroupLocation.NULL

    /**
     * 颜色主题枚举类
     * 可以添加更多好看的主题
     * @property textColor 字体颜色 argb
     * @property defaultColor 默认状态下的背景颜色 argb
     * @property pressedColor 按下状态下的背景颜色 argb
     */
    enum class NiceTheme(val textColor: Int, val defaultColor: Int, val pressedColor: Int) {
        RED(ColorUtil argb 0xFFFFFFFF, ColorUtil argb 0xFFDC3545, ColorUtil argb 0xFFC82333),
        GREEN(ColorUtil argb 0xFFFFFFFF, ColorUtil argb 0xFF28A745, ColorUtil argb 0xFF218838),
        BLUE(ColorUtil argb 0xFFFFFFFF, ColorUtil argb 0xFF007BFF, ColorUtil argb 0xFF0069A9),
        CYAN(ColorUtil argb 0xFFFFFFFF, ColorUtil argb 0xFF17A2B8, ColorUtil argb 0xFF138496),
        YELLOW(ColorUtil argb 0xFF212529, ColorUtil argb 0xFFFFC107, ColorUtil argb 0xFFE0A800),
        GRAY(ColorUtil argb 0xFFFFFFFF, ColorUtil argb 0xFF6C757D, ColorUtil argb 0xFF5A6268),
        LIGHT(ColorUtil argb 0xFF212529, ColorUtil argb 0xFFF8F9FA, ColorUtil argb 0xFFE2E6EA),
        DARK(ColorUtil argb 0xFFFFFFFF, ColorUtil argb 0xFF343A40, ColorUtil argb 0xFF23272B)
    }

    /**
     * 在按钮组中的位置枚举类
     * @property factory radius,inset调节因子
     */
    enum class GroupLocation(val factory: IntArray) {
        NULL(intArrayOf(1, 1, 1, 1, 1, 1, 1, 1)),
        LEFT(intArrayOf(1, 0, 0, 1, 1, 1, 0, 1)),
        HORIZONTAL_BETWEEN(intArrayOf(0, 0, 0, 0, 0, 1, 0, 1)),
        RIGHT(intArrayOf(0, 1, 1, 0, 0, 1, 1, 1)),
        TOP(intArrayOf(1, 1, 0, 0, 1, 1, 1, 0)),
        VERTICAL_BETWEEN(intArrayOf(0, 0, 0, 0, 1, 0, 1, 0)),
        BOTTOM(intArrayOf(0, 0, 1, 1, 1, 0, 1, 1)),
    }

    init {
        mTextColor = NiceTheme.GRAY.textColor
        mBackgroundColor = NiceTheme.GRAY.defaultColor
        mPressedBackgroundColor = NiceTheme.GRAY.pressedColor
    }

    /**
     * 构造函数
     */
    constructor(context: Context?) : super(context) {
        configViewByXML(context, null)
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        configViewByXML(context, attrs)
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        configViewByXML(context, attrs)
    }

    /**
     * 由XML初始化视图
     */
    private fun configViewByXML(context: Context?, attrs: AttributeSet?) {
        if (context != null && attrs != null) {
            val typedArray = context.obtainStyledAttributes(attrs, R.styleable.NiceButton)
            configBackgroundByXML(typedArray)
            typedArray.recycle()
        }
        adjustDimension()
        done()
    }

    /**
     * 拓展方法，获取圆角半径数组
     */
    private fun TypedArray.getRadiusArray() = intArrayOf(
        getDimensionPixelSize(R.styleable.NiceButton_radius_leftTop, mRadius[0]),
        getDimensionPixelSize(R.styleable.NiceButton_radius_rightTop, mRadius[1]),
        getDimensionPixelSize(R.styleable.NiceButton_radius_rightBottom, mRadius[2]),
        getDimensionPixelSize(R.styleable.NiceButton_radius_leftBottom, mRadius[3])
    )

    /**
     * 拓展方法，获取内嵌距离数组
     */
    private fun TypedArray.getInsetArray() = intArrayOf(
        getDimensionPixelSize(R.styleable.NiceButton_inset_left, mInset[0]),
        getDimensionPixelSize(R.styleable.NiceButton_inset_top, mInset[1]),
        getDimensionPixelSize(R.styleable.NiceButton_inset_right, mInset[2]),
        getDimensionPixelSize(R.styleable.NiceButton_inset_bottom, mInset[3])
    )


    /**
     * 由XML设置主题背景
     */
    private fun configBackgroundByXML(typedArray: TypedArray) {
        // 设置主题颜色
        val themeIndex = typedArray.getInt(R.styleable.NiceButton_niceTheme, -1)
        if (themeIndex >= 0) {
            setNiceColorTheme(NiceTheme.values()[themeIndex])
        }
        // 设置按钮位置
        val locationIndex = typedArray.getInt(R.styleable.NiceButton_groupLocation, -1)
        if (locationIndex >= 0) {
            setGroupLocation(GroupLocation.values()[locationIndex])
        }
        // 设置是否是波纹背景
        val isRipple = typedArray.getBoolean(R.styleable.NiceButton_isRipple, true)
        setIsRippleBackground(isRipple)
        // 设置是否是内嵌背景
        val isInset = typedArray.getBoolean(R.styleable.NiceButton_isInset, true)
        setIsInsetBackground(isInset)
        // 设置圆角半径
        val radius = typedArray.getDimensionPixelSize(R.styleable.NiceButton_radius, -1)
        if (radius >= 0) {
            setRadius(radius)
        } else {
            setRadius(typedArray.getRadiusArray())
        }
        // 设置内嵌距离
        val inset = typedArray.getDimensionPixelSize(R.styleable.NiceButton_inset, -1)
        if (inset >= 0) {
            setInset(inset)
        } else {
            setInset(typedArray.getInsetArray())
        }
    }

    /**
     * 设置主题颜色 enum
     */
    fun setNiceColorTheme(theme: NiceTheme) {
        mTextColor = theme.textColor
        mBackgroundColor = theme.defaultColor
        mPressedBackgroundColor = theme.pressedColor
    }

    /**
     * 设置在按钮组中的位置 enum
     */
    fun setGroupLocation(location: GroupLocation) {
        mGroupLocation = location
    }

    fun adjustDimension() {
        // 调节圆角半径
        mRadius[0] *= mGroupLocation.factory[0]
        mRadius[1] *= mGroupLocation.factory[1]
        mRadius[2] *= mGroupLocation.factory[2]
        mRadius[3] *= mGroupLocation.factory[3]
        // 调节内嵌距离
        mInset[0] *= mGroupLocation.factory[4]
        mInset[1] *= mGroupLocation.factory[5]
        mInset[2] *= mGroupLocation.factory[6]
        mInset[3] *= mGroupLocation.factory[7]
    }
}