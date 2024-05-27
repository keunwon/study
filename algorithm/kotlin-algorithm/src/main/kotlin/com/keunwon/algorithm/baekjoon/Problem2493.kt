package com.keunwon.algorithm.baekjoon

import java.util.*

class Problem2493 {
    fun solution(arr: IntArray): IntArray {
        val stack = Stack<Node>()
        val answer = mutableListOf<Int>()

        for ((i, height) in arr.withIndex()) {
            while (stack.isNotEmpty()) {
                if (stack.peek().height > height) {
                    answer.add(stack.peek().index)
                    break
                }
                stack.pop()
            }

            if (stack.isEmpty()) answer.add(0)
            stack.push(Node(i + 1, height))
        }
        return answer.toIntArray()
    }

    private class Node(val index: Int, val height: Int)
}

fun main() {
    val n = readln().toInt()
    val arr = run {
        val st = StringTokenizer(readln())
        IntArray(n) { st.nextToken().toInt() }
    }

    Problem2493().solution(arr).also { println(it.joinToString(" ")) }
}
