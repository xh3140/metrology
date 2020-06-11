package com.xh3140.metrology.appliance.document

object JJGN1078Y2012Document : StandardDocument("JJG 1078-2012") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val chineseName: String = "医用数字摄影(CR、DR)系统X射线辐射源"

    override val englishName: String = "X-ray Radiation Sources for Medical Computed Radiography System and Digital Radiography System"

    override val publishDate: String = "2012-06-18"

    override val executeDate: String = "2012-09-18"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "JJG 744-2004",
        "JJF 1035-2006",
        "GB 9706.3-2000",
        "GB/T 10149",
        "AAPM REPORT NO.93-射线影像成像系统验收测试和质量控制",
        "WS 76-2011",
        "GBZ 187-2007",
        "YY/T 0741-2009"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("辐射输出的空气比释动能") {
            override val type: Int = FIRST or SUBSEQUENT or USING

            override val techRequest: String = "在规定的条件下，空气比释动能应不大于10 mGy。"
        },
        object : Item("辐射输出的重复性") {
            override val type: Int = FIRST or SUBSEQUENT or USING

            override val techRequest: String = "在常规工作条件下，辐射输出的空气比释动能重复性不大于10%。"
        },
        object : Item("辐射输出的质") {
            override val type: Int = FIRST or SUBSEQUENT

            override val techRequest: String = "管电压70kV时，半值层应不大于2.1 mmAl。"
        },
        object : Item("空间分辨力") {
            override val type: Int = FIRST or SUBSEQUENT or USING

            override val techRequest: String = "首次检定的应满足出厂的技术指标。后续检定和使用中的CR、DR不小于20 Lp/cm。"
        },
        object : Item("低对比度分辨力") {
            override val type: Int = FIRST or SUBSEQUENT

            override val techRequest: String = "首次检定应满足出厂技术指标。后续检定和使用中的CR、DR不大于2.2%。"
        },
        object : Item("影像均匀性") {
            override val type: Int = FIRST

            override val techRequest: String = "影像均匀性不大于2.2%。"
        },
        object : Item("光野与照射野一致性") {
            override val type: Int = FIRST or SUBSEQUENT

            override val techRequest: String = "光野与照射野之间的偏差不应超过所选SID的0.2%。"
        },
        object : Item("有效焦点尺寸") {
            override val type: Int = FIRST

            override val techRequest: String = "有效焦点尺寸应符合出厂技术要求。"
        },
        object : Item("X射线管电压") {
            override val type: Int = FIRST or SUBSEQUENT or USING

            override val techRequest: String = "在工作范围内，X射线管电压的相对偏差不超过±10%。"
        }
    )
}