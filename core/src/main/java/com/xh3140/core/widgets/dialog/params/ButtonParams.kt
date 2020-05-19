package com.xh3140.core.widgets.dialog.params

import android.graphics.Typeface
import androidx.annotation.ColorInt

class ButtonParams {
    /**
     * 按钮文本
     */
    var text: String? = null

    /**
     * 按钮字体大小 sp
     */
    var textSize: Float = 16F

    /**
     * 按钮字体颜色 argb
     */
    @ColorInt
    var textColor: Int = 0xFF007AFF.toInt()

    /**
     * 按钮是否禁用
     */
    var disable: Boolean = false

    /**
     * 按钮禁用状态下的字体颜色 argb
     */
    @ColorInt
    var textColorDisable: Int = 0xFF898D8B.toInt()

    /**
     * 按钮字体样式
     */
    var textStyle: Int = Typeface.NORMAL

    /**
     * 按钮高度 dp
     */
    var height: Float = 46F

    /**
     * 按钮背景颜色 argb
     */
    @ColorInt
    var backgroundColor: Int = 0x00000000

    /**
     * 按钮按下状态背景颜色 argb
     */
    @ColorInt
    var backgroundColorPressed: Int = 0x00000000
}
