package com.xh3140.metrology.appliance.document

object JJFN1353Y2012Document : StandardDocument() {

    override val type: Type = Type.JJF

    override val number: String = "JJF 1353-2012"

    override val state: State = State.ACTIVE

    override val chineseName: String = "血液透析装置校准规范"

    override val englishName: String = "Calibration Specification for Hemodialysis Equipment"

    override val publishDate: String = "2012-06-18"

    override val executeDate: String = "2012-09-18"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "GB 9706.2-2003",
        "GB 13074-2009",
        "GB/T 14710-2009",
        "YY 0054-2010"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()
}