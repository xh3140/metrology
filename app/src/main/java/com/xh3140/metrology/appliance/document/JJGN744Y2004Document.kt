package com.xh3140.metrology.appliance.document

object JJGN744Y2004Document : StandardDocument("JJG 744-2004") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_FORCIBLE or LABEL_IMAGING or LABEL_RADIATION

    override val chineseName: String = "医用诊断X射线辐射源"

    override val englishName: String = "Medical Diagnostic X-ray Radiation Source"

    override val publishDate: String = "2004-06-04"

    override val executeDate: String = "2004-12-01"

    override val replaceDocuments: List<String> = listOf("JJG 744-1997")

    override val referenceDocuments: List<String> = listOf(
        "GB 9706.12-1997",
        "GB 8279-2001",
        "GB 9706.3-2000",
        "GB 3100-1993",
        "GB 3101-1993",
        "GB 3102-1993",
        "GB/T 11755.1-1989",
        "GB/T 11755.2-1989",
        "GB/T 10149-1988",
        "SJ/T 11094-1996",
        "WS/T 189-1999",
        "YY/T 0063-2000",
        "Federal Performance Standard for Diagnostic X-ray Systems and Their Major Components；Final Rule.Department of Health and Human Services Food and Drug Administration 21 CFR Part 1020[Federal Register:May191994]Part Ⅵ"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("辐射输出的空气比释动能") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("辐射输出的质") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("辐射输出的重复性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("辐射输出的线性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("分辨力") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("辐射野与光野的一致性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("X射线管的电压") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("X射线管的电流") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("X射线管的焦点") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = listOf(
                object : Item("夹缝法") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("星卡法") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                }
            )
        },
        object : Item("加载时间") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = listOf(
                object : Item("时间") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("电流时间积") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                }
            )
        }
    )

    override val itemsNotes: String = "注：“＋”表示应检项目，“－”表示可不检项目。\n" +
            "　　“X射线管的焦点”选用“夹缝法”与“星卡法”其中之一。"
}