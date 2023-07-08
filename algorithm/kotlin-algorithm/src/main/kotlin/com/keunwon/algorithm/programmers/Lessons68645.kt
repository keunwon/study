package com.keunwon.algorithm.programmers

/**
 * Title: 삼각 달팽이
 * Level: 2
 **/
class Lessons68645 {
    fun solution(n: Int): IntArray {
        val answer = Array(n) { IntArray(n) }
        var num = 1
        var (x, y) = 0 to -1

        for (i in 0 until n) {
            for (j in i until n) {
                when {
                    i % 3 == 0 -> y++
                    i % 3 == 1 -> x++
                    i % 3 == 2 -> {
                        y--
                        x--
                    }
                }
                answer[y][x] = num++
            }
        }
        return answer.flatMap { list -> list.filterNot { it == 0 } }.toIntArray()
    }
}
