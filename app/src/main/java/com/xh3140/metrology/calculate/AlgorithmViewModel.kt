package com.xh3140.metrology.calculate

import android.app.Application
import android.util.Log
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

    val mItems: LiveData<ObservableArrayList<String>> =
        MutableLiveData(ObservableArrayList<String>().apply { addAll(Array(MIN_ITEM_COUNT) { "" }) })

    val numbers: List<Double>
        get() {
            val list = mItems.value ?: ObservableArrayList()
            val numberList: MutableList<Double> = ArrayList()
            for (i in list.indices) {
                if (list[i] != "") {
                    val number = list[i].toDouble()
                    if (!number.isNaN()) {
                        numberList.add(number)
                    }
                }
            }
            return numberList
        }

    private fun toast(message: String) {
        Toast.makeText(getApplication(), message, Toast.LENGTH_SHORT).show()
    }

    fun appendEmptyItem() {
        val count = mItems.value?.size ?: 0
        if (count < MAX_ITEM_COUNT) {
            mItems.value?.add("")
        } else {
            toast("最多不能超过${MAX_ITEM_COUNT}项数据")
        }
        Log.d("xh3140", mItems.value.toString())
    }

    fun appendEmptyItems(count: Int) {
        if (count <= 0) {
            toast("添加失败")
            return
        }
        val currentCount = mItems.value?.size ?: 0
        if (currentCount + count <= MAX_ITEM_COUNT) {
            mItems.value?.addAll(List(count) { "" })
        } else {
            val appendCount = MAX_ITEM_COUNT - currentCount
            if (appendCount > 0) {
                mItems.value?.addAll(List(appendCount) { "" })
            }
            toast("最多不能超过${MAX_ITEM_COUNT}项数据")
        }
    }

    fun appendToEmptyItems(appendCount: Int) {
        val count = mItems.value?.size ?: 0
        appendEmptyItems(appendCount - count)
    }

    fun removeLastItem() {
        val count = mItems.value?.size ?: 0
        if (count > MIN_ITEM_COUNT) {
            mItems.value?.removeAt(count - 1)
        } else {
            toast("最少需要保留${MIN_ITEM_COUNT}项数据")
        }
    }

    fun removeLastItems(count: Int) {
        if (count <= 0) {
            toast("删除失败")
            return
        }
        val currentCount = mItems.value?.size ?: 0
        if (currentCount - count >= MIN_ITEM_COUNT) {
            for (i in 0 until count) {
                mItems.value?.removeAt(currentCount - i - 1)
            }
        } else {
            val removeCount = currentCount - MIN_ITEM_COUNT
            for (i in 0 until removeCount) {
                mItems.value?.removeAt(currentCount - i - 1)
            }
            toast("最少需要保留${MIN_ITEM_COUNT}项数据")
        }
    }

    fun removeToLastItems(removeCount: Int) {
        val count = mItems.value?.size ?: 0
        removeLastItems(count - removeCount)
    }

    fun emptyAllDataItems() {
        val count = mItems.value?.size ?: 0
        for (i in 0 until count) {
            mItems.value?.set(i, "")
        }
    }

    companion object {
        const val MIN_ITEM_COUNT = 3
        const val MAX_ITEM_COUNT = 100

        fun calculationBase(numbers: List<Double>): List<ResultItem> {
            val scale = 6
            val list = List(numbers.size) { BigDecimal(numbers[it]) }
            val result = MathFormula.calculateBase(list)
            val max = result.max.toDouble(scale)
            val min = result.min.toDouble(scale)
            val rng = result.rng.toDouble(scale)
            val sum = result.sum.toDouble(scale)
            val avg = result.avg.toDouble(scale)
            return listOf(
                ResultItem("最大值", max.toString()),
                ResultItem("最小值", min.toString()),
                ResultItem("极差", rng.toString()),
                ResultItem("总和", sum.toString()),
                ResultItem("平均值", avg.toString())
            )
        }

        fun calculationRMD(numbers: List<Double>): List<ResultItem> {
            val scale = 6
            val list = List(numbers.size) { BigDecimal(numbers[it]) }
            val result = MathFormulaRMD.calculate(list)
            val md = result.md.toDouble(scale)
            val rmd = result.rmd.multiply(BigDecimal(100)).toDouble(scale)
            return listOf(
                ResultItem("平均偏差", md.toString()),
                ResultItem("相对平均偏差", rmd.toString().plus("%"))
            )
        }

        fun calculationRSD(numbers: List<Double>): List<ResultItem> {
            val scale = 6
            val list = List(numbers.size) { BigDecimal(numbers[it]) }
            val result = MathFormulaRSD.calculate(list)
            val sd = result.sd.toDouble(scale)
            val rsd = result.rsd.multiply(BigDecimal(100)).toDouble(scale)
            return listOf(
                ResultItem("标准偏差", sd.toString()),
                ResultItem("相对标准偏差", rsd.toString().plus("%"))
            )
        }
    }
}