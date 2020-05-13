package com.xh3140.metrology.formula.math

import java.math.BigDecimal

abstract class MathFormula : MathFormulaInfo {
    /**
     * 获取由中文和英文名称组成的标题
     */
    fun getNameTitle(): String {
        val chineseName = getName()
        val englishName = getEnglishName()
        return if (englishName.isEmpty()) {
            chineseName
        } else {
            String.format("%s\n%s", chineseName, englishName)
        }
    }

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
        /**
         * 计算基本数据
         */
        fun calculateBase(list: List<BigDecimal>): BaseResult {
            val n = BigDecimal(list.size)
            var max = list.first()
            var min = list.first()
            var sum = BigDecimal.ZERO
            for (big in list) {
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