package com.xh3140.metrology.appliance.jjg.jjgn1163y2019

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.jjg.jjgn1163y2019.fragment.*
import com.xh3140.metrology.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_calculate.*

/**
 * @Author : xh3140
 * @Time : 2020/7/16 19:00
 * @File : JJGN1163Y2019Activity.kt
 */

class JJGN1163Y2019Activity : BaseActivity() {
    override fun getLayoutResID(): Int = R.layout.activity_jjg_1163_2019

    override fun initData() {
        setTitle(R.string.jjg_1163_2019_appliance_name)
        setActionBarBackEnabled(true)
        // 配适器
        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun getItemCount(): Int = 12

            override fun createFragment(position: Int): Fragment =
                when (position) {
                    0 -> JJGN1163Y2019P1Fragment()
                    1 -> JJGN1163Y2019P2Fragment()
                    2 -> JJGN1163Y2019P3Fragment()
                    3 -> JJGN1163Y2019P4Fragment()
                    4 -> JJGN1163Y2019P5Fragment()
                    5 -> JJGN1163Y2019P6Fragment()
                    6 -> JJGN1163Y2019P7Fragment()
                    7 -> JJGN1163Y2019P8Fragment()
                    8 -> JJGN1163Y2019P9Fragment()
                    9 -> JJGN1163Y2019P10Fragment()
                    10 -> JJGN1163Y2019P11Fragment()
                    else -> JJGN1163Y2019P12Fragment()
                }
        }
        // 选项卡
        TabLayoutMediator(tabLayout, viewPager2) { tab, position ->
            when (position) {
                0 -> tab.text = "电压测量误差"
                1 -> tab.text = "扫描速度误差"
                2 -> tab.text = "幅频特性"
                3 -> tab.text = "心率示值误差"
                4 -> tab.text = "静态压力测量范围"
                5 -> tab.text = "静态压力示值误差"
                6 -> tab.text = "血压示值重复性"
                7 -> tab.text = "气密性"
                8 -> tab.text = "脉搏血氧饱和度重复性"
                9 -> tab.text = "脉率示值误差"
                10 -> tab.text = "呼末二氧化碳浓度示值误差"
                else -> tab.text = "呼吸率示值误差"
            }
        }.attach()
    }


}