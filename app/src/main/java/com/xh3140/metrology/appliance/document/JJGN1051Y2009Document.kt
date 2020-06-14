package com.xh3140.metrology.appliance.document

object JJGN1051Y2009Document : StandardDocument("JJG 1051-2009") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_ANALYSIS

    override val chineseName: String = "电解质分析仪"

    override val englishName: String = "Electrolyte Analyzers"

    override val publishDate: String = "2009-10-09"

    override val executeDate: String = "2010-01-09"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "JJF 1001-1998",
        "JJF 1002-1998",
        "YY/T 0589-2005",
        "GB/T 14710-1993"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观及工作正常性检查") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("重复性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("示值误差") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("稳定性") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("线性误差") {
            override val type: Int = FIRST
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("交叉污染率") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        }
    )

    override val itemsNotes: String = "注：“＋”为必检项目，“－”为非必检项目。"
}