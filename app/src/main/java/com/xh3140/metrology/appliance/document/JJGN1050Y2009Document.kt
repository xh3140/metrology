package com.xh3140.metrology.appliance.document

object JJGN1050Y2009Document : StandardDocument("JJG 1050-2009") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

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

    override val items: List<Item> = emptyList()
}