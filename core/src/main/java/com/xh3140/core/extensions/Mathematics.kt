package com.xh3140.core.extensions

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

/**
 * 大数2，提高效率
 */
private val BIG_TWO: BigDecimal by lazy { BigDecimal(2) }

/**
 * 开方运算 牛顿迭代法（Newton's method）
 * @param precision 精度，迭代次数，默认100次
 * @param roundingMode 舍入模式，默认四舍五入
 */

fun BigDecimal.sqrt(precision: Int = 100, roundingMode: RoundingMode = RoundingMode.HALF_UP): BigDecimal {
    val cmp = compareTo(BigDecimal.ZERO)
    if (cmp < 0) {
        // 负数开根号
        throw ArithmeticException("the square root of a negative number.")
    }
    if (cmp == 0) {
        // 零开根号
        return BigDecimal.ZERO
    }
    var result = this
    val mc = MathContext(precision, roundingMode)
    for (i in 0 until precision) {
        result = result.add(divide(result, mc)).divide(BIG_TWO, mc)
    }
    return result
}

/**
 * 转换为Double
 */
fun BigDecimal.toDouble(scale: Int, roundingMode: Int = BigDecimal.ROUND_HALF_UP) =
    setScale(scale, roundingMode).toDouble()
