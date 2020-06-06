package com.xh3140.metrology.appliance.document

object JJGN543Y2008Document : StandardDocument() {

    override val type: Type = Type.JJG

    override val number: String = "JJG 543-2008"

    override val state: State = State.ACTIVE

    override val chineseName: String = "心电图机"

    override val englishName: String = "Electrocardiograph"

    override val publishDate: String = "2008-05-23"

    override val executeDate: String = "2008-11-23"

    override val replaceDocuments: List<String> = listOf("JJG 543-1996(心电图机部分)")

    override val referenceDocuments: List<String> = listOf("Electrocardiographs-Metrological characteristics methods and equipment for verification INTERNATIONAL RECOMMENDATION OIML R90 Edition 1990(E)")

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()
}