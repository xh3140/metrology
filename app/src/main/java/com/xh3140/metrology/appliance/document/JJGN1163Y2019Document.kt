package com.xh3140.metrology.appliance.document

object JJGN1163Y2019Document : StandardDocument("JJG 1163-2019") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

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
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("心电") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = listOf(
                object : Item("电压测量误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("扫描速度误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("幅频特性") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("心率示值误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                }
            )
        },
        object : Item("无创血压") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = listOf(
                object : Item("静态压力测量范围") {
                    override val type: Int = FIRST
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("静态压力示值误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("血压示值重复性") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("气密性") {
                    override val type: Int = FIRST
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                }
            )
        },
        object : Item("脉搏血氧饱和度") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = listOf(
                object : Item("脉搏血氧饱和度示值重复性") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("脉率示值误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                }
            )
        },
        object : Item("呼末二氧化碳") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = listOf(
                object : Item("呼末二氧化碳浓度示值误差") {
                    override val type: Int = FIRST
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("呼吸频率示值误差") {
                    override val type: Int = FIRST
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                }
            )
        }
    )

    override val itemsNotes: String = "注：“＋”表示必须检定的项目；“－”表示可不检定的项目。"
}