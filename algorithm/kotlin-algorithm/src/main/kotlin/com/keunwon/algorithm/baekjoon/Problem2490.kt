package com.keunwon.algorithm.baekjoon

/**
 * Title: 윷놀이
 * Level: 브론즈-3
 **/
class Problem2490 {
    fun solution(arr: Array<IntArray>): List<Char> {
        return arr.map {
            when (it.sum()) {
                3 -> 'A'
                2 -> 'B'
                1 -> 'C'
                0 -> 'D'
                4 -> 'E'
                else -> error("")
            }
        }
    }
}

fun main() {
    val arr = Array(3) {
        readLine()!!.split(" ")
            .map { it.toInt() }
            .toIntArray()
    }

    Problem2490().solution(arr).forEach { println(it) }
}
