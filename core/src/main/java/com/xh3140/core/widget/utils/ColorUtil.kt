package com.xh3140.core.widget.utils

import androidx.annotation.ColorInt

/**
 * 颜色工具
 */
object ColorUtil {
    @ColorInt
    infix fun ColorUtil.argb(color: Long): Int = color.toInt()
}