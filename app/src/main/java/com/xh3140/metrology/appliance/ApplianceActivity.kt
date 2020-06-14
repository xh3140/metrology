package com.xh3140.metrology.appliance

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.xh3140.core.extensions.startActivity
import com.xh3140.core.extensions.toast
import com.xh3140.core.widgets.dialog.CircleContentDialog
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.adapter.ApplianceAdapter
import com.xh3140.metrology.appliance.document.StandardDocument
import com.xh3140.metrology.appliance.document.StandardDocumentCache
import com.xh3140.metrology.appliance.jjg.JJGActivity
import com.xh3140.metrology.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_appliance.*

class ApplianceActivity : BaseActivity(), ApplianceAdapter.OnClickDocumentListener {
    override fun getLayoutResID(): Int = R.layout.activity_appliance

    override fun initData() {
        title = "计量器具"
        setActionBarBackEnabled(true)
        val applianceAdapter = ApplianceAdapter(this)
        recyclerViewIndex.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = applianceAdapter
        }
        applianceAdapter.submitList(StandardDocumentCache.getDocuments())
    }

    override fun onClickOnLineReading(document: StandardDocument) {
        val number = document.number.substring(4)
        if (number.isNotEmpty()) {
            val builder = CircleContentDialog.Builder(2)
                .setTitleText("在线阅读")
                .setSummaryText(document.chineseName)
                .setContentText("请问是否打开浏览器在线阅读文件？")
                .setButtonText(0, "取消")
                .setButtonText(1, "确定")
                .setButtonOnClickListener { dialog, _, i ->
                    if (i == 1) {
                        val url = Uri.parse(document.getOnLineReadingUrl())
                        val intent = Intent(Intent.ACTION_VIEW, url)
                        startActivity(intent)
                    }
                    dialog.dismiss()
                }
            builder.create().show(supportFragmentManager, null)
        }
    }

    override fun onClickDetailedInformation(document: StandardDocument) {
        val bundle = Bundle()
        bundle.putInt(StandardDocumentCache.KEY_DOCUMENT_HASH_CODE, document.hashCode())
        if (document.type == StandardDocument.Type.JJG) {
            startActivity<JJGActivity>(bundle)
        } else {
            toast("暂未启用")
        }
    }
}