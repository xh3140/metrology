package com.xh3140.metrology.appliance.document

object JJGN589Y2008Document : StandardDocument("JJG 589-2008") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJG or LABEL_IMAGING or LABEL_RADIATION

    override val chineseName: String = "医用电子加速器辐射源"

    override val englishName: String = "Medical Electron Accelerator Radiation Source"

    override val publishDate: String = "2008-12-22"

    override val executeDate: String = "2009-06-22"

    override val replaceDocuments: List<String> = listOf("JJG 589-2001")

    override val referenceDocuments: List<String> = listOf(
        "GB 3100-1993",
        "GB 3101-1993",
        "GB 3102-1993",
        "GB/T 19046-2003",
        "GB/T 17857-1999",
        "GB 15213-1994",
        "IAEA第277号技术报告(1997年第二版)《光子和电子束的吸收剂量测定，国际使用规定》",
        "JJF 1035-2006",
        "TECHNICAL REPORTS SERIES No.381 THE USE OF PLANE PARALLEL IONIZATION CHAMBERS IN HIGH ENERGY ELECTRON AND PHOTON BEAMS An International Code of Practice for Dosimetry(剂量学国际规范第381号报告：用平板型电离室测量高能电子和光子束)"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("医用加速器X辐射源") {
            override val type: Int = NULL
            override val requests: List<String> = listOf()
            override val children: List<Item> = listOf(
                object : Item("X射线辐射质") {
                    override val type: Int = FIRST or SUBSEQUENT
                    override val requests: List<String> = listOf(
                        "在测量吸收剂量时，加速器X射线的辐射质由剂量比D20/D10或组织模体比TPR10^20确定。检定结果与实际使用的数值的偏差不应超过土3%。",
                        "在测量吸收剂量时，X辐射质应按实测得D20/D10或TPR10^20值确定。"
                    )
                },
                object : Item("X射线辐射野的均整度") {
                    override val type: Int = FIRST or SUBSEQUENT
                    override val requests: List<String> = listOf("SSD取正常治疗距离(NTD)，在X射线束轴水下10cm处垂直于射线束轴的平面上，光野为10cm×10cm，辐射野内最大吸收剂量点与均整区内(见图1)最小吸收剂量点处的吸收剂量的比值不应大于1.06。")
                },
                object : Item("X射线辐射野与光野的重合") {
                    override val type: Int = FIRST or USING
                    override val requests: List<String> = listOf("在正常治疗距离上，垂直于射线束轴平面上10cm×10cm的辐射野和相应光野在主轴上的偏差应不超过士2mm。")
                },
                object : Item("X射线辐射野的对称性") {
                    override val type: Int = FIRST or SUBSEQUENT
                    override val requests: List<String> = listOf("SSD取正常治疗距离，水模体表面光野为10cm×10cm，在射线束轴上水深10cm处垂直于射线束轴的平面上，在均整区内对称于射线束轴的任意两点吸收剂量的比值(大值比小值)不应大于1.03。")
                },
                object : Item("X射线剂量示值的重复性") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf("在规定的吸收剂量(率)测量条件下，剂量监测系统的指示值相对标准偏差应不超过0.7%。")
                },
                object : Item("X射线剂量示值的线性") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf("在规定的吸收剂量(率)测量条件下，剂量监测系统的指示值线性最大偏差应不超过±2%。")
                },
                object : Item("X射线剂量示值的误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("剂量监测系统的指示值与相应的吸收剂量测量结果的相对偏差应不超过±3%")
                })
        },
        /*

        5.2.2
        5.2.3
        5.2.4

        5.2.5
        5.2.6

         */
        object : Item("医用加速器电子束辐射源") {
            override val type: Int = NULL
            override val requests: List<String> = listOf()
            override val children: List<Item> = listOf(
                object : Item("电子束辐射质") {
                    override val type: Int = FIRST or SUBSEQUENT
                    override val requests: List<String> = listOf(
                        "加速器电子束的辐射质由其在水模表面的平均能量barE0确定。在SSD=100cm和宽束条件下，由实际测出的吸收剂量或电离量的半值深度值(分别以R50^D和R50^J表示)与表2中给出的相应值确定barE0。检定中，测量结果与实际使用的数值的偏差应不超过±3%。",
                        "首次检定时，应按照生产厂家给出辐射质的方法进行测量，检定结果与厂家给出的数值的偏差应不超过±3%。"
                    )
                },
                object : Item("电子束辐射野的均整度") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf("SSD取正常治疗距离，水模体表面光野为10cm×10cm，在电子束轴上最大吸收剂量深度处垂直于电子束轴的平面上，两个主轴上90%等剂量线与几何野投影边的距离不得大于10mm；在两个对角线上90%等剂量线与几何野投影边间的距离不得大于20mm(图2)。")
                },
                object : Item("电子束辐射野的对称性") {
                    override val type: Int = FIRST or SUBSEQUENT
                    override val requests: List<String> = listOf("SSD取正常治疗距离，水模体表面光野为10cm×10cm，在电子束轴上最大吸收剂量深度处垂直于电子束轴的平面上，90%等剂量曲线内1cm的区域内移(见图2)，对称于电子束轴的任意两点吸收剂量的比值(大值比小值)不应大于1.05。")
                },
                object : Item("电子束剂量示值重复性") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf("在规定的吸收剂量(率)测量条件下，剂量监测系统的指示值相对标准偏差应不超过0.7%。")
                },
                object : Item("电子束剂量示值的线性") {
                    override val type: Int = FIRST
                    override val requests: List<String> = listOf("在规定的吸收剂量(率)测量条件下，剂量监测系统的指示值线性最大偏差应不超过±2%")
                },
                object : Item("电子束剂量示值的误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val requests: List<String> = listOf("剂量监测系统的指示值与相应的吸收剂量实际值的相对偏差应不超过±3%。")
                }
            )
        }
    )

    override val itemsNotes: List<String> = listOf(
        "注：在后续检定和使用中检验时，规定以外的项目是否检定由用户决定。",
        "　　“＋”表示应检项目；“－”表示可不检项目。"
    )
}