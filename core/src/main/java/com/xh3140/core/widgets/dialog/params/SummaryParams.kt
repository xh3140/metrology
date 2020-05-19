package com.xh3140.core.widgets.dialog.params

import android.graphics.Typeface
import android.view.Gravity
import androidx.annotation.ColorInt

class SummaryParams {
    /**
     * 副标题文本
     */
    var text: String? = null

    /**
     * 副标题字体大小 sp
     */
    var textSize: Float = 14F

    /**
     * 副标题字体颜色 argb
     */
    @ColorInt
    var textColor: Int = 0x9F000000.toInt()

    /**
     * 副标题字体样式
     */
    var textStyle: Int = Typeface.NORMAL

    /**
     * 副标题位置
     */
    var gravity: Int = Gravity.CENTER

    /**
     * 副标题高度 dp
     */
    var height: Int = 0

    /**
     * 副标题内边距 dp [left, top, right, bottom]
     */
    var padding: IntArray? = intArrayOf(11, 0, 11, 11)

    /**
     * 副标题背景颜色 argb
     */
    @ColorInt
    var backgroundColor: Int = 0x00000000
}