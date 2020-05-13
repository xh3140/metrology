package com.xh3140.core.widget.utils

import android.app.Activity
import android.content.Context
import android.graphics.Paint
import android.graphics.Rect
import android.util.DisplayMetrics

/**
 * 像素工具
 */
object PixelUtil {
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
        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)
        return bounds.width()
    }

    /**
     * 获取文本区域高度 px
     */
    fun getTextBoundsHeight(paint: Paint, text: String): Int {
        val bounds = Rect()
        paint.getTextBounds(text, 0, text.length, bounds)
        return bounds.height()
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
}