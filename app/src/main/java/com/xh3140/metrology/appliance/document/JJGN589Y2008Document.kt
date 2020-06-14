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
        object : Item("医用X加速器辐射源") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = listOf(
                object : Item("辐射质") {
                    override val type: Int = FIRST or SUBSEQUENT
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("辐射野的均整度") {
                    override val type: Int = FIRST or SUBSEQUENT
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("辐射野与光野的重合") {
                    override val type: Int = FIRST or USING
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("辐射野的对称性") {
                    override val type: Int = FIRST or SUBSEQUENT
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("剂量示值的重复性") {
                    override val type: Int = FIRST
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("剂量示值的线性") {
                    override val type: Int = FIRST
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("剂量示值的误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                })
        },
        object : Item("医用加速器电子束辐射源") {
            override val type: Int = NULL
            override val techRequest: String = ""
            override val subItems: List<Item> = listOf(
                object : Item("辐射质") {
                    override val type: Int = FIRST or SUBSEQUENT
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("辐射野均整度") {
                    override val type: Int = FIRST
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("辐射野对称性") {
                    override val type: Int = FIRST or SUBSEQUENT
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("剂量示值的重复性") {
                    override val type: Int = FIRST
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("剂量示值的线性") {
                    override val type: Int = FIRST
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                },
                object : Item("剂量示值的误差") {
                    override val type: Int = FIRST or SUBSEQUENT or USING
                    override val techRequest: String = ""
                    override val subItems: List<Item> = emptyList()
                }
            )
        }
    )

    override val itemsNotes: String = "注：在后续检定和使用中检验时，规定以外的项目是否检定由用户决定。\n" +
            "　　“＋”表示应检项目；“－”表示可不检项目。"
}