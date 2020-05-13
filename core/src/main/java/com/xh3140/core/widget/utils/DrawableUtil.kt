package com.xh3140.core.widget.utils

import android.content.res.ColorStateList
import android.graphics.drawable.GradientDrawable
import android.graphics.drawable.InsetDrawable
import android.graphics.drawable.RippleDrawable
import android.graphics.drawable.StateListDrawable
import androidx.annotation.ColorInt

object DrawableUtil {
    /**
     * 获取其圆角半径
     */
    fun getCornerRadii(radius: IntArray): FloatArray =
        floatArrayOf(
            radius[0].toFloat(), radius[0].toFloat(),
            radius[1].toFloat(), radius[1].toFloat(),
            radius[2].toFloat(), radius[2].toFloat(),
            radius[3].toFloat(), radius[3].toFloat()
        )

    /**
     * 获取其圆角半径
     * @param leftTop 左上角 px
     * @param rightTop 右上角 px
     * @param rightBottom 右下角 px
     * @param leftBottom 左下角 px
     */
    fun getCornerRadii(leftTop: Int, rightTop: Int, rightBottom: Int, leftBottom: Int): FloatArray =
        getCornerRadii(intArrayOf(leftTop, rightTop, rightBottom, leftBottom))

    /**
     * 获取纯色背景
     *
     * @param backgroundColor 背景颜色
     * @param radius          圆角半径，左上、右上、右下、左下 px
     */
    fun getGradientDrawable(
        @ColorInt backgroundColor: Int,
        radius: IntArray?
    ): GradientDrawable? {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(backgroundColor)
        if (radius != null) {
            gradientDrawable.cornerRadii = getCornerRadii(radius)
        }
        return gradientDrawable
    }

    /**
     * 获取波纹背景
     *
     * @param defaultColor 默认状态下的背景颜色 argb
     * @param pressedColor 按下状态下的波纹颜色 argb
     * @param radius       圆角半径，左上、右上、右下、左下 px
     */
    fun getRippleDrawable(
        @ColorInt defaultColor: Int,
        @ColorInt pressedColor: Int,
        radius: IntArray?
    ): RippleDrawable? {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(defaultColor)
        if (radius != null) {
            gradientDrawable.cornerRadii = getCornerRadii(radius)
        }
        return RippleDrawable(ColorStateList.valueOf(pressedColor), gradientDrawable, null)
    }

    /**
     * 获取波纹背景
     * @param defaultColor 默认状态下的背景颜色 argb
     * @param pressedColor 按下状态下的波纹颜色 argb
     * @param radius 圆角半径，左上、右上、右下、左下 px
     * @param inset 内嵌距离，左、上、右、下 px
     */
    fun getRippleDrawable(
        @ColorInt defaultColor: Int,
        @ColorInt pressedColor: Int,
        radius: IntArray = intArrayOf(0, 0, 0, 0),
        inset: IntArray? = null
    ): RippleDrawable {
        val cornerRadius = getCornerRadii(radius)
        val gradientDrawable = GradientDrawable()
        gradientDrawable.setColor(defaultColor)
        gradientDrawable.cornerRadii = cornerRadius
        val contentDrawable = if (inset == null) {
            gradientDrawable
        } else {
            InsetDrawable(gradientDrawable, inset[0], inset[1], inset[2], inset[3])
        }
        return RippleDrawable(ColorStateList.valueOf(pressedColor), contentDrawable, null)
    }

    /**
     * 获取状态背景
     * @param defaultColor 默认状态下的背景颜色 argb
     * @param pressedColor 按下状态下的背景颜色 argb
     * @param radius 圆角半径，左上、右上、右下、左下 px
     * @param inset 内嵌距离，左、上、右、下 px
     */
    fun getStateListDrawable(
        @ColorInt defaultColor: Int,
        @ColorInt pressedColor: Int,
        radius: IntArray = intArrayOf(0, 0, 0, 0),
        inset: IntArray? = null
    ): StateListDrawable {
        val cornerRadius = getCornerRadii(radius)
        val stateListDrawable = StateListDrawable()
        val defaultGradient = GradientDrawable()
        defaultGradient.setColor(defaultColor)
        defaultGradient.cornerRadii = cornerRadius
        val defaultDrawable = if (inset == null) {
            defaultGradient
        } else {
            InsetDrawable(defaultGradient, inset[0], inset[1], inset[2], inset[3])
        }
        stateListDrawable.addState(intArrayOf(-android.R.attr.state_pressed), defaultDrawable)
        val pressedGradient = GradientDrawable()
        pressedGradient.setColor(pressedColor)
        pressedGradient.cornerRadii = cornerRadius
        val pressedDrawable = if (inset == null) {
            pressedGradient
        } else {
            InsetDrawable(pressedGradient, inset[0], inset[1], inset[2], inset[3])
        }
        stateListDrawable.addState(intArrayOf(android.R.attr.state_pressed), pressedDrawable)
        return stateListDrawable
    }
}