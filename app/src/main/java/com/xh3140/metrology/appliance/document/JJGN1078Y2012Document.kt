package com.xh3140.metrology.appliance.document

object JJGN1078Y2012Document : StandardDocument() {

    override val type: Type = Type.JJG

    override val number: String = "JJG 1078-2012"

    override val state: State = State.ACTIVE

    override val chineseName: String = "医用数字摄影(CR、DR)系统X射线辐射源"

    override val englishName: String = "X-ray Radiation Sources for Medical Computed Radiography System and Digital Radiography System"

    override val publishDate: String = "2012-06-18"

    override val executeDate: String = "2012-09-18"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "JJG 744-2004",
        "JJF 1035-2006",
        "GB 9706.3-2000",
        "GB/T 10149",
        "AAPM REPORT NO.93-射线影像成像系统验收测试和质量控制",
        "WS 76-2011",
        "GBZ 187-2007",
        "YY/T 0741-2009"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()
}