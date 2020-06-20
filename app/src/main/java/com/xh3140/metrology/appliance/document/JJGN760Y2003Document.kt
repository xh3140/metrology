package com.xh3140.metrology.appliance.document

object JJGN760Y2003Document : StandardDocument("JJG 760-2003") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_FORCIBLE or LABEL_BIOELECTRICITY

    override val chineseName: String = "心电监护仪"

    override val englishName: String = "Electro Cardiac Monitor"

    override val publishDate: String = "2003-11-24"

    override val executeDate: String = "2004-05-24"

    override val replaceDocuments: List<String> = listOf("JJG 760-1991")

    override val referenceDocuments: List<String> = emptyList()

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观及工作正常性检查") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf()
        },
        object : Item("心电图显示部分") {
            override val type: Int = NULL
            override val requests: List<String> = emptyList()
            override val children: List<Item> = listOf(
                object : Item("电压测量误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf()
                },
                object : Item("极化电压引起的电压测量偏差") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf()
                },
                object : Item("噪声电平") {
                    override val type: Int = FIRST or SUBSEQUENT
                    override val requests: List<String> = listOf()
                },
                object : Item("扫描速度误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf()
                },
                object : Item("输入回路电流") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf()
                },
                object : Item("幅频特性") {
                    override val type: Int = FIRST or SUBSEQUENT
                    override val requests: List<String> = listOf()
                },
                object : Item("共模抑制比") {
                    override val type: Int = NULL
                    override val requests: List<String> = emptyList()
                    override val children: List<Item> = listOf(
                        object : Item("监护导联") {
                            override val type: Int = FIRST
                            override val requests: List<String> = listOf()
                        },
                        object : Item("标准心电导联") {
                            override val type: Int = FIRST or SUBSEQUENT
                            override val requests: List<String> = emptyList()

                        }
                    )
                }
            )
        },
        object : Item("心率显示部分") {
            override val type: Int = NULL
            override val requests: List<String> = emptyList()
            override val children: List<Item> = listOf(
                object : Item("心率显示值误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf()
                },
                object : Item("心率报警发生时间") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf()
                },
                object : Item("心率报警预置值") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = emptyList()

                }
            )
        },
        object : Item("描笔式心电图记录部分") {
            override val type: Int = NULL
            override val requests: List<String> = emptyList()
            override val children: List<Item> = listOf(
                object : Item("电压测量误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf()
                },
                object : Item("记录速度误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf()
                },
                object : Item("时间常数") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf()
                },
                object : Item("滞后") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf()
                },
                object : Item("幅频特性") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf()
                },
                object : Item("移位非线性误差") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf()
                },
                object : Item("基线漂移") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf()
                },
                object : Item("共模抑制比") {
                    override val type: Int = NULL
                    override val requests: List<String> = emptyList()
                    override val children: List<Item> = listOf(
                        object : Item("监护导联") {
                            override val type: Int = FIRST
                            override val requests: List<String> = listOf()
                        },
                        object : Item("标准心电导联") {
                            override val type: Int = FIRST or SUBSEQUENT
                            override val requests: List<String> = emptyList()
                        }
                    )
                }
            )
        }
    )

    override val itemsNotes: List<String> = listOf(
        "注：1. 表中“＋”表示应检项目；“－”表示可不检项目；",
        "　　2. 根据监护仪的类型检定相应的项目。"
    )
}