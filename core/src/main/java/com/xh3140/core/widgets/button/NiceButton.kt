package com.xh3140.core.widgets.button

import android.content.Context
import android.content.res.TypedArray
import android.util.AttributeSet
import com.xh3140.core.R

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
    enum class NiceTheme(textColor: Long, defaultColor: Long, pressedColor: Long) {
        // 红色
        RED(0xFFFFFFFF, 0xFFDC3545, 0xFFC82333),

        // 绿色
        GREEN(0xFFFFFFFF, 0xFF28A745, 0xFF218838),

        // 蓝色
        BLUE(0xFFFFFFFF, 0xFF007BFF, 0xFF0069A9),

        // 蓝绿色
        CYAN(0xFFFFFFFF, 0xFF17A2B8, 0xFF138496),

        // 黄色
        YELLOW(0xFF212529, 0xFFFFC107, 0xFFE0A800),

        // 灰色
        GRAY(0xFFFFFFFF, 0xFF6C757D, 0xFF5A6268),

        // 亮色
        LIGHT(0xFF212529, 0xFFF8F9FA, 0xFFE2E6EA),

        // 暗色
        DARK(0xFFFFFFFF, 0xFF343A40, 0xFF23272B);

        val textColor: Int = textColor.toInt()

        val defaultColor: Int = defaultColor.toInt()

        val pressedColor: Int = pressedColor.toInt()
    }

    /**
     * 在按钮组中的位置枚举类
     * @property factory radius,inset调节因子
     */
    enum class GroupLocation(val factory: IntArray) {
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

    init {
        mTextColor = NiceTheme.GRAY.textColor
        mBackgroundColor = NiceTheme.GRAY.defaultColor
        mPressedBackgroundColor = NiceTheme.GRAY.pressedColor
    }

    /**
     * 构造函数
     */
    constructor(context: Context) : super(context) {
        configViewByXML(context, null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        configViewByXML(context, attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int)
            : super(context, attrs, defStyleAttr) {
        configViewByXML(context, attrs)
    }

    /**
     * 由XML初始化视图
     */
    private fun configViewByXML(context: Context, attrs: AttributeSet?) {
        if (attrs != null) {
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