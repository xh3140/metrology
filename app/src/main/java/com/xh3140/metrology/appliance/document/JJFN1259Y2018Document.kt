package com.xh3140.metrology.appliance.document

object JJFN1259Y2018Document : StandardDocument("JJF 1259-2018") {

    override val type: Type = Type.JJF

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJF or LABEL_PRESSURE or LABEL_FLOW

    override val chineseName: String = "医用注射泵和输液泵校准规范"

    override val englishName: String = "Calibration Specification for Syringe Pumps and Infusion Pumps"

    override val publishDate: String = "2018-02-27"

    override val executeDate: String = "2018-08-27"

    override val replaceDocuments: List<String> = listOf("JJF 1259-2010")

    override val referenceDocuments: List<String> = listOf(
        "JJG 1098-2014",
        "JJF 1004-2004",
        "GB 9706.27",
        "GB/T 6682"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观及功能性检查") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("流量相对示值误差") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("流量示值重复性") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("阻塞报警误差") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        }
    )

    override val itemsNotes: String = ""
}