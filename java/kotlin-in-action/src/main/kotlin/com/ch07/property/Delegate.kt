package com.ch07.property

import kotlin.reflect.KProperty

class Foo {
    var p: String by Delegate()
    val a by lazy { "lazy" }
}

class Delegate {
    private var _text: String? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        //return "$thisRef, thank you for delegating '${property.name}' to me!"
        return _text ?: ""
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        //println("$value has been assigned to '${property.name}' in $thisRef.")
        _text = value
    }
}

fun main() {
    val foo = Foo()
    println(foo.p)

    foo.p = "한글"
    println(foo.p)
}
