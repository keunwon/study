package com.keunwon.algorithm.baekjoon

import kotlin.math.absoluteValue

/**
 * <p>
 * 이름: 용액
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/2467">용액 (골드-5)</a>
 **/
class Problem2467 {
    fun solution(numbers: IntArray): IntArray {
        numbers.sort()

        var l = 0
        var r = numbers.lastIndex
        var min = 2e9.toInt()
        val result = intArrayOf(0, 0)

        while (l < r) {
            val sum = numbers[l] + numbers[r]
            val absSum = sum.absoluteValue

            if (min > absSum) {
                min = absSum
                result[0] = numbers[l]
                result[1] = numbers[r]
            }
            if (sum > 0) --r else ++l
        }
        return result
    }
}

fun main() {
    val n = readln().toInt()
    val numbers = run {
        val arr = readln().split(" ").map { it.toInt() }
        IntArray(n) { arr[it] }
    }
    Problem2467().solution(numbers).also { println(it.joinToString(" ")) }
}
