package com.xh3140.core.widget.utils

import androidx.annotation.ColorInt

/**
 * 颜色工具
 */
object ColorUtil {
    /**
     * 中缀表达式
     * val color: Int = ColorUtil argb 0xFF080808
     * Kotlin不能像Java那样用16进制Int表示ARGB颜色值，
     * “0xFF080808”这样的值在Kotlin中需要用Long才能表示，
     * 然而很多Java方法的参数是Int型的，这样的不一致造成了不便。
     * Kotlin用16进制Int表达ARGB的格式让人不易理解。
     */
    @ColorInt
    infix fun ColorUtil.argb(color: Long): Int = color.toInt()
}