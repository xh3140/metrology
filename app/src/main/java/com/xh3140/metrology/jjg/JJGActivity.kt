package com.xh3140.metrology.jjg

import com.xh3140.metrology.R
import com.xh3140.metrology.base.BaseActivity
import com.xh3140.metrology.jjg.jjg_961_2017.JJG9612017Activity
import kotlinx.android.synthetic.main.activity_jjg.*

class JJGActivity : BaseActivity() {

    override fun getLayoutResID(): Int = R.layout.activity_jjg

    override fun initData() {
        setActionBarBackEnabled(true)
        // 医用诊断螺旋计算机断层摄影装置(CT)X射线辐射源
        buttonJJG9612017.setOnClickListener {
            startActivity<JJG9612017Activity>()
        }
    }


}
