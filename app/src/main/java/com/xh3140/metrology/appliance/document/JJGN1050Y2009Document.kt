package com.xh3140.metrology.appliance.document

object JJGN1050Y2009Document : StandardDocument("JJG 1050-2009") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_IMAGING or LABEL_RADIATION or LABEL_DENSITY

    override val chineseName: String = "X、γ射线骨密度仪"

    override val englishName: String = "X、gamma-ray Densitometry for Bone Mineral Density"

    override val publishDate: String = "2009-07-10"

    override val executeDate: String = "2009-10-10"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "EJ/T 904-1994",
        "GB/T 8993-1998",
        "GB 9706.12-1997",
        "GB 3100-1993",
        "GB 3101-1993",
        "GB 3102-1993",
        "GB/T 10149-1988",
        "JJF 1035-2006"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    /*

     */
    override val items: List<Item> = listOf(
        object : Item("重复性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = emptyList()
            override val children: List<Item> = listOf(
                object : Item("单光子骨密度仪") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("在厂家规定的正常使用条件下，对四肢体模骨筒样品中BMC在(0.3～0.7)g/cm和(1.4～2.0)g/cm范围内各选一个骨筒样品进行测量,其骨横径和骨矿含量重复性不应大于3%。")
                },
                object : Item("双能X射线骨密度仪") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("在厂家规定的正常使用条件下，仪器对腰椎体模中BMD接近于1g/cm^2的椎骨样品进行测量，其重复性不应大于2%。")
                }
            )
        },
        object : Item("测量结果误差") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = emptyList()
            override val children: List<Item> = listOf(
                object : Item("单光子骨密度仪骨横径和骨矿含量") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("在厂家规定的正常使用条件下，骨密度仪对四肢体模的每个骨筒样品进行测量，其测量结果或经校正后的结果，与样品相应实际值的最大相对误差绝对值不应大于4%。")
                },
                object : Item("双能X射线骨密度仪") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("在厂家规定的正常使用条件下，仪器对腰椎体模的三个椎骨样品分别进行测量，其测量结果或经校正后的结果，与样品相应的实际值相比，其中最大相对误差绝对值不应大于10%。")
                }
            )
        },
        /*
        5.3短期稳定性
        5.4辐射防护性能
        5.4.1单光子骨密度仪

        5.4.2双能X射线骨密度仪
         */
        object : Item("单光子骨密度仪短期稳定性") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("在厂家规定的正常使用条件下，单光子骨密度仪对四肢体模骨筒样品BMC在(0.3～0.7)g/cm和(1.4～2.0)g/cm范围内各选一个骨筒样品进行测量，其骨横径和骨矿含量8h稳定性均不大于4%。")
        },
        object : Item("辐射防护性能") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = emptyList()
            override val children: List<Item> = listOf(
                object : Item("单光子骨密度仪") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("在测量架可接近表面任何一点的空气比释动能率与环境本底相比的增加值不应大于0.1μGy/h。")
                },
                object : Item("双能X射线骨密度仪") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("骨密度仪在加载条件下，以标称X射线管电压运行时，距焦点1m处，在任意100cm^2的区域，泄漏辐射空气比释动能率不应大于1.0mGy/h。")
                }
            )
        }
    )

    override val itemsNotes: List<String> = listOf("注：表中“＋”表示应检项目，“－”表示可不检项目。")
}