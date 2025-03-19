package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * <p>
 * 이름: 에디터
 * 난이도: 실버-2
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1406">에디터 (실버-2)</a>
 **/
class Problem1406 {
    fun solution(init: String, commands: Array<String>): String {
        val left = Stack<Char>().apply { init.forEach { push(it) } }
        val right = Stack<Char>()

        commands.forEach { command ->
            val arr = command.split(" ")
            when (arr[0][0]) {
                'L' -> if (left.isNotEmpty()) right.push(left.pop())
                'D' -> if (right.isNotEmpty()) left.push(right.pop())
                'B' -> if (left.isNotEmpty()) left.pop()
                'P' -> left.push(arr[1][0])
            }
        }

        return buildString(left.size + right.size) {
            while (left.isNotEmpty()) right.push(left.pop())
            while (right.isNotEmpty()) append(right.pop())
        }
    }
}

fun main() {
    val br = System.`in`.bufferedReader()

    val init = br.readLine()
    val n = br.readLine().toInt()
    val commands = Array(n) { br.readLine() }
    Problem1406().solution(init, commands).also { println(it) }
}
