package com.xh3140.metrology.appliance.document

object JJGN1067Y2011Document : StandardDocument("JJG 1067-2011") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val chineseName: String = "医用诊断数字减影血管造影(DSA)系统X射线辐射源"

    override val englishName: String = "Medical Diagnostic X-ray Radiation Source for Medical Digital Subtraction Angiography"

    override val publishDate: String = "2011-06-14"

    override val executeDate: String = "2011-09-14"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "IEC 61223-3-3:1996",
        "IEC 60601-2-54",
        "AAPM REPORT No.15",
        "GB 9706.23-2005",
        "JJG 744-2004",
        "YY/T 0740-2009",
        "YY/T 0608-2007"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = emptyList()
}