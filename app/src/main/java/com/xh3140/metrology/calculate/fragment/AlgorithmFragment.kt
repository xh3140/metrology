package com.xh3140.metrology.calculate.fragment

import android.content.SharedPreferences
import android.text.InputType
import androidx.fragment.app.viewModels
import androidx.preference.PreferenceManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.xh3140.core.widget.dialog.CircleContentDialog
import com.xh3140.core.widget.dialog.CircleInputDialog
import com.xh3140.metrology.R
import com.xh3140.metrology.base.BaseFragment
import com.xh3140.metrology.calculate.AlgorithmViewModel
import com.xh3140.metrology.calculate.CalculateActivity
import com.xh3140.metrology.calculate.adapter.ListDataAdapter
import com.xh3140.metrology.calculate.adapter.ListResultAdapter
import com.xh3140.metrology.calculate.math.MathFormulaRMD
import com.xh3140.metrology.calculate.math.MathFormulaRSD
import kotlinx.android.synthetic.main.fragment_calculate_algorithm.*


class AlgorithmFragment : BaseFragment() {

    private val mViewModel: AlgorithmViewModel by viewModels()

    private val mActivity: CalculateActivity by lazy { requireActivity() as CalculateActivity }

    private val mDataAdapter: ListDataAdapter by lazy { ListDataAdapter(mViewModel) }

    private val mResultAdapter: ListResultAdapter by lazy { ListResultAdapter() }

    private val mPreferences: SharedPreferences by lazy {
        PreferenceManager.getDefaultSharedPreferences(requireActivity())
    }

    override fun getLayoutResID(): Int = R.layout.fragment_calculate_algorithm

    override fun initData() {
        recyclerViewData.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mDataAdapter
        }
        recyclerViewResult.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mResultAdapter
        }
        mResultAdapter.submitList(
            listOf(
                ListResultAdapter.Item("总和", ""),
                ListResultAdapter.Item("极差", ""),
                ListResultAdapter.Item("平均值", "")
            )
        )
    }

    override fun initListener() {
        // 单击按钮添加数据
        buttonAppend.setOnClickListener {
            mViewModel.appendEmptyItem(recyclerViewData, mDataAdapter)
        }
        // 单击按钮删除数据
        buttonRemove.setOnClickListener {
            mViewModel.removeLastItem(recyclerViewData, mDataAdapter)
        }
        // 长按按钮批量添加数据
        buttonAppend.setOnLongClickListener {
            if (!mPreferences.getBoolean("switch_boolean_formula_batch", true)) {
                return@setOnLongClickListener false
            }
            val builder = CircleInputDialog.Builder(3)
                .setTitleText("批量添加数据")
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
                                1 -> mViewModel.appendEmptyItems(count, recyclerViewData, mDataAdapter)
                                2 -> mViewModel.appendToEmptyItems(count, recyclerViewData, mDataAdapter)
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
                                1 -> mViewModel.removeLastItems(
                                    count,
                                    recyclerViewData,
                                    mDataAdapter
                                )
                                2 -> mViewModel.removeToLastItems(
                                    count,
                                    recyclerViewData,
                                    mDataAdapter
                                )
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
            if (mViewModel.numbers.size < AlgorithmViewModel.MIN_ITEM_COUNT) {
                toast("有效数据不能少于${AlgorithmViewModel.MIN_ITEM_COUNT}项")
            } else {
                val numbers = mViewModel.numbers
                val result = mutableListOf<ListResultAdapter.Item>()
                val baseResult =
                    AlgorithmViewModel.calculationBase(
                        numbers
                    )
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
                when (mActivity.formulaFragment.getMathFormula()) {
                    is MathFormulaRMD -> {
                        result.addAll(
                            AlgorithmViewModel.calculationRMD(
                                numbers
                            )
                        )
                        mResultAdapter.submitList(result.toList())
                    }
                    is MathFormulaRSD -> {
                        result.addAll(
                            AlgorithmViewModel.calculationRSD(
                                numbers
                            )
                        )
                        mResultAdapter.submitList(result.toList())
                    }
                    else -> return@setOnClickListener
                }
            }
        }
        // 清除数据
        buttonClear.setOnClickListener {
            if (mViewModel.numbers.isEmpty()) {
                return@setOnClickListener
            }
            val builder = CircleContentDialog.Builder(2)
                .setTitleText("清除数据")
                .setContentText("请问是否清除数据？")
                .setButtonText(0, "取消")
                .setButtonText(1, "确定")
                .setButtonOnClickListener { dialog, _, i ->
                    if (i == 1) {
                        mViewModel.emptyAllDataItems(recyclerViewData, mDataAdapter)
                        val list: MutableList<ListResultAdapter.Item> = ArrayList()
                        list.add(ListResultAdapter.Item("总和", ""))
                        list.add(ListResultAdapter.Item("极差", ""))
                        list.add(ListResultAdapter.Item("平均值", ""))
                        mResultAdapter.submitList(list)
                    }
                    dialog.dismiss()
                }
            builder.create().show(parentFragmentManager, null)
        }
    }
}