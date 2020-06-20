package com.xh3140.metrology.appliance.document

object JJFN1213Y2008Document : StandardDocument("JJF 1213-2008") {

    override val type: Type = Type.JJF

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJF or LABEL_PRESSURE or LABEL_FLOW

    override val chineseName: String = "肺功能仪校准规范"

    override val englishName: String = "Calibration Specification for the Pulmonary Function Measuring Instrument"

    override val publishDate: String = "2008-09-27"

    override val executeDate: String = "2009-01-01"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "JJF 1001-1998",
        "JJF 1071-2000",
        "JJF 1059-1999",
        "Standardization of spirometry,1994 Update,American Thoracic Society. American Journal of Respiratory and Critical care Medicine. 1995，Vol152:1107-1136",
        "Standardization of spirometry,European Respiratory Journal. 2005,Vol26:319-338"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观及功能性检查") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("肺活量") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("用力肺活量") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("呼气峰值流量") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("最大分钟通气量") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("气体分析器的校准") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("气体分析器测量重复性") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        }
    )

    override val itemsNotes: List<String> = emptyList()
}