package com.ch07

class Email {}

fun loadEmails(person: Person): List<Email> {
    println("${person.name}의 이메일을 가져옴")
    return emptyList()
}

class Person(val name: String) {
    val emails by lazy { loadEmails(this) }
}
