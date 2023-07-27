package com.keunwon.algorithm.againresolve

import java.util.*

class AProblem1863 {
    fun solution(arr: Array<Pair<Int, Int>>): Int {
        var answer = 0
        var stack = Stack<Int>()

        for ((_, y) in arr) {
            while (stack.isNotEmpty() && y < stack.peek()) {
                stack.pop()
                answer++
            }

            if (stack.isNotEmpty() && stack.peek() == y) continue
            stack.push(y)
        }

        while (stack.isNotEmpty()) {
            if (stack.pop() > 0) answer++
        }
        return answer
    }
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = Array(n) {
        readLine()!!
            .split(" ")
            .map { it.toInt() }
            .let { (a, b) -> a to b }
    }

    AProblem1863().solution(arr).also { println(it) }
}
