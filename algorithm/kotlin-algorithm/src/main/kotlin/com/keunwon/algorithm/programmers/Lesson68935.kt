package com.keunwon.algorithm.programmers

class Lesson68935 {
    fun solution(n: Int): Int {
        return n.toString(3)
            .reversed()
            .toInt(3)
    }
}

fun main() {
    Lesson68935().solution(45).also { println(it) } // 7
    Lesson68935().solution(125).also { println(it) } // 229
}
