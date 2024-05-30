package com.keunwon.algorithm.programmers

class Lesson12939 {
    fun solution(s: String): String {
        return s.split(" ").map { it.toInt() }.let {
            "${it.minOrNull() ?: 0} ${it.maxOrNull() ?: 0}"
        }
    }
}
