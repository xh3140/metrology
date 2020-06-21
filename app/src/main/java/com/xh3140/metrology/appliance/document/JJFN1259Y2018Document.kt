package com.xh3140.metrology.appliance.document

object JJFN1259Y2018Document : StandardDocument("JJF 1259-2018") {

    override val type: Type = Type.JJF

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJF or LABEL_PRESSURE or LABEL_FLOW

    override val chineseName: String = "医用注射泵和输液泵校准规范"

    override val englishName: String = "Calibration Specification for Syringe Pumps and Infusion Pumps"

    override val publishDate: String = "2018-02-27"

    override val executeDate: String = "2018-08-27"

    override val replaceDocuments: List<String> = listOf("JJF 1259-2010")

    override val referenceDocuments: List<String> = listOf(
        "JJG 1098-2014",
        "JJF 1004-2004",
        "GB 9706.27",
        "GB/T 6682"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观及功能性检查") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("流量相对示值误差") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf(
                "注射泵：",
                "　　流量范围在[5,20)mL/h内，最大允许误差为±6%；",
                "　　流量范围在[20,200]mL/h内，最大允许误差为±5%；",
                "　　流量范围在(200,1000]mL/h内，最大允许误差为±6%。",
                "输液泵：",
                "　　流量范围在[5,20)mL/h内，最大允许误差为±8%；",
                "　　流量范围在[20,200]mL/h内，最大允许误差为±6%；",
                "　　流量范围在(200,1000]mL/h内，最大允许误差为±8%。"
            )
        },
        object : Item("流量示值重复性") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf("注射泵重复性为2%；输液泵重复性为3%。")
        },
        object : Item("阻塞报警误差") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf("阻塞报警设定值的最大允许误差:±13.33kPa(±100mmHg)或阻塞报警设定值的±30%,两者取大者。")
        }
    )

    override val itemsNotes: List<String> = emptyList()

    override val requestsNotes: List<String> = listOf(" 注：以上指标不用于合格性判别，似供参考。")
}