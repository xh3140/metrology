package com.xh3140.core.widgets.dialog.params

import android.graphics.Typeface
import android.text.InputType
import android.view.Gravity
import androidx.annotation.ColorInt

class InputParams {
    /**
     * 输入类型
     */
    var type: Int = InputType.TYPE_NULL

    /**
     * 输入框文本
     */
    var text: String? = null

    /**
     * 输入框字体大小 sp
     */
    var textSize: Float = 15F

    /**
     * 输入框字体颜色 argb
     */
    @ColorInt
    var textColor: Int = 0xFF000000.toInt()

    /**
     * 输入框字体字样式
     */
    var textStyle: Int = Typeface.NORMAL

    /**
     * 输入框文字对齐方式
     */
    var gravity: Int = Gravity.CENTER

    /**
     * 输入框提示语
     */
    var hint: String? = null

    /**
     * 输入框提示语颜色
     */
    var hintColor: Int = 0xFF8F8F8F.toInt()

    /**
     * 单行输入
     */
    var isSingleLine: Boolean = true

    /**
     * 最大行数
     */
    var maxLines: Int = -1

    /**
     * 每行最大显示长度
     */
    var maxEms: Int = 10

    /**
     * 最大输入长度
     */
    var maxLength: Int = -1

    /**
     * 输入框与body视图的距离 dp
     */
    var margins: IntArray? = intArrayOf(16, 32, 16, 32)

    /**
     * 输入框的高度 dp
     */
    var height: Int = 46

    /**
     * 输入框边框线条粗细 dp
     */
    var strokeWidth: Int = 1

    /**
     * 输入框边框线条颜色
     */
    var strokeColor: Int = 0xFF808080.toInt()

    /**
     * 输入框边框圆角半径 dp
     */
    var cornerRadius: Float = 5F

    /**
     * 输入框背景资源文件
     */
    var backgroundResourceId: Int = 0

    /**
     * 输入框背景颜色
     */
    var backgroundColor: Int = 0

    /**
     * 内间距 [left, top, right, bottom] dp
     */
    var padding: IntArray? = intArrayOf(5, 5, 5, 5)
}