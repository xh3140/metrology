package com.xh3140.metrology.appliance.document

object JJGN913Y2015Document : StandardDocument() {

    override val type: Type = Type.JJG

    override val number: String = "JJG 913-2015"

    override val state: State = State.ACTIVE

    override val chineseName: String = "浮标式氧气吸入器"

    override val englishName: String = "Buoy Type Oxygen Inhalers"

    override val publishDate: String = "2015-01-30"

    override val executeDate: String = "2015-07-30"

    override val replaceDocuments: List<String> = listOf("JJG 913-1996")

    override val referenceDocuments: List<String> = listOf(
        "JJG 52-2013",
        "JJG 257-2007",
        "JJF 1004-2004",
        "JJF 1008-2008",
        "GB/T 1226-2010",
        "YY 1107-2003"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()
}