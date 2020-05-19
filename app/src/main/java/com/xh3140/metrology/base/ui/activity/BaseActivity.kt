package com.xh3140.metrology.base.ui.activity

import android.os.Bundle
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity

abstract class BaseActivity : AppCompatActivity() {

    private var mActionBarBackEnabled: Boolean = false

    /**
     * 获取布局资源ID
     */
    @LayoutRes
    protected abstract fun getLayoutResID(): Int

    /**
     * 初始化数据
     */
    protected open fun initData() {
        // do nothing
    }

    /**
     * 初始化监听器
     */
    protected open fun initListener() {
        // do nothing
    }


    /**
     * 启动标题栏返回箭头
     */
    protected fun setActionBarBackEnabled(enabled: Boolean) {
        supportActionBar?.apply {
            mActionBarBackEnabled = enabled
            setDisplayHomeAsUpEnabled(enabled)
            setDisplayShowHomeEnabled(enabled)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResID())
        initData()
        initListener()
    }

    /**
     * 结束当前Activity并返回上级
     */
    override fun onSupportNavigateUp(): Boolean {
        if (mActionBarBackEnabled) finish()
        return super.onSupportNavigateUp()
    }
}