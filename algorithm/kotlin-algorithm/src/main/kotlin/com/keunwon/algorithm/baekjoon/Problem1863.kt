package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 스카이라인 쉬운거
 * Level: 골드-4
 **/
class Problem1863 {
    fun solution(arr: Array<Pair<Int, Int>>): Int {
        var answer = 0
        val stack = Stack<Int>()

        for ((_, h) in arr) {
            while (stack.isNotEmpty() && stack.peek() > h) {
                stack.pop()
                answer++
            }

            if (stack.isNotEmpty() && stack.peek() == h) continue
            stack.push(h)
        }

        while (stack.isNotEmpty()) {
            if (stack.peek() > 0) answer++
            stack.pop()
        }
        return answer
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = Array(n) { readLine()!!.split(" ").map { it.toInt() }.let { it[0] to it[1] } }

    Problem1863().solution(arr).also { println(it) }
}
