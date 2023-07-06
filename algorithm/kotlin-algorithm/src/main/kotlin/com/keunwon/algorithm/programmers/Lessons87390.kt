package com.keunwon.algorithm.programmers

/**
 * Title: n^2 배열 자르기
 * Level: 2
 **/
class Lessons87390 {
    fun solution(n: Int, left: Long, right: Long): IntArray {
        val answer = mutableListOf<Int>()

        for (i in left..right) {
            val div = (i / n).toInt() + 1
            val mod = (i % n).toInt() + 1
            answer.add(div.coerceAtLeast(mod))
        }
        return answer.toIntArray()
    }
}
