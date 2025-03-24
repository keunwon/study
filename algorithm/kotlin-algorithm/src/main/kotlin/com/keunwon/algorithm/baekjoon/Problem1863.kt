package com.keunwon.algorithm.baekjoon

import java.util.Stack

/**
 * <p>
 * 이름: 스카이라인 쉬운거
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1863">스카이라인 쉬운거 (골드-4)</a>
 **/
class Problem1863 {
    fun solution(positions: Array<Pair<Int, Int>>): Int {
        val stack = Stack<Int>()
        var result = 0

        for ((_, y) in positions) {
            while (stack.isNotEmpty() && stack.peek() > y) {
                ++result
                stack.pop()
            }

            if (stack.isNotEmpty() && stack.peek() == y) continue
            stack.push(y)
        }

        while (stack.isNotEmpty()) {
            if (stack.pop() > 0) ++result
        }
        return result
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val n = br.readLine().toInt()
    val positions = Array(n) {
        val arr = br.readLine().split(" ").map { it.toInt() }
        arr[0] to arr[1]
    }

    Problem1863().solution(positions).also { println(it) }
}
