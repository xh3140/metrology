package com.xh3140.metrology.appliance.document

object JJGN961Y2017Document : StandardDocument("JJG 961-2017") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_IMAGING or LABEL_RADIATION

    override val chineseName: String = "医用诊断螺旋计算机断层摄影装置(CT)X射线辐射源"

    override val englishName: String = "Medical Diagnostic X-ray Radiation Source for Spiral Computed Tomography(CT)"

    override val publishDate: String = "2017-11-20"

    override val executeDate: String = "2018-05-20"

    override val replaceDocuments: List<String> = listOf("JJG 961-2001", "JJG 1026-2007")

    override val referenceDocuments: List<String> = listOf(
        "JJF 1001",
        "JJF 1035",
        "GB 9706.18-2006",
        "GB/T 10149-1988",
        "GB/T 17006.11-2015",
        "GB/T 19042.5-2006",
        "IEC 60601-2-44:2016",
        "IEC 61223-3-5:2004"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("剂量指数") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf(
                "螺旋CT扫描测量时,用轴向扫描测量加权剂量指数(CTDIw)，通过容积剂量指数(CTDIvol)计算公式得到螺旋CT的剂量指数,即CTDIvol。",
                "剂量指数的准确性用容积剂量指数(CTDIvol)表示，即厂家给出的螺旋CT容积CT剂量指数(CTDIvol)与实际测量值变化范围在20%以内。"
            )
        },
        object : Item("均匀性") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf(
                "剂量指数(CTDI100(中心))不大于40mGy时，均匀性应符合以下要求。",
                "　　新安装的螺旋CT，模体中心感兴趣区域平均CT值与周边每个感兴趣区域平均CT值之差的绝对值不应超过4HU；运行的螺旋CT，模体中心感兴趣区域平均CT值与周边每个感兴趣区域平均CT值之差的绝对值不应超过5HU。",
                "　　新安装的螺旋CT扫描装置，均匀性与随机文件规定的标称值的偏差不应超过±4HU；运行的螺旋CT，均匀性与随机文件规定的标称值的偏差不应超过±5HU。"
            )
        },
        object : Item("噪声水平") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf(
                "用直径为20cm的圆柱模体，头部条件状况下，剂量指数(CTDI100(中心))不大于40mGy时，扫描层厚为10mm，噪声水平应不大于0.35%。",
                "新安装的螺旋CT扫描装置，噪声水平与随机文件规定运行条件下的标称值的偏差不应超过15%。"
            )
        },
        object : Item("图像之间的一致性") {
            override val type: Int = FIRST
            override val requests: List<String> = listOf("对于多排螺旋CT扫描装置，图像之间不同层面同一中心位置CT值的偏差应不超过±6HU。")
        },
        object : Item("CT值") {
            override val type: Int = FIRST or SUBSEQUENT
            override val requests: List<String> = listOf(
                "在头部临床典型条件时，CT值应符合下列的要求：",
                "　　空气：(-1000±30)HU；水：(0±4)HU。"
            )
        },
        object : Item("层厚") {
            override val type: Int = FIRST or USING
            override val requests: List<String> = listOf(
                "标称层厚大于2mm，实测值与标称值之差的绝对值不大于1mm；",
                "标称层厚在(1～2)mm的范围内，实测值与标称值之差的绝对值不大于标称值的50%；",
                "标称层厚小于1mm，实测值与标称值之差的绝对值不大于0.5mm。"
            )
        },
        object : Item("空间分辨力(率)") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf(
                "新安装的螺旋CT，应符合下列要求：",
                "　　规定的标准值与MTF曲线上50%和10%处的测量值之差不大于0.5Lp/cm或10%,取0.5Lp/cm和10%中较小的一个。",
                "运行的螺旋CT，应符合下列要求：",
                "　　用直径为20cm的圆柱模体，在512X512矩阵、视野(FOV)不低于20cm、头部常规标准条件下，能分辨出至少5.0Lp/cm，或一组(多于4个)1.0mm的圆孔。",
                "　　用直径为20cm的圆柱模体，在512X512矩阵、视野(FOV)不大于20cm、头部的高分辦条件下，应能分辦出至少7.5Lp/cm,或一组(多于4个)0.6mm的圆孔。"
            )
        },
        object : Item("低对比度分辨力(率)") {
            override val type: Int = FIRST or SUBSEQUENT or USING
            override val requests: List<String> = listOf(
                "新安装的螺旋CT应符合下列要求：",
                "　　应满足厂家出厂的技术指标。",
                "　　用直径为20cm的圆柱模体，在头部条件状况下，头部条件10mm或最大层厚下，剂量指数(CTDI100(中心))不超过40mGy时，1%对比度应能分辨模体中2mm的圆孔和0.3%对比度应能分辨模体中5mm的圆孔。",
                "运行的螺旋CT，应符合下列要求：",
                "　　用直径为20cm的圆柱模体，在头部条件状况下，头部条件10mm或最大层厚下，剂量指数(CTDI100(中心))不超过40mGy时，1%对比度应能分辨模体中3mm的圆孔和0.3%对比度应能分辨模体中6mm的圆孔。"
            )
        }
    )

    override val itemsNotes: List<String> = listOf(
        "注：“＋”表示应检项目，“－”表示可不检项目。",
        "　　“图像之间的一致性”是指多排螺旋CT。"
    )
}