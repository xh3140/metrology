package com.xh3140.metrology.appliance.document

object JJFN1260Y2010Document : StandardDocument("JJF 1260-2010") {

    override val type: Type = Type.JJF

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJF or LABEL_HUMITURE

    override val chineseName: String = "婴儿培养箱校准规范"

    override val englishName: String = "Calibration Specification for Baby Incubator"

    override val publishDate: String = "2010-06-10"

    override val executeDate: String = "2010-09-10"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "GB 9706.1-2007",
        "GB 11243-2008",
        "JJG 535-2004",
        "JJF 1001-1998",
        "JJF 1071-2000",
        "JJF 1101-2003"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("报警功能及电气安全检查") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("温度偏差") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("温度均匀度") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("温度波动度") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("平均培养箱温度与控制温度之差") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("温度超调量") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("相对湿度偏差") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("氧分析器示值误差") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("婴儿舱内噪声") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("报警器报警噪声") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        }
    )

    override val itemsNotes: String = ""
}