package com.xh3140.metrology.calculate.math

import com.xh3140.core.base.CD2D
import java.math.BigDecimal

object MathFormulaLSM : MathFormula() {

    override fun getDimension(): Int = 2

    override fun getChineseName(): String = "最小二乘法"

    override fun getEnglishName(): String = "least-squares method"

    override fun getDescription(): String {
        return "最小二乘法（又称最小平方法）是一种数学优化技术。它通过最小化误差的平方和寻找数据的最佳函数匹配。" +
                "利用最小二乘法可以简便地求得未知的数据，并使得这些求得的数据与实际数据之间误差的平方和为最小。" +
                "最小二乘法还可用于曲线拟合。其他一些优化问题也可通过最小化能量或最大化熵用最小二乘法来表达。"
    }

    override fun getLatexString(): String {
        val builder = StringBuilder()
        builder.append("[center]$\\[\\hat{b}=\\frac{\\sum_{i=1}^{n}x_iy_i-n\\bar{x}\\bar{y}}{\\sum_{i=1}^{n}x_i^2-n\\bar{x}^2}\\]$[/center]\n")
        builder.append("[center]$\\[\\hat{a}=\\bar{y}-\\hat{b}\\bar{x}\\]$[/center]\n")
        builder.append("$$\\[\\hat{b}:最小二乘法拟合直线的斜率；\\]$$\n")
        builder.append("$$\\[\\hat{a}:最小二乘法拟合直线的截距。\\]$$\n")
        return builder.toString()
    }

    /**
     * @property a 截距
     * @property b 斜率
     */
    data class Result(val a: BigDecimal, val b: BigDecimal)

    fun calculate(data: List<CD2D<BigDecimal>>): Result {
        if (data.isEmpty()) {
            throw IllegalArgumentException("data list is empty")
        }
        val n = BigDecimal(data.size)
        var sumA = BigDecimal.ZERO
        var sumB = BigDecimal.ZERO
        var sumPow2A = BigDecimal.ZERO
        var sumMulAB = BigDecimal.ZERO
        for (big in data) {
            sumA = sumA.add(big.value1)
            sumB = sumB.add(big.value2)
            sumPow2A = sumPow2A.add(big.value1.multiply(big.value1))
            sumMulAB = sumMulAB.add(big.value1.multiply(big.value2))
        }
        val avgA = sumA.divide(n, 16, BigDecimal.ROUND_HALF_UP)
        val avgB = sumB.divide(n, 16, BigDecimal.ROUND_HALF_UP)
        val numeratorB = sumMulAB.subtract(n.multiply(avgA).multiply(avgB))
        val denominatorB = sumPow2A.subtract(n.multiply(avgA.pow(2)))
        val barB = numeratorB.divide(denominatorB, 16, BigDecimal.ROUND_HALF_UP)
        val barA = avgB.subtract(barB.multiply(avgA))
        return Result(barA, barB)
    }
}