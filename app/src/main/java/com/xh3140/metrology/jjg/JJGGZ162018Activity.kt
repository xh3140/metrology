package com.xh3140.metrology.jjg

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.xh3140.metrology.R
import com.xh3140.metrology.base.BaseActivity
import com.xh3140.metrology.jjg.jjg_961_2017.fragment.JJG96120171Part1Fragment
import com.xh3140.metrology.jjg.jjg_961_2017.fragment.JJG96120171Part2Fragment
import com.xh3140.metrology.jjg.jjg_961_2017.fragment.JJG96120171Part3Fragment
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
                    0 -> JJG96120171Part1Fragment()
                    1 -> JJG96120171Part2Fragment()
                    else -> JJG96120171Part3Fragment()
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