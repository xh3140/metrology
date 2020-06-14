package com.xh3140.metrology.appliance.document

object JJGN954Y2019Document : StandardDocument("JJG 954-2019") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_FORCIBLE or LABEL_BIOELECTRICITY

    override val chineseName: String = "数字脑电图仪"

    override val englishName: String = "Digital Electroencephalographs"

    override val publishDate: String = "2019-09-27"

    override val executeDate: String = "2020-03-27"

    override val replaceDocuments: List<String> = listOf("JJG 954-2000")

    override val referenceDocuments: List<String> = listOf("JJG 1043-2008", "OIML R89-1990")

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观和工作正常性检查") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("电压测量") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("时间间隔") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("幅频特性") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("低通滤波器") {
            override val type: Int = FIRST
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("高通滤波器") {
            override val type: Int = FIRST
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("噪声电平") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("共模抑制比") {
            override val type: Int = FIRST
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("耐极化电压") {
            override val type: Int = FIRST
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        }
    )

    override val itemsNotes: String = "注：表中“＋”表示应检项目，“－”表示可不检项目。"
}