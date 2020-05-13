package com.xh3140.core.widget.dialog.params

import android.graphics.Typeface
import android.view.Gravity
import androidx.annotation.ColorInt
import com.xh3140.core.widget.utils.ColorUtil
import com.xh3140.core.widget.utils.ColorUtil.argb


class TitleParams {
    /**
     * 标题文本
     */
    var text: String? = null

    /**
     * 标题字体大小 sp
     */
    var textSize: Float = 16F

    /**
     * 标题字体颜色 argb
     */
    @ColorInt
    var textColor: Int = ColorUtil argb 0xFF000000

    /**
     * 标题字体样式
     */
    var textStyle: Int = Typeface.NORMAL

    /**
     * 标题位置
     */
    var gravity: Int = Gravity.CENTER

    /**
     * 标题高度 dp
     */
    var height: Int = 0

    /**
     * 标题内边距 dp [left, top, right, bottom]
     */
    var padding: IntArray? = intArrayOf(11, 11, 11, 11)

    /**
     * 标题背景颜色 argb
     */
    @ColorInt
    var backgroundColor: Int = ColorUtil argb 0x00000000
}