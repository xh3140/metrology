package com.xh3140.metrology.appliance.document

object JJGN464Y2011Document : StandardDocument("JJG 464-2011") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_TEST or LABEL_ANALYSIS

    override val chineseName: String = "半自动生化分析仪"

    override val englishName: String = "Semiautomatic Clinical Chemistry Analyzers"

    override val publishDate: String = "2011-11-30"

    override val executeDate: String = "2012-05-30"

    override val replaceDocuments: List<String> = listOf("JJG 464-1996")

    override val referenceDocuments: List<String> = emptyList()

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观检查") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf()
        },
        object : Item("绝缘电阻") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf()
        },
        object : Item("零点漂移") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf()
        },
        object : Item("杂散光") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf()
        },
        object : Item("吸光度示值误差") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf()
        },
        object : Item("吸光度重复性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf()
        },
        object : Item("线性示值误差") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf()
        },
        object : Item("总和交叉污染率") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf()
        },
        object : Item("波长示值误差和重复性") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf()
        }
    )

    override val itemsNotes: List<String> = listOf(
        "注：1 “＋”为需检项目，“－”为不需检项目。",
        "　　2 经过维修后可能对分析仪有较大的影响时，其后续检定按首次检定进行。"
    )
}