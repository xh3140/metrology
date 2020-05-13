package com.xh3140.metrology.base

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
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

    /**
     * 安全简便的toast
     */
    protected fun toast(message: String, duration: Int = Toast.LENGTH_SHORT) {
        runOnUiThread {
            Toast.makeText(this, message, duration).show()
        }
    }

    protected fun toast(@StringRes resId: Int, duration: Int = Toast.LENGTH_SHORT) {
        runOnUiThread {
            Toast.makeText(this, resId, duration).show()
        }
    }

    /**
     * 跳转到指定Activity
     */
    protected inline fun <reified T : BaseActivity> startActivity() {
        startActivity(Intent(this, T::class.java))
    }

    protected inline fun <reified T : BaseActivity> startActivity(bundle: Bundle) {
        startActivity(Intent(this, T::class.java).apply { putExtras(bundle) })
    }
}