package com.xh3140.metrology.appliance.jjg.jjgn1163y2019.fragment

import com.xh3140.core.widgets.dialog.CircleContentDialog
import com.xh3140.metrology.R
import com.xh3140.metrology.base.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.activity_mr_homogeneity.buttonCalculate
import kotlinx.android.synthetic.main.activity_mr_homogeneity.buttonClear
import kotlinx.android.synthetic.main.fragment_jjg_1163_2019_part2.*
import kotlinx.android.synthetic.main.include_raw_data_button_group.*


/**
 * @Author : xh3140
 * @Time : 2020/7/18 10:52
 * @File : JJGN1163Y2019P2Fragment.kt
 */

class JJGN1163Y2019P2Fragment : BaseFragment() {
    override fun getLayoutResID(): Int = R.layout.fragment_jjg_1163_2019_part2

    override fun initListener() {
        // 计算数据
        buttonCalculate.setOnClickListener {
            rawDataView.calculateAllData()
        }
        // 随机数据
        buttonRandom.setOnClickListener {
            rawDataView.randomAllData()
        }
        // 清除数据
        buttonClear.setOnClickListener {
            val builder = CircleContentDialog.Builder(2)
                .setTitleText("清除数据")
                .setContentText("请问是否清除数据？")
                .setButtonText(0, "取消")
                .setButtonText(1, "确定")
                .setButtonOnClickListener { dialog, _, i ->
                    if (i == 1) {
                        rawDataView.clearAllData()
                    }
                    dialog.dismiss()
                }
            builder.create().show(parentFragmentManager, null)
        }
    }
}