package com.xh3140.metrology.appliance.document

object JJGN639Y1998Document : StandardDocument("JJG 639-1998") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val chineseName: String = "医用超声诊断仪超声源"

    override val englishName: String = "Ultrasonic Source for Medical Ultrasonic Diagnostic Equipment"

    override val publishDate: String = "1998-09-16"

    override val executeDate: String = "1999-03-01"

    override val replaceDocuments: List<String> = listOf("JJG 639-1990")

    override val referenceDocuments: List<String> = emptyList()

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = emptyList()
}