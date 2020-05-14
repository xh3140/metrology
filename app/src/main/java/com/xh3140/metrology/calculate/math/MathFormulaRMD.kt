package com.xh3140.metrology.calculate.math

import java.math.BigDecimal

object MathFormulaRMD : MathFormula() {


    override fun getChineseName(): String = "相对平均偏差"

    override fun getEnglishName(): String = "Relative Mean Deviation"

    override fun getDescription(): String {
        return "平均偏差（Average Deviation or Mean Deviation ）是数列中各项数值与其算术平均数的离差绝对值的算术平均数。" +
                "平均偏差是用来测定数列中各项数值对其平均数离势程度的一种尺度。\n\n" +
                "相对平均偏差（Relative Mean Deviation）是指平均偏差与相应的算数平均值的比值。"
    }

    override fun getLatexString(): String {
        return "[center]$\\[M=\\frac{\\sum_{i=1}^{n}\\left|x_i-\\bar{x}\\right|}{n\\bar{x}}\\times{100\\%}\\]$[/center]\n\n" +
                "$$\\[M:n次测量中某单个测得值的相对平均偏差；\\]$$\n" +
                "$$\\[n:测量次数；\\]$$\n" +
                "$$\\[x_i:第i次测量的测得值；\\]$$\n" +
                "$$\\[\\bar{x}:n次测量所得一组测得值的算数平均值。\\]$$"
    }

    /**
     * @property md 平均偏差
     * @property rmd 相对平均偏差
     */
    data class Result(val md: BigDecimal, val rmd: BigDecimal)

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
            temp = temp.add(big.subtract(avg).abs())
        }
        val md = temp.divide(n, 16, BigDecimal.ROUND_HALF_UP)
        val rmd = md.divide(avg, 16, BigDecimal.ROUND_HALF_UP)
        return Result(md, rmd)
    }
}