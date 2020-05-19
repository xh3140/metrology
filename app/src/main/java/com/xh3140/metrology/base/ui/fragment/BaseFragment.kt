package com.xh3140.metrology.base.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment


abstract class BaseFragment : Fragment() {
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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(getLayoutResID(), container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initData()
        initListener()
    }

}