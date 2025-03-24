package com.keunwon.algorithm.baekjoon

import java.util.Stack

/**
 * <p>
 * 이름: 문자열 폭발
 * 난이도: 골드-4
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/9935">문자열 폭발 (골드-4)</a>
 **/
class Problem9935 {
    fun solution(str1: String, str2: String): String {
        val stack = Stack<Char>()
        for (c in str1) {
            stack.push(c)

            if (stack.size >= str2.length) {
                var flag = true
                for (i in str2.indices) {
                    if (stack[stack.lastIndex - i] != str2[str2.lastIndex - i]) {
                        flag = false
                        break
                    }
                }
                if (flag) {
                    repeat(str2.length) { stack.pop() }
                }
            }
        }
        return if (stack.isEmpty()) "FRULA" else stack.joinToString("")
    }
}

fun main() {
    val str1 = readln()
    val str2 = readln()
    Problem9935().solution(str1, str2).also { println(it) }
}
