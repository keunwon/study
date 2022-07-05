package com.ch09

fun <T> copyData(source: MutableList<T>, destination: MutableList<T>) {
    for (item in source) {
        destination.add(item)
    }
    source.add(destination[0])
    val list = mutableListOf<T>()
    list.add(destination[0])
}

fun <T: R, R> copyData2(source: MutableList<T>, destination: MutableList<R>) {
    for (item in source) {
        destination.add(item)
    }
}

fun <T> copyData3(source: MutableList<out T>, destination: MutableList<T>) {
    for (item in source) {
        destination.add(item)
    }

    val list = mutableListOf<T>()
    list.add(destination[0])

    //ERROR: compile
    //source.add(source[0])
}

fun <T> copyData4(source: MutableList<T>, destination: MutableList<in T>) {
    for (item in source) {
        destination.add(item)
    }

    val list = mutableListOf<T>()
    //ERROR: compile
    //list.add(destination[0])

    source.add(source[0])
}

fun <T> copyData5(source: MutableList<out T>, destination: MutableList<in T>) {
    for (item in source) {
        destination.add(item)
    }

    val list = mutableListOf<T>()
    // ERROR: compile
    // list.add(destination[0])
    //source.add(source[0])
}
