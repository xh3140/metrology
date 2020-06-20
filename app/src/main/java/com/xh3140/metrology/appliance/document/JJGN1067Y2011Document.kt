package com.xh3140.metrology.appliance.document

object JJGN1067Y2011Document : StandardDocument("JJG 1067-2011") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_IMAGING or LABEL_RADIATION

    override val chineseName: String = "医用诊断数字减影血管造影(DSA)系统X射线辐射源"

    override val englishName: String = "Medical Diagnostic X-ray Radiation Source for Medical Digital Subtraction Angiography"

    override val publishDate: String = "2011-06-14"

    override val executeDate: String = "2011-09-14"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "IEC 61223-3-3:1996",
        "IEC 60601-2-54",
        "AAPM REPORT No.15",
        "GB 9706.23-2005",
        "JJG 744-2004",
        "YY/T 0740-2009",
        "YY/T 0608-2007"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("空气比释动能") {
            override val type: Int = FIRST or USING
            override val requests: List<String> = listOf()
        },
        object : Item("辐射输出的质(HVL)") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf()
        },
        object : Item("模拟血管最小尺寸") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf()
        },
        object : Item("空间分辨力") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf()
        },
        object : Item("低对比度分辨力") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf()
        },
        object : Item("对比度线性") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf()
        },
        object : Item("减影性能影响") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf()
        },
        object : Item("X射线管电压") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf()
        },
        object : Item("X射线管的焦点(夹缝法)") {
            override val type: Int = FIRST
            override val requests: List<String> = emptyList()
        }
    )

    override val itemsNotes: List<String> = listOf("注：“＋”表示应检项目，“－”表示可不检项目。")
}