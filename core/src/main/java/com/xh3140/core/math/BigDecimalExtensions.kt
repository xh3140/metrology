package com.xh3140.core.math

import java.math.BigDecimal
import java.math.MathContext
import java.math.RoundingMode

/**
 * 开方运算 牛顿迭代法（Newton's method）
 */
fun BigDecimal.sqrt(): BigDecimal {
    if (compareTo(BigDecimal.ZERO) == 0) {
        return BigDecimal.ZERO
    }
    var temp = this
    val two = BigDecimal(2)
    val mc = MathContext(100, RoundingMode.HALF_UP)
    for (i in 0 until mc.precision) {
        temp = temp.add(divide(temp, mc)).divide(two, mc)
    }
    return temp
}

/**
 * 转换为Double
 */
fun BigDecimal.toDouble(scale: Int, roundingMode: Int = BigDecimal.ROUND_HALF_UP) =
    setScale(scale, roundingMode).toDouble()
