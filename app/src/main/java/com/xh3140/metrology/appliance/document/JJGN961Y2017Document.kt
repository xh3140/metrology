package com.xh3140.metrology.appliance.document

object JJGN961Y2017Document : StandardDocument("JJG 961-2017") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_IMAGING or LABEL_RADIATION

    override val chineseName: String = "医用诊断螺旋计算机断层摄影装置(CT)X射线辐射源"

    override val englishName: String = "Medical Diagnostic X-ray Radiation Source for Spiral Computed Tomography(CT)"

    override val publishDate: String = "2017-11-20"

    override val executeDate: String = "2018-05-20"

    override val replaceDocuments: List<String> = listOf("JJG 961-2001", "JJG 1026-2007")

    override val referenceDocuments: List<String> = listOf(
        "JJF 1001",
        "JJF 1035",
        "GB 9706.18-2006",
        "GB/T 10149-1988",
        "GB/T 17006.11-2015",
        "GB/T 19042.5-2006",
        "IEC 60601-2-44:2016",
        "IEC 61223-3-5:2004"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("剂量指数") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("均匀性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("噪声水平") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("图像之间的一致性") {
            override val type: Int = FIRST
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("CT值") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("层厚") {
            override val type: Int = FIRST or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("空间分辨力(率)") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("低对比度分辨力(率)") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        }
    )

    override val itemsNotes: String = "注：“＋”表示应检项目，“－”表示可不检项目。\n" +
            "　　“图像之间的一致性”是指多排螺旋CT。"
}