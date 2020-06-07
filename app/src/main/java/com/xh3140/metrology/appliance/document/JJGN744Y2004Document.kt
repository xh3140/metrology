package com.xh3140.metrology.appliance.document

object JJGN744Y2004Document : StandardDocument() {

    override val type: Type = Type.JJG

    override val number: String = "JJG 744-2004"

    override val state: State = State.ACTIVE

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
}