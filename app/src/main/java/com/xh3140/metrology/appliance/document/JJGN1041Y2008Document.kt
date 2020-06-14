package com.xh3140.metrology.appliance.document

object JJGN1041Y2008Document : StandardDocument("JJG 1041-2008") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_FORCIBLE or LABEL_BIOELECTRICITY

    override val chineseName: String = "数字心电图机"

    override val englishName: String = "Digital Electrocardiographs"

    override val publishDate: String = "2008-04-16"

    override val executeDate: String = "2008-07-16"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = emptyList()

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观及工作正常性检查") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("内定标电压") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("输入电压范围") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("耐极化电压") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("加权系数误差") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("内部噪声电平") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("波形识别能力与幅度-时间参数测量") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("频率响应") {
            override val type: Int = FIRST
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("时间常数") {
            override val type: Int = FIRST
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("心率(HR)测量误差") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("共模抑制比") {
            override val type: Int = FIRST
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        }
    )

    override val itemsNotes: String = "注：表中“＋”表示应检项目，“－”表示可不检项目。"
}