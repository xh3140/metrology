package com.xh3140.core.base


/**
 * 一维常量数据类
 */
data class CD1D<T>(val value: T)

/**
 * 一维变量数据类
 */
data class VD1D<T>(var value: T)

/**
 * 二维常量数据类
 */
data class CD2D<T>(val value1: T, val value2: T)

/**
 * 二维变量数据类
 */
data class VD2D<T>(var value1: T, var value2: T)

/**
 * 双类型二维常量数据类
 */
data class CD2T2D<T1, T2>(val value1: T1, val value2: T2)

/**
 * 双类型二维变量数据类
 */
data class VD2T2D<T1, T2>(var value1: T1, var value2: T2)


