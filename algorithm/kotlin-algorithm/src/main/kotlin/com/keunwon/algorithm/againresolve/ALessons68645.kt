package com.keunwon.algorithm.againresolve

class ALessons68645 {
    fun solution(n: Int): IntArray {
        val map = Array(n) { IntArray(n) }
        var id = 1
        var r = -1
        var c = 0

        for (i in 0 until n) {
            for (j in i until n) {
                when (i % 3) {
                    0 -> r++
                    1 -> c++
                    2 -> {
                        r--
                        c--
                    }
                }
                map[r][c] = id++
            }
        }
        return map.flatMap { arr -> arr.filter { it != 0 } }.toIntArray()
    }
}

fun main() {
    intArrayOf(4, 5, 6).forEach { num ->
        ALessons68645().solution(num).also { println(it.joinToString(", ")) }
    }
}
