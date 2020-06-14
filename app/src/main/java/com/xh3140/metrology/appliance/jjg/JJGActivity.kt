package com.xh3140.metrology.appliance.jjg

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.xh3140.core.extensions.dp2px
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.document.StandardDocument
import com.xh3140.metrology.appliance.document.StandardDocumentCache
import com.xh3140.metrology.appliance.widgets.ItemTableView
import com.xh3140.metrology.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_jjg_gz_16_2018.*

class JJGActivity : BaseActivity() {
    private var mDocument: StandardDocument? = null

    override fun getLayoutResID(): Int = R.layout.activity_appliance_jjg

    override fun initData() {
        setTitle(R.string.appliance_document_jjg)
        setActionBarBackEnabled(true)
        val hashCode = intent.extras?.getInt(StandardDocumentCache.KEY_DOCUMENT_HASH_CODE) ?: -1
        mDocument = StandardDocumentCache.findDocument(hashCode)
        // 配适器
        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> JJGItemFragment(mDocument)
                    1 -> JJGTechRequestFragment(mDocument)
                    else -> JJGMethodFragment(mDocument)
                }
            }

            override fun getItemCount(): Int {
                return 3
            }
        }
        // 选项卡
        TabLayoutMediator(tabLayout, viewPager2, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            when (position) {
                0 -> tab.setText(R.string.appliance_document_jjg_item)
                1 -> tab.setText(R.string.appliance_document_tech_request)
                else -> tab.setText(R.string.appliance_document_jjg_method)
            }
        }).attach()
    }

    /**
     * 检定项目
     */
    class JJGItemFragment(val document: StandardDocument?) : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val scrollView = ScrollView(requireContext())
            val itemTableView = ItemTableView(requireContext()).setDocument(document).rebuild()
            val margin = dp2px(4)
            val lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            lp.setMargins(margin, margin, margin, margin)
            scrollView.addView(itemTableView, lp)
            return scrollView
        }
    }

    /**
     * 检定项目
     */
    class JJGTechRequestFragment(val document: StandardDocument?) : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val scrollView = ScrollView(requireContext())
            val itemTableView = ItemTableView(requireContext()).setDocument(document).rebuild()
            val margin = dp2px(4)
            val lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            lp.setMargins(margin, margin, margin, margin)
            scrollView.addView(itemTableView, lp)
            return scrollView
        }
    }

    /**
     * 检定项目
     */
    class JJGMethodFragment(val document: StandardDocument?) : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val scrollView = ScrollView(requireContext())
            val itemTableView = ItemTableView(requireContext()).setDocument(document).rebuild()
            val margin = dp2px(4)
            val lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
            lp.setMargins(margin, margin, margin, margin)
            scrollView.addView(itemTableView, lp)
            return scrollView
        }
    }
}