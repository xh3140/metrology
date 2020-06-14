package com.xh3140.metrology.appliance.document

object StandardDocumentCache {
    const val KEY_DOCUMENT_HASH_CODE = "KEY_DOCUMENT_HASH_CODE"
    private val mChildList: MutableList<StandardDocument> = ArrayList()
    private val mChildTable: HashMap<Int, StandardDocument> = HashMap()

    init {
        // 强检
        postDocument(JJGN270Y2008Document) // 血压计和血压表
        postDocument(JJGN692Y2010Document) // 无创自动测量血压计
        postDocument(JJGN760Y2003Document) // 心电监护仪
        postDocument(JJGN1163Y2019Document) //多参数监护仪
        postDocument(JJGN543Y2008Document) // 心电图机
        postDocument(JJGN1041Y2008Document) // 数字心电图机
        postDocument(JJGN1043Y2008Document) // 脑电图机
        postDocument(JJGN954Y2019Document) // 数字脑电图仪
        postDocument(JJGN744Y2004Document) // 医用诊断X射线辐射源
        // 检定
        postDocument(JJGN1078Y2012Document) // 医用数字摄影(CR、DR)系统X射线辐射源
        postDocument(JJGN961Y2017Document) // 医用诊断螺旋计算机断层摄影装置(CT)X射线辐射源
        postDocument(JJGN1067Y2011Document) // 医用诊断数字减影血管造影(DSA)系统X射线辐射源
        postDocument(JJGN1145Y2017Document) // 医用乳腺X射线辐射源
        postDocument(JJGN1101Y2014Document) // 医用诊断全景牙科X射线辐射源
        postDocument(JJGN1050Y2009Document) // X、γ射线骨密度仪
        postDocument(JJGN589Y2008Document) // 医用电子加速器辐射源
        postDocument(JJGN639Y1998Document) // 医用超声诊断仪超声源
        postDocument(JJGN913Y2015Document) // 浮标式氧气吸入器
        postDocument(JJGN714Y2012Document) // 血细胞分析仪
        postDocument(JJGN1051Y2009Document) // 电解质分析仪
        postDocument(JJGN861Y2007Document) // 酶标分析仪
        postDocument(JJGN464Y2011Document) // 半自动生化分析仪
        postDocument(JJGN581Y2016Document) // 医用激光源
        // 校准
        postDocument(JJFN1720Y2018Document) // 全自动生化分析仪校准规范
        postDocument(JJFN1259Y2018Document) // 医用注射泵和输液泵校准规范
        postDocument(JJFN1234Y2018Document) // 呼吸机校准规范
        postDocument(JJFN1149Y2014Document) // 心脏除颤器校准规范
        postDocument(JJFN1217Y2009Document) // 高频电刀校准规范
        postDocument(JJFN1260Y2010Document) // 婴儿培养箱校准规范
        postDocument(JJFN1353Y2012Document) // 血液透析装置校准规范
        postDocument(JJFN1213Y2008Document) // 肺功能仪校准规范
        postDocument(JJFN1438Y2013Document) // 彩色多普勒超声诊断仪(血流测量部分)校准规范
        postDocument(JJFN1649Y2017Document) // 超声骨密度仪校准规范
    }

    private fun postDocument(document: StandardDocument) {
        mChildList.add(document)
        mChildTable[document.hashCode()] = document
    }

    fun getDocuments(): List<StandardDocument> {
        return mChildList
    }

    fun findDocument(key: Int): StandardDocument? {
        return mChildTable[key]
    }

    fun findDocumentsByLabels(labels: Int): List<StandardDocument> {
        val list: MutableList<StandardDocument> = ArrayList()
        for (document in mChildList) {
            if (labels and document.labels != 0) {
                list.add(document)
            }
        }
        return list
    }

}