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
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("零位误差") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("血压计的灵敏度") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("气密性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("示值误差") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("血压表指针偏转平稳性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        }
    )

    override val itemsNotes: String = "注：表中“＋”表示应检项目，“－”表示可不检项目。"
}