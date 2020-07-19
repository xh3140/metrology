package com.xh3140.metrology.appliance.jjgn1163y2019

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.ScrollView
import android.widget.Space
import androidx.core.view.setMargins
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.xh3140.core.extensions.dp2px
import com.xh3140.core.widgets.button.NiceButton
import com.xh3140.core.widgets.button.NiceButtonGroup
import com.xh3140.core.widgets.dialog.CircleContentDialog
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.jjgn1163y2019.widgets.*
import com.xh3140.metrology.appliance.widgets.MethodsView
import com.xh3140.metrology.appliance.widgets.RawDataView
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
            private val context = this@JJGN1163Y2019Activity

            override fun getItemCount(): Int = 12

            override fun createFragment(position: Int): Fragment =
                when (position) {
                    0 -> TabFragment(context, Tab1MethodsView(context), Tab1RawDataView(context))
                    1 -> TabFragment(context, Tab2MethodsView(context), Tab2RawDataView(context))
                    2 -> TabFragment(context, Tab3MethodsView(context), Tab3RawDataView(context))
                    3 -> TabFragment(context, Tab4MethodsView(context), Tab4RawDataView(context))
                    4 -> TabFragment(context, Tab5MethodsView(context), null)
                    5 -> TabFragment(context, Tab6MethodsView(context), Tab6RawDataView(context))
                    6 -> TabFragment(context, Tab7MethodsView(context), null)
                    7 -> TabFragment(context, Tab8MethodsView(context), null)
                    8 -> TabFragment(context, Tab9MethodsView(context), null)
                    9 -> TabFragment(context, Tab10MethodsView(context), null)
                    10 -> TabFragment(context, Tab11MethodsView(context), null)
                    11 -> TabFragment(context, Tab12MethodsView(context), null)
                    else -> Fragment()
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

    class TabFragment(context: Context, methodsView: MethodsView?, rawDataView: RawDataView?) : Fragment() {
        private val mMethodsView = methodsView
        private val mRawDataView = rawDataView
        private val mButtonCalculate = NiceButton(context, "计算数据", NiceButton.ColorStyle.BLUE)
        private val mButtonRandom = NiceButton(context, "随机数据", NiceButton.ColorStyle.CYAN)
        private val mButtonClear = NiceButton(context, "清除数据", NiceButton.ColorStyle.RED)

        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val context = requireContext()
            val scrollView = ScrollView(context)
            val linearLayout = LinearLayout(context).apply {
                orientation = LinearLayout.VERTICAL
                layoutParams = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            }
            mMethodsView?.also { linearLayout.addView(it) }
            mRawDataView?.also {
                linearLayout.addView(it)
                val buttonGroup = NiceButtonGroup(context, LinearLayout.HORIZONTAL, mButtonCalculate, mButtonRandom, mButtonClear).apply {
                    layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT).apply { setMargins(dp2px(6)) }
                }
                linearLayout.addView(buttonGroup)
            }
            val space = Space(context).apply {
                layoutParams = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, dp2px(300))
            }
            linearLayout.addView(space)
            scrollView.addView(linearLayout)
            return scrollView
        }

        override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
            super.onViewCreated(view, savedInstanceState)
            if (mRawDataView != null) {
                mButtonCalculate.setOnClickListener {
                    mRawDataView.calculateAllData()
                }
                mButtonRandom.setOnClickListener {
                    mRawDataView.randomAllData()
                }
                mButtonClear.setOnClickListener {
                    val builder = CircleContentDialog.Builder(2)
                        .setTitleText("清除数据")
                        .setContentText("请问是否清除数据？")
                        .setButtonText(0, "取消")
                        .setButtonText(1, "确定")
                        .setButtonOnClickListener { dialog, _, i ->
                            if (i == 1) {
                                mRawDataView.clearAllData()
                            }
                            dialog.dismiss()
                        }
                    builder.create().show(parentFragmentManager, null)
                }
            }
        }
    }
}