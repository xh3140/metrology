package com.xh3140.core.extensions

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics
import android.util.Size
import android.view.View
import androidx.annotation.DimenRes
import androidx.fragment.app.Fragment
import org.jetbrains.anko.px2sp

/**
 * Context Dimensions Extensions
 */
fun Context.px2dp(px: Int): Float = px.toFloat() / resources.displayMetrics.density
fun Context.px2sp(px: Int): Float = px.toFloat() / resources.displayMetrics.scaledDensity

fun Context.dp2px(dp: Int): Int = (dp * resources.displayMetrics.density).toInt()
fun Context.dp2px(dp: Float): Int = (dp * resources.displayMetrics.density).toInt()

fun Context.sp2px(sp: Int): Int = (sp * resources.displayMetrics.scaledDensity).toInt()
fun Context.sp2px(sp: Float): Int = (sp * resources.displayMetrics.scaledDensity).toInt()

fun Context.dimen(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)

/**
 * 获取屏幕大小 px
 */
fun getScreenSize(activity: Activity): Size {
    val metrics = DisplayMetrics()
    activity.windowManager.defaultDisplay.getMetrics(metrics)
    return Size(metrics.widthPixels, metrics.heightPixels)
}


/**
 * Fragment Dimensions Extensions
 * androidx.fragment.app.Fragment
 */
fun Fragment.px2dp(px: Int): Float = px.toFloat() / resources.displayMetrics.density
fun Fragment.px2sp(px: Int): Float = px.toFloat() / resources.displayMetrics.scaledDensity

fun Fragment.dp2px(dp: Int): Int = (dp * resources.displayMetrics.density).toInt()
fun Fragment.dp2px(dp: Float): Int = (dp * resources.displayMetrics.density).toInt()

fun Fragment.sp2px(sp: Int): Int = (sp * resources.displayMetrics.scaledDensity).toInt()
fun Fragment.sp2px(sp: Float): Int = (sp * resources.displayMetrics.scaledDensity).toInt()

fun Fragment.dimen(@DimenRes resource: Int): Int = resources.getDimensionPixelSize(resource)

/**
 * View Dimensions Extensions
 */
fun View.px2dp(px: Int): Float = context.px2dp(px)
fun View.px2sp(px: Int): Float = context.px2sp(px)

fun View.dp2px(dp: Int): Int = context.dp2px(dp)
fun View.dp2px(dp: Float): Int = context.dp2px(dp)

fun View.sp2px(sp: Int): Int = context.sp2px(sp)
fun View.sp2px(sp: Float): Int = context.sp2px(sp)

fun View.dimen(@DimenRes resource: Int): Int = context.dimen(resource)

/**
 * Activity Dimensions Extensions By Screen
 */

private val DISPLAY_METRICS = DisplayMetrics()

fun Activity.getScreenWidth(): Int {
    windowManager.defaultDisplay.getMetrics(DISPLAY_METRICS)
    return DISPLAY_METRICS.widthPixels
}

fun Activity.getScreenHeight(): Int {
    windowManager.defaultDisplay.getMetrics(DISPLAY_METRICS)
    return DISPLAY_METRICS.heightPixels
}

/**
 * Fragment Dimensions Extensions By Screen
 * androidx.fragment.app.Fragment
 */

fun Fragment.getScreenWidth(): Int = requireActivity().getScreenWidth()

fun Fragment.getScreenHeight(): Int = requireActivity().getScreenHeight()