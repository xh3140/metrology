package com.xh3140.metrology.appliance.document

object JJFN1649Y2017Document : StandardDocument() {

    override val type: Type = Type.JJF

    override val number: String = "JJF 1649-2017"

    override val state: State = State.ACTIVE

    override val chineseName: String = "超声骨密度仪校准规范"

    override val englishName: String = "Calibration Specification for Ultrasound Bone Sonometers"

    override val publishDate: String = "2017-11-20"

    override val executeDate: String = "2018-02-20"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "JJF 1001-2011",
        "JJF 1034-2005",
        "GB 3102.7-1993",
        "GB/T 3947-1996",
        "YY 0774-2010",
        "YY/T 0939-2014"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()
}