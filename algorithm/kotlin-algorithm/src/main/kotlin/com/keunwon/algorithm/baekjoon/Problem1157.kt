package com.keunwon.algorithm.baekjoon

/**
 * <p>
 * 이름: 단어 공부
 * 난이도: 브론즈-1
 * </p>
 *
 * @see <a href="https://www.acmicpc.net/problem/1157">단어 공부 (브론즈-1)</a>
 **/
class Problem1157 {
    fun solution(s: String): String {
        val alphabet = IntArray(26)
        var max = 0
        var result = ""
        var flag = false

        for (c in s.uppercase()) {
            val idx = c - 'A'
            ++alphabet[idx]

            if (max == alphabet[idx]) {
                flag = true
            } else if (max < alphabet[idx]) {
                flag = false
                max = alphabet[idx]
                result = c.toString()
            }
        }
        return if (flag) "?" else result
    }
}

fun main() {
    val str = readln()
    Problem1157().solution(str).also { println(it) }
}
