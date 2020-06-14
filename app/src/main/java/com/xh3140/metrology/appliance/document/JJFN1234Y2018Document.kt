package com.xh3140.metrology.appliance.document

object JJFN1234Y2018Document : StandardDocument("JJF 1234-2018") {

    override val type: Type = Type.JJF

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJF or LABEL_PRESSURE or LABEL_FLOW

    override val chineseName: String = "呼吸机校准规范"

    override val englishName: String = "Calibration Specification for Ventilators"

    override val publishDate: String = "2018-02-27"

    override val executeDate: String = "2018-08-27"

    override val replaceDocuments: List<String> = listOf("JJF 1234-2010")

    override val referenceDocuments: List<String> = listOf(
        "GB/T 8982-2009",
        "GB 9706.28-2006",
        "YY 0600.3-2007",
        "YY 0601-2009"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观及功能性检查") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("潮气量") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("呼吸频率") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("气道峰压") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("呼气末正压") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("吸气氧浓度") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        }
    )

    override val itemsNotes: String = ""
}