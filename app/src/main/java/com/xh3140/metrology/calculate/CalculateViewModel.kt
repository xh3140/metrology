package com.xh3140.metrology.calculate

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.xh3140.core.base.CD2D
import com.xh3140.core.base.VD1D
import com.xh3140.core.base.VD2D
import com.xh3140.core.extensions.toDouble
import com.xh3140.metrology.calculate.math.MathFormula
import com.xh3140.metrology.calculate.math.MathFormulaRMD
import com.xh3140.metrology.calculate.math.MathFormulaRSD
import com.xh3140.metrology.calculate.math.MathFormulaZXECF
import java.math.BigDecimal

class CalculateViewModel : ViewModel() {

    private val mLiveItems1: LiveData<ObservableArrayList<VD1D<String>>> =
        MutableLiveData(ObservableArrayList<VD1D<String>>().apply { addAll(Array(MIN_ITEM_COUNT) { VD1D("") }) })

    private val mLiveItems2: LiveData<ObservableArrayList<VD2D<String>>> =
        MutableLiveData(ObservableArrayList<VD2D<String>>().apply { addAll(Array(MIN_ITEM_COUNT) { VD2D("", "") }) })

    val formula: MutableLiveData<MathFormula> = MutableLiveData()

    val dimension: Int get() = formula.value?.getDimension() ?: 0

    val items1: ObservableArrayList<VD1D<String>> get() = mLiveItems1.value ?: ObservableArrayList()

    val items2: ObservableArrayList<VD2D<String>> get() = mLiveItems2.value ?: ObservableArrayList()

    val numbers1: List<BigDecimal>
        get() {
            val currentItems = items1
            val numberList: MutableList<BigDecimal> = ArrayList()
            for (i in currentItems.indices) {
                if (currentItems[i].value.isNotEmpty()) {
                    numberList.add(BigDecimal(currentItems[i].value))
                }
            }
            return numberList
        }

    val numbers2: List<CD2D<BigDecimal>>
        get() {
            val currentItems = items2
            val numberList: MutableList<CD2D<BigDecimal>> = ArrayList()
            for (i in currentItems.indices) {
                if (currentItems[i].value1.isNotEmpty() && currentItems[i].value2.isNotEmpty()) {
                    numberList.add(CD2D(BigDecimal(currentItems[i].value1), BigDecimal(currentItems[i].value2)))
                }
            }
            return numberList
        }

    companion object {
        const val NUMBER_SCALE: Int = 6
        const val MIN_ITEM_COUNT: Int = 3
        const val MAX_ITEM_COUNT: Int = 100
    }

    fun calculationBase(): List<CD2D<String>> {
        val result = MathFormula.calculateBase(numbers1)
        val max = result.max.toDouble(NUMBER_SCALE)
        val min = result.min.toDouble(NUMBER_SCALE)
        val rng = result.rng.toDouble(NUMBER_SCALE)
        val sum = result.sum.toDouble(NUMBER_SCALE)
        val avg = result.avg.toDouble(NUMBER_SCALE)
        return listOf(
            CD2D("最大值", max.toString()),
            CD2D("最小值", min.toString()),
            CD2D("极差", rng.toString()),
            CD2D("总和", sum.toString()),
            CD2D("平均值", avg.toString())
        )
    }

    fun calculationRMD(): List<CD2D<String>> {
        val result = MathFormulaRMD.calculate(numbers1)
        val md = result.md.toDouble(NUMBER_SCALE)
        val rmd = result.rmd.multiply(BigDecimal(100)).toDouble(NUMBER_SCALE)
        return listOf(
            CD2D("平均偏差", md.toString()),
            CD2D("相对平均偏差", rmd.toString().plus("%"))
        )
    }

    fun calculationRSD(): List<CD2D<String>> {
        val result = MathFormulaRSD.calculate(numbers1)
        val sd = result.sd.toDouble(NUMBER_SCALE)
        val rsd = result.rsd.multiply(BigDecimal(100)).toDouble(NUMBER_SCALE)
        return listOf(
            CD2D("标准偏差", sd.toString()),
            CD2D("相对标准偏差", rsd.toString().plus("%"))
        )
    }

    fun calculationZXECF(): List<CD2D<String>> {
        val result = MathFormulaZXECF.calculate(numbers2)
        val a = result.a.toDouble(NUMBER_SCALE)
        val b = result.b.toDouble(NUMBER_SCALE)
        return listOf(
            CD2D("斜率", b.toString()),
            CD2D("截距", a.toString())
        )
    }

    fun appendEmptyItem(): String? {
        var message: String? = null
        when (dimension) {
            1 -> {
                if (items1.size < MAX_ITEM_COUNT) {
                    items1.add(VD1D(""))
                } else {
                    message = "最多不能超过${MAX_ITEM_COUNT}项数据"
                }
            }
            2 -> {
                if (items2.size < MAX_ITEM_COUNT) {
                    items2.add(VD2D("", ""))
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
                val currentCount = items1.size
                if (currentCount + count <= MAX_ITEM_COUNT) {
                    items1.addAll(List(count) { VD1D("") })
                } else {
                    val appendCount = MAX_ITEM_COUNT - currentCount
                    if (appendCount > 0) {
                        items1.addAll(List(appendCount) { VD1D("") })
                    }
                    message = "最多不能超过${MAX_ITEM_COUNT}项数据"
                }
            }
            2 -> {
                val currentCount = items2.size
                if (currentCount + count <= MAX_ITEM_COUNT) {
                    items2.addAll(List(count) { VD2D("", "") })
                } else {
                    val appendCount = MAX_ITEM_COUNT - currentCount
                    if (appendCount > 0) {
                        items2.addAll(List(appendCount) { VD2D("", "") })
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
            1 -> appendEmptyItems(appendCount - items1.size)
            2 -> appendEmptyItems(appendCount - items2.size)
            else -> "错误，维度未定义"
        }
    }

    fun removeLastItem(): String? {
        var message: String? = null
        when (dimension) {
            1 -> {
                if (items1.size > MIN_ITEM_COUNT) {
                    items1.removeAt(items1.size - 1)
                } else {
                    message = "最少需要保留${MIN_ITEM_COUNT}项数据"
                }
            }
            2 -> {
                if (items2.size > MIN_ITEM_COUNT) {
                    items2.removeAt(items2.size - 1)
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
                val currentCount = items1.size
                if (currentCount - count >= MIN_ITEM_COUNT) {
                    for (i in 0 until count) {
                        items1.removeAt(currentCount - i - 1)
                    }
                } else {
                    val removeCount = currentCount - MIN_ITEM_COUNT
                    for (i in 0 until removeCount) {
                        items1.removeAt(currentCount - i - 1)
                    }
                    message = "最少需要保留${MIN_ITEM_COUNT}项数据"
                }
            }
            2 -> {
                val currentCount = items2.size
                if (currentCount - count >= MIN_ITEM_COUNT) {
                    for (i in 0 until count) {
                        items2.removeAt(currentCount - i - 1)
                    }
                } else {
                    val removeCount = currentCount - MIN_ITEM_COUNT
                    for (i in 0 until removeCount) {
                        items2.removeAt(currentCount - i - 1)
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
            1 -> removeLastItems(items1.size - removeCount)
            2 -> removeLastItems(items2.size - removeCount)
            else -> "错误，维度未定义"
        }
    }

    fun emptyAllDataItems(): String? {
        var message: String? = null
        when (dimension) {
            1 -> {
                for (i in 0 until items1.size) {
                    items1[i].value = ""
                }
            }
            2 -> {
                for (i in 0 until items2.size) {
                    items2[i].value1 = ""
                    items2[i].value2 = ""
                }
            }
            else -> message = "错误，维度未定义"
        }
        return message
    }
}