package com.xh3140.metrology.appliance.document

object JJGN589Y2008Document : StandardDocument("JJG 589-2008") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

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

    override val items: List<Item> = emptyList()
}