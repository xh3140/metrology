package com.xh3140.metrology.jjg.document

abstract class StandardDocument {
    // 文件类型
    abstract val type: Type

    // 文件编号
    abstract val number: String

    // 文件状态
    abstract val state: State

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
     * 国家标准文件类型
     * @property JJG 国家计量检定规程
     * @property JJF 国家技术校准规范
     */
    enum class Type { JJG, JJF }

    /**
     * 国家标准文件状态
     * @property ACTIVE 现行的
     * @property SUPERSEDED 被替代的
     */
    enum class State { ACTIVE, SUPERSEDED }
}