package com.xh3140.core.widget.chart

import android.graphics.Paint
import com.xh3140.core.widget.utils.PixelUtil
import java.math.BigDecimal

/**
 * 刻度列表，用于控制坐标系的坐标轴刻度
 * @property start 开始值
 * @property interval 间隔大小
 * @property count 有效数量
 * @property index 初始索引
 * @property scale 小数点后保留位数
 * @property initial 初始索引对应的值（频繁使用，优化）
 * @property end 末尾值（频繁使用，优化）
 */
abstract class ScaleList {
    abstract val start: Float
    abstract val interval: Float
    abstract val count: Int
    abstract val index: Int
    abstract val scale: Int
    val initial: Float by lazy { getValue(index) }
    val end: Float by lazy { getValue(count - 1) }

    abstract fun getValue(index: Int): Float

    abstract fun getText(index: Int): String

    fun getMaxWidth(paint: Paint): Int {
        return maxOf(
            PixelUtil.getTextBoundsWidth(paint, getText(0)),
            PixelUtil.getTextBoundsWidth(paint, getText(count - 1))
        )
    }

    fun getMaxHeight(paint: Paint): Int {
        return maxOf(
            PixelUtil.getTextBoundsHeight(paint, getText(0)),
            PixelUtil.getTextBoundsHeight(paint, getText(count - 1))
        )
    }

    /**
     * 标准刻度列表，默认情况使用
     */
    object Standard : ScaleList() {
        override val start: Float = 0f
        override val interval: Float = 1f
        override val count: Int = 100
        override val index: Int = 0
        override val scale: Int = 0
        override fun getValue(index: Int): Float = index.toFloat()
        override fun getText(index: Int): String = index.toString()
    }

    /**
     * 通用刻度列表，理论上可以用来构造任何刻度
     */
    class Common(private val list: List<String>) : ScaleList() {
        override val start: Float = 1f
        override val interval: Float = 1f
        override val count: Int = list.size
        override val index: Int = 0
        override val scale: Int = 0
        override fun getValue(index: Int): Float = index + 1f

        override fun getText(index: Int): String = list[index]
    }

    /**
     * 线性刻度列表，相邻两个值之间为等差关系，可看做等差数列
     */
    class Linear(
        override val start: Float,
        override val interval: Float,
        override val count: Int,
        override val index: Int,
        override val scale: Int
    ) : ScaleList() {
        private val _start: BigDecimal =
            start.toBigDecimal().setScale(scale, BigDecimal.ROUND_HALF_UP)
        private val _interval: BigDecimal =
            interval.toBigDecimal().setScale(scale, BigDecimal.ROUND_HALF_UP)

        override fun getValue(index: Int): Float =
            index.toBigDecimal().multiply(_interval).add(_start)
                .setScale(scale, BigDecimal.ROUND_HALF_UP).toFloat()

        override fun getText(index: Int): String =
            String.format("%.${scale}f", getValue(index))
    }
}