package com.xh3140.metrology.appliance.jjg.jjgn961y2017

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.jjg.jjgn961y2017.fragment.JJGN961Y2017P1Fragment
import com.xh3140.metrology.appliance.jjg.jjgn961y2017.fragment.JJGN961Y2017P2Fragment
import com.xh3140.metrology.appliance.jjg.jjgn961y2017.fragment.JJGN961Y2017P3Fragment
import com.xh3140.metrology.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_calculate.*

class JJGN961Y2017Activity : BaseActivity() {
    override fun getLayoutResID(): Int = R.layout.activity_jjg_961_2017

    override fun initData() {
        title = "CT计量检定"
        setActionBarBackEnabled(true)
        // 配适器
        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 3

            override fun createFragment(position: Int): Fragment =
                when (position) {
                    0 -> JJGN961Y2017P1Fragment()
                    1 -> JJGN961Y2017P2Fragment()
                    else -> JJGN961Y2017P3Fragment()
                }
        }
        // 选项卡
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "低对比度分辨力"
                1 -> tab.text = "窗宽窗位"
                else -> tab.text = "检测模体"
            }
        }.attach()
    }
}
