package com.xh3140.metrology.appliance.document

object JJGN692Y2010Document : StandardDocument() {

    override val type: Type = Type.JJG

    override val number: String = "JJG 692-2010"

    override val state: State = State.ACTIVE

    override val chineseName: String = "无创自动测量血压计"

    override val englishName: String = "Non-invasive Automated Sphygmomanometers"

    override val publishDate: String = "2010-05-11"

    override val executeDate: String = "2010-11-11"

    override val replaceDocuments: List<String> = listOf("JJG 692-1999")

    override val referenceDocuments: List<String> = listOf(
        "GB/T 14710-1993",
        "GB 9706.1-2007/IEC 60601-1:1988",
        "YY 0505-2005/IEC 60601-1-2:2001",
        "ISO 81060-2:2009"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()
}