package com.xh3140.core.json.tokenizer

object JsonParse {
//
//    fun parse(tokens: TokenList): Any {
//        val token = tokens.next()
//        return when {
//            token == null -> JsonObject()
//            token.type === Token.Type.BEGIN_OBJECT -> tokens.parseJsonObject()
//            token.type === Token.Type.BEGIN_ARRAY -> tokens.parseJsonArray()
//            else -> throw JsonParseException("Parse error, invalid Token.")
//        }
//    }
//
//    private fun TokenList.parseJsonObject(): JsonObject {
//        val jsonObject = JsonObject()
//        var expectToken = Token.Type.STRING.code or Token.Type.END_OBJECT.code
//        var key: String? = null
//        var value: Any? = null
//
//        while (midway()) {
//            val token: Token? = next()
//            val tokenType = token?.type
//            val tokenValue: String? = token?.value
//            when (tokenType) {
//                BEGIN_OBJECT -> {
//                    checkExpectToken(tokenType, expectToken)
//                    jsonObject.put(key, parseJsonObject()) // 递归解析 json object
//                    expectToken = Parser.SEP_COMMA_TOKEN or Parser.END_OBJECT_TOKEN
//                }
//                END_OBJECT -> {
//                    checkExpectToken(tokenType, expectToken)
//                    return jsonObject
//                }
//                BEGIN_ARRAY -> {
//                    checkExpectToken(tokenType, expectToken)
//                    jsonObject.put(key, parseJsonArray())
//                    expectToken = Parser.SEP_COMMA_TOKEN or Parser.END_OBJECT_TOKEN
//                }
//                NULL -> {
//                    checkExpectToken(tokenType, expectToken)
//                    jsonObject.put(key, null)
//                    expectToken = Parser.SEP_COMMA_TOKEN or Parser.END_OBJECT_TOKEN
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
//                    expectToken = Parser.SEP_COMMA_TOKEN or Parser.END_OBJECT_TOKEN
//                }
//                BOOLEAN -> {
//                    checkExpectToken(tokenType, expectToken)
//                    jsonObject.put(key, Boolean.valueOf(token.getValue()))
//                    expectToken = Parser.SEP_COMMA_TOKEN or Parser.END_OBJECT_TOKEN
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
//                        expectToken = Parser.SEP_COMMA_TOKEN or Parser.END_OBJECT_TOKEN
//                    } else {
//                        key = token.getValue()
//                        expectToken = Parser.SEP_COLON_TOKEN
//                    }
//                }
//                SEP_COLON -> {
//                    checkExpectToken(tokenType, expectToken)
//                    expectToken = (Parser.NULL_TOKEN or Parser.NUMBER_TOKEN or Parser.BOOLEAN_TOKEN or Parser.STRING_TOKEN
//                            or Parser.BEGIN_OBJECT_TOKEN or Parser.BEGIN_ARRAY_TOKEN)
//                }
//                SEP_COMMA -> {
//                    checkExpectToken(tokenType, expectToken)
//                    expectToken = Parser.STRING_TOKEN
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

}