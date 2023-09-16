package com.keunwon.algorithm.baekjoon

/**
 * Title: 8진수 2진수
 * Level: 브론즈-2
 **/
class Problem1212 {
    fun solution(n: String): String {
        val sb = StringBuilder()

        for (i in n.indices) {
            val binary = (n[i] - '0').toString(2)

            if (i != 0 && binary.length < 3) {
                val zero = "0".repeat(3 - binary.length)
                sb.append(zero)
            }
            sb.append(binary)
        }
        return sb.toString()
    }
}

fun main() {
    val n = readLine()!!
    Problem1212().solution(n).also(::println)
}
