package com.keunwon.algorithm.programmers

class Lesson68645 {
    fun solution(n: Int): IntArray {
        val map = Array(n) { IntArray(n) }
        var (r, c) = -1 to 0
        var num = 1

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
                map[r][c] = num++
            }
        }
        return map.flatMap { arr -> arr.filterNot { it == 0 } }.toIntArray()
    }
}
