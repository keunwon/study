package com.keunwon.algorithm.programmers

/**
 * <p>
 * 이름: 봉인된 주문
 * 난이도: Level-3
 * </p>
 *
 * @see <a href="https://school.programmers.co.kr/learn/courses/30/lessons/389481"> (Level-3)</a>
 **/
class Lesson389481 {
    fun solution(n: Long, bans: Array<String>): String {
        val bases = bans.map { convertStringToBase26(it) }.sorted()
        var target = n

        for (base in bases) {
            if (target >= base) ++target else break
        }
        return convertToBase26(target)
    }

    private fun convertToBase26(num: Long): String {
        var number = num
        val result = StringBuilder()

        while (number-- > 0) {
            val c = 'a' + (number % 26).toInt()
            result.append(c)
            number /= 26
        }
        return result.reverse().toString()
    }

    private fun convertStringToBase26(input: String): Long {
        var result = 0L
        for (c in input) {
            val value = c - 'a' + 1
            result = result * 26 + value
        }
        return result
    }
}
