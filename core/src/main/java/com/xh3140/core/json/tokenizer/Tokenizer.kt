package com.xh3140.core.json.tokenizer

/**
 * @Author : xh3140
 * @Time : 2020/7/11 21:03
 * @File : Tokenizer.kt
 */

// 词法分析器
object Tokenizer {
    // 词法分析
    fun tokenize(reader: CharReader): TokenList {
        val tokens = TokenList()
        while (true) {
            val token = reader.readToken()
            tokens.append(token)
            if (token.type === Token.Type.END_JSON) {
                break
            }
        }
        return tokens
    }

    // 科学计数
    private fun Char.isExp(): Boolean {
        return this == 'e' || this == 'E'
    }

    // 数字
    private fun Char.isDigit(): Boolean {
        return this in '0'..'9'
    }

    private fun Char.isDigitNotZero(): Boolean {
        return this in '1'..'9'
    }

    // 空白
    private fun Char.isSpace(): Boolean {
        return this == ' ' || this == '\t' || this == '\r' || this == '\n'
    }

    // 转义
    private fun Char.isEscape(): Boolean {
        return this == '"' || this == '\\' || this == 'u' || this == 'r' || this == 'n' || this == 'b' || this == 't' || this == 'f'
    }

    // 16进制
    private fun Char.isHex(): Boolean {
        return this in '0'..'9' || this in 'a'..'f' || this in 'A'..'F'
    }

    private fun CharReader.readToken(): Token {
        var char: Char
        do {
            // 结束
            if (!more()) {
                return Token(Token.Type.END_JSON, "")
            }
            char = next()
        } while (char.isSpace())
        // 处理各种情况
        when (char) {
            '{' -> return Token(Token.Type.BEGIN_OBJECT, char.toString())
            '}' -> return Token(Token.Type.END_OBJECT, char.toString())
            '[' -> return Token(Token.Type.BEGIN_ARRAY, char.toString())
            ']' -> return Token(Token.Type.END_ARRAY, char.toString())
            ',' -> return Token(Token.Type.SEP_COMMA, char.toString())
            ':' -> return Token(Token.Type.SEP_COLON, char.toString())
            'n' -> return readNull()
            't' -> return readTrue()
            'f' -> return readFalse()
            '-' -> return readNumber()
            '"' -> return readString()
        }
        // 处理数字
        if (char.isDigit()) {
            return readNumber()
        }
        // 非法字符
        throw JsonParseException("Illegal character")
    }

    private fun CharReader.readNull(): Token {
        if (peek() == 'n' && next() == 'u' && next() == 'l' && next() == 'l') {
            return Token(Token.Type.NULL, "null")
        }
        throw JsonParseException("Invalid json string")
    }

    private fun CharReader.readTrue(): Token {
        if (peek() == 't' && next() == 'r' && next() == 'u' && next() == 'e') {
            return Token(Token.Type.BOOLEAN, "true")
        }
        throw JsonParseException("Invalid json string when matching true")
    }

    private fun CharReader.readFalse(): Token {
        if (peek() == 'f' && next() == 'a' && next() == 'l' && next() == 's' && next() == 'e') {
            return Token(Token.Type.BOOLEAN, "false")
        }
        throw JsonParseException("Invalid json string when matching false")
    }


    private fun CharReader.readNumber(): Token {
        var char = peek()
        val builder = StringBuilder()
        if (char == '-') {
            // 处理负数
            builder.append(char)
            char = next()
            if (char == '0') {
                builder.append(char)
                builder.append(readDecimalAndExp())
            } else if (char.isDigitNotZero()) {
                do {
                    builder.append(char)
                    char = next()
                } while (char.isDigit())
                if (char != (-1).toChar()) {
                    back()
                    builder.append(readDecimalAndExp())
                }
            } else {
                throw JsonParseException("Invalid minus number")
            }
        } else if (char == '0') {
            // 处理小数
            builder.append(char)
            builder.append(readDecimalAndExp())
        } else {
            // 可能是整数
            do {
                builder.append(char)
                char = next()
            } while (char.isDigit())
            // 可能是小数或科学计数
            if (char != (-1).toChar()) {
                back()
                builder.append(readDecimalAndExp())
            }
        }
        return Token(Token.Type.NUMBER, builder.toString())
    }

    private fun CharReader.readDecimalAndExp(): String {
        val builder = StringBuilder()
        var char = next()
        if (char == '.') {
            // 处理小数
            builder.append(char)
            char = next()
            if (!char.isDigit()) {
                throw JsonParseException("Invalid decimal")
            }
            do {
                builder.append(char)
                char = next()
            } while (char.isDigit())
            if (char.isExp()) {
                // 处理科学计数法
                builder.append(char)
                builder.append(readExp())
            } else {
                if (char != (-1).toChar()) {
                    back()
                }
            }
        } else if (char.isExp()) {
            // 处理科学计数法
            builder.append(char)
            builder.append(readExp())
        } else {
            back()
        }
        return builder.toString()
    }

    private fun CharReader.readExp(): String {
        val builder = StringBuilder()
        var char = next()
        if (char == '+' || char == '-' || char.isDigit()) {
            do {
                builder.append(char)
                char = next()
            } while (char.isDigit())
            if (char != (-1).toChar()) {
                back()
            }
        } else {
            throw JsonParseException("e or E")
        }
        return builder.toString()
    }


    private fun CharReader.readString(): Token {
        val builder = StringBuilder()
        while (true) {
            var char: Char = next()
            if (char == '\\') {
                if (!next().isEscape()) {
                    throw JsonParseException("Invalid escape character")
                }
                builder.append('\\')
                char = peek()
                builder.append(char)
                if (char == 'u') {
                    for (i in 0..3) {
                        char = next()
                        if (char.isHex()) {
                            builder.append(char)
                        } else {
                            throw JsonParseException("Invalid character")
                        }
                    }
                }
            } else if (char == '"') {
                return Token(Token.Type.STRING, builder.toString())
            } else if (char == '\r' || char == '\n') {
                throw JsonParseException("Invalid character")
            } else {
                builder.append(char)
            }
        }
    }
}