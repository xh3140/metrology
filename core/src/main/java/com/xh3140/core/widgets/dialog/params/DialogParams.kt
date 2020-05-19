package com.xh3140.core.widgets.dialog.params

import android.view.Gravity
import androidx.annotation.AnimRes
import androidx.annotation.ColorInt
import androidx.annotation.StyleRes

class DialogParams {
    /**
     * 对话框位置
     */
    var gravity: Int = Gravity.NO_GRAVITY

    /**
     * 是否触摸外部关闭
     */
    var canceledOnTouchOutside: Boolean = true

    /**
     * 返回键是否关闭
     */
    var cancelable: Boolean = true

    /**
     * 对话框透明度，范围：0-1；1不透明
     */
    var alpha: Float = 1F

    /**
     * 对话框宽度，范围：0-1；1整屏宽
     */
    var width: Float = 0.9F

    /**
     * 对话框最大高度
     */
    var maxHeight: Float = 0.8F

    /**
     * 对话框与屏幕边距 px [left, top, right, bottom]
     */
    var padding: IntArray? = null

    /**
     * 对话框弹出动画 StyleRes
     */
    @StyleRes
    var animationStyle: Int = 0

    /**
     * 对话框刷新动画 AnimRes
     */
    @AnimRes
    var refreshAnimation: Int = 0

    /**
     * 对话框背景是否昏暗
     */
    var isDimEnabled: Boolean = true

    /**
     * 对话框的背景色 argb
     */
    @ColorInt
    var backgroundColor: Int = 0xFFF8F8F8.toInt()

    /**
     * 对话框按下状态颜色 argb
     */
    @ColorInt
    var backgroundColorPressed: Int = 0xFFEAEAEA.toInt()

    /**
     * 对话框的圆角半径 dp
     */
    var radius: Int = 7

    /**
     * 对话框 x 坐标偏移 px
     */
    var xOffset: Int = 0

    /**
     * 对话框 y 坐标偏移 px
     */
    var yOffset: Int = 0

    /**
     * 系统UI可视性
     */
    var systemUiVisibility: Int = 0

    /**
     * 延迟弹出 ms
     */
    var delayShow: Int = 0
}
