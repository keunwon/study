package com.keunwon.algorithm.againresolve

import java.util.*

class AProblem9935 {
    fun solution(str1: String, str2: String): String {
        val stack = Stack<Char>()

        for (c in str1) {
            stack.push(c)
            if (str2.length > stack.size) continue

            var flag = false
            for (i in str2.indices) {
                if (stack[stack.lastIndex - i] != str2[str2.lastIndex - i]) {
                    flag = true
                    break
                }
            }
            if (!flag) repeat(str2.length) { stack.pop() }
        }
        return if (stack.isEmpty()) "FRULA" else stack.joinToString("")
    }
}

fun main() {
    val br = System.`in`.bufferedReader()
    val bw = System.out.bufferedWriter()


    val str1 = br.readLine()
    val str2 = br.readLine()

    AProblem9935().solution(str1, str2).also { bw.write(it) }

    bw.flush()
    bw.close()
    br.close()
}
