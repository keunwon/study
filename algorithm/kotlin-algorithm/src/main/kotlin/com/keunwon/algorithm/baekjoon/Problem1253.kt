package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 좋다
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1253">좋다 (골드-4)</a>
 **/
class Problem1253 {
    fun solution(numbers: IntArray): Int {
        numbers.sort()

        var result = 0
        for ((i, num) in numbers.withIndex()) {
            var left = 0
            var right = numbers.lastIndex
            var flag = false

            while (left < right) {
                if (left == i) ++left
                if (right == i) --right

                if (left >= right) break

                val sum = numbers[left] + numbers[right]
                if (num == sum) {
                    flag = true
                    break
                } else if (num > sum) {
                    ++left
                } else {
                    --right
                }
            }

            if (flag) ++result
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

    Problem1253().solution(numbers).also { println(it) }
}
