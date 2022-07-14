package com.supplement

import kotlin.math.sqrt

data class Foo2(val value: Int) {
    val double get() = value * 2
}

val toplevel: Double
    inline get() = Math.PI

class InlinePropExample(var value: Int) {
    var setOnly: Int
        get() = value
        inline set(v) { value = v }

    // ERROR: 컴파일 에러
    /*val backing: Int = 10
        inline get() = field * 1000*/
}

inline var InlinePropExample.square: Int
    get() = value * value
    set(v) { value = sqrt(v.toDouble()).toInt() }

fun main() {
    println(toplevel)
}
