package com.xh3140.metrology.appliance.document

object JJGN1163Y2019Document : StandardDocument("JJG 1163-2019") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_FORCIBLE or LABEL_BIOELECTRICITY

    override val chineseName: String = "多参数监护仪"

    override val englishName: String = "Multifunction Patient Monitoring Instruments"

    override val publishDate: String = "2019-12-31"

    override val executeDate: String = "2020-03-31"

    override val replaceDocuments: List<String> = listOf("JJG 692", "JJG 760-2003", "YY 0601-2009")

    override val referenceDocuments: List<String> = emptyList()

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观及工作正常性检查") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf()
        },
        object : Item("心电") {
            override val type: Int = NULL
            override val requests: List<String> = listOf()
            override val children: List<Item> = listOf(
                object : Item("电压测量误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("电压测量最大允许误差为±10%。")
                },
                object : Item("扫描速度误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("扫描速度最大允许误差为±10%。")
                },
                object : Item("幅频特性") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("在监护模式下，以10Hz正弦波为参考值，在(1～25)Hz频率范围内，幅度变化应在+5%～-30%。")
                },
                object : Item("心率示值误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("在(30～200)次/min范围内，最大允许误差为±(示值的5%+1)次/min。")
                }
            )
        },
        object : Item("无创血压") {
            override val type: Int = NULL
            override val requests: List<String> = listOf()
            override val children: List<Item> = listOf(
                object : Item("静态压力测量范围") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf("测量范围：(0.0～34.7)kPa[(0～260)mmHg]。")
                },
                object : Item("静态压力示值误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("最大允许误差：±0.4kPa(±3mmHg)或者±2%读数(两者取其大)。")
                },
                object : Item("血压示值重复性") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("血压示值重复性不大于0.4kPa(3mmHg)。")
                },
                object : Item("气密性") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf("压力泄漏率不大于0.8kPa/min(或6mmHg/min)。")
                }
            )
        },
        object : Item("脉搏血氧饱和度") {
            override val type: Int = NULL
            override val requests: List<String> = listOf()
            override val children: List<Item> = listOf(
                object : Item("脉搏血氧饱和度示值重复性") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("在70%～84%测量范围内，示值重复性不大于3%；在85%～100%测量范围内，示值重复性不大于2%。")
                },
                object : Item("脉率示值误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("在(30～200)次/min测量范围内，最大允许误差为±(示值的5%+1)次/min。")
                }
            )
        },
        object : Item("呼末二氧化碳") {
            override val type: Int = NULL
            override val requests: List<String> = listOf()
            override val children: List<Item> = listOf(
                object : Item("呼末二氧化碳浓度示值误差") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf("最大允许误差：±(示值的8%+0.43)kPa，或±(示值的8%+0.43%)体积百分比，或±(示值的8%+3.2)mmHg。")
                },
                object : Item("呼吸频率示值误差") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf("在(10～60)次/min测量范围内,最大允许误差为±2次/min。")
                }
            )
        }
    )

    override val itemsNotes: List<String> = listOf("注：“＋”表示必须检定的项目；“－”表示可不检定的项目。")
}