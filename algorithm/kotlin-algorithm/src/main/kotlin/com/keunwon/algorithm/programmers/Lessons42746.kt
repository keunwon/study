package com.keunwon.algorithm.programmers

/**
 * Title: 가장 큰 수
 * Level: 2
 **/
class Lessons42746 {
    fun solution(numbers: IntArray): String {
        return numbers.map { it.toString() }
            .sortedWith { str1, str2 -> -"$str1$str2".compareTo("$str2$str1") }
            .joinToString("")
            .also { println(it) }
            .let { if (it.first() == '0') "0" else it }
    }
}
