package com.ch06.kttype

class Person(val name: String, val age: Int?) {
    fun findOlderThan(other: Person) : Boolean? {
        if (age == null || other.age == null) {
            return null
        }
        return age > other.age
    }
}

fun main() {
    val person = Person("홍길동", 20)
    println(person.findOlderThan(Person("세종대왕", 15)))
}
