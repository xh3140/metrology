package com.xh3140.metrology.jjg.document

object JJG9612017Document : StandardDocument() {

    override val type: Type = Type.JJG

    override val number: String = "JJG 961-2017"

    override val state: State = State.ACTIVE

    override val chineseName: String = "医用诊断螺旋计算机断层摄影装置(CT)X射线辐射源"

    override val englishName: String =
        "Medical Diagnostic X-ray Radiation Source for Spiral Computed Tomography(CT)"

    override val publishDate: String = "2017-11-20"

    override val executeDate: String = "2018-05-20"

    override val replaceDocuments: List<String> = listOf("JJG 961-2001", "JJG 1026-2007")

    override val referenceDocuments: List<String> = listOf(
        "JJF 1001",
        "JJF 1035",
        "GB 9706.18-2006",
        "GB/T 10149-1988",
        "GB/T 17006.11-2015",
        "GB/T 19042.5-2006",
        "IEC 60601-2-44:2016",
        "IEC 61223-3-5:2004"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()
}