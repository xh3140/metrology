package com.xh3140.metrology.appliance.document

object JJGN1043Y2008Document : StandardDocument("JJG 1043-2008") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_FORCIBLE or LABEL_BIOELECTRICITY

    override val chineseName: String = "脑电图机"

    override val englishName: String = "Electroencephalographs"

    override val publishDate: String = "2008-05-23"

    override val executeDate: String = "2008-11-23"

    override val replaceDocuments: List<String> = listOf("JJG 543-1996(脑电图机部分)")

    override val referenceDocuments: List<String> = listOf("Electroencephalographs-Metrological characteristics Methods and equipment for verification INTERNATIONAL RECOMMENDATION OIML R89 Edition 1990(E)")

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观和工作正常性检查") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf()
        },
        object : Item("定标电压(内部幅度校准器)") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf()
        },
        object : Item("电压测量") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf()
        },
        object : Item("灵敏度") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf()
        },
        object : Item("时间间隔") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf()
        },
        object : Item("记录速度") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf()
        },
        object : Item("记录滞后") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf()
        },
        object : Item("时标") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf()
        },
        object : Item("过冲") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf()
        },
        object : Item("时间常数") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf()
        },
        object : Item("幅频特性") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf()
        },
        object : Item("滤波器") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf()
        },
        object : Item("基线宽度") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf()
        },
        object : Item("基线漂移") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf()
        },
        object : Item("噪声") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf()
        },
        object : Item("共模抑制比") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf()
        },
        object : Item("耐极化电压") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf()
        },
        object : Item("输入阻抗") {
            override val type: Int = FIRST
            override val requests: List<String> = emptyList()
        }
    )

    override val itemsNotes: List<String> = listOf("注：表中“＋”表示要检定，“－”表示不检定。")
}