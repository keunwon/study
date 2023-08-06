package com.keunwon.algorithm.baekjoon

/**
 * Title: 부분 문자열
 * Level: 실버-5
 **/
class Problem6550 {
    fun solution(str1: String, str2: String): String {
        var index = 0
        for (i in str2.indices) {
            if (str1[index] == str2[i]) {
                index++
            }
            if (index == str1.length) return "Yes"
        }
        return "No"
    }
}

fun main() {
    while (true) {
        val line = readLine()
        if (line == null || line.isBlank()) break

        val (str1, str2) = line.split(" ")
        Problem6550().solution(str1, str2).also { println(it) }
    }
}
