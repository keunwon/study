package com.keunwon.algorithm.programmers

import kotlin.math.min

/**
 * Title: 행렬 테두리 회전하기
 * Level: 2
 **/
class Lessons77485 {
    fun solution(rows: Int, columns: Int, queries: Array<IntArray>): IntArray {
        val map = Array(rows) { r -> IntArray(columns) { c -> r * columns + c + 1 } }
        val answer = mutableListOf<Int>()

        for (query in queries) {
            val (y1, x1, y2, x2) = query.map { it - 1 }
            val start = map[y1][x1]
            var min = start

            for (i in y1 until y2) {
                map[i][x1] = map[i + 1][x1].also { min = min(min, it) }
            }

            for (i in x1 until x2) {
                map[y2][i] = map[y2][i + 1].also { min = min(min, it) }
            }

            for (i in y2 downTo y1 + 1) {
                map[i][x2] = map[i - 1][x2].also { min = min(min, it) }
            }

            for (i in x2 downTo x1 + 1) {
                map[y1][i] = map[y1][i - 1].also { min = min(min, it) }
            }

            map[y1][x1 + 1] = start
            answer.add(min)
        }
        return answer.toIntArray()
    }
}
