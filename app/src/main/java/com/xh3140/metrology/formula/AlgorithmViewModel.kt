package com.xh3140.metrology.formula

import android.app.Application
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.SavedStateHandle
import androidx.recyclerview.widget.RecyclerView
import com.xh3140.core.math.toDouble
import com.xh3140.metrology.formula.adapter.ListDataAdapter
import com.xh3140.metrology.formula.adapter.ListResultAdapter
import com.xh3140.metrology.formula.math.MathFormula
import com.xh3140.metrology.formula.math.MathFormulaRMD
import com.xh3140.metrology.formula.math.MathFormulaRSD
import java.math.BigDecimal

class AlgorithmViewModel(application: Application, private val handle: SavedStateHandle) :
    AndroidViewModel(application) {

    val items: List<String>
        get() = if (handle.contains(KEY_DATA_ITEMS)) {
            handle.getLiveData<List<String>>(KEY_DATA_ITEMS).value ?: List(
                MIN_ITEM_COUNT
            ) { "" }
        } else {
            List(MIN_ITEM_COUNT) { "" }.apply {
                handle.set(KEY_DATA_ITEMS, this)
            }
        }

    val numbers: List<Double>
        get() {
            val list = items
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


    fun setItemValue(index: Int, value: String) {
        val list = items.toMutableList()
        list[index] = value
        handle.set(KEY_DATA_ITEMS, list)
    }

    fun appendEmptyItem(recyclerView: RecyclerView, adapter: ListDataAdapter) {
        val list = items.toMutableList()
        if (list.size < MAX_ITEM_COUNT) {
            list.add("")
            handle.set(KEY_DATA_ITEMS, list)
            adapter.notifyItemInserted(list.size - 1)
            adapter.checkSizeMutation(true)
            recyclerView.scrollToPosition(list.size - 1)
        } else {
            toast("最多不能超过${MAX_ITEM_COUNT}项数据")
        }
    }

    fun appendEmptyItems(count: Int, recyclerView: RecyclerView, adapter: ListDataAdapter) {
        if (count <= 0) {
            toast("添加失败")
            return
        }
        val list = items.toMutableList()
        val currentCount = list.size
        if (currentCount + count <= MAX_ITEM_COUNT) {
            list.addAll(List(count) { "" })
            handle.set(KEY_DATA_ITEMS, list)
            adapter.notifyItemRangeInserted(currentCount, count)
            adapter.checkSizeMutation(true)
            recyclerView.scrollToPosition(list.size - 1)
        } else {
            val appendCount = MAX_ITEM_COUNT - currentCount
            if (appendCount > 0) {
                list.addAll(List(appendCount) { "" })
                handle.set(KEY_DATA_ITEMS, list)
                adapter.notifyItemRangeInserted(currentCount, appendCount)
                adapter.checkSizeMutation(true)
                recyclerView.scrollToPosition(list.size - 1)
            }
            toast("最多不能超过${MAX_ITEM_COUNT}项数据")
        }
    }

    fun appendToEmptyItems(count: Int, recyclerView: RecyclerView, adapter: ListDataAdapter) {
        appendEmptyItems(count - items.size, recyclerView, adapter)
    }

    fun removeLastItem(recyclerView: RecyclerView, adapter: ListDataAdapter) {
        val list = items.toMutableList()
        if (list.size > MIN_ITEM_COUNT) {
            if (list.isNotEmpty()) {
                list.removeAt(list.size - 1)
                handle.set(KEY_DATA_ITEMS, list)
                adapter.notifyItemRemoved(list.size)
                adapter.checkSizeMutation(false)
                recyclerView.scrollToPosition(list.size - 1)
            }
        } else {
            toast("最少需要保留${MIN_ITEM_COUNT}项数据")
        }
    }

    fun removeLastItems(count: Int, recyclerView: RecyclerView, adapter: ListDataAdapter) {
        if (count <= 0) {
            toast("删除失败")
            return
        }
        val list = items.toMutableList()
        val currentCount = list.size
        if (currentCount - count >= MIN_ITEM_COUNT) {
            for (i in 0 until count) list.removeAt(list.size - 1)
            handle.set(KEY_DATA_ITEMS, list)
            adapter.notifyItemRangeRemoved(currentCount - count, count)
            adapter.checkSizeMutation(false)
            recyclerView.scrollToPosition(list.size - 1)
        } else {
            val removeCount = currentCount - MIN_ITEM_COUNT
            for (i in 0 until removeCount) list.removeAt(list.size - 1)
            handle.set(KEY_DATA_ITEMS, list)
            adapter.notifyItemRangeRemoved(currentCount - removeCount, removeCount)
            adapter.checkSizeMutation(false)
            recyclerView.scrollToPosition(list.size - 1)
            toast("最少需要保留${MIN_ITEM_COUNT}项数据")
        }
    }

    fun removeToLastItems(count: Int, recyclerView: RecyclerView, adapter: ListDataAdapter) {
        removeLastItems(items.size - count, recyclerView, adapter)
    }

    fun emptyAllDataItems(recyclerView: RecyclerView, adapter: ListDataAdapter) {
        val list = items.toMutableList()
        for (i in list.indices) list[i] = ""
        handle.set(KEY_DATA_ITEMS, list)
        adapter.notifyItemRangeChanged(0, list.size)
        recyclerView.scrollToPosition(0)
    }

    companion object {
        const val MIN_ITEM_COUNT = 3
        const val MAX_ITEM_COUNT = 100
        const val KEY_DATA_ITEMS = "DATA_ITEMS"

        fun calculationBase(numbers: List<Double>): List<ListResultAdapter.Item> {
            val scale = 6
            val list = List(numbers.size) { BigDecimal(numbers[it]) }
            val result = MathFormula.calculateBase(list)
            val max = result.max.toDouble(scale)
            val min = result.min.toDouble(scale)
            val rng = result.rng.toDouble(scale)
            val sum = result.sum.toDouble(scale)
            val avg = result.avg.toDouble(scale)
            return listOf(
                ListResultAdapter.Item("最大值", max.toString()),
                ListResultAdapter.Item("最小值", min.toString()),
                ListResultAdapter.Item("极差", rng.toString()),
                ListResultAdapter.Item("总和", sum.toString()),
                ListResultAdapter.Item("平均值", avg.toString())
            )
        }

        fun calculationRMD(numbers: List<Double>): List<ListResultAdapter.Item> {
            val scale = 6
            val list = List(numbers.size) { BigDecimal(numbers[it]) }
            val result = MathFormulaRMD.calculate(list)
            val md = result.md.toDouble(scale)
            val rmd = result.rmd.multiply(BigDecimal(100)).toDouble(scale)
            return listOf(
                ListResultAdapter.Item("平均偏差", md.toString()),
                ListResultAdapter.Item("相对平均偏差", rmd.toString().plus("%"))
            )
        }

        fun calculationRSD(numbers: List<Double>): List<ListResultAdapter.Item> {
            val scale = 6
            val list = List(numbers.size) { BigDecimal(numbers[it]) }
            val result = MathFormulaRSD.calculate(list)
            val sd = result.sd.toDouble(scale)
            val rsd = result.rsd.multiply(BigDecimal(100)).toDouble(scale)
            return listOf(
                ListResultAdapter.Item("标准偏差", sd.toString()),
                ListResultAdapter.Item("相对标准偏差", rsd.toString().plus("%"))
            )
        }
    }
}