package com.keunwon.algorithm.againresolve

class ALessons12939 {
    fun solution(s: String): String {
        return s.split(" ")
            .map { it.toInt() }
            .sorted()
            .let { "${it.first()} ${it.last()}" }
    }
}

fun main() {
    ALessons12939().solution("1 2 3 4").also(::println)
}
