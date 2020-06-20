package com.xh3140.metrology.appliance.document

object JJGN692Y2010Document : StandardDocument("JJG 692-2010") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_FORCIBLE or LABEL_PRESSURE

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
            override val requests: List<String> = listOf("符合规程《JJG 692-2010》6.1")
        },
        object : Item("静态压力测量范围") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("至少应满足(0.0～34.7)kPa[(0～260)mmHg]。")
        },
        object : Item("静态压力示值误差") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf(
                "首次检定：±0.4kPa(±3mmHg)。",
                "后续检定和使用中检验：±0.5kPa(±4mmHg)。"
            )
        },
        object : Item("血压示值重复性(适用于示波法原理的血压计)") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("不大于0.7kPa(5mmHg)。")
        },
        object : Item("自动放气阀放气速率(适用于听诊法原理的血压计)") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("(0.3～0.4)kPa/s[(2～3)mmHg/s]。")
        },
        object : Item("气压系统气密性") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("空气泄漏导致的气压系统压力变化应不超过0.8kPa/min(6mmHg/min)。")
        }
    )

    override val itemsNotes: List<String> = listOf("注：表中“＋”表示必须检定或试验的项目，“－”为可不检定或试验的项目。")
}