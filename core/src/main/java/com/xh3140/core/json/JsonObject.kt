package com.xh3140.core.json


/**
 * @Author : xh3140
 * @Time : 2020/7/9 18:55
 * @File : JsonObject.kt
 */

class JsonObject {
    private val mMap: MutableMap<String, JsonValue> = LinkedHashMap()

    val size: Int get() = mMap.size

    fun containsKey(key: String): Boolean = mMap.containsKey(key)

    fun containsValue(value: JsonValue): Boolean = mMap.containsValue(value)
    fun containsValue(value: Boolean): Boolean = mMap.containsValue(JsonValue(value))
    fun containsValue(value: Number): Boolean = mMap.containsValue(JsonValue(value.toDouble()))
    fun containsValue(value: String): Boolean = mMap.containsValue(JsonValue(value))
    fun containsValue(value: JsonArray): Boolean = mMap.containsValue(JsonValue(value))
    fun containsValue(value: JsonObject): Boolean = mMap.containsValue(JsonValue(value))

    operator fun contains(value: JsonValue): Boolean = mMap.containsValue(value)
    operator fun contains(value: Boolean): Boolean = mMap.containsValue(JsonValue(value))
    operator fun contains(value: Number): Boolean = mMap.containsValue(JsonValue(value.toDouble()))
    operator fun contains(value: String): Boolean = mMap.containsValue(JsonValue(value))
    operator fun contains(value: JsonArray): Boolean = mMap.containsValue(JsonValue(value))
    operator fun contains(value: JsonObject): Boolean = mMap.containsValue(JsonValue(value))

    operator fun get(key: String): JsonValue? = mMap[key]

    fun isEmpty(): Boolean = mMap.isEmpty()

    val entries: MutableSet<MutableMap.MutableEntry<String, JsonValue>> get() = mMap.entries

    val keys: MutableSet<String> get() = mMap.keys

    val values: MutableCollection<JsonValue> get() = mMap.values

    fun clear() = mMap.clear()

    fun put(key: String, value: JsonValue): JsonValue? = mMap.put(key, value)
    fun put(key: String, value: Boolean): JsonValue? = mMap.put(key, JsonValue(value))
    fun put(key: String, value: Number): JsonValue? = mMap.put(key, JsonValue(value.toDouble()))
    fun put(key: String, value: String): JsonValue? = mMap.put(key, JsonValue(value))
    fun put(key: String, value: JsonArray): JsonValue? = mMap.put(key, JsonValue(value))
    fun put(key: String, value: JsonObject): JsonValue? = mMap.put(key, JsonValue(value))

    operator fun set(key: String, value: JsonValue): JsonValue? = mMap.put(key, value)
    operator fun set(key: String, value: Boolean): JsonValue? = mMap.put(key, JsonValue(value))
    operator fun set(key: String, value: Number): JsonValue? = mMap.put(key, JsonValue(value.toDouble()))
    operator fun set(key: String, value: String): JsonValue? = mMap.put(key, JsonValue(value))
    operator fun set(key: String, value: JsonArray): JsonValue? = mMap.put(key, JsonValue(value))
    operator fun set(key: String, value: JsonObject): JsonValue? = mMap.put(key, JsonValue(value))

    fun putAll(from: Map<out String, JsonValue>) = mMap.putAll(from)

    fun remove(key: String): JsonValue? = mMap.remove(key)

    fun toJson(): String {
        val json: MutableList<String> = ArrayList()
        for ((key, value) in mMap) {
            when (value.type) {
                JsonValue.Type.Null -> json.add("\"${key}\":null")
                JsonValue.Type.Boolean -> json.add("\"${key}\":${value.toBool()}")
                JsonValue.Type.Double -> json.add("\"${key}\":${value.toDouble()}")
                JsonValue.Type.String -> json.add("\"${key}\":\"${value.toString2()}\"")
                JsonValue.Type.Array -> json.add("\"${key}\":${value.toArray(JsonArray()).toJson()}")
                JsonValue.Type.Object -> json.add("\"${key}\":${value.toObject(JsonObject()).toJson()}")
                JsonValue.Type.Undefined -> {
                }
            }
        }
        return "{${json.joinToString(",")}}"
    }
}