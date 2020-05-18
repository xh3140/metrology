package com.xh3140.metrology.calculate.fragment

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.xh3140.metrology.R
import com.xh3140.metrology.base.ui.fragment.BaseFragment


class SettingFragment : BaseFragment() {

    override fun getLayoutResID(): Int = R.layout.fragment_calculate_setting

    override fun initData() {
        parentFragmentManager.beginTransaction()
            .replace(
                R.id.frameLayoutContainer,
                SettingPreferenceFragment()
            )
            .commit()
    }

    class SettingPreferenceFragment : PreferenceFragmentCompat() {
        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
            setPreferencesFromResource(R.xml.preferences_formula_setting, rootKey)
        }
    }
}
