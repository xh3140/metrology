package com.xh3140.core.widgets.dialog.params

import android.graphics.Typeface
import android.view.Gravity
import androidx.annotation.ColorInt

class ContentParams {
    /**
     * 文本
     */
    var text: String? = null

    /**
     * 字体大小 sp
     */
    var textSize: Float = 14F

    /**
     * 字体颜色 argb
     */
    @ColorInt
    var textColor: Int = 0xFF8F8F8F.toInt()

    /**
     * 字体样式
     */
    var textStyle: Int = Typeface.NORMAL

    /**
     * 位置
     */
    var gravity: Int = Gravity.CENTER

    /**
     * 高度 dp
     */
    var height: Int = 105

    /**
     * 内边距 dp [left, top, right, bottom]
     */
    var padding: IntArray? = intArrayOf(11, 11, 11, 11)

    /**
     * 背景颜色 argb
     */
    @ColorInt
    var backgroundColor: Int = 0x00000000
}