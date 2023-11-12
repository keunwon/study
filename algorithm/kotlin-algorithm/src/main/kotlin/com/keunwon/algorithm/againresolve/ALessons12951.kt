package com.keunwon.algorithm.againresolve

class ALessons12951 {
    fun solution(s: String): String {
        return s.lowercase().split(" ").joinToString(" ") {
            if (it.isEmpty()) "" else it[0].uppercase() + it.substring(1)
        }
    }
}

fun main() {
    ALessons12951().solution("3people unFollowed me").also(::println)
    ALessons12951().solution("people  me").also(::println)
}
