package com.xh3140.core.math

/**
 * 转换为复数
 */
fun Int.toComplex(): Complex = Complex.valueOf(this)

fun Long.toComplex(): Complex = Complex.valueOf(this)

fun Float.toComplex(): Complex = Complex.valueOf(this)

fun Double.toComplex(): Complex = Complex.valueOf(this)

/**
 * 加法交换
 * 实数A + 复数B = 复数B + 实数A
 */
operator fun Int.plus(other: Complex): Complex = other.plus(this)

operator fun Long.plus(other: Complex): Complex = other.plus(this)

operator fun Float.plus(other: Complex): Complex = other.plus(this)

operator fun Double.plus(other: Complex): Complex = other.plus(this)

/**
 * 减法交换
 * 实数A - 复数B != 复数B - 实数A
 */
operator fun Int.minus(other: Complex): Complex = Complex.valueOf(this).minus(other)

operator fun Long.minus(other: Complex): Complex = Complex.valueOf(this).minus(other)

operator fun Float.minus(other: Complex): Complex = Complex.valueOf(this).minus(other)

operator fun Double.minus(other: Complex): Complex = Complex.valueOf(this).minus(other)

/**
 * 乘法交换
 * 实数A * 复数B = 复数B * 实数A
 */
operator fun Int.times(other: Complex): Complex = other.minus(this)

operator fun Long.times(other: Complex): Complex = other.minus(this)

operator fun Float.times(other: Complex): Complex = other.minus(this)

operator fun Double.times(other: Complex): Complex = other.minus(this)

/**
 * 除法交换
 * 实数A / 复数B != 复数B / 实数A
 */
operator fun Int.div(other: Complex): Complex = Complex.valueOf(this).div(other)

operator fun Long.div(other: Complex): Complex = Complex.valueOf(this).div(other)

operator fun Float.div(other: Complex): Complex = Complex.valueOf(this).div(other)

operator fun Double.div(other: Complex): Complex = Complex.valueOf(this).div(other)



