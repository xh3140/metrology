package com.xh3140.metrology.appliance.document

object JJGN464Y2011Document : StandardDocument() {

    override val type: Type = Type.JJG

    override val number: String = "JJG 464-2011"

    override val state: State = State.ACTIVE

    override val chineseName: String = "半自动生化分析仪"

    override val englishName: String = "Semiautomatic Clinical Chemistry Analyzers"

    override val publishDate: String = "2011-11-30"

    override val executeDate: String = "2012-05-30"

    override val replaceDocuments: List<String> = listOf("JJG 464-1996")

    override val referenceDocuments: List<String> = emptyList()

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()
}