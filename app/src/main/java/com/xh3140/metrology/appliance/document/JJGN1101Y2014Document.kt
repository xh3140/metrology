package com.xh3140.metrology.appliance.document

object JJGN1101Y2014Document : StandardDocument("JJG 1101-2014") {

    override val type: Type = Type.JJG

    override val state: State = State.ACTIVE

    override val chineseName: String = "医用诊断全景牙科X射线辐射源"

    override val englishName: String = "Medical Diagnostic X-ray Source for Dental Panorama"

    override val publishDate: String = "2014-08-25"

    override val executeDate: String = "2014-11-25"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "JJG 744-2004",
        "GB 9706.1-2007/IEC 60601-1:1988",
        "GB/T 19042.4-2005/IEC 61223-3-4:2000"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = emptyList()
}