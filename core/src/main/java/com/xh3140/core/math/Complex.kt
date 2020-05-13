package com.xh3140.core.math

import kotlin.math.atan2
import kotlin.math.sqrt

class Complex(var real: Double, var imaginary: Double) {

    constructor(real: Int, imaginary: Int) : this(real.toDouble(), imaginary.toDouble())
    constructor(real: Long, imaginary: Long) : this(real.toDouble(), imaginary.toDouble())
    constructor(real: Float, imaginary: Float) : this(real.toDouble(), imaginary.toDouble())

    /**
     * 判断是不是复数
     */
    fun isNaN(): Boolean = real.isNaN() || imaginary.isNaN()

    /**
     * 幅度，长度，模
     */
    fun modulus(): Double = sqrt(real * real + imaginary * imaginary)

    /**
     * 相位
     */
    fun phase(): Double = atan2(imaginary, real)

    /**
     * 共轭复数
     */
    fun conjugate(): Complex = Complex(real, -imaginary)

    /**
     * 加法运算
     */
    operator fun plus(other: Complex): Complex =
        Complex(real + other.real, imaginary + other.imaginary)

    operator fun plus(other: Int): Complex = Complex(real + other, imaginary)

    operator fun plus(other: Long): Complex = Complex(real + other, imaginary)

    operator fun plus(other: Float): Complex = Complex(real + other, imaginary)

    operator fun plus(other: Double): Complex = Complex(real + other, imaginary)

    operator fun inc(): Complex = plus(1)

    operator fun unaryPlus(): Complex = valueOf(this)

    /**
     * 减法运算
     */
    operator fun minus(other: Complex): Complex {
        return Complex(real - other.real, imaginary - other.imaginary)
    }

    operator fun minus(other: Int): Complex = Complex(real - other, imaginary)

    operator fun minus(other: Long): Complex = Complex(real - other, imaginary)

    operator fun minus(other: Float): Complex = Complex(real - other, imaginary)

    operator fun minus(other: Double): Complex = Complex(real - other, imaginary)

    operator fun dec(): Complex = minus(1)

    operator fun unaryMinus(): Complex = valueOf(0).minus(this)


    /**
     * 乘法运算
     * 复数的乘法运算 Z = X * Y 的运算法则：
     * Z.实部 = X.实部 * Y.实部 - X.虚部 * Y.虚部
     * Z.虚部 = X.实部 * Y.虚部 + X.虚部 * Y.实部
     */
    operator fun times(other: Complex): Complex =
        Complex(
            real * other.real - imaginary * other.imaginary,
            real * other.imaginary + imaginary * other.real
        )

    operator fun times(other: Int): Complex =
        Complex(real * other, imaginary * other)

    operator fun times(other: Long): Complex =
        Complex(real * other, imaginary * other)

    operator fun times(other: Float): Complex =
        Complex(real * other, imaginary * other)

    operator fun times(other: Double): Complex =
        Complex(real * other, imaginary * other)

    /**
     * 除法运算
     * 复数的除法运算 Z = X / Y 的运算法则：
     * Z.实部 = (X.实部 * Y.实部 + X.虚部 * Y.虚部) / (Y.实部 * Y.实部 + Y.虚部 * Y.虚部)
     * Z.虚部 = (X.虚部 * Y.实部 - X.实部 * Y.虚部) / (Y.实部 * Y.实部 + Y.虚部 * Y.虚部)
     */
    operator fun div(other: Complex): Complex {
        if (other.real == 0.0 && other.imaginary == 0.0) {
            throw ArithmeticException("除数不能为0")
        }
        val divisor = other.real * other.real + other.imaginary * other.imaginary
        return Complex(
            (real * other.real + imaginary * other.imaginary) / divisor,
            (imaginary * other.real - real * other.imaginary) / divisor
        )
    }

    operator fun div(other: Int): Complex {
        if (other == 0) throw ArithmeticException("除数不能为0")
        return Complex(real / other, imaginary / other)
    }

    operator fun div(other: Long): Complex {
        if (other == 0L) throw ArithmeticException("除数不能为0")
        return Complex(real / other, imaginary / other)
    }

    operator fun div(other: Float): Complex {
        if (other == 0F) throw ArithmeticException("除数不能为0")
        return Complex(real / other, imaginary / other)
    }

    operator fun div(other: Double): Complex {
        if (other == 0.0) throw ArithmeticException("除数不能为0")
        return Complex(real / other, imaginary / other)
    }

    /**
     * 比较运算
     */
    override operator fun equals(other: Any?): Boolean =
        when {
            other === this -> true
            other !is Complex -> false
            else -> real == other.real && imaginary == other.imaginary
        }

    override fun toString(): String {
        return if (imaginary < 0) {
            String.format("[%.3f%.3fi]", real, imaginary)
        } else {
            String.format("[%.3f+%.3fi]", real, imaginary)
        }
    }

    override fun hashCode(): Int = 31 * real.hashCode() + imaginary.hashCode()

    companion object {
        /**
         * 由实数取值
         */
        fun valueOf(other: Complex): Complex = Complex(other.real, other.imaginary)

        fun valueOf(real: Int): Complex = Complex(real.toDouble(), 0.0)

        fun valueOf(real: Long): Complex = Complex(real.toDouble(), 0.0)

        fun valueOf(real: Float): Complex = Complex(real.toDouble(), 0.0)

        fun valueOf(real: Double): Complex = Complex(real, 0.0)

        /**
         * 由纯虚数取值
         */
        fun pureImaginary(imaginary: Int): Complex = Complex(0.0, imaginary.toDouble())

        fun pureImaginary(imaginary: Long): Complex = Complex(0.0, imaginary.toDouble())

        fun pureImaginary(imaginary: Float): Complex = Complex(0.0, imaginary.toDouble())

        fun pureImaginary(imaginary: Double): Complex = Complex(0.0, imaginary)
    }

}
