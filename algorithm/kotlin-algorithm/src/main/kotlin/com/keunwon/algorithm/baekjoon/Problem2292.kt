package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 벌집
 * 난이도: 브론즈-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/2292">벌집 (브론즈-2)</a>
 **/
class Problem2292 {
    fun solution(n: Int): Int {
        var num = 1
        var result = 1

        while (num < n) {
            num += result * 6
            ++result
        }
        return result
    }
}

fun main() {
    val n = readln().toInt()
    Problem2292().solution(n).also { println(it) }
}
