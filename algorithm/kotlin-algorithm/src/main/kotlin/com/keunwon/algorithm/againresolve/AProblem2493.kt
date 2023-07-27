package com.keunwon.algorithm.againresolve

import java.util.*

class AProblem2493 {
    fun solution(arr: IntArray): IntArray {
        val stack = Stack<Node>()
        val answer = mutableListOf<Int>()

        for ((i, h) in arr.withIndex()) {
            while (stack.isNotEmpty()) {
                if (stack.peek().h >= h) {
                    answer.add(stack.peek().index)
                    break
                }
                stack.pop()
            }

            if (stack.isEmpty()) answer.add(0)
            stack.push(Node(i + 1, h))
        }
        return answer.toIntArray()
    }

    private data class Node(val index: Int, val h: Int)
}

fun main() {
    val n = readLine()!!.toInt()
    val st = StringTokenizer(readLine())
    val arr = IntArray(n) { st.nextToken().toInt() }

    AProblem2493().solution(arr).also { println(it.joinToString(" ")) }
}
