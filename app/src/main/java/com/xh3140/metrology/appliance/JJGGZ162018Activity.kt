package com.xh3140.metrology.appliance

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.jjgn961y2017.fragment.JJGN961Y2017P1Fragment
import com.xh3140.metrology.appliance.jjgn961y2017.fragment.JJGN961Y2017P2Fragment
import com.xh3140.metrology.appliance.jjgn961y2017.fragment.JJGN961Y2017P3Fragment
import com.xh3140.metrology.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_jjg_gz_16_2018.*

class JJGGZ162018Activity : BaseActivity() {
    override fun getLayoutResID(): Int = R.layout.activity_jjg_gz_16_2018
    override fun initData() {
        title = "MR计量检定"
        setActionBarBackEnabled(true)
        // 配适器
        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> JJGN961Y2017P1Fragment()
                    1 -> JJGN961Y2017P2Fragment()
                    else -> JJGN961Y2017P3Fragment()
                }
            }

            override fun getItemCount(): Int {
                return 3
            }
        }
        // 选项卡
        TabLayoutMediator(tabLayout, viewPager2, TabConfigurationStrategy { tab, position ->
            when (position) {
                0 -> tab.text = "信噪比"
                1 -> tab.text = "图像均匀性"
                else -> tab.text = "检测模体"
            }
        }).attach()
    }
}