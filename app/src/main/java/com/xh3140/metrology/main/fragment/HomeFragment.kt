package com.xh3140.metrology.main.fragment

import com.xh3140.core.extensions.startActivity
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.ApplianceActivity
import com.xh3140.metrology.appliance.MrHomogeneityActivity
import com.xh3140.metrology.appliance.jjgn1163y2019.JJGN1163Y2019Activity
import com.xh3140.metrology.appliance.jjgn961y2017.JJGN961Y2017Activity
import com.xh3140.metrology.base.ui.fragment.BaseFragment
import com.xh3140.metrology.calculate.CalculateActivity
import kotlinx.android.synthetic.main.fragment_main_home.*


class HomeFragment : BaseFragment() {

    override fun getLayoutResID(): Int = R.layout.fragment_main_home

    override fun initListener() {
        // 公式算法
        buttonFormula.setOnClickListener {
            startActivity<CalculateActivity>()
        }
        // 计量器具
        buttonAppliance.setOnClickListener {
            startActivity<ApplianceActivity>()
        }
        // CT低对比度分辨力
        buttonCT.setOnClickListener {
            startActivity<JJGN961Y2017Activity>()
        }
        // MR图像均匀性
        buttonMrHomogeneity.setOnClickListener {
            startActivity<MrHomogeneityActivity>()
        }
        // 多参数监护仪
        buttonJHY.setOnClickListener {
            startActivity<JJGN1163Y2019Activity>()
        }
    }
}