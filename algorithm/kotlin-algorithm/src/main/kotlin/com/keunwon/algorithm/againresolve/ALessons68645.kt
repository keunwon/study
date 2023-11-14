package com.keunwon.algorithm.againresolve

class ALessons68645 {
    fun solution(n: Int): IntArray {
        val answer = Array(n) { IntArray(n) }
        var num = 1
        var (r, c) = -1 to 0

        for (i in 0 until n) {
            for (j in i until n) {
                when (i % 3) {
                    0 -> ++r
                    1 -> ++c
                    2 -> {
                        --r
                        --c
                    }
                }
                answer[r][c] = num++
            }
        }
        return answer.flatMap { list -> list.filterNot { it == 0 } }.toIntArray()
    }
}

fun main() {
    arrayOf(4, 5, 6).forEach {
        ALessons68645().solution(it).also { println(it.contentToString()) }
    }
}
