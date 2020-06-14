package com.xh3140.metrology.appliance.document

object JJFN1649Y2017Document : StandardDocument("JJF 1649-2017") {

    override val type: Type = Type.JJF

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJF or LABEL_ULTRASOUND or LABEL_DENSITY

    override val chineseName: String = "超声骨密度仪校准规范"

    override val englishName: String = "Calibration Specification for Ultrasound Bone Sonometers"

    override val publishDate: String = "2017-11-20"

    override val executeDate: String = "2018-02-20"

    override val replaceDocuments: List<String> = emptyList()

    override val referenceDocuments: List<String> = listOf(
        "JJF 1001-2011",
        "JJF 1034-2005",
        "GB 3102.7-1993",
        "GB/T 3947-1996",
        "YY 0774-2010",
        "YY/T 0939-2014"
    )

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("声速") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("声速测量重复性") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        },
        object : Item("宽带超声衰减") {
            override val type: Int = CALIBRATION
            override val techRequest: String = ""
            override val subItems: List<Item> = emptyList()
        }
    )
    override val itemsNotes: String = ""
}