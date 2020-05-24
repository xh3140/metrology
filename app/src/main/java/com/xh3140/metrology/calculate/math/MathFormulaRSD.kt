package com.xh3140.metrology.calculate.math

import com.xh3140.core.extensions.sqrt
import java.math.BigDecimal

object MathFormulaRSD : MathFormula() {

    override fun getDimension(): Int = 1

    override fun getChineseName(): String = "相对标准偏差"

    override fun getEnglishName(): String = "RSD,Relative Standard Deviation"

    override fun getDescription(): String {
        val builder = StringBuilder()
        builder.append("标准偏差（Std Dev,Standard Deviation）的计算公式为贝塞尔公式。\n\n")
        builder.append("标准偏差（也称标准离差或均方根差）是反映一组测量数据离散程度的统计指标。")
        builder.append("是指统计结果在某一个时段内误差上下波动的幅度。\n\n")
        builder.append("相对标准偏差（RSD,Relative Standard Deviation）又叫标准偏差系数、变异系数、变动系数等，")
        builder.append("是指标准偏差与相应的算数平均值的比值。\n\n")
        builder.append("偏差（标准偏差和相对标准偏差）通常用来表示分析测试结果的精密度。")
        return builder.toString()
    }

    override fun getLatexString(): String {
        val builder = StringBuilder()
        builder.append("[center]$\\[S=\\frac{1}{\\bar{x}}\\sqrt{\\frac{\\sum_{i=1}^{n}\\left(x_i-\\bar{x}\\right)^{2}}{n-1}}\\times{100\\%}\\]$[/center]\n")
        builder.append("$$\\[S:n次测量中某单个测得值的相对标准偏差；\\]$$\n")
        builder.append("$$\\[n:测量次数；\\]$$\n")
        builder.append("$$\\[x_i:第i次测量的测得值；\\]$$\n")
        builder.append("$$\\[\\bar{x}:n次测量所得一组测得值的算数平均值。\\]$$\n")
        return builder.toString()
    }

    /**
     * @property sd 标准偏差
     * @property rsd 相对标准偏差
     */
    data class Result(val sd: BigDecimal, val rsd: BigDecimal)

    fun calculate(data: List<BigDecimal>): Result {
        if (data.isEmpty()) {
            throw IllegalArgumentException("data list is empty")
        }
        val n = BigDecimal(data.size)
        var sum = BigDecimal.ZERO
        for (big in data) {
            sum = sum.add(big)
        }
        val avg = sum.divide(n, 16, BigDecimal.ROUND_HALF_UP)
        var temp = BigDecimal.ZERO
        for (big in data) {
            temp = temp.add(big.subtract(avg).pow(2))
        }
        val sd = (temp.divide(n.subtract(BigDecimal.ONE), 16, BigDecimal.ROUND_HALF_UP)).sqrt()
        val rsd = sd.divide(avg, 16, BigDecimal.ROUND_HALF_UP)
        return Result(sd, rsd)
    }
}