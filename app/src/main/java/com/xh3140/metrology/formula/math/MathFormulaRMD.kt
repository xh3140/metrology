package com.xh3140.metrology.formula.math

import java.math.BigDecimal

object MathFormulaRMD : MathFormula() {

    override fun getName(): String = "相对平均偏差"

    override fun getEnglishName(): String = "Relative Mean Deviation"

    override fun getDescription(): String {
        val builder = StringBuilder()
        builder.append("平均偏差（Average Deviation or Mean Deviation ）是数列中各项数值与其算术平均数的离差绝对值的算术平均数。")
        builder.append("平均偏差是用来测定数列中各项数值对其平均数离势程度的一种尺度。\n\n")
        builder.append("相对平均偏差（Relative Mean Deviation）是指平均偏差与相应的算数平均值的比值。")
        return builder.toString()
    }

    override fun getLatexString(): String {
        val builder = StringBuilder()
        builder.append("[center]$\\[M=\\frac{\\sum_{i=1}^{n}\\left|x_i-\\bar{x}\\right|}{n\\bar{x}}\\times{100\\%}\\]$[/center]\n\n")
        builder.append("$$\\[M:n次测量中某单个测得值的相对平均偏差；\\]$$\n")
        builder.append("$$\\[n:测量次数；\\]$$\n")
        builder.append("$$\\[x_i:第i次测量的测得值；\\]$$\n")
        builder.append("$$\\[\\bar{x}:n次测量所得一组测得值的算数平均值。\\]$$\n")
        return builder.toString()
    }

    /**
     * @property md 平均偏差
     * @property rmd 相对平均偏差
     */
    data class Result(val md: BigDecimal, val rmd: BigDecimal)

    fun calculate(list: List<BigDecimal>): Result {
        val n = BigDecimal(list.size)
        var sum = BigDecimal.ZERO
        for (big in list) sum = sum.add(big)
        val avg = sum.divide(n, 16, BigDecimal.ROUND_HALF_UP)
        var temp = BigDecimal.ZERO
        for (big in list) temp = temp.add(big.subtract(avg).abs())
        val md = temp.divide(n, 16, BigDecimal.ROUND_HALF_UP)
        val rmd = md.divide(avg, 16, BigDecimal.ROUND_HALF_UP)
        return Result(md, rmd)
    }
}