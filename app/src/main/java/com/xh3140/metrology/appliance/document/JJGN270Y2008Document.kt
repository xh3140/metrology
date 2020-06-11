package com.xh3140.metrology.appliance.document

object JJGN270Y2008Document : StandardDocument("JJG 270-2008") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val chineseName: String = "血压计和血压表"

    override val englishName: String = "Sphygmomanometer"

    override val publishDate: String = "2008-03-25"

    override val executeDate: String = "2008-09-25"

    override val replaceDocuments: List<String> = listOf("JJG 270-1995")

    override val referenceDocuments: List<String> = emptyList()

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = emptyList()
}