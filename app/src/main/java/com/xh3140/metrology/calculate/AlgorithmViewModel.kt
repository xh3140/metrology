package com.xh3140.metrology.calculate

import android.app.Application
import android.widget.Toast
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.xh3140.core.extensions.toDouble
import com.xh3140.metrology.calculate.math.MathFormula
import com.xh3140.metrology.calculate.math.MathFormulaRMD
import com.xh3140.metrology.calculate.math.MathFormulaRSD
import java.math.BigDecimal

class AlgorithmViewModel(application: Application) : AndroidViewModel(application) {

    private val mLiveItems: LiveData<ObservableArrayList<String>> =
        MutableLiveData(ObservableArrayList<String>().apply { addAll(Array(MIN_ITEM_COUNT) { "" }) })

    val items: ObservableArrayList<String> get() = mLiveItems.value ?: ObservableArrayList()

    val numbers: List<BigDecimal>
        get() {
            val currentItems = items
            val numberList: MutableList<BigDecimal> = ArrayList()
            for (i in currentItems.indices) {
                if (currentItems[i].isNotEmpty()) {
                    numberList.add(BigDecimal(currentItems[i]))
                }
            }
            return numberList
        }

    companion object {
        const val NUMBER_SCALE: Int = 6
        const val MIN_ITEM_COUNT: Int = 3
        const val MAX_ITEM_COUNT: Int = 100
    }

    fun calculationBase(): List<ResultItem> {
        val result = MathFormula.calculateBase(numbers)
        val max = result.max.toDouble(NUMBER_SCALE)
        val min = result.min.toDouble(NUMBER_SCALE)
        val rng = result.rng.toDouble(NUMBER_SCALE)
        val sum = result.sum.toDouble(NUMBER_SCALE)
        val avg = result.avg.toDouble(NUMBER_SCALE)
        return listOf(
            ResultItem("最大值", max.toString()),
            ResultItem("最小值", min.toString()),
            ResultItem("极差", rng.toString()),
            ResultItem("总和", sum.toString()),
            ResultItem("平均值", avg.toString())
        )
    }

    fun calculationRMD(): List<ResultItem> {
        val result = MathFormulaRMD.calculate(numbers)
        val md = result.md.toDouble(NUMBER_SCALE)
        val rmd = result.rmd.multiply(BigDecimal(100)).toDouble(NUMBER_SCALE)
        return listOf(
            ResultItem("平均偏差", md.toString()),
            ResultItem("相对平均偏差", rmd.toString().plus("%"))
        )
    }

    fun calculationRSD(): List<ResultItem> {
        val result = MathFormulaRSD.calculate(numbers)
        val sd = result.sd.toDouble(NUMBER_SCALE)
        val rsd = result.rsd.multiply(BigDecimal(100)).toDouble(NUMBER_SCALE)
        return listOf(
            ResultItem("标准偏差", sd.toString()),
            ResultItem("相对标准偏差", rsd.toString().plus("%"))
        )
    }

    private fun toast(message: String) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show()
    }

    fun appendEmptyItem() {
        if (items.size < MAX_ITEM_COUNT) {
            items.add("")
        } else {
            toast("最多不能超过${MAX_ITEM_COUNT}项数据")
        }
    }

    fun appendEmptyItems(count: Int) {
        if (count <= 0) {
            toast("添加失败")
            return
        }
        val currentCount = items.size
        if (currentCount + count <= MAX_ITEM_COUNT) {
            items.addAll(List(count) { "" })
        } else {
            val appendCount = MAX_ITEM_COUNT - currentCount
            if (appendCount > 0) {
                items.addAll(List(appendCount) { "" })
            }
            toast("最多不能超过${MAX_ITEM_COUNT}项数据")
        }
    }

    fun appendToEmptyItems(appendCount: Int) {
        appendEmptyItems(appendCount - items.size)
    }

    fun removeLastItem() {
        if (items.size > MIN_ITEM_COUNT) {
            items.removeAt(items.size - 1)
        } else {
            toast("最少需要保留${MIN_ITEM_COUNT}项数据")
        }
    }

    fun removeLastItems(count: Int) {
        if (count <= 0) {
            toast("删除失败")
            return
        }
        val currentCount = items.size
        if (currentCount - count >= MIN_ITEM_COUNT) {
            for (i in 0 until count) {
                items.removeAt(currentCount - i - 1)
            }
        } else {
            val removeCount = currentCount - MIN_ITEM_COUNT
            for (i in 0 until removeCount) {
                items.removeAt(currentCount - i - 1)
            }
            toast("最少需要保留${MIN_ITEM_COUNT}项数据")
        }
    }

    fun removeToLastItems(removeCount: Int) {
        removeLastItems(items.size - removeCount)
    }

    fun emptyAllDataItems() {
        for (i in 0 until items.size) {
            items[i] = ""
        }
    }
}