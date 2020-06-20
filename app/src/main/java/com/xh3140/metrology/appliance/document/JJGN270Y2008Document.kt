package com.xh3140.metrology.appliance.document

object JJGN270Y2008Document : StandardDocument("JJG 270-2008") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_FORCIBLE or LABEL_PRESSURE

    override val chineseName: String = "血压计和血压表"

    override val englishName: String = "Sphygmomanometer"

    override val publishDate: String = "2008-03-25"

    override val executeDate: String = "2008-09-25"

    override val replaceDocuments: List<String> = listOf("JJG 270-1995")

    override val referenceDocuments: List<String> = emptyList()

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf()
        },
        object : Item("零位误差") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf(
                "血压计的贮汞瓶与大气相通后，汞柱读数面顶端应处于与零位刻度线相切的位置，允许误差为：-0.2kPa～0.5kPa(-1.5mmHg～3.75mmHg)。",
                "血压表的弹性敏感元件内腔与大气相通后，指针应在零位标志内。"
            )
        },
        object : Item("血压计的灵敏度") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("汞柱在快速下降中突然停顿时，其波动幅度不应小于0.3kPa(2.25mmHg)。")
        },
        object : Item("气密性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf(
                "橡皮球上的气阀旋钮旋紧时应不漏气，旋松时应不会脱落；回气阀应有止气作用。",
                "血压计、血压表在1 min内压力下降值:首次检定不应超过0.5 kPa(3.75 mmHg)，后续检定和使用中检验不应超过0.8 kPa(6 mmHg)；血压计的贮汞瓶不得漏汞,水银柱不得有翻泡现象。"
            )
        },
        object : Item("示值误差") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("血压计、血压表的示值最大允许误差均为:±0.5 kPa(±3.75 mmHg)。")
        },
        object : Item("血压表指针偏转平稳性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("血压表指针偏转时应平稳，不应有跳动和停滞现象。")
        }
    )

    override val itemsNotes: List<String> = listOf("注：表中“＋”表示应检项目，“－”表示可不检项目。")
}