package com.xh3140.metrology.calculate

import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.google.android.material.tabs.TabLayoutMediator.TabConfigurationStrategy
import com.xh3140.metrology.R
import com.xh3140.metrology.base.ui.activity.BaseActivity
import com.xh3140.metrology.calculate.fragment.AlgorithmFragment
import com.xh3140.metrology.calculate.fragment.FormulaFragment
import com.xh3140.metrology.calculate.fragment.SettingFragment
import kotlinx.android.synthetic.main.activity_calculate.*


class CalculateActivity : BaseActivity() {

    internal val formulaFragment: FormulaFragment by lazy { FormulaFragment() }

    internal val algorithmFragment: AlgorithmFragment by lazy { AlgorithmFragment() }

    internal val settingFragment: SettingFragment by lazy { SettingFragment() }

    override fun getLayoutResID(): Int = R.layout.activity_calculate

    override fun initData() {
        setTitle(R.string.activity_calculate_title)
        setActionBarBackEnabled(true)
        // 配适器
        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> formulaFragment
                    1 -> algorithmFragment
                    2 -> settingFragment
                    else -> Fragment()
                }
            }

            override fun getItemCount(): Int = 3
        }
        // 选项卡
        TabLayoutMediator(tabLayout, viewPager2, TabConfigurationStrategy { tab, position ->
            when (position) {
                0 -> tab.setText(R.string.calculate_fragment_formula_title)
                1 -> tab.setText(R.string.calculate_fragment_algorithm_title)
                2 -> tab.setText(R.string.calculate_fragment_setting_title)
                else -> tab.text = ""
            }
        }).attach()
    }

}