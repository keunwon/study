package com.ch02

class Person(
    val name: String,
    var isMarried: Boolean
)

fun main() {
    val person = Person("Bob", true)
    println(person.name)
    println(person.isMarried)
}