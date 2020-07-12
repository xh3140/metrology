package com.xh3140.core.json

/**
 * @Author : xh3140
 * @Time : 2020/7/9 18:55
 * @File : JsonArray.kt
 */


class JsonArray {

    private val mList: MutableList<JsonValue> = ArrayList()

    val size: Int get() = mList.size

    operator fun contains(element: JsonValue): Boolean = mList.contains(element)
    operator fun contains(element: Boolean): Boolean = mList.contains(JsonValue(element))
    operator fun contains(element: Number): Boolean = mList.contains(JsonValue(element.toDouble()))
    operator fun contains(element: String): Boolean = mList.contains(JsonValue(element))
    operator fun contains(element: JsonArray): Boolean = mList.contains(JsonValue(element))
    operator fun contains(element: JsonObject): Boolean = mList.contains(JsonValue(element))

    fun containsAll(elements: Collection<JsonValue>): Boolean = mList.containsAll(elements)

    operator fun get(index: Int): JsonValue = mList[index]

    fun indexOf(element: JsonValue): Int = mList.indexOf(element)
    fun indexOf(element: Boolean): Int = mList.indexOf(JsonValue(element))
    fun indexOf(element: Number): Int = mList.indexOf(JsonValue(element.toDouble()))
    fun indexOf(element: String): Int = mList.indexOf(JsonValue(element))
    fun indexOf(element: JsonArray): Int = mList.indexOf(JsonValue(element))
    fun indexOf(element: JsonObject): Int = mList.indexOf(JsonValue(element))

    fun isEmpty(): Boolean = mList.isEmpty()

    fun iterator(): MutableIterator<JsonValue> = mList.iterator()

    fun lastIndexOf(element: JsonValue): Int = mList.lastIndexOf(element)
    fun lastIndexOf(element: Boolean): Int = mList.lastIndexOf(JsonValue(element))
    fun lastIndexOf(element: Number): Int = mList.lastIndexOf(JsonValue(element.toDouble()))
    fun lastIndexOf(element: String): Int = mList.lastIndexOf(JsonValue(element))
    fun lastIndexOf(element: JsonArray): Int = mList.lastIndexOf(JsonValue(element))
    fun lastIndexOf(element: JsonObject): Int = mList.lastIndexOf(JsonValue(element))

    fun add(element: JsonValue): Boolean = mList.add(element)
    fun add(element: Boolean): Boolean = mList.add(JsonValue(element))
    fun add(element: Number): Boolean = mList.add(JsonValue(element.toDouble()))
    fun add(element: String): Boolean = mList.add(JsonValue(element))
    fun add(element: JsonArray): Boolean = mList.add(JsonValue(element))
    fun add(element: JsonObject): Boolean = mList.add(JsonValue(element))

    fun add(index: Int, element: JsonValue) = mList.add(index, element)
    fun add(index: Int, element: Boolean) = mList.add(index, JsonValue(element))
    fun add(index: Int, element: Number) = mList.add(index, JsonValue(element.toDouble()))
    fun add(index: Int, element: String) = mList.add(index, JsonValue(element))
    fun add(index: Int, element: JsonArray) = mList.add(index, JsonValue(element))
    fun add(index: Int, element: JsonObject) = mList.add(index, JsonValue(element))

    fun addAll(index: Int, elements: Collection<JsonValue>): Boolean = mList.addAll(index, elements)

    fun addAll(elements: Collection<JsonValue>): Boolean = mList.addAll(elements)

    fun clear() = mList.clear()

    fun listIterator(): MutableListIterator<JsonValue> = mList.listIterator()

    fun listIterator(index: Int): MutableListIterator<JsonValue> = mList.listIterator(index)

    fun remove(element: JsonValue): Boolean = mList.remove(element)
    fun remove(element: Boolean): Boolean = mList.remove(JsonValue(element))
    fun remove(element: Number): Boolean = mList.remove(JsonValue(element.toDouble()))
    fun remove(element: String): Boolean = mList.remove(JsonValue(element))
    fun remove(element: JsonArray): Boolean = mList.remove(JsonValue(element))
    fun remove(element: JsonObject): Boolean = mList.remove(JsonValue(element))

    fun removeAll(elements: Collection<JsonValue>): Boolean = mList.removeAll(elements)

    fun removeAt(index: Int): JsonValue = mList.removeAt(index)

    fun retainAll(elements: Collection<JsonValue>): Boolean = mList.retainAll(elements)

    operator fun set(index: Int, element: JsonValue): JsonValue = mList.set(index, element)
    operator fun set(index: Int, element: Boolean): JsonValue = mList.set(index, JsonValue(element))
    operator fun set(index: Int, element: Number): JsonValue = mList.set(index, JsonValue(element.toDouble()))
    operator fun set(index: Int, element: String): JsonValue = mList.set(index, JsonValue(element))
    operator fun set(index: Int, element: JsonArray): JsonValue = mList.set(index, JsonValue(element))
    operator fun set(index: Int, element: JsonObject): JsonValue = mList.set(index, JsonValue(element))

    fun subList(fromIndex: Int, toIndex: Int): MutableList<JsonValue> = mList.subList(fromIndex, toIndex)

    fun toJson(): String {
        val json: MutableList<String> = ArrayList()
        for (value in mList) {
            when (value.type) {
                JsonValue.Type.Null -> json.add("null")
                JsonValue.Type.Boolean -> json.add("${value.toBool()}")
                JsonValue.Type.Double -> json.add("${value.toDouble()}")
                JsonValue.Type.String -> json.add("\"${value.toString2()}\"")
                JsonValue.Type.Array -> json.add(value.toArray(JsonArray()).toJson())
                JsonValue.Type.Object -> json.add(value.toObject(JsonObject()).toJson())
                JsonValue.Type.Undefined -> {
                }
            }
        }
        return "[${json.joinToString(",")}]"
    }
}