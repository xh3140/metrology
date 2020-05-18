package com.xh3140.core.utils

import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import android.util.DisplayMetrics
import android.util.Size

/**
 * 像素工具
 */
object PixelUtil {
    /**
     * 测量区域，复用优化
     */
    private val BOUNDS: Rect by lazy { Rect() }

    /**
     * dp转换为px
     */
    fun dp2px(context: Context, dp: Int): Int =
        (dp * context.resources.displayMetrics.density + 0.5f).toInt()

    fun dp2px(context: Context, dp: Float): Int =
        (dp * context.resources.displayMetrics.density + 0.5f).toInt()

    /**
     * sp转换为px
     */
    fun sp2px(context: Context, sp: Int): Int =
        (sp * context.resources.displayMetrics.scaledDensity + 0.5f).toInt()

    fun sp2px(context: Context, sp: Float): Int =
        (sp * context.resources.displayMetrics.scaledDensity + 0.5f).toInt()

    /**
     * px转换为dp
     */
    fun px2dp(context: Context, px: Int): Float =
        (px / context.resources.displayMetrics.density + 0.5f)

    /**
     * px转换为sp
     */
    fun px2sp(context: Context, px: Int): Float =
        (px / context.resources.displayMetrics.scaledDensity + 0.5f)

    /**
     * 获取文本区域宽度 px
     */
    fun getTextBoundsWidth(paint: Paint, text: String): Int {
        paint.getTextBounds(text, 0, text.length, BOUNDS)
        return BOUNDS.width()
    }

    /**
     * 获取文本区域高度 px
     */
    fun getTextBoundsHeight(paint: Paint, text: String): Int {
        paint.getTextBounds(text, 0, text.length, BOUNDS)
        return BOUNDS.height()
    }

    /**
     * 获取文本区域大小 px
     */
    fun getTextBoundsSize(paint: Paint, text: String): Size {
        paint.getTextBounds(text, 0, text.length, BOUNDS)
        return Size(BOUNDS.width(), BOUNDS.height())
    }

    /**
     * 获取屏幕宽度 px
     */
    fun getScreenWidth(activity: Activity): Int {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        return metrics.widthPixels
    }

    /**
     * 获取屏幕高度 px
     */
    fun getScreenHeight(activity: Activity): Int {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        return metrics.heightPixels
    }

    /**
     * 获取屏幕大小 px
     */
    fun getScreenSize(activity: Activity): Size {
        val metrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(metrics)
        return Size(metrics.widthPixels, metrics.heightPixels)
    }
}