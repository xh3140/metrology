package com.xh3140.metrology.appliance.document

object JJFN1353Y2012Document : StandardDocument("JJF 1353-2012") {

    override val type: Type = Type.JJF

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJF or LABEL_PRESSURE or LABEL_FLOW or LABEL_HUMITURE

    override val chineseName: String = "血液透析装置校准规范"

    override val englishName: String = "Calibration Specification for Hemodialysis Equipment"

    override val publishDate: String = "2012-06-18"

    override val executeDate: String = "2012-09-18"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "GB 9706.2-2003",
        "GB 13074-2009",
        "GB/T 14710-2009",
        "YY 0054-2010"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观及工作正常性检查") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("透析液电导率示值误差") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("透析液温度示值误差和超温报警误差") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("静(动)脉压监控示值误差和静(动)脉压监控报警误差") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("透析液压力监控示值误差和透析液压力监控报警误差") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("透析液流量监控示值误差") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("抗凝泵注入流量监控示值误差") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("透析液pH监控示值误差") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("称重计示值误差") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("脱水量示值误差") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        }
    )

    override val itemsNotes: String = ""
}