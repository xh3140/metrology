package com.xh3140.metrology.appliance.document

object JJGN861Y2007Document : StandardDocument() {

    override val type: Type = Type.JJG

    override val number: String = "JJG 861-2007"

    override val state: State = State.ACTIVE

    override val chineseName: String = "酶标分析仪"

    override val englishName: String = "ELISA Analytical Instruments"

    override val publishDate: String = "2007-11-21"

    override val executeDate: String = "2008-05-21"

    override val replaceDocuments: List<String> = listOf("JJG 861-1994")

    override val referenceDocuments: List<String> = emptyList()

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()
}