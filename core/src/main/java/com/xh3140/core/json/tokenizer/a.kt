package com.xh3140.core.json.tokenizer

/**
 * Created by code4wt on 17/5/19.
 */
class Parser {

//    private fun parse(tokens: TokenList): Any {
//        val token = tokens.next()
//        return when {
//            token == null -> JsonObject()
//            token.type === Token.Type.BEGIN_OBJECT -> parseJsonObject()
//            token.type === Token.Type.BEGIN_ARRAY -> parseJsonArray()
//            else -> throw JsonParseException("Parse error, invalid Token.")
//        }
//    }
//
//    private fun parseJsonObject(): JsonObject {
//        val jsonObject = JsonObject()
//        var expectToken = STRING_TOKEN or END_OBJECT_TOKEN
//        var key: String? = null
//        var value: Any? = null
//        while (tokens!!.hasMore()) {
//            val token: Token? = tokens!!.next()
//            val tokenType: TokenType = token.getTokenType()
//            val tokenValue: String = token.getValue()
//            when (tokenType) {
//                BEGIN_OBJECT -> {
//                    checkExpectToken(tokenType, expectToken)
//                    jsonObject.put(key, parseJsonObject()) // 递归解析 json object
//                    expectToken = SEP_COMMA_TOKEN or END_OBJECT_TOKEN
//                }
//                END_OBJECT -> {
//                    checkExpectToken(tokenType, expectToken)
//                    return jsonObject
//                }
//                BEGIN_ARRAY -> {
//                    checkExpectToken(tokenType, expectToken)
//                    jsonObject.put(key, parseJsonArray())
//                    expectToken = SEP_COMMA_TOKEN or END_OBJECT_TOKEN
//                }
//                NULL -> {
//                    checkExpectToken(tokenType, expectToken)
//                    jsonObject.put(key, null)
//                    expectToken = SEP_COMMA_TOKEN or END_OBJECT_TOKEN
//                }
//                NUMBER -> {
//                    checkExpectToken(tokenType, expectToken)
//                    if (tokenValue.contains(".") || tokenValue.contains("e") || tokenValue.contains("E")) {
//                        jsonObject.put(key, java.lang.Double.valueOf(tokenValue))
//                    } else {
//                        val num = java.lang.Long.valueOf(tokenValue)
//                        if (num > Int.MAX_VALUE || num < Int.MIN_VALUE) {
//                            jsonObject.put(key, num)
//                        } else {
//                            jsonObject.put(key, num.toInt())
//                        }
//                    }
//                    expectToken = SEP_COMMA_TOKEN or END_OBJECT_TOKEN
//                }
//                BOOLEAN -> {
//                    checkExpectToken(tokenType, expectToken)
//                    jsonObject.put(key, Boolean.valueOf(token.getValue()))
//                    expectToken = SEP_COMMA_TOKEN or END_OBJECT_TOKEN
//                }
//                STRING -> {
//                    checkExpectToken(tokenType, expectToken)
//                    val preToken: Token? = tokens!!.peekPrevious()
//                    /*
//                 * 在 JSON 中，字符串既可以作为键，也可作为值。
//                 * 作为键时，只期待下一个 Token 类型为 SEP_COLON。
//                 * 作为值时，期待下一个 Token 类型为 SEP_COMMA 或 END_OBJECT
//                 */if (preToken.getTokenType() === TokenType.SEP_COLON) {
//                        value = token.getValue()
//                        jsonObject.put(key, value)
//                        expectToken = SEP_COMMA_TOKEN or END_OBJECT_TOKEN
//                    } else {
//                        key = token.getValue()
//                        expectToken = SEP_COLON_TOKEN
//                    }
//                }
//                SEP_COLON -> {
//                    checkExpectToken(tokenType, expectToken)
//                    expectToken = (NULL_TOKEN or NUMBER_TOKEN or BOOLEAN_TOKEN or STRING_TOKEN
//                            or BEGIN_OBJECT_TOKEN or BEGIN_ARRAY_TOKEN)
//                }
//                SEP_COMMA -> {
//                    checkExpectToken(tokenType, expectToken)
//                    expectToken = STRING_TOKEN
//                }
//                END_DOCUMENT -> {
//                    checkExpectToken(tokenType, expectToken)
//                    return jsonObject
//                }
//                else -> throw JsonParseException("Unexpected Token.")
//            }
//        }
//        throw JsonParseException("Parse error, invalid Token.")
//    }
//
//    private fun parseJsonArray(): JsonArray {
//        var expectToken = (BEGIN_ARRAY_TOKEN or END_ARRAY_TOKEN or BEGIN_OBJECT_TOKEN or NULL_TOKEN
//                or NUMBER_TOKEN or BOOLEAN_TOKEN or STRING_TOKEN)
//        val jsonArray = JsonArray()
//        while (tokens!!.hasMore()) {
//            val token: Token? = tokens!!.next()
//            val tokenType: TokenType = token.getTokenType()
//            val tokenValue: String = token.getValue()
//            expectToken = when (tokenType) {
//                BEGIN_OBJECT -> {
//                    checkExpectToken(tokenType, expectToken)
//                    jsonArray.add(parseJsonObject())
//                    SEP_COMMA_TOKEN or END_ARRAY_TOKEN
//                }
//                BEGIN_ARRAY -> {
//                    checkExpectToken(tokenType, expectToken)
//                    jsonArray.add(parseJsonArray())
//                    SEP_COMMA_TOKEN or END_ARRAY_TOKEN
//                }
//                END_ARRAY -> {
//                    checkExpectToken(tokenType, expectToken)
//                    return jsonArray
//                }
//                NULL -> {
//                    checkExpectToken(tokenType, expectToken)
//                    jsonArray.add(null)
//                    SEP_COMMA_TOKEN or END_ARRAY_TOKEN
//                }
//                NUMBER -> {
//                    checkExpectToken(tokenType, expectToken)
//                    if (tokenValue.contains(".") || tokenValue.contains("e") || tokenValue.contains("E")) {
//                        jsonArray.add(java.lang.Double.valueOf(tokenValue))
//                    } else {
//                        val num = java.lang.Long.valueOf(tokenValue)
//                        if (num > Int.MAX_VALUE || num < Int.MIN_VALUE) {
//                            jsonArray.add(num)
//                        } else {
//                            jsonArray.add(num.toInt())
//                        }
//                    }
//                    SEP_COMMA_TOKEN or END_ARRAY_TOKEN
//                }
//                BOOLEAN -> {
//                    checkExpectToken(tokenType, expectToken)
//                    jsonArray.add(Boolean.valueOf(tokenValue))
//                    SEP_COMMA_TOKEN or END_ARRAY_TOKEN
//                }
//                STRING -> {
//                    checkExpectToken(tokenType, expectToken)
//                    jsonArray.add(tokenValue)
//                    SEP_COMMA_TOKEN or END_ARRAY_TOKEN
//                }
//                SEP_COMMA -> {
//                    checkExpectToken(tokenType, expectToken)
//                    (STRING_TOKEN or NULL_TOKEN or NUMBER_TOKEN or BOOLEAN_TOKEN
//                            or BEGIN_ARRAY_TOKEN or BEGIN_OBJECT_TOKEN)
//                }
//                END_DOCUMENT -> {
//                    checkExpectToken(tokenType, expectToken)
//                    return jsonArray
//                }
//                else -> throw JsonParseException("Unexpected Token.")
//            }
//        }
//        throw JsonParseException("Parse error, invalid Token.")
//    }
//
//    private fun checkExpectToken(tokenType: TokenType, expectToken: Int) {
//        if (tokenType.getTokenCode() and expectToken === 0) {
//            throw JsonParseException("Parse error, invalid Token.")
//        }
//    }
//
//    companion object {
//        private const val BEGIN_OBJECT_TOKEN = 1
//        private const val END_OBJECT_TOKEN = 2
//        private const val BEGIN_ARRAY_TOKEN = 4
//        private const val END_ARRAY_TOKEN = 8
//        private const val NULL_TOKEN = 16
//        private const val NUMBER_TOKEN = 32
//        private const val STRING_TOKEN = 64
//        private const val BOOLEAN_TOKEN = 128
//        private const val SEP_COLON_TOKEN = 256
//        private const val SEP_COMMA_TOKEN = 512
//    }
}