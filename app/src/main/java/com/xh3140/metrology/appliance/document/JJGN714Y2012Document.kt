package com.xh3140.metrology.appliance.document

object JJGN714Y2012Document : StandardDocument() {

    override val type: Type = Type.JJG

    override val number: String = "JJG 714-2012"

    override val state: State = State.ACTIVE

    override val chineseName: String = "血细胞分析仪"

    override val englishName: String = "Blood Cell Analyzers"

    override val publishDate: String = "2012-06-18"

    override val executeDate: String = "2012-12-18"

    override val replaceDocuments: List<String> = listOf("JJG 714-1990")

    override val referenceDocuments: List<String> = listOf("YY/T 0653—2008")

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()
}