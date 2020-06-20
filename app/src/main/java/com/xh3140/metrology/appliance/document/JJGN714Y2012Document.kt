package com.xh3140.metrology.appliance.document

object JJGN714Y2012Document : StandardDocument("JJG 714-2012") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_TEST or LABEL_ANALYSIS

    override val chineseName: String = "血细胞分析仪"

    override val englishName: String = "Blood Cell Analyzers"

    override val publishDate: String = "2012-06-18"

    override val executeDate: String = "2012-12-18"

    override val replaceDocuments: List<String> = listOf("JJG 714-1990")

    override val referenceDocuments: List<String> = listOf("YY/T 0653—2008")

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("通用技术要求") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf()
        },
        object : Item("空白值") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf()
        },
        object : Item("携带污染率") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf()
        },
        object : Item("示值误差") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf()
        },
        object : Item("重复性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = emptyList()
        }
    )

    override val itemsNotes: List<String> = listOf("注：“＋”为需检定的项目，“－”为不需检定的项目。")
}