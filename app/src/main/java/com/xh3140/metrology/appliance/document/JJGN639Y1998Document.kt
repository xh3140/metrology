package com.xh3140.metrology.appliance.document

object JJGN639Y1998Document : StandardDocument("JJG 639-1998") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val chineseName: String = "医用超声诊断仪超声源"

    override val englishName: String = "Ultrasonic Source for Medical Ultrasonic Diagnostic Equipment"

    override val publishDate: String = "1998-09-16"

    override val executeDate: String = "1999-03-01"

    override val replaceDocuments: List<String> = listOf("JJG 639-1990")

    override val referenceDocuments: List<String> = emptyList()

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观及一般工作性能检查") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("输出声强") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("患者漏电流") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("探测深度") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("侧向分辨力") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("轴向分辨力") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("盲区") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("纵向几何位置示值误差") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("横向几何位置示值误差") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("囊性病灶直径误差") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("外观及一般工作性能检查") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        }
    )

    override val itemsNotes: String = "注：“／”表示项目在规程中未明确。"
}