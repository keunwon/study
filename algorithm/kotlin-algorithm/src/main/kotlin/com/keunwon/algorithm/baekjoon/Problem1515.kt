package com.keunwon.algorithm.baekjoon

class Problem1515 {
    fun solution(text: String): Int {
        var answer = 1
        var sIndex = 0

        while (sIndex < text.length) {
            val str = "$answer"

            for (c in str) {
                if (c == text[sIndex]) ++sIndex

                if (sIndex == text.length) break
            }
            ++answer
        }
        return answer - 1
    }
}

fun main() {
    val n = readln()
    println(Problem1515().solution(n))
}
