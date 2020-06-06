package com.xh3140.metrology.appliance

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.adapter.IndexAdapter
import com.xh3140.metrology.appliance.document.*
import com.xh3140.metrology.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_index.*

class IndexActivity : BaseActivity() {
    override fun getLayoutResID(): Int = R.layout.activity_index

    override fun initData() {
        val indexAdapter = IndexAdapter(this)
        recyclerViewIndex.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
            adapter = indexAdapter
        }
        indexAdapter.submitList(initList())
    }

    private fun initList(): List<StandardDocument> {
        val list: MutableList<StandardDocument> = ArrayList()
        list.add(JJGN270Y2008Document)
        list.add(JJGN692Y2010Document)
        list.add(JJGN760Y2003Document)
        list.add(JJGN1163Y2019Document)
        list.add(JJGN543Y2008Document)
        list.add(JJGN1041Y2008Document)
        list.add(JJGN961Y2017Document)
        return list
    }
}