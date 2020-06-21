package com.xh3140.metrology.appliance.document

object JJGN1041Y2008Document : StandardDocument("JJG 1041-2008") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_FORCIBLE or LABEL_BIOELECTRICITY

    override val chineseName: String = "数字心电图机"

    override val englishName: String = "Digital Electrocardiographs"

    override val publishDate: String = "2008-04-16"

    override val executeDate: String = "2008-07-16"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = emptyList()

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观及工作正常性检查") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf()
        },
        object : Item("内定标电压") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("各挡位灵敏度最大允许误差为±5%。")
        },
        object : Item("输入电压范围") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("各导联不小于(0.03～5)mV，且所记录波形无失真。")
        },
        object : Item("耐极化电压") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("施加±300mV直流电压后，各导联记录和显示的基线应无偏移。")
        },
        object : Item("加权系数误差") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("应不大于±10%。")
        },
        object : Item("内部噪声电平") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf("折合到输入端的内部噪声电平应不大于20μV(峰-峰值)。")
        },
        object : Item("波形识别能力与幅度-时间参数测量") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("应与附录A所示波形吻合，且测量值应在表A.1.1～A.1.4，A.2.1～A.2.2所述限定值范围内。")
            override val children: List<Item> = listOf(
                object : Item("电压测量误差") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf("应不超过附录A中表A.1.1～A.1.4中给定的误差限。")
                },
                object : Item("时间间隔测量误差") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf("应不超过附录A中表A.2.1、A.2.2中给定的误差限。")
                }
            )
        },
        object : Item("频率响应(幅频特性)") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("以10Hz正弦波为参考值，滤波器关闭时，在(0.5～50)Hz内随频率变化，幅度的最大允许偏差-10%～+5%；在(50～75)Hz内随频率变化，幅度的最大允许偏差-30%～+5%。")
        },
        object : Item("时间常数") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("不小于3.2s。")
        },
        object : Item("心率(HR)测量误差") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("最大允许误差为±(显示值的5%+1个字)。")
        },
        object : Item("共模抑制比") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("大于89dB。")
        }
    )

    override val itemsNotes: List<String> = listOf("注：表中“＋”表示应检项目，“－”表示可不检项目。")
}