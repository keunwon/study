package com.ch03

fun main() {
    val array = arrayOf("Apple", "Banana", "Cat")
    val list = listOf("args: ", *array)
    println(list)
}