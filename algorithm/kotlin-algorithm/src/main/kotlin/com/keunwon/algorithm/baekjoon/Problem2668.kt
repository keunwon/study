package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 숫자고르기
 * 난이도: 골드-5
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/2668">숫자고르기 (골드-5)</a>
 **/
// todo
class Problem2668 {
    fun solution(numbers: IntArray): IntArray {
        return intArrayOf()
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val numbers = IntArray(n) { br.readLine().toInt() }

    Problem2668().solution(numbers).also { println(it.joinToString(", ")) }
}
