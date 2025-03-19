package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 카드2
 * 난이도: 실버-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/2164">카드2 (실버-4)</a>
 **/
class Problem2164 {
    fun solution(n: Int): Int {
        val deque = ArrayDeque((1..n).toList())
        while (deque.size > 1) {
            deque.removeFirst()
            deque.addLast(deque.removeFirst())
        }
        return deque.first()
    }
}

fun main() {
    val n = readln().toInt()
    Problem2164().solution(n).also { println(it) }
}
