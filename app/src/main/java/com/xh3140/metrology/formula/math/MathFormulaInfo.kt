package com.xh3140.metrology.formula.math

/**
 * 数学公式接口
 */
interface MathFormulaInfo {
    /**
     * 公式名称
     */
    fun getName(): String

    /**
     * 公式英文名称
     */
    fun getEnglishName(): String

    /**
     * 公式描述
     */
    fun getDescription(): String

    /**
     * 公式LaTeX
     */
    fun getLatexString(): String
}