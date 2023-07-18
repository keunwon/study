package com.keunwon.algorithm.baekjoon

import java.util.*

/**
 * Title: 문자열 폭발
 * Level: 골드-4
 **/
class Problem9935 {
    fun solution(line: String, target: String): String {
        val stack = Stack<Char>()

        for (c in line) {
            stack.push(c)
            if (target.length > stack.size) continue

            var flag = false
            for (i in target.indices) {
                if (stack[stack.lastIndex - i] != target[target.lastIndex - i]) {
                    flag = true
                    break
                }
            }
            if (!flag) repeat(target.length) { stack.pop() }
        }
        return if (stack.isEmpty()) "FRULA" else stack.joinToString("")
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()

    val line = br.readLine()
    val removeStr = br.readLine()

    Problem9935().solution(line, removeStr).also { bw.write(it) }

    bw.flush()
    bw.close()
}
