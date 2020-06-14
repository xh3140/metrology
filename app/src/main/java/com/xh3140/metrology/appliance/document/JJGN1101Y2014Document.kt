package com.xh3140.metrology.appliance.document

object JJGN1101Y2014Document : StandardDocument("JJG 1101-2014") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val chineseName: String = "医用诊断全景牙科X射线辐射源"

    override val englishName: String = "Medical Diagnostic X-ray Source for Dental Panorama"

    override val publishDate: String = "2014-08-25"

    override val executeDate: String = "2014-11-25"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "JJG 744-2004",
        "GB 9706.1-2007/IEC 60601-1:1988",
        "GB/T 19042.4-2005/IEC 61223-3-4:2000"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("空气比释动能") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("辐射输出的重复性") {
            override val type: Int = FIRST
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("辐射输出的质") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("管电压") {
            override val type: Int = FIRST or SUBSEQUENT
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()

        },
        object : Item("空间分辨力") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()

        },
        object : Item("低对比度分辨力") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()

        },
        object : Item("曝光时间") {
            override val type: Int = FIRST
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()

        }
    )

    override val itemsNotes: String = "注：1 “＋”表示应检项目，“－”表示可不检项目。\n" +
            "　　2 上述检定项目仅针对具有全景照射的牙科X射线辐射源。"
}