package com.xh3140.metrology.appliance.document

object JJG10412008Document : StandardDocument() {

    override val type: Type = Type.JJG

    override val number: String = "JJG 1041-2008"

    override val state: State = State.ACTIVE

    override val chineseName: String = "数字心电图机"

    override val englishName: String = "Digital Electrocardiographs"

    override val publishDate: String = "2008-04-16"

    override val executeDate: String = "2008-07-16"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = emptyList()

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()
}