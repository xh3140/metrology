package com.xh3140.metrology.appliance.document

object JJGN744Y2004Document : StandardDocument("JJG 744-2004") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_FORCIBLE or LABEL_IMAGING or LABEL_RADIATION

    override val chineseName: String = "医用诊断X射线辐射源"

    override val englishName: String = "Medical Diagnostic X-ray Radiation Source"

    override val publishDate: String = "2004-06-04"

    override val executeDate: String = "2004-12-01"

    override val replaceDocuments: List<String> = listOf("JJG 744-1997")

    override val referenceDocuments: List<String> = listOf(
        "GB 9706.12-1997",
        "GB 8279-2001",
        "GB 9706.3-2000",
        "GB 3100-1993",
        "GB 3101-1993",
        "GB 3102-1993",
        "GB/T 11755.1-1989",
        "GB/T 11755.2-1989",
        "GB/T 10149-1988",
        "SJ/T 11094-1996",
        "WS/T 189-1999",
        "YY/T 0063-2000",
        "Federal Performance Standard for Diagnostic X-ray Systems and Their Major Components；Final Rule.Department of Health and Human Services Food and Drug Administration 21 CFR Part 1020[Federal Register:May191994]Part Ⅵ"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("辐射输出的空气比释动能") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf(
                "在规定的范围内，以连续工作方式时，不带有影像增强器的医用诊断X射线机辐射输出的空气比释动能率不大于50.0mGy/min。",
                "在规定的范固内，以连续工作方式时，带有影像增强器的医用诊断X射线机辐射输出的空气比释动能率如下。",
                "　　a.用于诊断的：影像増强器输入屏尺寸大于或等于150mm时，应不大于25.0mGy/min；",
                "　　b.用于介入诊断放射学使用的：应不大于100.0mGy/min。",
                "　　上述空气比释动能率的允许误差限为10%。"
            )
        },
        object : Item("辐射输出的质") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("辐射输出的质用半价层表示，其测量结果应不小于表1的要求。")
        },
        object : Item("辐射输出的重复性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("在规定的范围内，以间歇方式工作时，以单次测量相对标准偏差表示，辐射输出的重复性应不大于10%。")
        },
        object : Item("辐射输出的线性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("在规定的范围内，以间歇方式工作时的X射线管的电压为标称电压的40%～100%之间时，辐射输出值随毫安秒范围内的变化非线性应不大于20%。")
        },
        object : Item("分辨力") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf(
                "首次检定的诊断X射线机应满足出厂的技术指标，并不低于表2的要求。",
                "　　有传递函数(MTF)表示分辨力的诊断X射线机，其最小分辨力是在20%MTF附近函数曲线所对应的分辨能力，且不允许有负偏差。",
                "　　无MTF函数表示分辨力的诊断X射线机，其最小分辨力应符合生产厂家技术要求。",
                "运行中带有影像增强器的医用诊断X射线机，其分辨力应符合表2的要求。"
            )
        },
        object : Item("辐射野与光野的一致性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("在光野平面上，没着X射线野每个主轴测量，X射线野各边与光野相应各边之间的偏差总数不应超过光野平面到焦点距离的2%。")
        },
        object : Item("X射线管的电压") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("医用诊断X射线机工作的范围内，其加载因素的任意组合，X射线管电压值的误差不超过±10%。")
        },
        object : Item("X射线管的电流") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf("医用诊断X射线机工作的范围内，其加载因素的任意组合，X射线管电流值的误差不超过±20%。")
        },
        object : Item("X射线管的焦点") {
            override val type: Int = NULL
            override val requests: List<String> = listOf(
                "新安装的医用诊断X射线机，在规定的范围内，用狭缝的方法测量X射线管的有效焦点尺寸，其值符合表3的要求。",
                "新安装及运行的医用诊断X射线机，在规定的范围内，用星卡的方法测量X射线管的有效焦点尺寸，其值符合表3的要求。"
            )
            override val children: List<Item> = listOf(
                object : Item("夹缝法") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("新安装的医用诊断X射线机,在规定的范围内，用狭缝的方法测量X射线管的有效焦点尺寸，其值符合表3的要求。")
                },
                object : Item("星卡法") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("新安装及运行的医用诊断X射线机，在规定的范围内，用星卡的方法测量X射线管的有效焦点尺寸，其值符合表3的要求。")
                }
            )
        },
        object : Item("加载时间") {
            override val type: Int = NULL
            override val requests: List<String> = listOf(
                "在医用诊断X射线机工作的范围内，其加载因素的任意组合，X射线管加载时间值的误差不超过±(10%+1ms)。",
                "在医用诊断X射线机工作的范围内，其加载因素的任意组合，X射线管电流时间积值的误差不超过±(10%+0.2mAs)。"
            )
            override val children: List<Item> = listOf(
                object : Item("时间") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("在医用诊断X射线机工作的范围内，其加载因素的任意组合，X射线管加载时间值的误差不超过±(10%+1ms)。")
                },
                object : Item("电流时间积") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("在医用诊断X射线机工作的范围内，其加载因素的任意组合，X射线管电流时间积值的误差不超过±(10%+0.2mAs)。")
                }
            )
        }
    )

    override val itemsNotes: List<String> = listOf(
        "注：“＋”表示应检项目，“－”表示可不检项目。",
        "　　“X射线管的焦点”选用“夹缝法”与“星卡法”其中之一。"
    )
}