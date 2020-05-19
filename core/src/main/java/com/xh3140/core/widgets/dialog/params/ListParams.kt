package com.xh3140.core.widgets.dialog.params

import androidx.annotation.ColorInt

class ListParams {
    /**
     * 项目列表
     */
    var items: List<String> = ArrayList()

    /**
     * 是否是多选列表
     */
    var isMultiChoice: Boolean = false

    /**
     * 单选列表默认的选取索引
     */
    var checkedIndex: Int = -1

    /**
     * 多选选列表默认的选取索引组
     */
    var checkedIndexList: List<Int> = ArrayList()

    /**
     * 是否有分割线
     */
    var isDividerList: Boolean = true

    /**
     * 列表项目文本字体大小 sp
     */
    var textSize: Float = 16F

    /**
     * 列表项目文本字体颜色 argb
     */
    @ColorInt
    var textColor: Int = 0xFF8F8F8F.toInt()

    /**
     * 列表项目内边距 dp [left, top, right, bottom]
     */
    var padding: IntArray? = intArrayOf(11, 11, 11, 11)
}