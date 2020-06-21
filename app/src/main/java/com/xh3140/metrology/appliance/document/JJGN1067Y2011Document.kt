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
            override val requests: List<String> = listOf("用自动透视模式，最大FOV(视野)和最大帧数脉冲透视或连续透视条件下，空气比释动能率不大于88mGy/min。")
        },
        object : Item("辐射输出的质(HVL)") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("X射线管电压70kV时，半价层(HVL)应不小于2.1mmAl。")
        },
        object : Item("模拟血管最小尺寸") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf(
                "在常规减影曝光条件下，影像探测器输入面与X线焦点距离(SID)调至最小，" +
                        "其模体厚度为100mm，减影后应能看到模体中直径为1mm的模拟血管，" +
                        "同时应能分辨造影剂浓度为150mg/cm^3、直径2mm的模拟血管上1/2宽度的畸变模拟血管。"
            )
        },
        object : Item("空间分辨力") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf(
                "在透视条件下(最大FOV)，空间分辦力应满足出厂技术指标，并不低于以下要求。",
                "　　影像探测器类型为影像增强器：不低于12Lp/cm。",
                "　　影像探测器类型为平板探测器：应符合出厂指标。"
            )
        },
        object : Item("低对比度分辨力") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("与“模拟血管最小尺寸”所规定的条件相同，应能均匀分辦碘浓度为5.0mg/cm^3，直径2.0mm的模拟血管。")
        },
        object : Item("对比度线性") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("模体碘浓度与平均光密度值之间的相关系数应大于0.9。")
        },
        object : Item("减影性能影响") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("与“模拟血管最小尺寸”所规定的条件相同，减影过程中，加载骨骼模块后应能分辦浓度为150mg/cm^3直径2.0mm的模拟血管。")
        },
        object : Item("X射线管电压") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("DSA系统在工作范围内，其X射线管电压的相对误差应不超过±10%。")
        },
        object : Item("X射线管的焦点(夹缝法)") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("新安装的DSA辐射源，在规定的范围内，用狭缝的方法测量X射线管的有效焦点尺寸,其值符合表2的要求。")
        }
    )

    override val itemsNotes: List<String> = listOf("注：“＋”表示应检项目，“－”表示可不检项目。")
}