package com.xh3140.metrology.appliance.document

object JJGN913Y2015Document : StandardDocument("JJG 913-2015") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_PRESSURE or LABEL_FLOW

    override val chineseName: String = "浮标式氧气吸入器"

    override val englishName: String = "Buoy Type Oxygen Inhalers"

    override val publishDate: String = "2015-01-30"

    override val executeDate: String = "2015-07-30"

    override val replaceDocuments: List<String> = listOf("JJG 913-1996")

    override val referenceDocuments: List<String> = listOf(
        "JJG 52-2013",
        "JJG 257-2007",
        "JJF 1004-2004",
        "JJF 1008-2008",
        "GB/T 1226-2010",
        "YY 1107-2003"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("工作压力(墙式吸入器不适应)") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("密封性") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("安全阀排气压力") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("潮化瓶耐压强度") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("氧气压力表零位误差(墙式吸入器不适应)") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("氧气压力表示值误差(墙式吸入器不适应)") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("氧气压力表回程误差(墙式吸入器不适应)") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("氧气压力表轻敲位移(墙式吸入器不适应)") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("氧气压力表指针偏转平稳性(墙式吸入器不适应)") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("流量计的流量调节范围") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("流量计的示值误差") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        }
    )

    override val itemsNotes: String = "注：“＋”为应检项目，“－”为可不检项目。"
}