package com.xh3140.metrology.calculate.fragment

import android.content.SharedPreferences
import android.text.InputType
import android.util.Log
import androidx.lifecycle.Observer
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.xh3140.core.base.CD2D
import com.xh3140.core.base.VD1D
import com.xh3140.core.base.VD2D
import com.xh3140.core.extensions.toast
import com.xh3140.core.widgets.dialog.CircleContentDialog
import com.xh3140.core.widgets.dialog.CircleInputDialog
import com.xh3140.metrology.R
import com.xh3140.metrology.base.ui.fragment.BaseFragment
import com.xh3140.metrology.calculate.CalculateViewModel
import com.xh3140.metrology.calculate.adapter.D1DataAdapter
import com.xh3140.metrology.calculate.adapter.D2DataAdapter
import com.xh3140.metrology.calculate.adapter.ResultAdapter
import com.xh3140.metrology.calculate.math.MathFormulaRMD
import com.xh3140.metrology.calculate.math.MathFormulaRSD
import com.xh3140.metrology.calculate.math.MathFormulaZXECF
import kotlinx.android.synthetic.main.fragment_calculate_algorithm.*


class AlgorithmFragment(viewModel: CalculateViewModel) : BaseFragment() {

    private val mViewModel: CalculateViewModel = viewModel

    private val mD1DataAdapter: D1DataAdapter by lazy { D1DataAdapter(mViewModel.items1) }

    private val mD2DataAdapter: D2DataAdapter by lazy { D2DataAdapter(mViewModel.items2) }

    private val mResultAdapter: ResultAdapter by lazy { ResultAdapter() }

    private val mPreferences: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(requireActivity())
    }

    override fun getLayoutResID(): Int = R.layout.fragment_calculate_algorithm

    override fun initData() {
        recyclerViewData.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mD1DataAdapter
        }
        recyclerViewResult.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mResultAdapter
        }
        mViewModel.formula.observe(this, Observer {
            when (it.getDimension()) {
                1 -> {
                    mViewModel.items1.clear()
                    mViewModel.items1.addAll(Array(CalculateViewModel.MIN_ITEM_COUNT) { VD1D("") })
                    recyclerViewData.adapter = mD1DataAdapter
                }
                2 -> {
                    mViewModel.items2.clear()
                    mViewModel.items2.addAll(Array(CalculateViewModel.MIN_ITEM_COUNT) { VD2D("", "") })
                    recyclerViewData.adapter = mD2DataAdapter
                }
            }
        })
        mResultAdapter.submitList(listOf(CD2D("总和", ""), CD2D("极差", ""), CD2D("平均值", "")))
    }

    override fun initListener() {
        // 单击按钮添加数据
        buttonAppend.setOnClickListener {
            mViewModel.appendEmptyItem()?.also { toast(it) }
        }
        // 单击按钮删除数据
        buttonRemove.setOnClickListener {
            mViewModel.removeLastItem()?.also { toast(it) }
        }
        // 长按按钮批量添加数据
        buttonAppend.setOnLongClickListener {
            if (!mPreferences.getBoolean("switch_boolean_formula_batch", true)) {
                return@setOnLongClickListener false
            }
            val builder = CircleInputDialog.Builder(3)
                .setTitleText("批量添加数据")
                .setSummaryText("数据量只能在${CalculateViewModel.MIN_ITEM_COUNT}~${CalculateViewModel.MAX_ITEM_COUNT}之间")
                .setInputType(InputType.TYPE_CLASS_NUMBER)
                .setInputHintText("请输入数量")
                .setInputMaxLength(5)
                .setInputBackgroundResourceId(R.drawable.theme_input_gray)
                .setButtonText(0, "取消")
                .setButtonText(1, "添加")
                .setButtonText(2, "添加到")
                .setButtonOnClickListener { dialog, _, index ->
                    if (index > 0) {
                        val countText = (dialog as CircleInputDialog).getInputText()
                        if (countText.isEmpty()) {
                            toast("请输入正确的整数格式！")
                        } else {
                            val count = countText.toInt()
                            when (index) {
                                1 -> mViewModel.appendEmptyItems(count)?.also { toast(it) }
                                2 -> mViewModel.appendToEmptyItems(count)?.also { toast(it) }
                            }
                        }
                    }
                    dialog.dismiss()
                }
            builder.create().show(parentFragmentManager, null)
            return@setOnLongClickListener true
        }
        // 长按按钮批量删除数据
        buttonRemove.setOnLongClickListener {
            if (!mPreferences.getBoolean("switch_boolean_formula_batch", true)) {
                return@setOnLongClickListener false
            }
            val builder = CircleInputDialog.Builder(3)
                .setTitleText("批量删除数据")
                .setSummaryText("数据量只能在${CalculateViewModel.MIN_ITEM_COUNT}~${CalculateViewModel.MAX_ITEM_COUNT}之间")
                .setInputType(InputType.TYPE_CLASS_NUMBER)
                .setInputHintText("请输入数量")
                .setInputMaxLength(5)
                .setInputBackgroundResourceId(R.drawable.theme_input_gray)
                .setButtonText(0, "取消")
                .setButtonText(1, "删除")
                .setButtonText(2, "删除到")
                .setButtonOnClickListener { dialog, _, index ->
                    if (index > 0) {
                        val countText = (dialog as CircleInputDialog).getInputText()
                        if (countText.isEmpty()) {
                            toast("请输入正确的整数格式！")
                        } else {
                            val count = countText.toInt()
                            when (index) {
                                1 -> mViewModel.removeLastItems(count)?.also { toast(it) }
                                2 -> mViewModel.removeToLastItems(count)?.also { toast(it) }
                            }
                        }
                    }
                    dialog.dismiss()
                }
            builder.create().show(parentFragmentManager, null)
            return@setOnLongClickListener true
        }
        // 计算数据
        buttonCalculate.setOnClickListener {
            Log.d("xh3140", "${mViewModel.dimension}")
            when (mViewModel.dimension) {
                1 -> {
                    if (mViewModel.numbers1.size < CalculateViewModel.MIN_ITEM_COUNT) {
                        toast("有效数据不能少于${CalculateViewModel.MIN_ITEM_COUNT}项")
                    } else {
                        val result: MutableList<CD2D<String>> = ArrayList()
                        val baseResult = mViewModel.calculationBase()
                        if (mPreferences.getBoolean("check_boolean_formula_maximum", false))
                            result.add(baseResult[0])
                        if (mPreferences.getBoolean("check_boolean_formula_minimum", false))
                            result.add(baseResult[1])
                        if (mPreferences.getBoolean("check_boolean_formula_range", true))
                            result.add(baseResult[2])
                        if (mPreferences.getBoolean("check_boolean_formula_summation", true))
                            result.add(baseResult[3])
                        if (mPreferences.getBoolean("check_boolean_formula_average", true))
                            result.add(baseResult[4])
                        when (mViewModel.formula.value) {
                            is MathFormulaRMD -> {
                                result.addAll(mViewModel.calculationRMD())
                                mResultAdapter.submitList(result.toList())
                            }
                            is MathFormulaRSD -> {
                                result.addAll(mViewModel.calculationRSD())
                                mResultAdapter.submitList(result.toList())
                            }
                            is MathFormulaZXECF -> {
                                mResultAdapter.submitList(mViewModel.calculationZXECF())
                            }
                        }
                    }
                }
                2 -> {
                    if (mViewModel.numbers2.size < CalculateViewModel.MIN_ITEM_COUNT) {
                        toast("有效数据不能少于${CalculateViewModel.MIN_ITEM_COUNT}项")
                    } else {
                        when (mViewModel.formula.value) {
                            is MathFormulaZXECF -> {
                                mResultAdapter.submitList(mViewModel.calculationZXECF())
                            }
                        }
                    }
                }
            }
        }
        // 清除数据
        buttonClear.setOnClickListener {
            if (mViewModel.numbers1.isEmpty()) {
                return@setOnClickListener
            }
            val builder = CircleContentDialog.Builder(2)
                .setTitleText("清除数据")
                .setContentText("请问是否清除数据？")
                .setButtonText(0, "取消")
                .setButtonText(1, "确定")
                .setButtonOnClickListener { dialog, _, i ->
                    if (i == 1) {
                        mViewModel.emptyAllDataItems()?.also { toast(it) }
                        when (mViewModel.dimension) {
                            1 -> mD1DataAdapter.notifyItemRangeChanged(0, mD1DataAdapter.itemCount)
                            2 -> mD2DataAdapter.notifyItemRangeChanged(0, mD2DataAdapter.itemCount)
                        }
                        recyclerViewData.scrollToPosition(0)
                        val list: MutableList<CD2D<String>> = ArrayList()
                        list.add(CD2D("总和", ""))
                        list.add(CD2D("极差", ""))
                        list.add(CD2D("平均值", ""))
                        mResultAdapter.submitList(list)
                    }
                    dialog.dismiss()
                }
            builder.create().show(parentFragmentManager, null)
        }
    }
}