package com.xh3140.metrology.calculate.math

import java.math.BigDecimal

abstract class MathFormula {
    /**
     * 公式名称
     */
    abstract fun getChineseName(): String

    /**
     * 公式英文名称
     */
    abstract fun getEnglishName(): String

    /**
     * 公式描述
     */
    abstract fun getDescription(): String

    /**
     * 公式LaTeX
     */
    abstract fun getLatexString(): String

    /**
     * @property max 最大值
     * @property min 最小值
     * @property rng 极差
     * @property sum 总和
     * @property avg 平均值
     */
    data class BaseResult(
        val max: BigDecimal,
        val min: BigDecimal,
        val rng: BigDecimal,
        val sum: BigDecimal,
        val avg: BigDecimal
    )

    companion object {
        fun calculateBase(data: List<BigDecimal>): BaseResult {
            if (data.isEmpty()) {
                throw IllegalArgumentException("data list is empty")
            }
            val n = BigDecimal(data.size)
            var max = data.first()
            var min = data.first()
            var sum = BigDecimal.ZERO
            for (big in data) {
                max = max.max(big)
                min = min.min(big)
                sum = sum.add(big)
            }
            val rng = max.subtract(min)
            val avg = sum.divide(n, 16, BigDecimal.ROUND_HALF_UP)
            return BaseResult(max, min, rng, sum, avg)
        }
    }
}