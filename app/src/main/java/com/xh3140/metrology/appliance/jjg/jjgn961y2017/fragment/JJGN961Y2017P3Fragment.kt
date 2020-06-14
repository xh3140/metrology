package com.xh3140.metrology.appliance.jjg.jjgn961y2017.fragment

import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.jjg.jjgn961y2017.widgets.OnClickHoleListener
import com.xh3140.metrology.base.ui.fragment.BaseFragment
import kotlinx.android.synthetic.main.fragment_jjg_961_2017_part3.*
import java.util.*


class JJGN961Y2017P3Fragment : BaseFragment() {

    override fun getLayoutResID(): Int = R.layout.fragment_jjg_961_2017_part3

    override fun initListener() {
        lcrView.setOnClickHoleListener(object : OnClickHoleListener {
            override fun onClickHole(index: Int, contrast: Float, radius: Float) {
                if (index < 0) {
                    textViewRequire.text = getString(R.string.appliance_ct_lcr_model_require)
                } else {
                    textViewRequire.text = String.format(Locale.CHINA, "[低对比度：%.1f%%][直径：%.1fmm]", contrast, 2 * radius)
                }
            }
        })
    }

}