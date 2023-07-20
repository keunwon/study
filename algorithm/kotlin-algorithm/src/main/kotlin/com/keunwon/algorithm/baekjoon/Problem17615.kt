package com.keunwon.algorithm.baekjoon

/**
 * Title: 볼 모으기
 * Level: 실버-1
 **/
class Problem17615 {
    fun solution(arr: CharArray): Int {
        val redTotal = arr.count { it == 'R' }
        val blueTotal = arr.count { it == 'B' }
        val r1 = generateSequence(0, Int::inc)
            .takeWhile { it in arr.indices && arr[it] == 'R' }
            .count()
        val r2 = generateSequence(arr.lastIndex, Int::dec)
            .takeWhile { it in arr.indices && arr[it] == 'R' }
            .count()
        val b1 = generateSequence(0, Int::inc)
            .takeWhile { it in arr.indices && arr[it] == 'B' }
            .count()
        val b2 = generateSequence(arr.lastIndex, Int::dec)
            .takeWhile { it in arr.indices && arr[it] == 'B' }
            .count()
        return redTotal
            .coerceAtMost(blueTotal)
            .coerceAtMost(redTotal - r1)
            .coerceAtMost(redTotal - r2)
            .coerceAtMost(blueTotal - b1)
            .coerceAtMost(blueTotal - b2)
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.toCharArray()
    Problem17615().solution(arr).also { println(it) }
}
