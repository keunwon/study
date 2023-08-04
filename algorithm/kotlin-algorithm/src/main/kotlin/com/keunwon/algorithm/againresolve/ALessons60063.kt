package com.keunwon.algorithm.againresolve

class ALessons60063 {
    fun solution(board: Array<IntArray>): Int {
        return 0
    }
}

fun main() {
    val board = arrayOf(
        intArrayOf(0, 0, 0, 1, 1),
        intArrayOf(0, 0, 0, 1, 0),
        intArrayOf(0, 1, 0, 1, 1),
        intArrayOf(1, 1, 0, 0, 1),
        intArrayOf(0, 0, 0, 0, 0),
    )
    ALessons60063().solution(board).also(::println)
}
