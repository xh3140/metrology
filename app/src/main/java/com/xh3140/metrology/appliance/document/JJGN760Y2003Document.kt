package com.xh3140.metrology.appliance.document

object JJGN760Y2003Document : StandardDocument() {

    override val type: Type = Type.JJG

    override val number: String = "JJG 760-2003"

    override val state: State = State.ACTIVE

    override val chineseName: String = "心电监护仪"

    override val englishName: String = "Electro Cardiac Monitor"

    override val publishDate: String = "2003-11-24"

    override val executeDate: String = "2004-05-24"

    override val replaceDocuments: List<String> = listOf("JJG 760-1991")

    override val referenceDocuments: List<String> = emptyList()

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()
}