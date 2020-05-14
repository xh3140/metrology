package com.xh3140.metrology.formula

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.xh3140.metrology.R
import com.xh3140.metrology.base.BaseActivity
import com.xh3140.metrology.formula.fragment.AboutFragment
import com.xh3140.metrology.formula.fragment.AlgorithmFragment
import com.xh3140.metrology.formula.fragment.SettingFragment
import kotlinx.android.synthetic.main.activity_formula.*


class FormulaActivity : BaseActivity() {

    internal val aboutFragment: AboutFragment by lazy { AboutFragment() }

    internal val algorithmFragment: AlgorithmFragment by lazy { AlgorithmFragment() }

    internal val settingFragment: SettingFragment by lazy { SettingFragment() }

    override fun getLayoutResID(): Int = R.layout.activity_formula

    override fun initData() {
        setTitle(R.string.activity_calculate_title)
        setActionBarBackEnabled(true)
        // 配适器
        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> aboutFragment
                    1 -> algorithmFragment
                    else -> settingFragment
                }
            }

            override fun getItemCount(): Int = 3
        }
        // 选项卡
        TabLayoutMediator(tabLayout, viewPager2, TabConfigurationStrategy { tab, position ->
            when (position) {
                0 -> tab.setText(R.string.formula_fragment_intro_title)
                1 -> tab.setText(R.string.calculate_fragment_algorithm_title)
                else -> tab.setText(R.string.calculate_fragment_setting_title)
            }
        }).attach()

    }

}