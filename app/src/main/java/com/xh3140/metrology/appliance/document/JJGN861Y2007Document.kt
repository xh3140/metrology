package com.xh3140.metrology.appliance.document

object JJGN861Y2007Document : StandardDocument("JJG 861-2007") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_ANALYSIS

    override val chineseName: String = "酶标分析仪"

    override val englishName: String = "ELISA Analytical Instruments"

    override val publishDate: String = "2007-11-21"

    override val executeDate: String = "2008-05-21"

    override val replaceDocuments: List<String> = listOf("JJG 861-1994")

    override val referenceDocuments: List<String> = emptyList()

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观检查") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf()
        },
        object : Item("示值稳定性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf()
        },
        object : Item("波长示值误差") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf()
        },
        object : Item("波长重复性") {
            override val type: Int = FIRST
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
        object : Item("灵敏度") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf()
        },
        object : Item("通道异常") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf()
        },
        object : Item("绝缘电阻") {
            override val type: Int = FIRST
            override val requests: List<String> = emptyList()
        }
    )

    override val itemsNotes: List<String> = listOf(
        "注：1 “＋”为需检项目，“－”为不需检项目。",
        "　　2 经过维修后可能对仪器有较大的影响时，其后续检定按首次检定进行。"
    )
}