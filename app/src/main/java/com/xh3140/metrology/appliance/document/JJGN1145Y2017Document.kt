package com.xh3140.metrology.appliance.document

object JJGN1145Y2017Document : StandardDocument("JJG 1145-2017") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_IMAGING or LABEL_RADIATION

    override val chineseName: String = "医用乳腺X射线辐射源"

    override val englishName: String = "Medical X-ray Radiation Sources for Mammographic Equipment"

    override val publishDate: String = "2017-11-20"

    override val executeDate: String = "2018-02-20"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "JJF 1001",
        "JJF 1035",
        "WS 522-2017",
        "IEC 61223-3-2:2007",
        "Health ＆ Consumer Protection Directorate-General:2006",
        "IAEA Human Health Reports No.4-2011"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("乳腺腺体平均剂量") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("分辨力") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("输出的辐射质") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("X射线管电压") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()

        }
    )

    override val itemsNotes: String = "注：“＋”为需检定的项目，“－”为不需检定的项目。"
}