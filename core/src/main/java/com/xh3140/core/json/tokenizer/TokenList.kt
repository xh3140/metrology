package com.xh3140.core.json.tokenizer


/**
 * @Author : xh3140
 * @Time : 2020/7/11 21:06
 * @File : TokenList.kt
 */

class TokenList {
    private var position: Int = 0
    private val tokens: MutableList<Token> = ArrayList()

    fun append(token: Token) {
        tokens.add(token)
    }

    fun midway(): Boolean {
        return position in 0 until tokens.size
    }

    fun peek(): Token? {
        return if (midway()) tokens[position] else null
    }

    fun previous(): Token? {
        return if (position - 2 in 0 until tokens.size) tokens[position - 2] else null
    }

    fun next(): Token? {
        return if (midway()) tokens[position++] else null
    }

    override fun toString(): String {
        val builder = StringBuffer()
        for ((i, t) in tokens.withIndex()) {
            builder.append(i + 1)
            builder.append("\t\t")
            builder.append(t.type.name)
            builder.append("\t\t\t")
            builder.append(t.value)
            builder.append("\n")
        }
        return builder.toString()
    }
}