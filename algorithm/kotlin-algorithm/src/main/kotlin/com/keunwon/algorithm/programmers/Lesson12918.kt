package com.keunwon.algorithm.programmers

class Lesson12918 {
    fun solution(s: String): Boolean {
        return s.length in intArrayOf(4, 6) && s.all { it.isDigit() }
    }
}
