package com.supplement.v1

import kotlin.reflect.KProperty

class BarDelegate {
    operator fun getValue(num: Int?, property: KProperty<*>): Int {
        println("thisRef = $num")
        println("property.name = ${property.name}")
        return 100
    }
}
