package com.xh3140.metrology.appliance

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

class DocumentActivity : BaseActivity() {
    private var mDocument: StandardDocument? = null

    override fun getLayoutResID(): Int = R.layout.activity_appliance_jjg

    override fun initData() {
        val hashCode = intent.extras?.getInt(StandardDocumentCache.KEY_DOCUMENT_HASH_CODE) ?: -1
        mDocument = StandardDocumentCache.findDocument(hashCode)
        title = mDocument?.chineseName
        setActionBarBackEnabled(true)
        // 配适器
        viewPager2.adapter = object : FragmentStateAdapter(this) {
            override fun createFragment(position: Int): Fragment {
                return when (position) {
                    0 -> ItemFragment(mDocument)
                    1 -> TechRequestFragment(mDocument)
                    else -> MethodFragment(mDocument)
                }
            }

            override fun getItemCount(): Int = 3
        }
        // 选项卡
        TabLayoutMediator(tabLayout, viewPager2, TabLayoutMediator.TabConfigurationStrategy { tab, position ->
            when (mDocument?.type) {
                StandardDocument.Type.JJG -> {
                    when (position) {
                        0 -> tab.setText(R.string.appliance_document_jjg_item)
                        1 -> tab.setText(R.string.appliance_document_tech_request)
                        else -> tab.setText(R.string.appliance_document_jjg_method)
                    }
                }
                StandardDocument.Type.JJF -> {
                    when (position) {
                        0 -> tab.setText(R.string.appliance_document_jjf_item)
                        1 -> tab.setText(R.string.appliance_document_tech_request)
                        else -> tab.setText(R.string.appliance_document_jjf_method)
                    }
                }
            }
        }).attach()
    }

    /**
     * 检定、校准项目
     */
    class ItemFragment(val document: StandardDocument?) : Fragment() {
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
     * 技术要求
     */
    class TechRequestFragment(val document: StandardDocument?) : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return ScrollView(requireContext())
        }
    }

    /**
     * 检定、校准方法
     */
    class MethodFragment(val document: StandardDocument?) : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return ScrollView(requireContext())
        }
    }
}