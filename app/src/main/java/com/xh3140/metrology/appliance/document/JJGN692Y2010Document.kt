package com.xh3140.metrology.appliance.document

object JJGN692Y2010Document : StandardDocument("JJG 692-2010") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val chineseName: String = "无创自动测量血压计"

    override val englishName: String = "Non-invasive Automated Sphygmomanometers"

    override val publishDate: String = "2010-05-11"

    override val executeDate: String = "2010-11-11"

    override val replaceDocuments: List<String> = listOf("JJG 692-1999")

    override val referenceDocuments: List<String> = listOf(
        "GB/T 14710-1993",
        "GB 9706.1-2007/IEC 60601-1:1988",
        "YY 0505-2005/IEC 60601-1-2:2001",
        "ISO 81060-2:2009"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观及要求") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("静态压力测量范围") {
            override val type: Int = FIRST
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("静态压力示值误差") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("血压示值重复性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("自动放气阀放气速率") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("气压系统气密性") {
            override val type: Int = FIRST
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        }
    )

    override val itemsNotes: String = "注：表中“＋”表示必须检定或试验的项目，“－”为可不检定或试验的项目。\n" +
            "　　“血压示值重复性”为示波法原理血压计检测项目；\n" +
            "　　“自动放气阀放弃速率”为听诊法原理血压计检测项目。"
}