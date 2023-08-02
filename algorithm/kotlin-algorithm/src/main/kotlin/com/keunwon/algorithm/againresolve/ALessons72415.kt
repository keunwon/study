package com.keunwon.algorithm.againresolve

class ALessons72415 {
    fun solution(board: Array<IntArray>, r: Int, c: Int): Int {
        return 0
    }
}

fun main() {
    val board = arrayOf(
        intArrayOf(1, 0, 0, 3),
        intArrayOf(2, 0, 0, 0),
        intArrayOf(0, 0, 0, 2),
        intArrayOf(3, 0, 1, 0),
    )
    val r = 1
    val c = 0

    ALessons72415().solution(board, r, c).also { println(it) }
}
