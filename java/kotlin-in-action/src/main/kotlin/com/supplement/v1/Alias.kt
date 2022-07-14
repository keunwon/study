package com.supplement.v1

typealias MyHandler = (Int, String, Any) -> Unit

fun addHandler(h: MyHandler) {
    h(1, "2", 3)
}

typealias Args = Array<String>

fun printArgs(arr: Args) = println(arr.joinToString())

typealias StringKeyMap<V> = Map<String, V>

class Foo {
    class Bar {
        inner class Baz
    }
}

typealias FooBarBaz = Foo.Bar.Baz
