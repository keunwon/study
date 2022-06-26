package com.ch06

fun printIndices(arr: Array<String>) {
    for (i in arr.indices) {
        println("Argument $i is: ${arr[i]}")
    }
}

fun generateAlphabet(): Array<String> {
    return Array(26) { i -> ('a' + i).toString() }
}

fun printFormat(arr: List<String>) {
    println("%s/%s/%s".format(*arr.toTypedArray()))
}

fun printIntArray() {
    val arr = IntArray(5) { i -> (i + 1) * (i + 1) }
    println(arr.joinToString())
}
