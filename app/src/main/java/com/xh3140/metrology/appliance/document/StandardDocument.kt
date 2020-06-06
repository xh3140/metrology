package com.xh3140.metrology.appliance.document

abstract class StandardDocument {
    // 文件类型
    abstract val type: Type

    // 文件状态
    abstract val state: State

    // 文件编号
    abstract val number: String

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
            else -> (type == other.type
                    && number == other.number
                    && state == other.state
                    && chineseName == other.chineseName
                    && englishName == other.englishName
                    && publishDate == other.publishDate
                    && executeDate == other.executeDate
                    && replaceDocuments == other.replaceDocuments
                    && referenceDocuments == other.referenceDocuments
                    && supersededDocuments == other.supersededDocuments
                    && adoptDocuments == other.adoptDocuments)
        }
    }


}