package com.keunwon.algorithm.programmers

/**
 * Title: 숫자 변환하기
 * Level: 2
 **/
class Lessons154538 {
    fun solution(x: Int, y: Int, n: Int): Int {
        val numbers = mutableSetOf<Int>().apply { add(x) }
        var count = 0

        while (numbers.isNotEmpty()) {
            if (numbers.contains(y)) return count

            val nextNumbers = mutableSetOf<Int>().also { set ->
                numbers.forEach { number ->
                    val tmp = intArrayOf(number + n, number * 2, number * 3).filter { it <= y }
                    tmp.forEach { set.add(it) }
                }
            }

            numbers.clear()
            numbers.addAll(nextNumbers)
            count++
        }
        return -1
    }
}
