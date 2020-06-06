package com.xh3140.metrology.appliance.document

object JJGN1163Y2019Document : StandardDocument() {

    override val type: Type = Type.JJG

    override val number: String = "JJG 1163-2019"

    override val state: State = State.ACTIVE

    override val chineseName: String = "多参数监护仪"

    override val englishName: String = "Multifunction Patient Monitoring Instruments"

    override val publishDate: String = "2019-12-31"

    override val executeDate: String = "2020-03-31"

    override val replaceDocuments: List<String> = listOf("JJG 692", "JJG 760-2003", "YY 0601-2009")

    override val referenceDocuments: List<String> = emptyList()

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()
}