package com.xh3140.metrology.appliance

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.CompoundButton
import androidx.appcompat.widget.AppCompatCheckBox
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.xh3140.core.extensions.startActivity
import com.xh3140.core.widgets.dialog.CircleContentDialog
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.adapter.ApplianceAdapter
import com.xh3140.metrology.appliance.document.StandardDocument
import com.xh3140.metrology.appliance.document.StandardDocumentCache
import com.xh3140.metrology.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_appliance.*

class ApplianceActivity : BaseActivity(), CompoundButton.OnCheckedChangeListener, ApplianceAdapter.OnClickDocumentListener {

    private var mLabels: Int = StandardDocument.LABEL_NULL

    private val mCheckBoxLabelList: MutableList<AppCompatCheckBox> = ArrayList()

    private val mApplianceAdapter: ApplianceAdapter by lazy { ApplianceAdapter(this) }

    override fun getLayoutResID(): Int = R.layout.activity_appliance

    override fun initData() {
        setTitle(R.string.activity_appliance_title)
        setActionBarBackEnabled(true)
        mCheckBoxLabelList.add(checkBoxLabel1x1)
        mCheckBoxLabelList.add(checkBoxLabel1x2)
        mCheckBoxLabelList.add(checkBoxLabel1x3)
        mCheckBoxLabelList.add(checkBoxLabel1x4)
        mCheckBoxLabelList.add(checkBoxLabel2x1)
        mCheckBoxLabelList.add(checkBoxLabel2x2)
        mCheckBoxLabelList.add(checkBoxLabel2x3)
        mCheckBoxLabelList.add(checkBoxLabel2x4)
        mCheckBoxLabelList.add(checkBoxLabel3x1)
        mCheckBoxLabelList.add(checkBoxLabel3x2)
        mCheckBoxLabelList.add(checkBoxLabel3x3)
        mCheckBoxLabelList.add(checkBoxLabel3x4)
        mCheckBoxLabelList.add(checkBoxLabel4x1)
        mCheckBoxLabelList.add(checkBoxLabel4x2)
        mCheckBoxLabelList.add(checkBoxLabel4x3)
        mCheckBoxLabelList.add(checkBoxLabel4x4)
        val labels = StandardDocument.Label.values()
        checkBoxLabel1x1.text = "所有"
        for (i in 1 until minOf(labels.size, mCheckBoxLabelList.size)) {
            mCheckBoxLabelList[i].text = labels[i].text
        }
        recyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = mApplianceAdapter
        }
        mApplianceAdapter.submitList(StandardDocumentCache.getDocuments())
    }

    override fun initListener() {
        for (checkBox in mCheckBoxLabelList) {
            checkBox.setOnCheckedChangeListener(this)
        }
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        if (buttonView == checkBoxLabel1x1) {
            mLabels = 0
            mApplianceAdapter.submitList(StandardDocumentCache.getDocuments())
        } else {
            for (i in 1 until mCheckBoxLabelList.size) {
                if (buttonView == mCheckBoxLabelList[i]) {
                    val label = StandardDocument.Label.values()[i].value
                    mLabels = if (isChecked) {
                        mLabels or label
                    } else {
                        mLabels and label.inv()
                    }
                    mApplianceAdapter.submitList(StandardDocumentCache.findDocumentsByLabels(mLabels))
                }
            }
        }
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
        startActivity<DocumentActivity>(bundle)
    }


}