package com.ch04

class User(val name: String) {
    var address = "unspecified"
        set(value) {
            println("""
                Address was changed for $name:
                "$field" -> "$value"
            """.trimIndent())
            field = value
        }
}

class LengthCounter {
    var counter: Int = 0
        private set

    fun addWord(word: String) {
        counter += word.length
    }
}

fun main() {
    val user = User("홍길동")
    user.address = "서울특별시"
    println(user.address)
}