package com.xh3140.metrology.appliance.document

object JJFN1234Y2018Document : StandardDocument() {

    override val type: Type = Type.JJF

    override val number: String = "JJF 1234-2018"

    override val state: State = State.ACTIVE

    override val chineseName: String = "呼吸机校准规范"

    override val englishName: String = "Calibration Specification for Ventilators"

    override val publishDate: String = "2018-02-27"

    override val executeDate: String = "2018-08-27"

    override val replaceDocuments: List<String> = listOf("JJF 1234-2010")

    override val referenceDocuments: List<String> = listOf(
        "GB/T 8982-2009",
        "GB 9706.28-2006",
        "YY 0600.3-2007",
        "YY 0601-2009"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()
}