package com.xh3140.metrology.appliance.document

object JJGN1101Y2014Document : StandardDocument("JJG 1101-2014") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_IMAGING or LABEL_RADIATION

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
            override val requests: List<String> = listOf("在常规使用条件下，以连续工作方式工作时，空气比释动能率不应超过60mGy/min")
        },
        object : Item("辐射输出的重复性") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("以相对实验标准偏差表示，辐射输出的重复性应不大于10%。")
        },
        object : Item("辐射输出的质") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("辐射输出的质应满足表1的要求。")
        },
        object : Item("管电压") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("X射线管电压示值误差应不超过±10%。")
        },
        object : Item("空间分辨力") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("空间分辦力不低于20Lp/cm。")
        },
        object : Item("低对比度分辨力") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("低对比度分辦力应能分辨模体中0.5mm厚的铝模内孔径为1mm的圆孔。")
        },
        object : Item("曝光时间") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("示值误差应不超过±(10%×示值+1ms)。")
        }
    )

    override val itemsNotes: List<String> = listOf(
        "注：1 “＋”表示应检项目，“－”表示可不检项目。",
        "　　2 上述检定项目仅针对具有全景照射的牙科X射线辐射源。"
    )
}