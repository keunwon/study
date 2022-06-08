package com.ch01

fun main() {
    val persons = listOf(Person("영희"), Person("철수", age = 29))
    val oldest = persons.maxByOrNull { it.age ?: 0 }
    println("나이가 가장 많은 사람: $oldest")
}

data class Person(val name: String, val age: Int? = null)
