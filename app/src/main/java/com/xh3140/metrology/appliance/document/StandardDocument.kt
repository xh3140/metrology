package com.xh3140.metrology.appliance.document

abstract class StandardDocument(val number: String) {
    // 文件类型
    abstract val type: Type

    // 文件状态
    abstract val state: State

    // 标签
    abstract val labels: Int

    // 中文名称
    abstract val chineseName: String

    // 英文名称
    abstract val englishName: String

    // 发布时间
    abstract val publishDate: String

    // 实施时间
    abstract val executeDate: String

    // 代替文件
    abstract val replaceDocuments: List<String>

    // 引用文件
    abstract val referenceDocuments: List<String>

    // 被代替文件
    abstract val supersededDocuments: List<String>

    // 采用文件
    abstract val adoptDocuments: List<String>

    // 项目树
    abstract val items: List<Item>

    // 通用技术要求
    open val generalRequests: List<Item> = emptyList()

    // 项目注释
    abstract val itemsNotes: List<String>

    //技术要求注释
    open val requestsNotes: List<String> = emptyList()

    /**
     * 静态
     */
    companion object {
        const val LABEL_ALL: Int = -1
        const val LABEL_JJG: Int = 1
        const val LABEL_JJF: Int = 2
        const val LABEL_FORCIBLE: Int = 4
        const val LABEL_TEST: Int = 8
        const val LABEL_IMAGING: Int = 16
        const val LABEL_RADIATION: Int = 32
        const val LABEL_ULTRASOUND: Int = 64
        const val LABEL_PRESSURE: Int = 128
        const val LABEL_FLOW: Int = 256
        const val LABEL_DENSITY: Int = 512
        const val LABEL_BIOELECTRICITY: Int = 1024
        const val LABEL_ANALYSIS: Int = 2048
        const val LABEL_LASER: Int = 4096
        const val LABEL_MAGNETISM: Int = 8192
        const val LABEL_HUMITURE: Int = 16384
    }

    /**
     * 标准文件类型
     * @property JJG 计量检定规程
     * @property JJF 计量校准规范
     */
    enum class Type(val text: String) { JJG("计量检定规程"), JJF("计量校准规范") }

    /**
     * 国家标准文件状态
     * @property ACTIVE 现行的
     * @property SUPERSEDED 被替代的
     */
    enum class State(val text: String) { ACTIVE("现行的"), SUPERSEDED("被替代的") }

    /**
     * 标签
     */
    enum class Label(val value: Int, val text: String) {
        ALL(LABEL_ALL, "所有"),
        JJG(LABEL_JJG, "检定"),
        JJF(LABEL_JJF, "校准"),
        FORCIBLE(LABEL_FORCIBLE, "强制检定"),
        TEST(LABEL_TEST, "测试"),
        IMAGING(LABEL_IMAGING, "影像"),
        RADIATION(LABEL_RADIATION, "辐射"),
        ULTRASOUND(LABEL_ULTRASOUND, "超声"),
        PRESSURE(LABEL_PRESSURE, "压力"),
        FLOW(LABEL_FLOW, "流量"),
        DENSITY(LABEL_DENSITY, "密度"),
        BIOELECTRICITY(LABEL_BIOELECTRICITY, "生物电"),
        ANALYSIS(LABEL_ANALYSIS, "分析"),
        LASER(LABEL_LASER, "激光"),
        MAGNETISM(LABEL_MAGNETISM, "磁性"),
        HUMITURE(LABEL_HUMITURE, "温湿度");
    }

    /**
     * 检定、校准项目
     */
    abstract class Item(val name: String) {
        // 项目类型
        abstract val type: Int

        // 技术要求
        abstract val requests: List<String>

        // 子项目
        open val children: List<Item> = emptyList()

        companion object {
            // 未表明
            const val NULL: Int = 0

            // 首次检定项目
            const val FIRST: Int = 1

            // 后续检定项目
            const val SUBSEQUENT: Int = 2

            // 使用中检查项目
            const val USING: Int = 4

            // 校准项目
            const val CALIBRATION: Int = 8
        }
    }

    /**
     * 在线阅读网址
     */
    fun getOnLineReadingUrl(): String {
        return "http://jjg.spc.org.cn/resmea/standard/${number.replace(" ", "%2520")}/?"
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + number.hashCode()
        result = 31 * result + state.hashCode()
        result = 31 * result + chineseName.hashCode()
        result = 31 * result + englishName.hashCode()
        result = 31 * result + publishDate.hashCode()
        result = 31 * result + executeDate.hashCode()
        result = 31 * result + replaceDocuments.hashCode()
        result = 31 * result + referenceDocuments.hashCode()
        result = 31 * result + supersededDocuments.hashCode()
        result = 31 * result + adoptDocuments.hashCode()
        return result
    }

    override operator fun equals(other: Any?): Boolean {
        return when {
            other === this -> true
            other !is StandardDocument -> false
            else -> type == other.type
                    && number == other.number
                    && state == other.state
                    && chineseName == other.chineseName
                    && englishName == other.englishName
                    && publishDate == other.publishDate
                    && executeDate == other.executeDate
                    && replaceDocuments == other.replaceDocuments
                    && referenceDocuments == other.referenceDocuments
                    && supersededDocuments == other.supersededDocuments
                    && adoptDocuments == other.adoptDocuments
        }
    }
}