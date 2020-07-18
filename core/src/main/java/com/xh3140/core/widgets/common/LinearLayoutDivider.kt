package com.xh3140.core.widgets.common

import android.graphics.drawable.GradientDrawable

/**
 * @Author : xh3140
 * @Time : 2020/7/18 17:28
 * @File : LinearLayoutDivider.kt
 */

class LinearLayoutDivider(color: Int) : GradientDrawable() {
    init {
        shape = RECTANGLE
        setColor(color)
        setSize(1, 1)
    }
}