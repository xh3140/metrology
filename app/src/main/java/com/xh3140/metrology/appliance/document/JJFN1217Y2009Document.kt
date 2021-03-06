package com.xh3140.metrology.appliance.document

object JJFN1217Y2009Document : StandardDocument("JJF 1217-2009") {

    override val type: Type = Type.JJF

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJF

    override val chineseName: String = "高频电刀校准规范"

    override val englishName: String = "Calibratior Specification for Electrosurgical Generator"

    override val publishDate: String = "2009-05-18"

    override val executeDate: String = "2009-08-18"

    override val replaceDocuments: List<String> = listOf("JJF 1149-2006")

    override val referenceDocuments: List<String> = listOf(
        "JJF 1001-1998",
        "JJF 1071-2000",
        "JJF 1059-1999",
        "GB 9706.1-2007",
        "GB 9706.4-1999"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观及工作正常性的检查") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("高频漏电流的校准") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("输出功率设置值误差的校准") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("最大输出功率的校准") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("外壳漏电流的测量") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        }
    )

    override val itemsNotes: List<String> = emptyList()
}