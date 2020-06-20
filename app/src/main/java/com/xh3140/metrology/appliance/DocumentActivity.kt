package com.xh3140.metrology.appliance

import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ScrollView
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.android.material.tabs.TabLayoutMediator
import com.xh3140.core.widgets.dialog.CircleContentDialog
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.document.StandardDocument
import com.xh3140.metrology.appliance.document.StandardDocumentCache
import com.xh3140.metrology.appliance.widgets.ItemsView
import com.xh3140.metrology.appliance.widgets.RequestsView
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
                    0 -> ItemsFragment(mDocument)
                    1 -> RequestsFragment(mDocument)
                    else -> MethodsFragment(mDocument)
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
     * 检校项目
     */
    class ItemsFragment(val document: StandardDocument?) : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val scrollView = ScrollView(requireContext())
            val contentView = ItemsView(requireContext()).build(document)
            scrollView.addView(contentView)
            return scrollView
        }
    }

    /**
     * 技术要求
     */
    class RequestsFragment(val document: StandardDocument?) : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            val scrollView = ScrollView(requireContext())
            val contentView = RequestsView(requireContext()).build(document)
            contentView.setOnLongClickRequestListener(object : RequestsView.OnLongClickRequestListener {
                override fun onLongClickRequest(request: String) {
                    val builder = CircleContentDialog.Builder(0)
                        .setContentGravity(Gravity.CENTER_VERTICAL)
                        .setContentTextSize(18f)
                        .setContentTextColor(Color.BLACK)
                        .setContentText(request)
                    builder.create().show(parentFragmentManager, null)
                }
            })
            scrollView.addView(contentView)
            return scrollView
        }
    }

    /**
     * 检校方法
     */
    class MethodsFragment(val document: StandardDocument?) : Fragment() {
        override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
            return ScrollView(requireContext())
        }
    }
}