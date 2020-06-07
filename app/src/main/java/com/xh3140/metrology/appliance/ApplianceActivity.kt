package com.xh3140.metrology.appliance

import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.xh3140.metrology.R
import com.xh3140.metrology.appliance.adapter.ApplianceAdapter
import com.xh3140.metrology.appliance.document.*
import com.xh3140.metrology.base.ui.activity.BaseActivity
import kotlinx.android.synthetic.main.activity_appliance.*

class ApplianceActivity : BaseActivity() {
    override fun getLayoutResID(): Int = R.layout.activity_appliance

    override fun initData() {
        title = "计量器具"
        setActionBarBackEnabled(true)
        val indexAdapter = ApplianceAdapter(this)
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
        // 强检
        list.add(JJGN270Y2008Document) // 血压计和血压表
        list.add(JJGN692Y2010Document) // 无创自动测量血压计
        list.add(JJGN760Y2003Document) // 心电监护仪
        list.add(JJGN1163Y2019Document) //多参数监护仪
        list.add(JJGN543Y2008Document) // 心电图机
        list.add(JJGN1041Y2008Document) // 数字心电图机
        list.add(JJGN744Y2004Document) // 医用诊断X射线辐射源
        // 检定
        list.add(JJGN1078Y2012Document) // 医用数字摄影(CR、DR)系统X射线辐射源
        list.add(JJGN961Y2017Document) // 医用诊断螺旋计算机断层摄影装置(CT)X射线辐射源
        list.add(JJGN1067Y2011Document) // 医用诊断数字减影血管造影(DSA)系统X射线辐射源
        list.add(JJGN1145Y2017Document) // 医用乳腺X射线辐射源
        list.add(JJGN1101Y2014Document) // 医用诊断全景牙科X射线辐射源
        list.add(JJGN1050Y2009Document) // X、γ射线骨密度仪
        list.add(JJGN589Y2008Document) // 医用电子加速器辐射源
        list.add(JJGN639Y1998Document) // 医用超声诊断仪超声源
        list.add(JJGN913Y2015Document) // 浮标式氧气吸入器
        list.add(JJGN714Y2012Document) // 血细胞分析仪
        list.add(JJGN1051Y2009Document) // 电解质分析仪
        list.add(JJGN861Y2007Document) // 酶标分析仪
        list.add(JJGN464Y2011Document) // 半自动生化分析仪
        list.add(JJGN581Y2016Document) // 医用激光源
        // 校准
        list.add(JJFN1720Y2018Document) // 全自动生化分析仪校准规范
        list.add(JJFN1259Y2018Document) // 医用注射泵和输液泵校准规范
        list.add(JJFN1234Y2018Document) // 呼吸机校准规范
        list.add(JJFN1149Y2014Document) // 心脏除颤器校准规范
        list.add(JJFN1217Y2009Document) // 高频电刀校准规范
        list.add(JJFN1353Y2012Document) // 血液透析装置校准规范
        list.add(JJFN1213Y2008Document) // 肺功能仪校准规范
        list.add(JJFN1438Y2013Document) // 彩色多普勒超声诊断仪(血流测量部分)校准规范
        list.add(JJFN1649Y2017Document) // 超声骨密度仪校准规范
        return list
    }
}