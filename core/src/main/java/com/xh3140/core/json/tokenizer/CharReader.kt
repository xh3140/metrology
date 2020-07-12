package com.xh3140.core.json.tokenizer

import java.io.IOException
import java.io.Reader

/**
 * @Author : xh3140
 * @Time : 2020/7/11 21:03
 * @File : CharReader.kt
 */

class CharReader(private val reader: Reader) {
    private val buffer: CharArray = CharArray(1024)
    private var position: Int = 0
    private var size: Int = 0

    private fun fill() {
        try {
            val readSize = reader.read(buffer)
            if (readSize != -1) {
                position = 0
                size = readSize
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }

    fun more(): Boolean {
        if (position in 0 until size) {
            return true
        }
        fill()
        return position in 0 until size
    }

    fun peek(): Char {
        return if (position - 1 in 0 until size) buffer[position - 1] else (-1).toChar()
    }

    fun next(): Char {
        return if (position in 0 until size) buffer[position++] else (-1).toChar()
    }

    fun back() {
        position = maxOf(0, --position)
    }
}
