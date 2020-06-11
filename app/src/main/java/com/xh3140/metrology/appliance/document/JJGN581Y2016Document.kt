package com.xh3140.metrology.appliance.document

object JJGN581Y2016Document : StandardDocument("JJG 581-2016") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val chineseName: String = "医用激光源"

    override val englishName: String = "Lasers for Medicine"

    override val publishDate: String = "2016-11-30"

    override val executeDate: String = "2017-05-30"

    override val replaceDocuments: List<String> = listOf("JJG 581-1999")

    override val referenceDocuments: List<String> = listOf(
        "GB 9706.20-2000",
        "GB 11748-2005",
        "GB 12257-2000",
        "IEC 60825-1:2007",
        "YY 0307-2011"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = emptyList()
}