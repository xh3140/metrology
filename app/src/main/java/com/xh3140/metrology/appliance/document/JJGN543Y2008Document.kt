package com.xh3140.metrology.appliance.document

object JJGN543Y2008Document : StandardDocument("JJG 543-2008") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_FORCIBLE or LABEL_BIOELECTRICITY

    override val chineseName: String = "心电图机"

    override val englishName: String = "Electrocardiograph"

    override val publishDate: String = "2008-05-23"

    override val executeDate: String = "2008-11-23"

    override val replaceDocuments: List<String> = listOf("JJG 543-1996(心电图机部分)")

    override val referenceDocuments: List<String> = listOf("Electrocardiographs-Metrological characteristics methods and equipment for verification INTERNATIONAL RECOMMENDATION OIML R90 Edition 1990(E)")

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观和正常工作性检查") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf()
        },
        object : Item("定标电压(内部幅度校准器)") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("最大允许相对偏差为±5%。")
        },
        object : Item("电压测量") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("最大允许相对误差按±10(1+Ul/Uin)%计算(式中Ul为电压测量范围的最小值，即0.1mV)。")
        },
        object : Item("时间测量") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("最大允许相对误差按±10(1+Tl/Tin)%计算(式中Tl为时间间隔测量范围的最小值，等于0.06s)。")
        },
        object : Item("时标") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("最大允许相对偏差为±5%。")
        },
        object : Item("幅频特性") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("(1～60)Hz，最大允许相对偏差为+5%～-10%。")
        },
        object : Item("耐极化电压") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("加±300mV的直流极化电压，幅度最大允许相对偏差为±5%。")
        },
        object : Item("噪声") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("不大于35μV。")
        },
        object : Item("共模抑制比") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("各导联不小于2.8×10^4(89dB)。")
        },
        object : Item("灵敏度(增益)") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("最大允许相对偏差为±5%。")
        },
        object : Item("记录速度") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("最大允许相对偏差为±5%。")
        },
        object : Item("纪录滞后") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("记录系统滞后不大于0.5mm。")
        },
        object : Item("过冲") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("不大于10%。")
        },
        object : Item("时间常数") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("不小于3.2s。")
        },
        object : Item("基线宽度") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("不大于1mm。")
        },
        object : Item("基线漂移") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("60s内不大于5mm。")
        },
        object : Item("输入阻抗") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("不小于2.4MΩ。")
        }
    )

    override val itemsNotes: List<String> = listOf("注：表中“＋”表示要检定，“－”表示不检定。")
}