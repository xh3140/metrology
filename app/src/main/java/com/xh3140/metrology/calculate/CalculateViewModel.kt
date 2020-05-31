package com.xh3140.metrology.calculate

import android.app.Application
import android.content.SharedPreferences
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.xh3140.core.base.CD2D
import com.xh3140.core.base.VD1D
import com.xh3140.core.base.VD2D
import com.xh3140.core.extensions.toDouble
import com.xh3140.metrology.calculate.math.MathFormula
import com.xh3140.metrology.calculate.math.MathFormulaLSM
import com.xh3140.metrology.calculate.math.MathFormulaMD
import com.xh3140.metrology.calculate.math.MathFormulaSD
import java.math.BigDecimal

class CalculateViewModel(application: Application) : AndroidViewModel(application) {

    val formula: MutableLiveData<MathFormula> = MutableLiveData()

    val preferences: SharedPreferences by lazy { PreferenceManager.getDefaultSharedPreferences(application) }

    private val mLiveItems1D: LiveData<ObservableArrayList<VD1D<String>>> =
        MutableLiveData(ObservableArrayList<VD1D<String>>().apply { addAll(Array(MIN_ITEM_COUNT) { VD1D("") }) })

    private val mLiveItems2D: LiveData<ObservableArrayList<VD2D<String>>> =
        MutableLiveData(ObservableArrayList<VD2D<String>>().apply { addAll(Array(MIN_ITEM_COUNT) { VD2D("", "") }) })

    val dimension: Int get() = formula.value?.getDimension() ?: 0

    val items1d: ObservableArrayList<VD1D<String>> get() = mLiveItems1D.value ?: ObservableArrayList()

    val items2d: ObservableArrayList<VD2D<String>> get() = mLiveItems2D.value ?: ObservableArrayList()

    private val mData1D: List<BigDecimal>
        get() {
            val data: MutableList<BigDecimal> = ArrayList()
            for (item in items1d) {
                if (item.value.isNotEmpty()) {
                    data.add(BigDecimal(item.value))
                }
            }
            return data
        }

    private val mData2D: List<CD2D<BigDecimal>>
        get() {
            val data: MutableList<CD2D<BigDecimal>> = ArrayList()
            for (item in items2d) {
                if (item.value1.isNotEmpty() && item.value2.isNotEmpty()) {
                    data.add(CD2D(BigDecimal(item.value1), BigDecimal(item.value2)))
                }
            }
            return data
        }

    companion object {
        const val NUMBER_SCALE: Int = 6
        const val MIN_ITEM_COUNT: Int = 3
        const val MAX_ITEM_COUNT: Int = 100
        const val KEY_ACTION_OPTION_BATCH: String = "switch_boolean_formula_batch"
        const val KEY_RESULT_OPTION_MAX: String = "check_boolean_formula_maximum"
        const val KEY_RESULT_OPTION_MIN: String = "check_boolean_formula_minimum"
        const val KEY_RESULT_OPTION_RNG: String = "check_boolean_formula_range"
        const val KEY_RESULT_OPTION_SUM: String = "check_boolean_formula_summation"
        const val KEY_RESULT_OPTION_AVG: String = "check_boolean_formula_average"
    }

    fun initItems() {
        mLiveItems1D.value?.clear()
        mLiveItems1D.value?.addAll(Array(MIN_ITEM_COUNT) { VD1D("") })
        mLiveItems2D.value?.clear()
        mLiveItems2D.value?.addAll(Array(MIN_ITEM_COUNT) { VD2D("", "") })
    }

    fun isExistData(): Boolean {
        when (dimension) {
            1 -> {
                for (item in items1d) {
                    if (item.value.isNotEmpty()) {
                        return true
                    }
                }
            }
            2 -> {
                for (item in items2d) {
                    if (item.value1.isNotEmpty() || item.value2.isNotEmpty()) {
                        return true
                    }
                }
            }
        }
        return false
    }

    fun isEnoughData(): Boolean {
        var count = 0
        when (dimension) {
            1 -> {
                for (item in items1d) {
                    if (item.value.isNotEmpty() && ++count >= MIN_ITEM_COUNT) {
                        return true
                    }
                }
            }
            2 -> {
                for (item in items2d) {
                    if (item.value1.isNotEmpty() && item.value2.isNotEmpty() && ++count >= MIN_ITEM_COUNT) {
                        return true
                    }
                }
            }
        }
        return false
    }

    fun appendEmptyItem(): String? {
        var message: String? = null
        when (dimension) {
            1 -> {
                if (items1d.size < MAX_ITEM_COUNT) {
                    items1d.add(VD1D(""))
                } else {
                    message = "最多不能超过${MAX_ITEM_COUNT}项数据"
                }
            }
            2 -> {
                if (items2d.size < MAX_ITEM_COUNT) {
                    items2d.add(VD2D("", ""))
                } else {
                    message = "最多不能超过${MAX_ITEM_COUNT}项数据"
                }
            }
            else -> message = "错误，维度未定义"
        }
        return message
    }

    fun appendEmptyItems(count: Int): String? {
        if (count <= 0) {
            return "错误的数量,添加失败"
        }
        var message: String? = null
        when (dimension) {
            1 -> {
                val currentCount = items1d.size
                if (currentCount + count <= MAX_ITEM_COUNT) {
                    items1d.addAll(List(count) { VD1D("") })
                } else {
                    val appendCount = MAX_ITEM_COUNT - currentCount
                    if (appendCount > 0) {
                        items1d.addAll(List(appendCount) { VD1D("") })
                    }
                    message = "最多不能超过${MAX_ITEM_COUNT}项数据"
                }
            }
            2 -> {
                val currentCount = items2d.size
                if (currentCount + count <= MAX_ITEM_COUNT) {
                    items2d.addAll(List(count) { VD2D("", "") })
                } else {
                    val appendCount = MAX_ITEM_COUNT - currentCount
                    if (appendCount > 0) {
                        items2d.addAll(List(appendCount) { VD2D("", "") })
                    }
                    message = "最多不能超过${MAX_ITEM_COUNT}项数据"
                }
            }
            else -> message = "错误，维度未定义"
        }
        return message
    }

    fun appendToEmptyItems(appendCount: Int): String? {
        return when (dimension) {
            1 -> appendEmptyItems(appendCount - items1d.size)
            2 -> appendEmptyItems(appendCount - items2d.size)
            else -> "错误，维度未定义"
        }
    }

    fun removeLastItem(): String? {
        var message: String? = null
        when (dimension) {
            1 -> {
                if (items1d.size > MIN_ITEM_COUNT) {
                    items1d.removeAt(items1d.size - 1)
                } else {
                    message = "最少需要保留${MIN_ITEM_COUNT}项数据"
                }
            }
            2 -> {
                if (items2d.size > MIN_ITEM_COUNT) {
                    items2d.removeAt(items2d.size - 1)
                } else {
                    message = "最少需要保留${MIN_ITEM_COUNT}项数据"
                }
            }
            else -> message = "错误，维度未定义"
        }
        return message
    }

    fun removeLastItems(count: Int): String? {
        if (count <= 0) {
            return "错误的数量,删除失败"
        }
        var message: String? = null
        when (dimension) {
            1 -> {
                val currentCount = items1d.size
                if (currentCount - count >= MIN_ITEM_COUNT) {
                    for (i in 0 until count) {
                        items1d.removeAt(currentCount - i - 1)
                    }
                } else {
                    val removeCount = currentCount - MIN_ITEM_COUNT
                    for (i in 0 until removeCount) {
                        items1d.removeAt(currentCount - i - 1)
                    }
                    message = "最少需要保留${MIN_ITEM_COUNT}项数据"
                }
            }
            2 -> {
                val currentCount = items2d.size
                if (currentCount - count >= MIN_ITEM_COUNT) {
                    for (i in 0 until count) {
                        items2d.removeAt(currentCount - i - 1)
                    }
                } else {
                    val removeCount = currentCount - MIN_ITEM_COUNT
                    for (i in 0 until removeCount) {
                        items2d.removeAt(currentCount - i - 1)
                    }
                    message = "最少需要保留${MIN_ITEM_COUNT}项数据"
                }
            }
            else -> message = "错误，维度未定义"
        }
        return message
    }

    fun removeToLastItems(removeCount: Int): String? {
        return when (dimension) {
            1 -> removeLastItems(items1d.size - removeCount)
            2 -> removeLastItems(items2d.size - removeCount)
            else -> "错误，维度未定义"
        }
    }

    fun clearValueItems(): String? {
        var message: String? = null
        when (dimension) {
            1 -> {
                for (item in items1d) {
                    item.value = ""
                }
            }
            2 -> {
                for (item in items2d) {
                    item.value1 = ""
                    item.value2 = ""
                }
            }
            else -> message = "错误，维度未定义"
        }
        return message
    }

    fun calculationBase(): List<CD2D<String>> {
        val result: MutableList<CD2D<String>> = ArrayList()
        when (dimension) {
            1 -> {
                val resultX = MathFormula.calculateBase(mData1D)
                if (preferences.getBoolean(KEY_RESULT_OPTION_MAX, false)) {
                    result.add(CD2D("最大值", resultX.max.toDouble(NUMBER_SCALE).toString()))
                }
                if (preferences.getBoolean(KEY_RESULT_OPTION_MIN, false)) {
                    result.add(CD2D("最小值", resultX.min.toDouble(NUMBER_SCALE).toString()))
                }
                if (preferences.getBoolean(KEY_RESULT_OPTION_RNG, true)) {
                    result.add(CD2D("极差", resultX.rng.toDouble(NUMBER_SCALE).toString()))
                }
                if (preferences.getBoolean(KEY_RESULT_OPTION_SUM, true)) {
                    result.add(CD2D("总和", resultX.sum.toDouble(NUMBER_SCALE).toString()))
                }
                if (preferences.getBoolean(KEY_RESULT_OPTION_AVG, true)) {
                    result.add(CD2D("平均值", resultX.avg.toDouble(NUMBER_SCALE).toString()))
                }
            }
            2 -> {
                val numbersX: MutableList<BigDecimal> = ArrayList()
                val numbersY: MutableList<BigDecimal> = ArrayList()
                for (number in mData2D) {
                    numbersX.add(number.value1)
                    numbersY.add(number.value2)
                }
                val resultX = MathFormula.calculateBase(numbersX)
                val resultY = MathFormula.calculateBase(numbersY)
                if (preferences.getBoolean(KEY_RESULT_OPTION_MAX, false)) {
                    result.add(CD2D("最大值X", resultX.max.toDouble(NUMBER_SCALE).toString()))
                    result.add(CD2D("最大值Y", resultY.max.toDouble(NUMBER_SCALE).toString()))
                }
                if (preferences.getBoolean(KEY_RESULT_OPTION_MIN, false)) {
                    result.add(CD2D("最小值X", resultX.min.toDouble(NUMBER_SCALE).toString()))
                    result.add(CD2D("最小值Y", resultY.min.toDouble(NUMBER_SCALE).toString()))
                }
                if (preferences.getBoolean(KEY_RESULT_OPTION_RNG, true)) {
                    result.add(CD2D("极差X", resultX.rng.toDouble(NUMBER_SCALE).toString()))
                    result.add(CD2D("极差Y", resultY.rng.toDouble(NUMBER_SCALE).toString()))
                }
                if (preferences.getBoolean(KEY_RESULT_OPTION_SUM, true)) {
                    result.add(CD2D("总和X", resultX.sum.toDouble(NUMBER_SCALE).toString()))
                    result.add(CD2D("总和Y", resultY.sum.toDouble(NUMBER_SCALE).toString()))
                }
                if (preferences.getBoolean(KEY_RESULT_OPTION_AVG, true)) {
                    result.add(CD2D("平均值X", resultX.avg.toDouble(NUMBER_SCALE).toString()))
                    result.add(CD2D("平均值Y", resultY.avg.toDouble(NUMBER_SCALE).toString()))
                }
            }
        }
        return result
    }

    fun calculationFormula(): List<CD2D<String>> {
        val result: MutableList<CD2D<String>> = ArrayList()
        when (formula.value) {
            is MathFormulaMD -> result.addAll(calculationRMD())
            is MathFormulaSD -> result.addAll(calculationRSD())
            is MathFormulaLSM -> result.addAll(calculationLSM())
        }
        return result
    }

    private fun calculationRMD(): List<CD2D<String>> {
        val result = MathFormulaMD.calculate(mData1D)
        val md = result.md.toDouble(NUMBER_SCALE).toString()
        val rmd = result.rmd.multiply(BigDecimal(100)).toDouble(NUMBER_SCALE).toString().plus("%")
        return listOf(CD2D("平均偏差", md), CD2D("相对平均偏差", rmd))
    }

    private fun calculationRSD(): List<CD2D<String>> {
        val result = MathFormulaSD.calculate(mData1D)
        val sd = result.sd.toDouble(NUMBER_SCALE).toString()
        val rsd = result.rsd.multiply(BigDecimal(100)).toDouble(NUMBER_SCALE).toString().plus("%")
        return listOf(CD2D("标准偏差", sd), CD2D("相对标准偏差", rsd))
    }

    private fun calculationLSM(): List<CD2D<String>> {
        val result = MathFormulaLSM.calculate(mData2D)
        val a = result.a.toDouble(NUMBER_SCALE)
        val b = result.b.toDouble(NUMBER_SCALE)
        return listOf(CD2D("斜率", b.toString()), CD2D("截距", a.toString()))
    }
}