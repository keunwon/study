package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 탑
 * Level: 골드-5
 **/
class Problem2493 {
    fun solution(arr: IntArray): IntArray {
        val answer = mutableListOf<Int>()
        val stack = Stack<Node>()

        for (i in 1..arr.size) {
            val height = arr[i - 1]

            while (stack.isNotEmpty()) {
                if (stack.peek().height >= height) {
                    answer.add(stack.peek().index)
                    break
                }
                stack.pop()
            }
            if (stack.isEmpty()) answer.add(0)
            stack.push(Node(i, height))
        }
        return answer.toIntArray()
    }

    private data class Node(val index: Int, val height: Int)
}

fun main() {
    val n = readLine()!!.toInt()
    val arr = readLine()!!.split(" ").map { it.toInt() }.toIntArray()

    Problem2493().solution(arr).also { println(it.joinToString(" ")) }
}
