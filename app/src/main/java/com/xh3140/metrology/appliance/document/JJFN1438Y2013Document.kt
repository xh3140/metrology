package com.xh3140.metrology.appliance.document

object JJFN1438Y2013Document : StandardDocument("JJF 1438-2013") {

    override val type: Type = Type.JJF

    override val state: State = State.ACTIVE

    override val chineseName: String = "彩色多普勒超声诊断仪(血流测量部分)校准规范"

    override val englishName: String = "Calibration Specification for Color Doppler Ultrasound Diagnostic Equipments—Blood Flow Measurement"

    override val publishDate: String = "2013-11-28"

    override val executeDate: String = "2014-02-28"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "JJF 1001",
        "JJF 1034",
        "GB/T 15261-2008",
        "YY 0593-2005",
        "YY 0767-2009",
        "IEC 61685-2001"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = emptyList()

    override val itemsNotes: String = "注：表中“＋”表示应检项目，“－”表示可不检项目。"
}