package com.xh3140.core.json

/**
 * @Author : xh3140
 * @Time : 2020/7/8 20:24
 * @File : JsonValue.kt
 */

class JsonValue(val type: Type = Type.Null) {
    // 数据
    private var mBool: Boolean = false
    private var mDouble: Double = 0.0
    private var mString: String = ""
    private var mArray: JsonArray = JsonArray()
    private var mObject: JsonObject = JsonObject()

    // 基本数据类型
    enum class Type {
        Null,           // 空值
        Boolean,           // 布尔值
        Double,         // 数值
        String,         // 字符串
        Array,          // 列表
        Object,         // 对象
        Undefined       // 未定义
    }

    // 构造函数
    constructor(value: Boolean) : this(Type.Boolean) {
        mBool = value
    }

    constructor(value: Int) : this(Type.Double) {
        mDouble = value.toDouble()
    }

    constructor(value: Double) : this(Type.Double) {
        mDouble = value
    }

    constructor(value: String) : this(Type.String) {
        mString = value
    }

    constructor(value: JsonArray) : this(Type.Array) {
        mArray = value
    }

    constructor(value: JsonObject) : this(Type.Object) {
        mObject = value
    }

    // 类型判断
    fun isNull() = type == Type.Null
    fun isBool() = type == Type.Boolean
    fun isDouble() = type == Type.Double
    fun isString() = type == Type.String
    fun isArray() = type == Type.Array
    fun isObject() = type == Type.Object
    fun isUndefined() = type == Type.Undefined

    // 数据提取
    fun toBool(defaultValue: Boolean = false): Boolean {
        return if (type == Type.Boolean) mBool else defaultValue
    }

    fun toInt(defaultValue: Int = 0): Int {
        return if (type == Type.Double) mDouble.toInt() else defaultValue
    }

    fun toDouble(defaultValue: Double = 0.0): Double {
        return if (type == Type.Double) mDouble else defaultValue
    }

    fun toString2(): String? {
        return if (type == Type.String) mString else null
    }

    fun toString2(defaultValue: String): String {
        return if (type == Type.String) mString else defaultValue
    }

    fun toArray(): JsonArray? {
        return if (type == Type.Array) mArray else null
    }

    fun toArray(defaultValue: JsonArray): JsonArray {
        return if (type == Type.Array) mArray else defaultValue
    }

    fun toObject(): JsonObject? {
        return if (type == Type.Object) mObject else null

    }

    fun toObject(defaultValue: JsonObject): JsonObject {
        return if (type == Type.Object) mObject else defaultValue
    }

    // 数据比较
    override fun equals(other: Any?): Boolean {
        return when {
            other === this -> true
            other !is JsonValue -> false
            else -> when (type) {
                Type.Boolean -> mBool == other.mBool
                Type.Double -> mDouble == other.mDouble
                Type.String -> mString == other.mString
                else -> true
            }
        }
    }

    override fun hashCode(): Int {
        var result = type.hashCode()
        result = 31 * result + mBool.hashCode()
        result = 31 * result + mDouble.hashCode()
        result = 31 * result + mString.hashCode()
        result = 31 * result + mArray.hashCode()
        result = 31 * result + mObject.hashCode()
        return result
    }

}