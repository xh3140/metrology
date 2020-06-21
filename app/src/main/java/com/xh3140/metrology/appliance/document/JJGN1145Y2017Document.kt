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
        object : Item("通用技术要求") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("医用乳腺X射线辐射源必须标有制造厂、型号、出厂编号、出厂日期等清晰可见的标志。")
        },
        object : Item("乳腺腺体平均剂量") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf(
                "乳腺X射线辐射源显示的乳腺腺体平均剂量与标准值(实际测量值)的误差在30%以内。",
                "注：仅适用医用数字式乳X射线辐射源。"
            )
        },
        object : Item("分辨力") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf(
                "医用乳腺X射线辐射源模拟肿块分辦力(见附录E)应能满足下列技术要求：",
                "能够分辨4个纤维束；",
                "能够分辦3组钙化点群；",
                "能够分辨3个块状物。"
            )
        },
        object : Item("输出的辐射质") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("辐射质用半值层(HVL)来表示，其测量值应符合表1的要求。")
        },
        object : Item("X射线管电压") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("医用乳腺X射线辐射源在工作范围内，其X射线管电压的相对误差不应超过±5%。")
        }
    )

    override val itemsNotes: List<String> = listOf("注：“＋”为需检定的项目，“－”为不需检定的项目。")
}