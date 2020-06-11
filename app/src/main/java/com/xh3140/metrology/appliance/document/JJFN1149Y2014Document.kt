package com.xh3140.metrology.appliance.document

object JJFN1149Y2014Document : StandardDocument("JJF 1149-2014") {

    override val type: Type = Type.JJF

    override val state: State = State.ACTIVE

    override val chineseName: String = "心脏除颤器校准规范"

    override val englishName: String = "Calibration Specification for Cardiac Defibrillators"

    override val publishDate: String = "2014-08-01"

    override val executeDate: String = "2015-02-01"

    override val replaceDocuments: List<String> = listOf("JJF 1149-2006")

    override val referenceDocuments: List<String> = listOf("JJG 760-2003", "GB 9706.8-2009")

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = emptyList()
}