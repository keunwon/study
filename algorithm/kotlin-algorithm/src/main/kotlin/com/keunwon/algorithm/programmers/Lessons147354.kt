package com.keunwon.algorithm.programmers

/**
 * Title: 테이블 해시 함수
 * Level: 2
 **/
class Lessons147354 {
    fun solution(data: Array<IntArray>, col: Int, row_begin: Int, row_end: Int): Int {
        data.sortWith(compareBy({ it[col - 1] }, { -it[0] }))

        var answer = 0
        for (i in row_begin - 1 until row_end) {
            val sum = data[i].sumOf { it % (i + 1) }
            answer = answer xor sum
        }
        return answer
    }
}
