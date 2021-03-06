package com.xh3140.metrology.appliance.document

object JJFN1720Y2018Document : StandardDocument("JJF 1720-2018") {

    override val type: Type = Type.JJF

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJF or LABEL_TEST or LABEL_ANALYSIS

    override val chineseName: String = "全自动生化分析仪校准规范"

    override val englishName: String = "Calibration Specification for Automatic Chemistry Analyzers"

    override val publishDate: String = "2018-12-25"

    override val executeDate: String = "2019-03-25"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "JJG 464-2011",
        "JJF 1071-2010",
        "JJF 1059.1-2012",
        "YY/T 0654-2017",
        "OIML R135:2004(E)"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("吸光度示值误差") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("吸光度重复性") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("线性误差") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("ALT和GLU测量示值误差") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("ALT和GLU测量重复性") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        }
    )

    override val itemsNotes: List<String> = emptyList()
}