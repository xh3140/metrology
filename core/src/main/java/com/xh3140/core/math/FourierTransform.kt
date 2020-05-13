package com.xh3140.core.math

import android.util.Log
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

/**
 * 傅里叶变换
 * 傅里叶级数
 * 离散傅里叶级数
 * 离散傅里叶变换 DFT
 * 快速傅里叶变换 FFT
 */
class FourierTransform {

    private fun getWTable(rows: Int, columns: Int): Array<Array<Complex>> {
        return Array(rows) { m ->
            Array(columns) { n ->
                val rad = 2 * PI * (m / rows + n / columns)
                Complex(cos(rad), -sin(rad))
            }
        }
    }

    fun dft(array: DoubleArray, size: Int): Array<Complex> {
        if (size == 0) return emptyArray()
        if (size == 1) return Array(1) { Complex.valueOf(array[0]) }
        return Array(size) { k ->
            var temp = Complex.valueOf(0)
            for (n in 0 until size) {
                val rad = -2 * PI * n * k / size
                temp += Complex(cos(rad), sin(rad)) * array[n]
            }
            temp
        }
    }

    fun dft(array: Array<Complex>, size: Int): Array<Complex> {
        if (size == 0) return emptyArray()
        if (size == 1) return Array(1) { Complex.valueOf(array[0]) }
        return Array(size) { k ->
            var temp = Complex.valueOf(0)
            for (n in 0 until size) {
                val rad = -2 * PI * n * k / size
                temp += Complex(cos(rad), sin(rad)) * array[n]
            }
            temp
        }
    }

    fun idft(array: Array<Complex>, size: Int): DoubleArray {
        if (size == 0) return doubleArrayOf()
        if (size == 1) return doubleArrayOf(array[0].real)
        return DoubleArray(size) { k ->
            var temp = Complex.valueOf(0)
            for (n in 0 until size) {
                val rad = 2 * PI * n * k / size
                temp += Complex(cos(rad), sin(rad)) * array[n]
            }
            temp.real / size
        }
    }

    fun dft2(matrix: Array<DoubleArray>, rows: Int, columns: Int): Array<Array<Complex>> {
        if (rows == 0 || columns == 0) return emptyArray()
        val f1d = Array(rows) { m ->
            dft(matrix[m], columns)
        }
        val f2d = Array(columns) { n ->
            val l = Array(rows) { m ->
                f1d[m][n]
            }
            dft(l, rows)
        }
        return f2d
    }


    companion object {
        fun test() {
            val ft = FourierTransform()
            val result = ft.dft(doubleArrayOf(1.0, 2.0, -1.0, 3.0), 4)
            for ((i, c) in result.withIndex()) {
                Log.d("LOG_D", "i=${i},c=${c}")
            }
            val iresult = ft.idft(result, 4)
            for ((i, d) in iresult.withIndex()) {
                Log.d("LOG_D", "i=${i},d=${d}")
            }
//            val matrix = arrayOf(
//                doubleArrayOf(0.0, 1.0, 0.0),
//                doubleArrayOf(0.0, 1.0, 0.0),
//                doubleArrayOf(1.0, 1.0, 1.0),
//                doubleArrayOf(1.0, 0.0, 1.0)
//            )
//            val table = ft.dft2(matrix, 4, 4)
//            for (m in table.indices) {
//                val rows = table[m]
//                for (n in rows.indices) {
//                    Log.d("LOG_D", "m=${m},n=${n},c=${rows[n]}")
//                }
//            }
        }
    }
}