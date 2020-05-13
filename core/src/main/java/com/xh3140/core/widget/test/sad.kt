package com.xh3140.core.widget.test

class A

class B {
    init {
        val a = A()
        a.x()
    }

    fun A.x() = 1
}

fun A.y() = 2

class C {
    init {
        val a = A()
        a.y()
    }
}