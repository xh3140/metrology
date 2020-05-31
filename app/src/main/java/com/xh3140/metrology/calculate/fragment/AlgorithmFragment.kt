package com.xh3140.metrology.calculate.fragment

import android.text.InputType
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.xh3140.core.base.CD2D
import com.xh3140.core.extensions.toast
import com.xh3140.core.widgets.dialog.CircleContentDialog
import com.xh3140.core.widgets.dialog.CircleInputDialog
import com.xh3140.metrology.R
import com.xh3140.metrology.base.ui.fragment.BaseFragment
import com.xh3140.metrology.calculate.CalculateViewModel
import com.xh3140.metrology.calculate.adapter.D1DataAdapter
import com.xh3140.metrology.calculate.adapter.D2DataAdapter
import com.xh3140.metrology.calculate.adapter.ResultAdapter
import kotlinx.android.synthetic.main.fragment_calculate_algorithm.*


class AlgorithmFragment(viewModel: CalculateViewModel) : BaseFragment() {

    private val mViewModel: CalculateViewModel = viewModel

    private val mData1DAdapter: D1DataAdapter by lazy { D1DataAdapter(mViewModel.items1d) }

    private val mData2DAdapter: D2DataAdapter by lazy { D2DataAdapter(mViewModel.items2d) }

    private val mResultAdapter: ResultAdapter by lazy { ResultAdapter() }

    override fun getLayoutResID(): Int = R.layout.fragment_calculate_algorithm

    override fun initData() {
        recyclerViewData.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mData1DAdapter
        }
        recyclerViewResult.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = mResultAdapter
        }
        mViewModel.formula.observe(this, Observer {
            mViewModel.initItems()
            when (mViewModel.dimension) {
                1 -> recyclerViewData.adapter = mData1DAdapter
                2 -> recyclerViewData.adapter = mData2DAdapter
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
            if (!mViewModel.preferences.getBoolean(CalculateViewModel.KEY_ACTION_OPTION_BATCH, true)) {
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
                        val countText = dialog.getInputText()
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
            if (!mViewModel.preferences.getBoolean(CalculateViewModel.KEY_ACTION_OPTION_BATCH, true)) {
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
                        val countText = dialog.getInputText()
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
            if (!mViewModel.isEnoughData()) {
                toast("有效数据不能少于${CalculateViewModel.MIN_ITEM_COUNT}项")
            } else {
                val result: MutableList<CD2D<String>> = ArrayList()
                result.addAll(mViewModel.calculationBase())
                result.addAll(mViewModel.calculationFormula())
                mResultAdapter.submitList(result)
            }
        }
        // 清除数据
        buttonClear.setOnClickListener {
            if (!mViewModel.isExistData()) {
                return@setOnClickListener
            }
            val builder = CircleContentDialog.Builder(2)
                .setTitleText("清除数据")
                .setContentText("请问是否清除数据？")
                .setButtonText(0, "取消")
                .setButtonText(1, "确定")
                .setButtonOnClickListener { dialog, _, i ->
                    if (i == 1) {
                        mViewModel.clearValueItems()?.also { toast(it) }
                        when (mViewModel.dimension) {
                            1 -> mData1DAdapter.notifyItemRangeChanged(0, mData1DAdapter.itemCount)
                            2 -> mData2DAdapter.notifyItemRangeChanged(0, mData2DAdapter.itemCount)
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