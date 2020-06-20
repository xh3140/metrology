package com.xh3140.metrology.appliance.document

object JJFN1149Y2014Document : StandardDocument("JJF 1149-2014") {

    override val type: Type = Type.JJF

    override val state: State = State.ACTIVE

    override val labels: Int = LABEL_JJF or LABEL_BIOELECTRICITY

    override val chineseName: String = "心脏除颤器校准规范"

    override val englishName: String = "Calibration Specification for Cardiac Defibrillators"

    override val publishDate: String = "2014-08-01"

    override val executeDate: String = "2015-02-01"

    override val replaceDocuments: List<String> = listOf("JJF 1149-2006")

    override val referenceDocuments: List<String> = listOf("JJG 760-2003", "GB 9706.8-2009")

    override val supersededDocuments: List<String> = emptyList()

    override val adoptDocuments: List<String> = emptyList()

    override val items: List<Item> = listOf(
        object : Item("外观、工作正常性检查") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("释放能量") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("充电或内部放电过程中心电监视器信号描记幅度的波动") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("经皮起搏脉冲频率") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("经皮起搏脉冲宽度") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("经皮起搏脉冲电流幅度") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("心电信号电压示值误差") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("心电显示扫描速度示值误差") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("幅频特性") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        },
        object : Item("心率示值误差") {
            override val type: Int = CALIBRATION
            override val requests: List<String> = listOf()
        }
    )

    override val itemsNotes: List<String> = listOf("注：以上指标不是用于合格性判别，仅供参考。")
}